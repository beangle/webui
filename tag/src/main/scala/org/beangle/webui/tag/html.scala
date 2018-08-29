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

import java.io.Writer

import org.beangle.commons.lang.Strings
import org.beangle.webmvc.view.tag.{ ComponentContext, UIBean, ClosingUIBean, Themes }

class Head(context: ComponentContext) extends ClosingUIBean(context) {
  var loadui = true
  var compressed = true
  override def evaluateParams() {
    val devMode = requestParameter("devMode")
    if (null != devMode) compressed = !("true".equals(devMode) || "on".equals(devMode))
  }
}

class Foot(context: ComponentContext) extends ClosingUIBean(context)

object Anchor {

  val ReservedTargets = Set("_blank", "_top", "_self", "_parent", "new")
}

class Anchor(context: ComponentContext) extends ClosingUIBean(context) {
  var href: String = _
  var target: String = _
  var onclick: String = _

  def reserved: Boolean = Anchor.ReservedTargets.contains(target)

  override def evaluateParams() = {
    this.href = render(this.href)
    if (!reserved) {
      if (null == onclick) {
        if (null != target) {
          onclick = Strings.concat("return bg.Go(this,'", target, "')")
          target = null
        } else {
          onclick = "return bg.Go(this,null)"
        }
      }
    }
  }

  override def doEnd(writer: Writer, body: String): Boolean = {
    if (context.theme.name == Themes.Default) {
      try {
        writer.append("<a href=\"")
        writer.append(href).append("\"")
        if (null != id) {
          writer.append(" id=\"").append(id).append("\"")
        }
        if (null != target) {
          writer.append(" target=\"").append(target).append("\"")
        }
        if (null != onclick) {
          writer.append(" onclick=\"").append(onclick).append("\"")
        }
        writer.append(parameterString)
        writer.append(">").append(body).append("</a>")
      } catch {
        case e: Exception =>
          e.printStackTrace()
      }
      return false
    } else {
      return super.doEnd(writer, body)
    }
  }
}
