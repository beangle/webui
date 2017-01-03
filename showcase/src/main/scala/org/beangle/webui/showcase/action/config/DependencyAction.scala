/*
 * Beangle, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2005-2017, Beangle Software.
 *
 * Beangle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Beangle is distributed in the hope that it will be useful.
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Beangle.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.beangle.webui.showcase.action.config

import org.beangle.commons.io.{ IOs, ResourcePatternResolver }
import org.beangle.commons.lang.annotation.description
import org.beangle.webmvc.api.action.{ ParamSupport, RouteSupport }

@description("项目依赖库查看器")
class DependencyAction extends RouteSupport with ParamSupport {

  def index(): String = {
    val resolver = new ResourcePatternResolver
    val urls = resolver.getResources("classpath*:META-INF/maven/**/pom.properties")
    val poms = new collection.mutable.ListBuffer[Map[String, String]]
    urls foreach { url =>
      poms += IOs.readJavaProperties(url)
    }
    put("jarPoms", poms.toList)
    forward()
  }
}
