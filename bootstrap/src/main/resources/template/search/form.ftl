[#ftl]
<form id="${tag.id}" name="${tag.name}" [#if tag.cssClass??] class="${tag.cssClass}"[/#if] action="${tag.action}" method="post"[#if tag.target??] target="${tag.target}"[/#if][#if tag.onsubmit??] onsubmit="${tag.onsubmit}"[/#if]>
[#if Parameters['_params']??]<input name="_params" type="hidden" value="${Parameters['_params']?html}" />[/#if]
<table class="search-widget">
[#if tag.title??]
<tr><td><span class="toolbar-icon action-info"></span><em>${tag.title}</em></td></tr>
<tr><td style="font-size:0px"><img src="${b.static_url("bui","icons/16x16/actions/keyline.png")}" height="2" width="100%" alt="keyline"/></td></tr>
[/#if]
${tag.body}
[#if !tag.body?contains('submit')]
<tr><td align="center" style="padding-top:3px">[@b.submit value="${b.text('action.search')}"/]</td></tr>
[/#if]
</table>
</form>
