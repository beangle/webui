<tr><td class="search-item">
[#if tag.option??][#assign optionTemplate=tag.option?interpret][/#if]
[#assign remoteSearch=tag.remoteSearch/]
[#assign localChosen=false]
[#if tag.items?? && (tag.items?size > (tag.chosenMin?number-1))]
  [#assign localChosen=true]
[/#if]
<label style="font-weight:inherit" for="${tag.id}">${tag.label}:</label>[#t/]
<select id="${tag.id}" [#if tag.title??]title="${tag.title}"[/#if] name="${tag.name}" [#if tag.width??]width="${tag.width}"[/#if] [#if tag.mutiple??]mutiple="${tag.mutiple}"[/#if]${tag.parameterString}>
${tag.body}
[#if tag.empty??][#if localChosen|| tag.remoteSearch]<option value=""></option>[#else]<option value="">${tag.empty}</option>[/#if][/#if][#rt/]
[#assign selected=false/]
[#if tag.items??]
[#list tag.items as item][#assign optionText][#if tag.option??][@optionTemplate/][#else]${item[tag.valueName]!}[/#if][/#assign]
<option value="${item[tag.keyName]}" title="${optionText!}" [#if !selected && tag.isSelected(item)] selected="selected" [#assign selected=true/][/#if]>${optionText!}</option>
[/#list]
[#if tag.value?? && !selected]<option value="${tag.value}" selected="selected">${tag.value}</option>[/#if]
[/#if]
</select>[#if tag.comment??]<label class="comment">${tag.comment}</label>[/#if]
[#if localChosen || tag.href??]
<script type="text/javascript">
[#if localChosen]
  beangle.load(["chosen"],function(){
    $("#${tag.id}").chosen({placeholder_text_single:"${tag.empty!'...'}",no_results_text: "没有找到结果！",search_contains:true,allow_single_deselect:true[#if tag.width??],width:'${tag.width}'[/#if]});
  });
[#elseif tag.href??]
  [#if remoteSearch]
  beangle.load(["chosen","bui-ajaxchosen"],function(){
    $("#${tag.id}").ajaxchosen(
    { method:"GET",
      url:"${tag.href}"
    },
    function (datas){
      var items=[]
      jQuery.each(datas,function(i,data){
        items.push({"value":data['${tag.keyName}'],"text":data['${tag.valueName}']});
       });
       return items;
    },{placeholder_text_single:"${tag.empty!'...'}",search_contains:true,allow_single_deselect:true[#if tag.width??],width:'${tag.width}'[/#if]}
    );
  });
  [#else]
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
      if( cnt >= ${tag.chosenMin}){
        beangle.load(["chosen"],function(){
          $("#${tag.id}").chosen({placeholder_text_single:"${tag.empty!'...'}",no_results_text: "没有找到结果！",search_contains:true,allow_single_deselect:true[#if tag.width??],width:'${tag.width}'[/#if]});
        });
      }
    }
  });
  [/#if]
[/#if]
</script>
[/#if]
<td></tr>
