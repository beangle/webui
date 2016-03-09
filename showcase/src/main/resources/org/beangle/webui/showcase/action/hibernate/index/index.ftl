[@b.head/]
[#if factories?size>0]
Found SessionFactories.
[#else]
Cannot find session factories.
[/#if]
<ul>
[#list factories?keys as k]
<li>[@b.a href="/hibernate/config/${k}/index"]${k}[/@]</li>
[/#list]
</ul>
[@b.foot/]