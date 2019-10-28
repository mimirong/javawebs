package cn.com.hugedata.spark.commons.storage.interceptors;

import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

/**
 * MyBatis SQL语句分页结果集拦截器.
 */
@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
public class ResultSetHandlerInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        ResultSetHandler resultSet = (ResultSetHandler) invocation.getTarget();
        RowBounds rowBounds = (RowBounds) InterceptorReflectUtils.getFieldValue(resultSet, "rowBounds");
        if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT) {
            InterceptorReflectUtils.setFieldValue(resultSet, "rowBounds", new RowBounds());
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object arg0) {
        return Plugin.wrap(arg0, this);
    }

    @Override
    public void setProperties(Properties arg0) {
    }

}
