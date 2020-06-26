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
import org.beangle.webmvc.view.tag.{ClosingUIBean, ComponentContext, UIBean}

class Toolbar(context: ComponentContext) extends ClosingUIBean(context) {
  var title: String = _

  override def evaluateParams(): Unit = {
    generateIdIfEmpty()
    if (null != title) {
      title = getText(title)
    }
  }
}

class Navbar(context: ComponentContext) extends ClosingUIBean(context) {
  var brand: String = _
}

class Nav(context: ComponentContext) extends ClosingUIBean(context) {

  override def evaluateParams(): Unit = {
    if (cssClass == null) cssClass = "nav"
    generateIdIfEmpty()
  }
}

class Navitem(context: ComponentContext) extends ClosingUIBean(context) {
  var href: String = _
  var onclick: String = _
  var target: String = _
  var active = false

  override def evaluateParams(): Unit = {
    if (null != href) {
      this.href = render(this.href)
      if (!active) {
        active = this.href.startsWith(request.getRequestURI)
      }
    }
  }
}

class Pagebar(context: ComponentContext) extends UIBean(context) {
  var page: Page[_] = _
}
