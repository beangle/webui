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
import java.text.SimpleDateFormat
import java.{ util => ju }
import org.beangle.commons.bean.Properties
import org.beangle.commons.lang.Strings
import org.beangle.webmvc.view.tag.ComponentContext
import org.beangle.commons.lang.Primitives
import org.beangle.webmvc.view.tag.ClosingUIBean
import org.beangle.webmvc.view.tag.UIBean

class Form(context: ComponentContext) extends ClosingUIBean(context) {
  var name: String = _
  var action: String = _
  var target: String = _

  var onsubmit: String = _

  /** Boolean */
  private var _validate: String = _

  var title: String = _

  private val elementChecks = new collection.mutable.HashMap[String, StringBuilder]

  private var extraChecks: StringBuilder = _

  override def evaluateParams(): Unit = {
    if (null == name && null == id) {
      generateIdIfEmpty()
      name = id
    } else if (null == id) {
      id = name
    }
    if (null != action) action = render(action)
    if (null != title) title = getText(title)
    if (null != onsubmit) {
      if (!onsubmit.trim().startsWith("return")) {
        onsubmit = "return " + onsubmit
      }
    }
  }

  def validate: String = {
    if (null == _validate) {
      if (!elementChecks.isEmpty || (null != extraChecks && extraChecks.length > 0)) _validate = "true"
      else _validate = "false"
    }
    _validate
  }

  def validate_=(validate: String): Unit = {
    this._validate = validate
  }

  /**
   * Required element by id
   */
  def addRequire(id: String): Unit = this.addCheck(id, "require().match('notBlank')")

  def addCheck(id: String, check: String): Unit = {
    elementChecks.get(id) match {
      case Some(sb) => sb.append('.').append(check)
      case None =>
        val sb = new StringBuilder(100)
        elementChecks.put(id, sb)
        sb.append('.').append(check)
    }
  }

  def addCheck(check: String): Unit = {
    if (null == extraChecks) extraChecks = new StringBuilder()
    extraChecks.append(check)
  }

  def validity: String = {
    // every element initial validity buffer is 80 chars.
    val sb = new StringBuilder((elementChecks.size * 80) +
      (if (null == extraChecks) 0 else extraChecks.length))
    for ((key, value) <- elementChecks) {
      sb.append("jQuery('#").append(Strings.replace(key, ".", "\\\\.")).append("')")
        .append(value).append("\n")
    }
    if (null != extraChecks) sb.append(extraChecks)
    sb.toString
  }
}

class Formfoot(context: ComponentContext) extends ClosingUIBean(context) {

}

class Reset(context: ComponentContext) extends UIBean(context) {

}

class Submit(context: ComponentContext) extends UIBean(context) {
  var formId: String = _
  var onsubmit: String = _
  var action: String = _
  var value: String = _
  var target: String = _

  override def evaluateParams() {
    if (null == formId) {
      val f = findAncestor(classOf[Form])
      if (null != f) formId = f.id
    }
    if (null != onsubmit && -1 != onsubmit.indexOf('(')) onsubmit = Strings.concat("'", onsubmit, "'")
    if (null != value) value = getText(value)
    if (null != action) action = render(action)
  }
}

class Validity(context: ComponentContext) extends ClosingUIBean(context) {

  override def doEnd(writer: Writer, body: String): Boolean = {
    val myform = findAncestor(classOf[Form])
    if (null != myform) myform.addCheck(body)
    false
  }
}

object Radio {
  val Booleans: Map[Any, String] = Map(true -> "1", false -> "0", "y" -> "1", "Y" -> "1", "true" -> "1", "false" -> "0", "n" -> "0", "N" -> "0")
  val DefaultItemMap = Map(("1", "是"), ("0", "否"))
  val DefaultKeys = List("1", "0")
  def booleanize(obj: Object): Object = Booleans.get(obj).getOrElse(obj)
}

class Radio(context: ComponentContext) extends UIBean(context) {

  var name: String = _
  var label: String = _
  var title: String = _
  var value: Object = ""

  override def evaluateParams() {
    if (null == this.id) generateIdIfEmpty()
    label = processLabel(label, name)
    if (null != title) title = getText(title)
    else title = label
    this.value = Radio.booleanize(value)
  }
}

class Field(context: ComponentContext) extends ClosingUIBean(context) {
  var label: String = _
  var required: String = _
  override def evaluateParams() {
    if (null != label) label = getText(label)
  }
}
class AbstractTextBean(context: ComponentContext) extends UIBean(context) {
  var name: String = _
  var label: String = _
  var title: String = _
  var comment: String = _
  var required: String = _
  var value: Object = ""
  var check: String = _
  var maxlength = "100"

  override def evaluateParams(): Unit = {
    generateIdIfEmpty()
    label = processLabel(label, name)
    if (null != title) {
      title = getText(title)
    } else {
      title = label
    }
    val myform = findAncestor(classOf[Form])
    if ("true".equals(required)) myform.addRequire(id)
    if (null != check) myform.addCheck(id, check)
  }
}

class Textfield(context: ComponentContext) extends AbstractTextBean(context)

class Textarea(context: ComponentContext) extends AbstractTextBean(context) {
  var cols: String = _
  var readonly: String = _
  var rows: String = _
  var wrap: String = _

  maxlength = "400"

  override def evaluateParams() {
    super.evaluateParams()
    val myform = findAncestor(classOf[Form])
    if (null != maxlength) myform.addCheck(id, "maxLength(" + maxlength + ")")
  }
}
object Date {
  val ResvervedFormats = Map(("date",
    "yyyy-MM-dd"), ("datetime", "yyyy-MM-dd HH:mm:ss"))
}

class Date(context: ComponentContext) extends UIBean(context) {
  var name: String = _
  var label: String = _
  var title: String = _
  var comment: String = _
  var check: String = _
  var required: String = _
  var value: Object = ""
  var format = "date"
  var minDate: String = _
  var maxDate: String = _

  override def evaluateParams() {
    if (null == this.id) generateIdIfEmpty()
    label = processLabel(label, name)

    if (null != title) title = getText(title)
    else title = label

    val myform = findAncestor(classOf[Form])
    if (null != myform) {
      if ("true".equals(required)) myform.addRequire(id)
      if (null != check) myform.addCheck(id, check)
    }
    val format2 = Date.ResvervedFormats.getOrElse(format, format)
    if (null != format2) format = format2
    if (value.isInstanceOf[ju.Date]) {
      val dformat = new SimpleDateFormat(format)
      value = dformat.format(value.asInstanceOf[java.util.Date])
    }
  }

}

class Checkbox(context: ComponentContext) extends UIBean(context) {
  var name: String = _
  var label: String = _
  var title: String = _
  var value: Object = ""
  var checked = false
  var required: String = _

  override def evaluateParams() = {
    if (null == this.id) generateIdIfEmpty()
    label = processLabel(label, name)

    if (null != title) title = getText(title)
    else title = label

    var myform = findAncestor(classOf[Form])
    if (null != myform) {
      if ("true".equals(required)) {
        myform.addCheck(id + "_span", "assert($(\"#" + id + ":checked\").length != 0,'必须勾选一项')")
      }
    }
  }
}

class Select(context: ComponentContext) extends ClosingUIBean(context) {
  var name: String = _
  var items: Object = _
  var empty: String = _
  var value: Object = _

  var keyName: String = _
  var valueName: String = _

  var label: String = _
  var title: String = _

  var comment: String = _
  var check: String = _
  var required: String = _

  /** option text template */
  var _option: String = _

  var href: String = _

  var mutiple: String = _

  override def evaluateParams() {
    if (null == keyName) {
      if (items.isInstanceOf[ju.Map[_, _]]) {
        keyName = "key"
        valueName = "value"
        items = items.asInstanceOf[ju.Map[_, _]].entrySet
      } else {
        keyName = "id"
        valueName = "name"
      }
    }
    if (null == this.id) generateIdIfEmpty()
    label = processLabel(label, name)
    if (null != title) title = getText(title)
    else title = label

    val myform = findAncestor(classOf[Form])
    if (null != myform) {
      if ("true".equals(required)) myform.addRequire(id)
      if (null != check) myform.addCheck(id, check)
    }
    if (null == value) value = requestParameter(name)
    if (null != value) {
      value = value match {
        case str: String         => if (Strings.isEmpty(str)) null else str
        case tuple: Tuple2[_, _] => tuple._1.toString
        case _ =>
          if (Primitives.isWrapperType(value.getClass())) value
          else Properties.get(value, keyName)
      }
    }
    if (null != href) href = render(href)
  }

  def isSelected(obj: Object): Boolean = {
    if (null == value) return false
    else try {
      var pObj =
        if (obj.isInstanceOf[Tuple2[_, _]]) obj.asInstanceOf[Tuple2[Object, _]]._1
        else if (obj.isInstanceOf[ju.Map.Entry[_, _]]) obj.asInstanceOf[ju.Map.Entry[Object, _]].getKey()
        else Properties.get(obj, keyName)

      value == pObj || value.toString == pObj.toString
    } catch {
      case e: Exception => false
    }
  }

  def option_=(o: String) {
    if (null != o) {
      if (Strings.contains(o, "$")) {
        this._option = o
      } else if (Strings.contains(o, ",")) {
        keyName = Strings.substringBefore(o, ",")
        valueName = Strings.substringAfter(o, ",")
      }
    }
  }
  def option = _option
}

class Email(context: ComponentContext) extends AbstractTextBean(context) {
  check = "match('email')"
}

class Password(context: ComponentContext) extends AbstractTextBean(context) {
  var minlength: String = "6"
  maxlength = "10"
  var showStrength = "false"
}
