[#ftl]
[#if !(request.getHeader('x-requested-with')??) && !Parameters['x-requested-with']??]
<!DOCTYPE html>
<html lang="zh_CN">
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="content-style-type" content="text/css"/>
    <meta http-equiv="content-script-type" content="text/javascript"/>
    <meta http-equiv="expires" content="0"/>
    <title>[#if tag.parameters['title']??]${tag.parameters['title']}[/#if]</title>
  ${b.script("jquery","jquery.min.js")}
  ${b.script("bui","js/jquery-form.js")}
  ${b.script("bui","js/jquery-history.js")}
  ${b.script("bui","js/beangle.js")}
  ${b.script("bui","js/beangle-ui.js")}
  [#if tag.loadui]
  ${b.script("my97","WdatePicker.js")}
  ${b.script("bui","js/jquery-colorbox.js")}
  ${b.script("bui","js/jquery-chosen.js")}
  [/#if]
  <script>
    beangle.base="${b.static_url('bui','')}"
  </script>
  ${b.css("bootstrap","css/bootstrap.min.css")}
  ${b.css("bootstrap","css/bootstrap-theme.min.css")}
  ${b.css("bui","css/beangle-ui.css")}
  ${b.script("bootstrap","js/bootstrap.min.js")}
    ${tag.body}
 </head>
 <body>
[/#if]
