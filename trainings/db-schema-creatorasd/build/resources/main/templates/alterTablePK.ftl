<#assign size = columnNames?size - 1>
<#assign i = 0>
ALTER TABLE ONLY ${tableName} <#t>
ADD CONSTRAINT ${keyName} <#t>
PRIMARY KEY <#t>
(<#list columnNames as columnName><#t>
${columnName}<#t>
    <#if i != size>
    , <#t>
    <#else>
    );<#t>

    </#if>
    <#assign i = i + 1>
</#list>