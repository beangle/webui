[#ftl]
<li${tag.parameterString}>[#if tag.label??]<label class="title">[#if (tag.required!"")=="true"]<em class="required">*</em>[/#if]${tag.label}:</label>[/#if]
 <input type="file" name="${tag.name}" [#if tag.id??]id="${tag.id}"[/#if] title="${tag.label!'File'}">
</li>
