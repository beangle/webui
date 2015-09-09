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
package org.beangle.webui.app.hibernate.helper

import org.beangle.commons.bean.Initializing
import org.beangle.commons.inject.Container
import org.beangle.data.jpa.hibernate.ConfigurableSessionFactory
import org.hibernate.SessionFactory

class SessionFactoryHelper extends Initializing {
  var factories: Map[Any, ConfigurableSessionFactory] = _

  var container: Container = _

  override def init() {
    factories = container.parent.getBeans(classOf[ConfigurableSessionFactory]).map {
      case (k, v) =>
        var name = k.toString
        name = name.replace(".", "_")
        name = name.replace("#", "_")
        (name, v)
    }
  }

  def getSessionFactory(id: String): SessionFactory = {
    factories(id).result
  }

  def getFactory(id: String): ConfigurableSessionFactory = {
    factories(id)
  }
}