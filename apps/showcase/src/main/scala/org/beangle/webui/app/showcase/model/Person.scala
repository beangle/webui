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
package org.beangle.webui.app.showcase.model

import java.io.File
import java.{ util => ju }
import org.beangle.data.model.IntId

class Person(var code: String, var name: String) extends IntId {
  var address = Address("minzu", "500", "jiading")
  var mobile: String = _
  var addresses = List(Address("minzu", "500", "jiading"), Address("minzu2", "5002", "jiading2"))
  var families = Map("wife" -> "a girl", "daught" -> "ketty")

}
trait Addressable {
  val name: String
  val street: String
  val city: String
}

case class Address(name: String, street: String, city: String) extends Addressable
