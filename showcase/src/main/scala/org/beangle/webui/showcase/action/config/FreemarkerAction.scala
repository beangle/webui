/*
 * Beangle, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2005-2016, Beangle Software.
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

import org.beangle.commons.lang.ClassLoaders
import org.beangle.commons.lang.annotation.description
import org.beangle.webmvc.api.action.ActionSupport
import org.beangle.template.freemarker.FreemarkerConfigurer

@description("Freemarker配置查看器")
class FreemarkerAction extends ActionSupport {
  var freemarkerConfigurer: FreemarkerConfigurer = _

  def index(): String = {
    put("config", freemarkerConfigurer.config)
    put("properties", freemarkerConfigurer.properties)
    put("templatePath", freemarkerConfigurer.templatePath)
    val configLocations = ClassLoaders.getResources("META-INF/freemarker.properties") ++ ClassLoaders.getResources("freemarker.properties")
    put("configLocations", configLocations)
    forward()
  }
}
