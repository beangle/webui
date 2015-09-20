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

import org.beangle.webmvc.api.context.{ ActionMessages, ActionContextHolder, Flash }
import org.beangle.webmvc.view.tag.{ ClosingUIBean, ComponentContext, UIBean }

class Messages(context: ComponentContext) extends UIBean(context) {
  var actionMessages: List[String] = null
  var actionErrors: List[String] = null

  var clear = "true"

  override def evaluateParams() {
    val messages = ActionContextHolder.context.flash.get(Flash.MESSAGES).asInstanceOf[ActionMessages]
    if (null != messages && (!messages.messages.isEmpty || !messages.errors.isEmpty)) {
      generateIdIfEmpty()

      actionMessages = messages.messages.toList
      actionErrors = messages.errors.toList
      if ("true".equals(clear)) messages.clear()
    }
  }

  def hasErrors: Boolean = !actionErrors.isEmpty

  def hasMessages: Boolean = !actionMessages.isEmpty

}

class Dialog(context: ComponentContext) extends ClosingUIBean(context) {
  var title: String = _
  var href: String = _
  var modal = "false"

  override def evaluateParams() {
    this.href = render(href);
  }
}