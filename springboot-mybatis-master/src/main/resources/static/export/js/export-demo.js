function exportTable(exportType) {
	$("#table_demo").tableExport({
		type : exportType,
		outputMode:'window'
	});
}