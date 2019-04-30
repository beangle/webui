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

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.ClassLoaders
import java.net.URL
import org.beangle.commons.lang.Strings

object Static {
  case class Resource(val name: String, val version: String)

  val Default = buildDefault()

  def buildDefault(): Static = {
    val rs = new Static
    ClassLoaders.getResources("META-INF/beangle/mvc-default.xml") foreach { url =>
      rs.addResources(buildResource(url))
    }
    ClassLoaders.getResources("META-INF/beangle/mvc.xml") foreach { url =>
      rs.addResources(buildResource(url))
    }
    rs
  }

  private def buildResource(url: URL): List[Resource] = {
    val xml = scala.xml.XML.load(url.openStream())
    val rss = Collections.newBuffer[Resource]
    (xml \\ "static" \\ "bundle") foreach { e =>
      rss += Resource((e \ "@name").text, (e \ "@version").text)
    }
    rss.toList
  }
}

class Static {
  import Static.Resource
  val resources = Collections.newMap[String, Resource]

  def addResources(res: List[Resource]): this.type = {
    res.foreach { r =>
      resources.get(r.name) match {
        case None => resources.put(r.name, r)
        case Some(er) =>
          if (r.version.compareTo(er.version) > 0) {
            resources.put(r.name, r)
          }
      }
    }
    this
  }

  def url(base: String, bundle: String, file: String): String = {
    val fileName = {
      if (Strings.isEmpty(file)) {
        ""
      } else {
        if (file.charAt(0) == '/') file else "/" + file
      }
    }
    resources.get(bundle) match {
      case Some(r) => s"${base}/${bundle}/${r.version}${fileName}"
      case None    => s"${base}/${bundle}/undefined${fileName}"
    }
  }
}
