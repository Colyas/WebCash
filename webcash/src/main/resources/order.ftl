<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:p="http://primefaces.org/ui">
<body>
<table border="1" cellpadding="5" width="100%">
<#list order.suborders as suborder>
    <tr><th colspan="4">Подача ${suborder.index}</th></tr>
    <tr><th>Назва товару</th><th>Ціна</th><th>Кількість</th><th>Сума</th></tr>
    <#list suborder.sales as sale>
    	<tr><td>${sale.name}</td><td>${sale.price?string("0.00")}</td><td>${sale.amount?string("0.000")}</td><td>${sale.calculatedSum?string("0.00")}</td></tr>
    </#list>
    <tr><th colspan="4" align="right">Сума по подачі: ${suborder.total?string("0.00")}</th></tr>
</#list>
<tr><th colspan="4" align="right">Сума по чеку: ${order.total?string("0.00")}</th></tr>
</table>
</body> 
</html>