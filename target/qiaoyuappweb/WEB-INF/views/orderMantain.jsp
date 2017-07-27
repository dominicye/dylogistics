<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Order Maintain Page</title>
	<jsp:include page="include/menubar.jsp" flush="true" />
	<link rel="stylesheet" href="resources/script/jquery-ui.css">
	<script src="resources/script/jquery-ui.min.js"></script>
	 <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
</head>
<body>
<div id="msgDiv"></div>
<div class="text-center"><label class="text-Info"><h3>Order Information</h3></label></div>
<table class="table table-bordered table-hover">
			<tbody>
			<input type="hidden" id="orderId" name="orderId" value="${orderId}" />
			<tr>
			<td><h5>Packing Fee:</h5></td><td><input type="text" id="packingFee" class="form-control" value="${order.packingFee}"></td>
			<td><h5>Additional Fee:</h5></td><td><input type="text" id="additionalFee" class="form-control" value="${order.additionalFee}"></td>
			<td><h5>Additional Fee Reason:</h5></td><td><input type="text" id="additionalFeeReason" class="form-control" value="${order.additionalFeeReason}"></td>
			</tr>
			<tr>
			<td><h5>Total Fee Paid:</h5></td><td><input type="text" id="totalFeePayed" class="form-control" value="${order.totalFeePaied}"></td>
			<td><h5>Shroff Fee:</h5></td><td><input type="text" id="shroffFee" class="form-control" value="${order.shroffFee}"></td>
			<td><h5>Delivery Fee:</h5></td><td><input type="text" id="deliveryFee" class="form-control" value="${order.deliveryFee}"></td>
			</tr>
			<tr>
			<td><h5>Net Weight:</h5></td><td><input type="text" id="netWeight" class="form-control" value="${order.netWeight}"></td>
			<td><h5>Capacity:</h5></td><td><input type="text" id="capacity" class="form-control" value="${order.capacity}"></td>
			<td><h5>Gross Weight:</h5></td><td><input type="text" id="grossWeight" class="form-control" value="${order.grossWeight}"></td>
			</tr>
			<tr>
			<td><h5>Order Status:</h5></td>
			<td> 
			<select id="orderStatus" >
			<option value="">Select...</option>
    		<option value="N">New</option>
    		<option value="P">Processing</option>
    		<option value="C">Complete</option>
    		</select>
    		<input type="hidden" id="orderStatusHidden" value="${order.status}">
    		</td>
    		<td><h5>Service Provider:</h5></td>
			<td>
			<select id="serviceProvider" >
			<option value="">Select...</option>
    		<option value="Sun Feng">Sun Feng</option>
    		<option value="UPS">UPS</option>
    		<option value="Fedex">Fedex</option>
    		<option value="DHL">DHL</option>
    		<option value="BSI">BSI</option>
    		<option value="BSI">EMS</option>
    		</select>
    		<input type="hidden" id="serviceProviderHidden" value="${order.serviceProvider}">
			</td>
			<td><h5>Delivery Date:</h5></td>
			<td>
            <input type="text" id="datepicker" value="${strDeliveryDate}">
			</td>
			</tr>
			<tr>
			<td><h5>Tracking No:</h5></td><td><input type="text" id="trackingNo" class="form-control" value="${order.trackingNo}"></td>
			<td><h5>Tracking URL:</h5></td><td><input type="text" id="trackingUrl" class="form-control" value="${order.trackingUrl}"></td>
			</tr>
			</tbody>
		</table>
		<div><input id="updateOrder" type="button" value="Update Order" class="btn btn-primary btn-md pull-right"></div>
		<div class="col-md-12 col-lg-12" style="height:10px;"></div>
<div class="text-right"><label class="text-Info"><a id="clientInfo"><h4 class="text-info">Client Information</h4></a></label></div>
<div id="clientDetailDiv">
	<table class="table table-bordered table-hover" id="tab_questionsList">
			<tbody id="clientContent">
			</tbody>
	</table>
</div>
<div class="col-md-12 col-lg-12" style="height:10px;"></div>
<div class="text-right"><label class="text-Info"><a id="productInfo"><h4 class="text-info">Product Information</h4></a></label></div>
<div id="productDetailDiv">
		<table class="table table-bordered table-hover" id="tab_questionsList">
		<thead>
				<tr>
					<th class="text-center col-md-3 col-lg-3">
						<h5>Item No</h5>
					</th>
					<th class="text-center col-md-7 col-lg-7">
						<h5>MEMO</h5>
					</th>
					<th class="text-center col-md-2 col-lg-2">
						<h5>QUANTITY</h5>
					</th>
				</tr>
		</thead>
			<tbody id="productContent">
			</tbody>
		</table>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#clientDetailDiv").hide();
	$("#productDetailDiv").hide();
	var osh = $("#orderStatusHidden").val();
	var sph = $("#serviceProviderHidden").val();
	
	$("#orderStatus").val(osh);
	$("#serviceProvider").val(sph);
	
	
	$("#updateOrder").click(function(){
		var orderId = $("#orderId").val();
		var packingFee = $("#packingFee").val();
		var additionalFee = $("#additionalFee").val();
		var additionalFeeReason = $("#additionalFeeReason").val();
		var totalFeePayed = $("#totalFeePayed").val();
		var shroffFee = $("#shroffFee").val();
		var deliveryFee = $("#deliveryFee").val();
		var netWeight = $("#netWeight").val();
		var capacity = $("#capacity").val();
		var grossWeight = $("#grossWeight").val();
		var orderStatus = $("#orderStatus").val();
		var serviceProvider = $("#serviceProvider").val();
		var deliveryDate = $("#datepicker").val();
		var trackingNo = $("#trackingNo").val();
		var trackingUrl = $("#trackingUrl").val();
		
		$.ajax({ 
			method: 'POST',
				url: ctx + '/updateOrder', 
				data: {orderId:orderId,packingFee:packingFee,additionalFee:additionalFee,additionalFeeReason:additionalFeeReason,
					totalFeePayed:totalFeePayed,shroffFee:shroffFee,deliveryFee:deliveryFee,netWeight:netWeight,capacity:capacity,
					grossWeight:grossWeight,orderStatus:orderStatus,serviceProvider:serviceProvider,deliveryDate:deliveryDate,
					trackingNo:trackingNo,trackingUrl:trackingUrl},
				dataType: 'json'
		})
		.done(function(data)
		{
			if(data.status === "success")
				{
					alert("update order successfully");
				}
			if(data.e != null)
				{
					$("#msgDiv").css("color","red").text("Update order failed, Please contact administrator:" + data.e.message);
				}
		})
		.fail(
		function(data){
			alert(data);
		}
		);
	});
	
	
	
	$("#clientInfo").click(function(){
		var orderId = $("#orderId").val();
		 $.ajax({ 
				method: 'GET',
					url: ctx + '/getClientInfo', 
					data: {orderId:orderId},
					dataType: 'json'
			})
			.done(function(data)
			{
				clientDetailCallBack(data);
			})
			.fail(
			function(data){
			}
			);
	});
	
	function clientDetailCallBack(d)
	{
		$("#clientDetailDiv").show();
		var content ='<tr><td class="text-center">First Name: '+d.firstName+'</td><td class="text-center">Last Name: '+d.lastName+'</td><td class="text-center">Email: '+d.email+'</td><td class="text-center">Shipping Addressee: '+d.shippingAddressee+'</td><td class="text-center">Shipping City: '+d.shippingCity+'</td><td class="text-center">Shipping state: '+d.shippingStateProvince+'</td></tr>';
			content += '<tr><td class="text-center">Shipping Zip: '+d.shippingZip+'</td><td class="text-center">Shipping Country: '+d.shippingCountry+'</td><td class="text-center">Shipping Phone: '+d.shippingPhone+'</td><td class="text-center">Shipping Address1: '+d.shippingAddress1+'</td><td class="text-center">Shipping Address2: '+d.shippingAddress2+'</td><td class="text-center">Shipping attention: '+d.shippingAttention+'</td></tr>';
			content += '<tr><td>Shipping Address: </td><td colspan="5" class="text-left"> '+d.shippingAddress+'</td></tr>';
		$("#clientContent").html(content);
	}
	
	$("#productInfo").click(function(){
		var orderId = $("#orderId").val();
		 $.ajax({ 
				method: 'GET',
					url: ctx + '/orderProductDetail', 
					data: {orderId:orderId},
					dataType: 'json'
			})
			.done(function(data)
			{
				productDetailCallBack(data);
			})
			.fail(
			function(data){
			}
			);
	});
	
	function productDetailCallBack(data)
	{
		$("#productDetailDiv").show();
		var content = '';
		for(i = 0,len=data.length; i < len; i++)
			{
				content +='<tr><td class="text-center">'+data[i][1]+'</td><td class="text-center">'+data[i][2]+'</td><td class="text-center">'+data[i][4]+'</td></tr>';
			}
		$("#productContent").html(content);
	}
	
	
});
	
</script>
</html>
