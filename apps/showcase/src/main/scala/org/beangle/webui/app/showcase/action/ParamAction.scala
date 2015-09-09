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
package org.beangle.webui.app.showcase.action

import org.beangle.webmvc.api.annotation.{cookie, header, param, response}

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

class ParamAction {

  @response
  def cookie(@cookie(value = "JSESSIONID", required = false) sid: String): String = {
    "Cookie JSESSIONID:" + String.valueOf(sid)
  }

  @response
  def param(@param("item") item: String, request: HttpServletRequest, response: HttpServletResponse): String = {
    item
  }

  @response
  def header(@header("Accept") accept: String, request: HttpServletRequest, response: HttpServletResponse): String = {
    accept
  }
}