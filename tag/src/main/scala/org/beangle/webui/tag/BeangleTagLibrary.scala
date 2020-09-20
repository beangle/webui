/*
 * Beangle, Agile Development Scaffold and Toolkits.
 *
 * Copyright © 2005, The Beangle Software.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.beangle.webui.tag

import jakarta.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.beangle.commons.lang.annotation.description
import org.beangle.webmvc.view.tag.AbstractTagLibrary

/**
 * Beangle tag Library
 *
 * @author chaostone
 * @since 2.0
 */
@description("beangle webui 标签库")
class BeangleTagLibrary extends AbstractTagLibrary {

  def getModels(req: HttpServletRequest, res: HttpServletResponse): AnyRef = {
    if (null == Static.Default.base) {
      val p = System.getProperty("beangle.webmvc.static_base")
      Static.Default.base = if (null == p) req.getContextPath + "/static" else p
    }
    new BeangleModels(this.buildComponentContext(req), req)
  }

}
