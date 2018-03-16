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

import org.beangle.commons.collection.page.Page
import org.beangle.webmvc.api.context.ActionContext
import org.beangle.webmvc.execution.{ Handler, MappingHandler }
import org.beangle.webmvc.view.tag.{ ClosingUIBean, ComponentContext, UIBean }

class Toolbar(context: ComponentContext) extends ClosingUIBean(context) {
  var title: String = _

  override def evaluateParams() {
    generateIdIfEmpty()
    if (null != title) {
      title = getText(title)
    }
  }
}

class Navbar(context: ComponentContext) extends ClosingUIBean(context) {
  var brand: String = _
}

class Navlist(context: ComponentContext) extends ClosingUIBean(context) {

  override def evaluateParams() {
    if (cssClass == null) cssClass = "nav navbar-nav"
    else if (!cssClass.startsWith("nav ")) cssClass = "nav " + cssClass
  }
}

class Navitem(context: ComponentContext) extends ClosingUIBean(context) {
  var href: String = _
  var onclick: String = _
  var target: String = _
  var active = false

  override def evaluateParams() {
    if (null != href) {
      this.href = render(this.href)
      if (!active) {
        val contextPath = ActionContext.current.request.getContextPath
        val requestUri = (if (contextPath != "/") contextPath else "") + Handler.current.asInstanceOf[MappingHandler].mapping.url
        active = requestUri.startsWith(this.href)
      }
    }
  }
}
class Pagebar(context: ComponentContext) extends UIBean(context) {
  var page: Page[_] = _
}
