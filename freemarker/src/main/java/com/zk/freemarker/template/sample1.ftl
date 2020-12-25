<#--Freemarker取值-->
${site}
${url}
<#--默认值-->
${author!"不存在的属性"}
<#-- ?string 格式化输出-->
${date?string("yyyy年MM月dd日 HH:mm:ss SSS")}
${number?string("0.00")}

SN:${computer.sn}
型号:${computer.model}
状态:${computer.state}
用户:${computer.user}
采购时间:${computer.dop?string("yyyy年MM月dd日")}
采购价格:${computer.price?string("0.00")}
配置信息:
-------------
CPU:${computer.info["cpu"]}
内存:${computer.info["memory"]!"无内存信息"}


