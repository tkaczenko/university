<#assign size = columns?size - 1>
<#assign i = 0>
CREATE TABLE ${tableName}(
<#list columns as column>
${column.name} <#t>
${column.type} <#t>
    <#if column.type == "numeric">
    (${column.precision},${column.scale}) <#t>
    </#if>
    <#if column.type == "character">
    (${column.size}) <#t>
    </#if>
    <#if column.nullable == false>
    NOT NULL<#t>
    </#if>
    <#if i != size>,<#t></#if>
    <#assign i = i + 1>
</#list>
);
