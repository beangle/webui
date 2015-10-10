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
package org.beangle.webui.app.hibernate.action

import org.beangle.commons.lang.Strings.isEmpty
import org.beangle.data.hibernate.ConfigurableSessionFactory
import org.beangle.webmvc.api.action.{ ParamSupport, RouteSupport }
import org.beangle.webui.app.hibernate.helper.SessionFactoryHelper
import org.hibernate.SessionFactory

abstract class AbstractAction extends RouteSupport with ParamSupport {

  var helper: SessionFactoryHelper = _

  def getSessionFactory(): SessionFactory = {
    val sfid = get("session_factory_id", "")
    if (isEmpty(sfid)) return null
    else helper.getSessionFactory(sfid)
  }

  def getFactory(): ConfigurableSessionFactory = {
    val sfid = get("session_factory_id", "")
    if (isEmpty(sfid)) return null
    else helper.getFactory(sfid)
  }

}