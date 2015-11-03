/*
 * Beangle, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2005-2015, Beangle Software.
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
package org.beangle.webui.tag

import java.io.StringWriter
import java.{ util => ju }

import org.beangle.commons.collection.page.Page
import org.beangle.webmvc.api.context.ActionContextHolder
import org.beangle.webmvc.view.UITheme
import org.beangle.webmvc.view.tag.{ ComponentContext, CoreModels }
import org.beangle.webmvc.view.tag.freemarker.TagModel
import org.beangle.webui.tag.Grid.{ Bar, Boxcol, Col, Filter, Row, Treecol }

import _root_.freemarker.template.utility.StringUtil
import javax.servlet.http.HttpServletRequest

class BeangleModels(context: ComponentContext, request: HttpServletRequest) extends CoreModels(context, request) {

  /**
   * Return useragent component.
   */
  def agent: TagModel = get(classOf[Agent])

  def head = get(classOf[Head])

  def dialog: TagModel = get(classOf[Dialog])

  def css: TagModel = get(classOf[Css])

  def iframe: TagModel = get(classOf[Iframe])

  def foot: TagModel = get(classOf[Foot])

  def form: TagModel = get(classOf[Form])

  def formfoot: TagModel = get(classOf[Formfoot])

  def submit: TagModel = get(classOf[Submit])

  def reset: TagModel = get(classOf[Reset])

  def toolbar: TagModel = get(classOf[Toolbar])

  def tabs: TagModel = get(classOf[Tabs])

  def tab: TagModel = get(classOf[Tab])

  def grid: TagModel = get(classOf[Grid])

  def gridbar: TagModel = get(classOf[Bar])

  def filter: TagModel = get(classOf[Filter])

  def row: TagModel = get(classOf[Row])

  def col: TagModel = {
    var model = models.get(classOf[Col])
    if (null == model) {
      // just for performance
      model = new TagModel(context) {
        override protected def getBean() = new Col(context)
      }
      models.put(classOf[Col], model)
    }
    return model
  }

  def treecol: TagModel = get(classOf[Treecol])

  def boxcol: TagModel = get(classOf[Boxcol])

  def pagebar: TagModel = get(classOf[Pagebar])

  def password: TagModel = get(classOf[Password])

  def a: TagModel = {
    var model = models.get(classOf[Anchor])
    if (null == model) {
      model = new TagModel(context) {
        override protected def getBean() = new Anchor(context)
      }
      models.put(classOf[Anchor], model)
    }
    return model
  }

  def messages: TagModel = get(classOf[Messages])

  def textfield: TagModel = get(classOf[Textfield])

  def email: TagModel = get(classOf[Email])

  def textarea: TagModel = get(classOf[Textarea])

  def field: TagModel = get(classOf[Field])

  def textfields: TagModel = get(classOf[Textfields])

  def datepicker: TagModel = get(classOf[Date])

  def div: TagModel = get(classOf[Div])

  def select: TagModel = get(classOf[Select])

  def select2: TagModel = get(classOf[Select2])

  def module: TagModel = get(classOf[Module])

  def navbar: TagModel = get(classOf[Navbar])

  def navlist: TagModel = get(classOf[Navlist])

  def navitem: TagModel = get(classOf[Navitem])

  def radio: TagModel = get(classOf[Radio])

  def radios: TagModel = get(classOf[Radios])

  def startend: TagModel = get(classOf[Startend])

  def checkbox: TagModel = get(classOf[Checkbox])

  def checkboxes: TagModel = get(classOf[Checkboxes])

  def validity: TagModel = get(classOf[Validity])

}