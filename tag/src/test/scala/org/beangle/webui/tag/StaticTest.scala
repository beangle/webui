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
import org.beangle.commons.logging.Logging
import org.junit.runner.RunWith
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StaticTest extends AnyFunSpec with Matchers with Logging {

  describe("Static") {
    it(" read and write") {
      val modules = Static.Default.modules
      val map = Collections.newMap[String, String]
      modules.keys.toList.sorted foreach { n =>
        var m = modules(n);
        map.put(n, m.toString)
        println(s"""    "${n}":${m.toString},""")
      }
      map.get("font-awesome").size should equal(1)
    }
  }
}
