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

/**
 * @author Clinton Begin
 */
public interface SqlNode {
  /**
   * 用于解析sql节点，根据参数信息，生成静态的sql内容
   * 这里生成的sql静态信息，其实是这种形式的：insert into user(create_time, name, password, phone, nick_name) values(?,?,?,?,?);
   * 就是包含 ???? 的形式，因为真正填充查询参数的时候，是在使用ParameterHandler进行设值的
   */
  boolean apply(DynamicContext context);
}
