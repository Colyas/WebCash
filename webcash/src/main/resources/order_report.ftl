<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
   "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <style type="text/css">
   body { 
    font-family:Tahoma;
   }
  </style>
</head>
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