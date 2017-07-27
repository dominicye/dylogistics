<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>File Upload</title>
	<jsp:include page="include/menubar.jsp" flush="true" />
	<link href="resources/bootstrap/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
	<script src="resources/bootstrap/js/canvas-to-blob.min.js" type="text/javascript"></script>
	<script src="resources/bootstrap/js/fileinput.min.js"></script>
</head>
<body>
<form action="">
<label class="control-label">Select File</label>
<input id="orderFile" name="orderFile" type="file" class="file file-loading" data-allowed-file-extensions='["xls", "xlsx"]'>
</form>
</body>
<script type="text/javascript">
$("#orderFile").fileinput({
    uploadUrl: ctx + "/uploadFile", // server upload action
    uploadAsync: true,
    dropZoneEnabled: false,
    enctype: 'multipart/form-data'
}).on('fileerror', function(data) {
 alert("failed");
}).on("fileuploaded", function (data) {
	alert("success");
});

</script>
</html>
