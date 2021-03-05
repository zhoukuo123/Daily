${name?cap_first}
${brand?upper_case}
${brand?length}
${words?replace("blood", "*****")}
${words?index_of("blood")}
<#--    利用?string实现三目运算符的操作-->
${(words?index_of("blood") != -1)?string("包含敏感词汇", "不包含敏感词汇")}
${(words?contains("blood"))?string("包含敏感词汇", "不包含敏感词汇")}

${n?round}
${n?floor}
${n?ceiling}
${computer?size}
${computer?first.model}
${computer?last.model}

<#list computer?sort_by("price") as c>
    ${c.sn}-${c.price}
</#list>

<#list computer?sort_by("price")?reverse as c>
    ${c.sn}-${c.price}
</#list>