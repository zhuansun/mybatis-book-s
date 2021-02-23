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
package org.apache.ibatis.builder;

import java.util.List;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

/**
 * 用于描述ProviderSqlSource，DynamicSqlSource，RawSqlSource解析之后的得到的静态sql资源。
 * @author Clinton Begin
 */
public class StaticSqlSource implements SqlSource {
  /**
   * mapper解析后的sql内容
   */
  private final String sql;
  /**
   * 参数映射信息
   * 这里的参数映射信息包括jdbcType,javaType,以及TypeHandler信息；
   * - 是在MappedStatement中的getBoundSql中调用sqlSource的getBoundSql然后调用sqlSourceParser的parse
   *    进行解析的时候，处理占位符的时候，会将占位符中的这些信息（#{userId,javaType=long,jdbcType=NUMBERIC,
   *    typeHandler=MyTypeHander} ）也进行解析并保存在boundSql中的parameterMappings中；
   * - 在执行sql的时候，会调用parameterHander的setParameters方法，会从boundSql中取出parameterMappings，然后进行设值
   */
  private final List<ParameterMapping> parameterMappings;
  /**
   * Mybatis的configuration
   */
  private final Configuration configuration;

  public StaticSqlSource(Configuration configuration, String sql) {
    this(configuration, sql, null);
  }

  public StaticSqlSource(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
    this.sql = sql;
    this.parameterMappings = parameterMappings;
    this.configuration = configuration;
  }

  /**
   * parameterMappings：当前类自己有的，是参数映射信息
   * parameterObject： 是要传进来的，是参数信息
   */
  @Override
  public BoundSql getBoundSql(Object parameterObject) {
    return new BoundSql(configuration, sql, parameterMappings, parameterObject);
  }

}
