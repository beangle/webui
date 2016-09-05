[#ftl]
[#if !(request.getHeader('x-requested-with')??) && !Parameters['x-requested-with']??]
<!DOCTYPE html>
<html lang="zh_CN">
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="content-style-type" content="text/css"/>
    <meta http-equiv="content-script-type" content="text/javascript"/>
    <meta http-equiv="expires" content="0"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>[#if tag.parameters['title']??]${tag.parameters['title']}[/#if]</title>

  [#assign themeName = Parameters['ui.theme']!"beangle"]
  <script type="text/javascript" src="${base}/static/jquery/jquery,jquery-form,jquery-history,/static/beangle/beangle.js,beangle-ui.js"></script>
  [#if tag.loadui]
  <script type="text/javascript" src="${base}/static/my97/WdatePicker.js"></script>
  <script type="text/javascript" src="${base}/static/jquery/jquery-colorbox.js"></script>
  <script type="text/javascript" src="${base}/static/jquery/jquery-chosen.js"></script>
  [/#if]
  <script>
    beangle.contextPath="${base}"
  </script>
  <link rel="stylesheet" href="${base}/static/bootstrap/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="${base}/static/bootstrap/css/bootstrap-theme.min.css"/>
  <link rel="stylesheet" href="${base}/static/beangle/css/beangle-ui.css"/>
  <script src="${base}/static/bootstrap/bootstrap.min.js"></script>
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
    ${tag.body}
  <style type="text/css">
  body {
    font-size: 13px;
  }
  </style>
  </head>
  <body>
[/#if]
