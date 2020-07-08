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

import javax.servlet.http.HttpServletRequest
import org.beangle.webmvc.view.tag.freemarker.TagModel
import org.beangle.webmvc.view.tag.{ComponentContext, CoreModels}
import org.beangle.webui.tag.Grid._

class BeangleModels(context: ComponentContext, request: HttpServletRequest) extends CoreModels(context, request) {

  val static_base: String = {
    Static.Default.base
  }

  def static: Static = {
    Static.Default
  }

  def static_url(bundle: String, filename: String): String = {
    Static.Default.url(bundle, filename)
  }

  def script(bundle: String, fileName: String): String = {
    script(bundle, fileName, false)
  }

  def script(bundle: String, fileName: String, deferable: Boolean): String = {
    Static.Default.script(bundle, fileName, deferable)
  }

  def css(bundle: String, fileName: String): String = {
    Static.Default.css(bundle, fileName)
  }

  val rest = new Rest(context.uriRender)

  def math: MathOps.type = MathOps

  def head: TagModel = get(classOf[Head])

  def dialog: TagModel = get(classOf[Dialog])

  def iframe: TagModel = get(classOf[Iframe])

  def foot: TagModel = get(classOf[Foot])

  def form: TagModel = get(classOf[Form])

  def fieldset: TagModel = get(classOf[Fieldset])

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
        override protected def getBean = new Col(context)
      }
      models.put(classOf[Col], model)
    }
    model
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
    model
  }

  def messages: TagModel = get(classOf[Messages])

  def textfield: TagModel = get(classOf[Textfield])

  def email: TagModel = get(classOf[Email])

  def number: TagModel = get(classOf[Number])

  def range: TagModel = get(classOf[Range])

  def time: TagModel = get(classOf[Time])

  def textarea: TagModel = get(classOf[Textarea])

  def editor: TagModel = get(classOf[Editor])

  def field: TagModel = get(classOf[Field])

  def textfields: TagModel = get(classOf[Textfields])

  @deprecated("using @b.date")
  def datepicker: TagModel = get(classOf[Date])

  def date: TagModel = get(classOf[Date])

  def div: TagModel = get(classOf[Div])

  def select: TagModel = get(classOf[Select])

  def select2: TagModel = get(classOf[Select2])

  def card: TagModel = get(classOf[Card])

  def navbar: TagModel = get(classOf[Navbar])

  def nav: TagModel = get(classOf[Nav])

  def navitem: TagModel = get(classOf[Navitem])

  def radio: TagModel = get(classOf[Radio])

  def radios: TagModel = get(classOf[Radios])

  def startend: TagModel = get(classOf[Startend])

  def checkbox: TagModel = get(classOf[Checkbox])

  def checkboxes: TagModel = get(classOf[Checkboxes])

  def validity: TagModel = get(classOf[Validity])

  def file: TagModel = get(classOf[File])

  def URL: TagModel = get(classOf[Url])
}
