[#if tag.id??]
<div id="${tag.id}">
[#if tag.hasMessages]
<div class="actionMessage">
  <div class="alert alert-info" style="margin-bottom: 0;padding: 0;">
    [#list tag.actionMessages as message]
    <span>${message!}</span>[#if message_has_next]<br/>[/#if]
    [/#list]
  </div>
</div>
[/#if]
[#if tag.hasErrors]
<div class="actionError">
  <div class="alert alert-danger" style="margin-bottom: 0;padding: 0;">
    [#list tag.actionErrors as message]
    <span>${message!}</span>[#if message_has_next]<br/>[/#if]
    [/#list]
  </div>
</div>
[/#if]
</div>
[#if tag.parameters['slash']??]
<script>
  setTimeout(function(){var msgdiv=document.getElementById('${tag.id}');if(msgdiv) msgdiv.style.display="none";},${tag.parameters['slash']}*1000);
</script>
[/#if]
[/#if]