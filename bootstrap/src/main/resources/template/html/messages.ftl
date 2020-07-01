[#if tag.id??]
<div id="${tag.id}" [#if tag.parameters['slash']??]style="position: fixed;margin: 0px 0px 0px 30%;"[/#if]>
[#if tag.hasMessages]
  <div class="alert alert-info alert-dismissible">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
    [#list tag.actionMessages as message]
    <span>${message!}</span>[#if message_has_next]<br/>[/#if]
    [/#list]
  </div>
[/#if]
[#if tag.hasErrors]
  <div class="alert alert-danger alert-dismissible">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
    [#list tag.actionErrors as message]
    <span>${message!}</span>[#if message_has_next]<br/>[/#if]
    [/#list]
  </div>
[/#if]
</div>
[#if tag.parameters['slash']?? && !tag.hasErrors]
<script>
  setTimeout(function(){var msgdiv=document.getElementById('${tag.id}');if(msgdiv) msgdiv.style.display="none";},${tag.parameters['slash']}*1000);
</script>
[/#if]
[/#if]
