package com.blog4java.plugin.zspc;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * @author : zhuansun
 * @date : 2021-02-23 16:48
 **/
@Intercepts( {
    //指定拦截Executor的query方法，因为query方法有很多个，我们要通过args执行拦截入参是这些的query方法
    //<E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
        Object.class, RowBounds.class, ResultHandler.class})
})
public class ZspcSqlInterceptor implements Interceptor {

    /**
     * 这个参数可以通过<plugin>标签中的<properties>设值
     */
    private String name;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        System.out.println("111111"+name);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.name = (String) properties.get("name");
    }
}
