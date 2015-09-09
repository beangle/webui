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

import java.io.File

import org.beangle.commons.lang.ClassLoaders
import org.beangle.webmvc.api.action.ActionSupport
import org.beangle.webmvc.api.view.{View, Stream}

class StreamAction extends ActionSupport {

  val url = ClassLoaders.getResource("ehcache.xml")
  val file = new File(url.getFile())
  def download(): View = {
    Stream(url)
  }

  def download2(): View = {
    Stream(url, "text/xml", "index")
  }

  def download3(): View = {
    Stream(file, "text/xml", "显示文件名.html")
  }

  def download4(): View = {
    Stream(url.openStream(), "text/xml", "显示文件名.html")
  }
}
