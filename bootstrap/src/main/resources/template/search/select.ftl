[#if tag.option??][#assign optionTemplate=tag.option?interpret][/#if]
<tr><td class="search-item"><label for="${tag.id}">${tag.label}:</label>
[#assign selected=false/]
<select id="${tag.id}" name="${tag.name}"${tag.parameterString}>
${tag.body}
[#if tag.empty??]<option value="">${tag.empty}</option>[/#if][#rt/]
[#if tag.items??]
[#list tag.items as item][#assign optionText][#if tag.option??][@optionTemplate/][#else]${item[tag.valueName]!}[/#if][/#assign]
<option value="${item[tag.keyName]}" title="${optionText!}" [#if !selected && tag.isSelected(item)] selected="selected" [#assign selected=true/][/#if]>${optionText!}</option>
[/#list]
[#if tag.value?? && !selected]<option value="${tag.value}" selected="selected">${tag.value}</option>[/#if]
[/#if]
</select></td></tr>
[#assign enableChoosen=false]
[#if tag.items?? && (tag.items?size > (tag.choosenMin?number-1))]
  [#assign enableChoosen=true]
[/#if]
[#if enableChoosen || tag.remote]
<script type="text/javascript">
[#if enableChoosen]
  $("#${tag.id}").chosen({no_results_text: "没有找到结果！",search_contains:true,allow_single_deselect:true});
[/#if]
[#if tag.remote]
jQuery.ajax({
  url: "${tag.href}",
  headers:{"Accept":"application/json"},
  success: function(datas){
    var select = $("#${tag.id}")
    var cnt=0;
    for(var i in datas){
      cnt += 1;
      var data = datas[i], value = data.${tag.keyName}
      select.append('<option value="'+value+'" title="'+data.name+'">'+data.${tag.valueName}+'</option>');
    }
    [#if tag.value??]
    select.val("${tag.value}")
    [/#if]
    if( cnt >= ${tag.choosenMin}){
      $("#${tag.id}").chosen({no_results_text: "没有找到结果！",search_contains:true,allow_single_deselect:true});
    }
  }
});
[/#if]
</script>
[/#if]
