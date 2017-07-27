<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Order Statistics</title>
	<jsp:include page="include/menubar.jsp" flush="true" />
	<style type="text/css">
	.row{
		margin-right: 80px;
   		margin-left: 80px;
	}
	</style>
	<link rel="stylesheet" href="resources/script/jquery-ui.css">
	<script src="resources/script/jquery-ui.min.js"></script>
	<script>
  		$( function() {
    		$( "#fromDeliveryDate" ).datepicker();
    		$( "#toDeliveryDate" ).datepicker();
  		} );
  </script>
  
</head>
<body>
<div class="col-md-12 col-lg-12" style="height:30px;"></div>
<h4 class="text-primary text-center">Order Statistics</h4>
<div class="col-md-12 col-lg-12" style="height:10px;"></div>
<div class="container text-center">
<div class="row">
	<div class="col-md-12 col-lg-12">
		<div class="col-md-2 col-lg-2">
			<h5>Delivery Provider:</h5>
		</div>
		<div class="col-md-2 col-lg-2 text-left">
		<select id="serviceProvider" >
			<option value="">Select...</option>
    		<option value="Sun Feng">Sun Feng</option>
    		<option value="UPS">UPS</option>
    		<option value="Fedex">Fedex</option>
    		<option value="DHL">DHL</option>
    		<option value="BSI">BSI</option>
    		<option value="BSI">EMS</option>
    	</select>
    	</div>
		<div class="col-md-3 col-lg-3"><input id ="fromDeliveryDate"  type="text" placeholder="From Delivery Date" class="form-control" /></div>
		<div class="col-md-3 col-lg-3"><input id ="toDeliveryDate"  type="text" placeholder="To Delivery Date" class="form-control" /></div>
    <div class="col-md-2 col-lg-2"><input id="doStatistics" type="submit" value="Generate Report" class="btn btn-primary btn-md pull-right"></div>
	</div>

</div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#doStatistics").click(function(){
		var deliveryDateFrom = $("#fromDeliveryDate").val();
		var deliveryDateTo = $("#toDeliveryDate").val();
		var deliveryProvider = $("#serviceProvider").val();
		if(deliveryDateFrom === '' || deliveryDateTo === '' || deliveryProvider === '')
			{
			alert("please input delivery date and delivery provider");
			return false;
			}
		
		window.location.href = ctx + "/doOrderStatistics?deliveryDateFrom="+deliveryDateFrom+"&deliveryDateTo="+deliveryDateTo+"&deliveryProvider="+deliveryProvider;
		
	});
	
});

</script>
</html>
