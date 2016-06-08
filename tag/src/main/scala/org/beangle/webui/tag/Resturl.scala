package org.beangle.webui.tag

import org.beangle.commons.bean.Properties

import org.beangle.commons.lang.functor.{ NotEmpty, NotZero }
import org.beangle.commons.lang.annotation.value
import org.beangle.webmvc.execution.Handler
import org.beangle.webmvc.dispatch.ActionUriRender

class Resturl(uriRender: ActionUriRender) {

  def save(obj: AnyRef): String = {
    val id: Any = Properties.get(obj, "id")
    if (isValid(id)) {
      uriRender.render(Handler.mapping, "!update?id=" + id)
    } else {
      uriRender.render(Handler.mapping, "!save")
    }
  }

  def info(obj: AnyRef): String = {
    val id: Any = Properties.get(obj, "id")
    uriRender.render(Handler.mapping, "!info?id=" + id)
  }

  private def isValid(id: Any): Boolean = {
    if (null == id) return false
    if (id.isInstanceOf[Number]) return NotZero(id.asInstanceOf[Number])
    return NotEmpty(id.toString)
  }
}
