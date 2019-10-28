package cn.com.hugedata.spark.commons.storage.interceptors;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

/**
 * MyBatis SQL语句分页拦截器.
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class StatementHandlerInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler statement = (RoutingStatementHandler) invocation.getTarget();
        Object handlerObject = InterceptorReflectUtils.getFieldValue(statement, "delegate");
        if (!(handlerObject instanceof PreparedStatementHandler)) {
            return invocation.proceed();
        }
        PreparedStatementHandler handler = (PreparedStatementHandler) handlerObject;
        RowBounds rowBounds = (RowBounds) InterceptorReflectUtils.getFieldValue(handler, "rowBounds");
        if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT) {
            BoundSql boundSql = statement.getBoundSql();
            String sql = buildForMysql(boundSql.getSql(), rowBounds.getOffset(), rowBounds.getLimit());
            InterceptorReflectUtils.setFieldValue(boundSql, "sql", sql);
        }
        return invocation.proceed();
    }

    /**
     * 生成MySQL分页查询.
     * 
     * @param sql
     *            需要分页的SQL语句
     * @param start
     *            起始记录
     * @param size
     *            记录总数
     * @return 构造后的分页语句
     */
    private String buildForMysql(String sql, int start, int size) {
        return String.format("SELECT * FROM (%s) __a LIMIT %d OFFSET %d", sql, size, start);
    }

    /**
     * 生成Oracle分页查询.
     * 
     * @param sql
     *            需要分页的SQL语句
     * @param start
     *            起始记录
     * @param size
     *            记录总数
     * @return 构造后的分页语句
     */
    @SuppressWarnings("unused")
    private String buildForOracle(String sql, int start, int size) {
        if (start == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT x__.* FROM (");
            sb.append(sql);
            sb.append(") x__ WHERE ROWNUM < ").append(start + size);
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT y__.* FROM (");
            sb.append("SELECT x__.*, ROWNUM rn__ FROM (");
            sb.append(sql);
            sb.append(") x__ WHERE ROWNUM < ").append(start + size);
            sb.append(") y__ WHERE rn__ >= ").append(start);
            return sb.toString();
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties arg0) {
    }
}
