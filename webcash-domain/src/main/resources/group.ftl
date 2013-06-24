<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:p="http://primefaces.org/ui">
<body> 
<table border="1" cellpadding="5" width="100%">
<#list groups as group>
    <tr><th colspan="2">${group_index + 1}. ${group.name}</th></tr>
		<tr><th>name</th><th>price</th></tr>
    <#list group.goods as good>
	    <tr><td>${good.name}</td><td>${good.price}</td></tr>
    </#list>
</#list>
</table>
</body> 
</html>