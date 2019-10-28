package cn.com.hugedata.spark.management.services.approval;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import cn.com.hugedata.spark.commons.exception.ServiceException;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceAttachment;
import cn.com.hugedata.spark.commons.storage.entity.ApServiceInfo;
import cn.com.hugedata.spark.commons.storage.entity.UcUserInfo;

public class AcceptNoticePdfGenerator {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AcceptNoticePdfGenerator.class);
    
    protected static Font defaultFont;
    protected static Font titleFont;
    protected static Font attachmentFont;
    
    private ApServiceInfo serviceInfo;
    private List<ApServiceAttachment> attList;
    private UcUserInfo creator;
    
    /**
     * 生成PDF文件.
     */
    public byte[] generate(ApServiceInfo service, List<ApServiceAttachment> attList, UcUserInfo creator) throws ServiceException {
        try {
            this.serviceInfo = service;
            this.attList = attList;
            this.creator = creator;
            
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            
            prepareFonts();
            
            Document document = new Document();
            PdfWriter.getInstance(document, buffer);
            document.open();
            createAll(document);
            document.close();

            return buffer.toByteArray();
            
        } catch (DocumentException e) {
            LOGGER.error("Failed to generate pdf file.", e);
            throw new ServiceException("FailedGeneratePdf", "生成PDF文件失败.");
            
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Failed to generate pdf file.", e);
            throw new ServiceException("FailedGeneratePdf", "生成PDF文件失败.");
            
        } catch (IOException e) {
            LOGGER.error("Failed to generate pdf file.", e);
            throw new ServiceException("FailedGeneratePdf", "生成PDF文件失败.");
        }
    }
    
    /**
     * 创建PDF文档内容.
     * @param document           PDF文档
     * @throws DocumentException 创建失败
     */
    protected void createAll(Document document) throws DocumentException {
        // title
        Paragraph pTitle = new Paragraph("长沙岳麓科技产业园企业服务中心\n行政许可申请受理通知书", titleFont);
        pTitle.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(pTitle);
        
        // p1
        String creatorText = serviceInfo.getBusinessDeptPerson();
        if (creator != null) {
            creatorText = creator.getCompanyName();
        }
                
        Paragraph p1 = new Paragraph(creatorText + "：", defaultFont);
        document.add(p1);

        // p2
        Paragraph p2 = new Paragraph("", defaultFont);
        p2.add("您企业于");
        p2.add(createUnderlined(new SimpleDateFormat("yyyy").format(serviceInfo.getCreateTime())));
        p2.add("年");
        p2.add(createUnderlined(new SimpleDateFormat("M").format(serviceInfo.getCreateTime())));
        p2.add("月");
        p2.add(createUnderlined(new SimpleDateFormat("d").format(serviceInfo.getCreateTime())));
        p2.add("日向我中心提出");
        p2.add(createUnderlined(serviceInfo.getGuidename()));
        p2.add("的行政许可申请，根据《中华人民共和国行政许可法》第三十二条第一款第（五）项之规定，我中心决定予以受理。");
        p2.setFirstLineIndent(28);
        document.add(p2);

        // p3
        Paragraph p3 = new Paragraph("特此通知。", defaultFont);
        p3.setFirstLineIndent(28);
        document.add(p3);
        
        // p4
        Paragraph p4 = new Paragraph("", defaultFont);
        p4.add("联系人（经办人）：");
        p4.add(createUnderlined(serviceInfo.getAcceptUserName()));
        p4.add("    联系电话：");
        p4.add(createUnderlined(serviceInfo.getAcceptCellphone()));
        p4.setFirstLineIndent(28);
        document.add(p4);
        
        // p5
        Paragraph p5 = new Paragraph("", defaultFont);
        p5.add(createUnderlined(new SimpleDateFormat("yyyy").format(serviceInfo.getAcceptTime())));
        p5.add("年");
        p5.add(createUnderlined(new SimpleDateFormat("M").format(serviceInfo.getAcceptTime())));
        p5.add("月");
        p5.add(createUnderlined(new SimpleDateFormat("d").format(serviceInfo.getAcceptTime())));
        p5.add("日");
        p5.setFirstLineIndent(28);
        p5.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(p5);
        
        // p6
        Paragraph p6 = new Paragraph(" ", defaultFont);
        document.add(p6);
        
        // 附件
        Paragraph px1 = new Paragraph("受理材料清单：", attachmentFont);
        document.add(px1);
        
        for (int i = 0; i < attList.size(); i++) {
            ApServiceAttachment att = attList.get(i);
            Paragraph p = new Paragraph((i + 1) + "、" + att.getFileName(), attachmentFont);
            document.add(p);
        }
    }
    
    private Chunk createUnderlined(String text) {
        Chunk c = new Chunk(" " + text + " ", defaultFont);
        c.setUnderline(0.1f, -2f);
        return c;
    }
    
    /**
     * 准备字体文件.
     */
    private void prepareFonts() throws DocumentException, IOException {
        defaultFont = new Font(BaseFont.createFont("simfang.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
        defaultFont.setSize(14);

        titleFont = new Font(BaseFont.createFont("simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
        titleFont.setSize(16);
        
        attachmentFont = new Font(BaseFont.createFont("simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED));
        attachmentFont.setSize(10.5f);
    }
    
//    public static void main(String[] args) throws Exception {
//        ApServiceInfo s = new ApServiceInfo();
//        s.setBusinessDeptPerson("王宁");
//        s.setCreateTime(new Date());
//        s.setGuidename("就是反馈据说老大");
//        s.setAcceptUserName("叶俊");
//        s.setAcceptTime(new Date());
//        s.setAcceptCellphone("18260096261");
//        
//        List<ApServiceAttachment> attList = new ArrayList<>();
//        
//        ApServiceAttachment att1 = new ApServiceAttachment();
//        att1.setFileName("代笔.docx");
//        attList.add(att1);
//        
//        ApServiceAttachment att2 = new ApServiceAttachment();
//        att2.setFileName("上课了的附近类似的.docx");
//        attList.add(att2);
//        
//        ApServiceAttachment att3 = new ApServiceAttachment();
//        att3.setFileName("我减肥啦是肯定.sdjf");
//        attList.add(att3);
//        
//        AcceptNoticePdfGenerator g = new AcceptNoticePdfGenerator();
//        byte[] data = g.generate(s, attList);
//        
//        FileUtils.writeByteArrayToFile(new File("C:\\Users\\xapphiron\\Desktop\\accno.pdf"), data);
//    }
}
