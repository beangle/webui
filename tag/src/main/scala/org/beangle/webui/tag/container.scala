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

import org.beangle.commons.lang.{Objects, Strings}
import org.beangle.webmvc.view.tag.{ClosingUIBean, ComponentContext}

class Div(context: ComponentContext) extends ClosingUIBean(context) {
  var href: String = _

  var astarget: String = _

  override def evaluateParams(): Unit = {
    if (null == astarget && (null != id || null != href)) astarget = "true"
    if (null != href) {
      generateIdIfEmpty()
      href = render(this.href)
    }
    if (!Objects.equals(astarget, "false")) {
      if (null != cssClass) cssClass = Strings.concat("ajax_container", " ", cssClass)
      else cssClass = "ajax_container"
    }
  }
}

class Iframe(context: ComponentContext) extends ClosingUIBean(context) {
  var src: String = _

  override def evaluateParams(): Unit = {
    src = render(src)
  }
}

class Tab(context: ComponentContext) extends ClosingUIBean(context) {
  var href: String = _
  var label: String = _

  override def evaluateParams(): Unit = {
    if (null != href) href = render(href)
    if (null != label) label = getText(label)
    val tabs = findAncestor(classOf[Tabs])
    if (Strings.isEmpty(id)) id = tabs.id + "_tab" + tabs.tabs.size
    tabs.addTab(this)
  }
}

class Tabs(context: ComponentContext) extends ClosingUIBean(context) {

  var selected: Int = 0
  val tabs = new collection.mutable.ListBuffer[Tab]

  def addTab(tab: Tab): Unit = {
    this.tabs += tab
  }

  override def evaluateParams(): Unit = {
    generateIdIfEmpty()
  }

}

class Module(context: ComponentContext) extends ClosingUIBean(context) {
  var title: String = _

  override def evaluateParams(): Unit = {
    generateIdIfEmpty()
    if (null != title) {
      title = getText(title)
    }
  }
}
