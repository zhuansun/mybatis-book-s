/**
 *    Copyright 2009-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.scripting.xmltags;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

/**
 * 用于描述mapper.xml中的sql资源信息，这些sql通常包含动态sql配置或者${}参数占位符，需要在Mapper调用时才能确定具体的SQL语句
 * @author Clinton Begin
 */
public class DynamicSqlSource implements SqlSource {

  private final Configuration configuration;
  private final SqlNode rootSqlNode;

  public DynamicSqlSource(Configuration configuration, SqlNode rootSqlNode) {
    this.configuration = configuration;
    this.rootSqlNode = rootSqlNode;
  }

  @Override
  public BoundSql getBoundSql(Object parameterObject) {
    //通过参数对象创建动态SQL上下文对象
    DynamicContext context = new DynamicContext(configuration, parameterObject);
    //rootSqlNode其实是MixSqlNode，调用apply方法，其实是循环调用下面的所有的NOde
    rootSqlNode.apply(context);
    SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
    Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
    //调用context.getSql()获取动态sql解析的结果
    //对SQL内容进一步处理，生成StaticSqlSource对象
    SqlSource sqlSource = sqlSourceParser.parse(context.getSql(), parameterType, context.getBindings());
    //调用StaticSqlSource对象，获取BoundSql对象
    BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
    //将<bind>标签绑定的参数添加到BoundSql对象中
    context.getBindings().forEach(boundSql::setAdditionalParameter);
    return boundSql;
  }

}
