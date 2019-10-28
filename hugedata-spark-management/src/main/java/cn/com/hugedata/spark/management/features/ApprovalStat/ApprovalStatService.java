package cn.com.hugedata.spark.management.features.ApprovalStat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.OrderItem;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;
import cn.com.hugedata.spark.commons.storage.mapper.UcUserInfoMapper;
import cn.com.hugedata.spark.commons.storage.queryentity.ApprovalStatItem;
import cn.com.hugedata.spark.commons.storage.querymapper.ApServiceInfoQueryMapper;
import cn.com.hugedata.spark.commons.storage.querymapper.ApprovalStatQueryMapper;
import cn.com.hugedata.spark.commons.utils.MyBatisUtils;
import cn.com.hugedata.spark.commons.web.login.LoginUtils;

@Service
public class ApprovalStatService {

    @Autowired
    private ApprovalStatQueryMapper approvalStatQueryMapper;
    
    @Autowired
    private ApServiceInfoQueryMapper apServiceInfoQueryMapper;
    
    @Autowired
    private UcUserInfoMapper ucUserInfoMapper;
    
//    @Autowired
//    private UcUserDeptMapper ucUserDeptMapper;
    
    public List<ApprovalStatItem> statByUser(Date beginTime, Date endTime) {
        List<Integer> deptIdList = null;
        List<Integer> userIdList = null;
        
        UcUserInfo login = LoginUtils.getCurrentLogin();
        userIdList = Arrays.asList(login.getUserId());
        
//        if (UcUserInfo.USER_TYPE_PARK.equals(login.getUserType())) {
//            List<UcUserDept> deptList = ucUserDeptMapper.selectByMap(MyBatisUtils.params(
//                    UcUserDept.Fields.USER_ID, login.getUserId()
//            ));
//            deptIdList = new ArrayList<>();
//            for (UcUserDept ud : deptList) {
//                deptIdList.add(ud.getDeptId());
//            }
//        }
        
        return approvalStatQueryMapper.summaryByUser(MyBatisUtils.params(
                "beginTime", beginTime,
                "endTime", endTime,
                "deptIdList", deptIdList,
                "userIdList", userIdList
        ));
    }

    public ResponseEntity<byte[]> exportDetails(Date beginTime, Date endTime, Integer userId) throws ServiceException {
        UcUserInfo user = ucUserInfoMapper.selectById(userId);
        
        // 查询办件信息
        List<ApServiceInfo> list = apServiceInfoQueryMapper.selectForParkUser(MyBatisUtils.params(
                ApServiceInfo.Query.CREATE_TIME__BEGIN, beginTime,
                ApServiceInfo.Query.CREATE_TIME__END, endTime,
                "operateUserId", userId,
                new OrderItem(ApServiceInfo.Fields.CREATE_TIME, OrderItem.ASC)
        ));
        
        // 生成Excel
        try (ApprovalStatExporter exporter = new ApprovalStatExporter(7)) {
            exporter.columnWidths(Arrays.asList(300, 300, 100, 120, 180, 140, 120));
            exporter.addTitle("岳麓科技产业园企业（党群）服务中心业务受理登记表", 
                    "（" + new SimpleDateFormat("yyyy").format(beginTime) + "年）");
            exporter.addHeaders(Arrays.asList("申报单位", "申报业务", "事项", "受理时间", "联系人", "联系电话", "备注"));
            
            List<String> aligns = Arrays.asList("left", "left", "center", "center", "center", "center", "center");
            
            for (ApServiceInfo  s : list) {
                ArrayList<Object> values = new ArrayList<>();
                values.add(s.getBusinessDeptPerson());
                values.add(s.getGuidename());
                values.add(1);
                values.add(new SimpleDateFormat("yyyy-MM-dd").format(s.getCreateTime()));
                values.add(s.getBusinessDeptPerson());
                values.add(s.getCellphone());
                values.add(statusToText(s.getStatus()));
                exporter.addRow(values, aligns);
            }
            
            // Http返回
            String fileNameDatePart = new SimpleDateFormat("yyyyMMdd").format(beginTime)
                    + "-" + new SimpleDateFormat("yyyyMMdd").format(endTime);
            String fileNameEn = userId + "-" + fileNameDatePart + ".xlsx";
            String fileNameCn = user.getName() + "-" + fileNameDatePart + ".xlsx";
            return exporter.export(fileNameEn, fileNameCn);
            
        } catch (IOException e) {
            throw new ServiceException("FailedExportExcel", "导出失败", e);
        }
    }
    
    private String statusToText(String status) {
        switch (status) {
            case "110": return "待预审";
            case "120": return "已受理";
            case "130": return "已受理";
            case "140": return "已受理";
            case "150": return "已受理";
            case "160": return "已受理";
            case "210": return "已受理";
            case "220": return "已受理";
            case "211": return "已办结";
            case "221": return "废弃件";
            case "231": return "废弃件";
            default: return "";
        }
    }
}
