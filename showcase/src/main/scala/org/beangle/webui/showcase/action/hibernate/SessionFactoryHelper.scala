/*
 * Beangle, Agile Development Scaffold and Toolkit
 *
 * Copyright (c) 2005-2017, Beangle Software.
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
package org.beangle.webui.showcase.action.hibernate

import org.beangle.data.hibernate.spring.LocalSessionFactoryBean
import org.hibernate.SessionFactory

class SessionFactoryHelper(val fs: Map[String, LocalSessionFactoryBean]) {
  val factories: Map[Any, LocalSessionFactoryBean] = fs.map {
    case (k, v) =>
      var name = k.toString
      name = name.replace(".", "_")
      name = name.replace("#", "_")
      (name, v)
  }

  def getSessionFactory(id: String): SessionFactory = {
    factories(id).result
  }

  def getFactory(id: String): LocalSessionFactoryBean = {
    factories(id)
  }
}
