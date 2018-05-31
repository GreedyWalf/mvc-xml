<#assign ctx=Request.contextPath/>
<html>
<head>
	<meta charset="UTF-8">
	<title>Aligning Columns in DataGrid - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="${ctx!}/static/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx!}/static/themes/icon.css">
	<script type="text/javascript" src="${ctx}/js/easy/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/easy/jquery.easyui.min.js"></script>
</head>
<body>
	<h2>Aligning Columns in DataGrid</h2>
	<p>Use align and halign properties to set the alignment of the columns and their header.</p>
	<div style="margin:20px 0;"></div>
	
	<table class="easyui-datagrid" title="Aligning Columns in DataGrid" style="width:700px;height:250px"
			data-options="singleSelect:true,collapsible:true,url:'${ctx!}/static/json/datagrid_data1.json',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'itemid',width:80,halign:'center'">Item ID</th>
				<th data-options="field:'productid',width:100,halign:'center'">Product</th>
				<th data-options="field:'listprice',width:80,align:'right',halign:'center'">List Price</th>
				<th data-options="field:'unitcost',width:80,align:'right',halign:'center'">Unit Cost</th>
				<th data-options="field:'attr1',width:250,halign:'center'">Attribute</th>
				<th data-options="field:'status',width:60,align:'center',halign:'center'">Status</th>
			</tr>
		</thead>
	</table>

</body>
</html>