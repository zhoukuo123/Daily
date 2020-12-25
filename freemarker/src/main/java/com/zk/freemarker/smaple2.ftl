<#list computers as c>
<#--    迭代变量_index 保存了循环的索引, 从0开始-->
    序号:${c_index + 1}
    SN:${c.sn}
    型号:${c.model}
    <#switch c.state>
        <#case 1>
            状态: 正在使用
            <#break>
        <#case 2>
            状态: 闲置
            <#break>
        <#case 3>
            状态: 已作废
            <#break>
        <#default>
            状态: 无效状态
    </#switch>
    状态:${c.state}
    <#if c.user??>
        用户:${c.user}
    </#if>
    采购时间:${c.dop?string("yyyy-MM-dd")}
    采购价格:${c.price?string("0.00")}
----------------------------------------------------
</#list>