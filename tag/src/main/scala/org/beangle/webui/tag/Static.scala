package org.beangle.webui.tag

import org.beangle.commons.collection.Collections
import org.beangle.commons.lang.ClassLoaders
import java.net.URL
import org.beangle.commons.lang.Strings

object Static {
  class Resource(val name: String, val version: String)

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
      rss += new Resource((e \ "@name").text, (e \ "@version").text)
    }
    rss.toList
  }
}

class Static {
  import Static.Resource
  val resources = Collections.newMap[String, Resource]

  def addResources(res: List[Resource]): this.type = {
    res.foreach { r =>
      resources.put(r.name, r)
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
      case None    => s"${base}/${bundle}undefined${fileName}"
    }
  }
}