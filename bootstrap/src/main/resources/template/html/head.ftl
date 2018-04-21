[#ftl]
[#if !(request.getHeader('x-requested-with')??) && !Parameters['x-requested-with']??]
<!DOCTYPE html>
<html lang="zh_CN">
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="content-style-type" content="text/css"/>
    <meta http-equiv="content-script-type" content="text/javascript"/>
    <meta http-equiv="expires" content="0"/>
    <title>[#if tag.parameters['title']??]${tag.parameters['title']}[/#if]</title>
  [#assign beangle_base=static_base+'/bui/0.0.2'/]
  <script type="text/javascript" src="${static_base}/jquery/1.10.2/jquery.min.js"></script>
  <script type="text/javascript" src="${beangle_base}/js/jquery-form.js"></script>
  <script type="text/javascript" src="${beangle_base}/js/jquery-history.js"></script>
  <script type="text/javascript" src="${beangle_base}/js/beangle.js"></script>
  <script type="text/javascript" src="${beangle_base}/js/beangle-ui.js"></script>
  [#if tag.loadui]
  <script type="text/javascript" src="${static_base}/my97/4.8/WdatePicker.js"></script>
  <script type="text/javascript" src="${beangle_base}/js/jquery-colorbox.js"></script>
  <script type="text/javascript" src="${beangle_base}/js/jquery-chosen.js"></script>
  [/#if]
  <script>
    beangle.base="${beangle_base}"
  </script>
  <link rel="stylesheet" href="${static_base}/bootstrap/3.3.7/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="${static_base}/bootstrap/3.3.7/css/bootstrap-theme.min.css"/>
  <link rel="stylesheet" href="${beangle_base}/css/beangle-ui.css"/>
  <script src="${static_base}/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    ${tag.body}
  <style type="text/css">
  body {
    font-size: 13px;
  }
  </style>
  </head>
  <body>
[/#if]
