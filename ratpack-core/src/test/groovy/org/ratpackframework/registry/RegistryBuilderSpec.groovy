/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ratpackframework.registry

import spock.lang.Specification

class RegistryBuilderSpec extends Specification {

  def "can retrieve successfully"() {
    given:
    def c = RegistryBuilder.builder().add(String, "foo").build()
    def p = RegistryBuilder.builder().add(Integer, 2).build()
    def n = RegistryBuilder.join(p, c)

    expect:
    n.get(String) == "foo"
    n.get(Number) == 2 // delegating to parent
    n.getAll(Object) == ["foo", 2]
  }

}