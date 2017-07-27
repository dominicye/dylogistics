<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Order Handling Main Page</title>
	<jsp:include page="include/menubar.jsp" flush="true" />
	<style type="text/css">
	.row{
		margin-right: 350px;
   		margin-left: 350px;
	}
	</style>
</head>
<body>
<div id="divMessage"></div>
<div class="col-md-12 col-lg-12" style="height:30px;"></div>
<h4 class="text-primary text-center">Order Sorting By Shipping Country Code</h4>
<div class="col-md-12 col-lg-12" style="height:10px;"></div>
<div class="container text-center">

<div class="row">
	<div class="col-md-12 col-lg-12">
		<div class="col-md-6 col-lg-6"><input id ="ShippingCountryCode" name ="ShippingCountryCode" type="text" placeholder="Shipping Country Code" class="form-control" /></div>
		<div class="col-md-6 col-lg-6"><input id="searchOrder" type="submit" value="Search" class="btn btn-primary btn-md pull-center"></div>
	</div>
</div>
<div class="col-md-12 col-lg-12" style="height:60px;"></div>
<div id="operatorDiv" class="col-md-12 col-lg-12">
		<table class="table table-bordered table-hover" id="tab_questionsList">
		<thead>
				<tr>
					<th class="text-center col-md-1 col-lg-1">
						<h5>Name</h5>
					</th>
					<th class="text-center col-md-2 col-lg-2">
						<h5>Shipping City/Province/Country/ZIP</h5>
					</th>
					<th class="text-center col-md-2 col-lg-2">
						<h5>Shipping Phone</h5>
					</th>
					<th class="text-center col-md-4 col-lg-4">
						<h5>Shipping Address</h5>
					</th>
					<th class="text-center col-md-3 col-lg-3">
						<h5>Operation</h5>
					</th>
				</tr>
		</thead>
			<tbody id="orderContent">
			</tbody>
		</table>
		</div>
		<div class="col-md-12 col-lg-12" style="height:10px;"></div>
		<div id="additionalDiv" class="col-md-12 col-lg-12">
		<div class="col-md-2 col-lg-2"><h5 class="text-right text-primary">Select Service Provider</h5></div>
		<div class="col-md-2 col-lg-2">
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
		<div class="col-md-2 col-lg-2"><input id="bindServiceProvider" type="button" value="Bind Service Provider" class="btn btn-primary btn-md pull-center"></div>
		<div class="col-md-2 col-lg-2"><input id="generateTemplate" type="button" value="Generate Template" class="btn btn-primary btn-md pull-center"></div>
		</div>
		
</div>
</body>
<script type="text/javascript">
var orderIds = [];
$(document).ready(function(){
	$("#additionalDiv").hide();
	
	$("#searchOrder").click(function(){
		$("#divMessage").text('');
		var scc = $("#ShippingCountryCode").val();
		if(scc==='')
			{
			alert("please input shipping country code");
			return false;
			}
		
		 $.ajax({ 
 			method: 'GET',
				url: ctx + '/orderSearch', 
				data: {shippingCountryCode:scc},
				dataType: 'json'
 		})
 		.done(function(data)
 		{
 			if(data.length==0)
 				{
 					$("#divMessage").css("color","red").text("There is no order can be processed for this shipping country");
 				}
 			else
 				{
 					orderCallBack(data);
 				}
 			
 		})
 		.fail(
 		function(data){
 		}
 		);
		
	});
	
});

function orderCallBack(data)
{
	var content = '';
	for(j = 0,len=data.length; j < len; j++) {
		orderIds.push(data[j].id);
		content += '<tr><td class="text-center col-md-2 col-lg-2">'+data[j].client.firstName+' '+data[j].client.lastName+'</td> <td class="text-center col-md-2 col-lg-2">'+data[j].client.shippingCity+'/'+data[j].client.shippingStateProvince+'/'+data[j].client.shippingCountry+'/'+data[j].client.shippingZip+'</td><td class="text-center col-md-2 col-lg-2">'+data[j].client.shippingPhone_+'</td><td class="text-center col-md-2 col-lg-2">'+data[j].client.shippingAddress+'</td><td class="text-center col-md-2 col-lg-2"><a id="orderMantain'+data[j].id+'">Process Order</a></td></tr>';   
	}
	$("#orderContent").html(content);
	$("#additionalDiv").show();
}

$("#operatorDiv table").on("click", "a[id*='orderMantain']", function(){
	var orderId = this.id.substr(12);
	window.location.href=ctx + "/orderMantain?id="+orderId; 
});

$("#serviceProvider").change(function(){
	var spvalue = $("#serviceProvider").val();
	if(spvalue === 'Sun Feng')
		{
			$("#additionalDiv").append('<div id="sunfeng" class="col-md-2 col-lg-2"><input id="goSunFeng" type="button" value="Interact Sun Feng" class="btn btn-primary btn-md pull-center"></div>');
			$("#generateTemplate").attr('disabled',true);
		}
	else
		{
		$("#generateTemplate").attr('disabled',false);
		$("#sunfeng").hide()
		}
});

$("#bindServiceProvider").click(function(){
	var spvalue = $("#serviceProvider").val();
	if(spvalue === '')
		{
			alert('please select service provider');
			return false;
		}
	
	 $.ajax({ 
			method: 'POST',
				url: ctx + '/bindOrderServiceProvider', 
				data: {orderIds:JSON.stringify(orderIds),spvalue:spvalue},
				dataType: 'json'
		})
		.done(function(data)
		{
			if(data.result === 'success')
				{
					alert("bind service provider successfully");
				}
			if(data.e != null)
				{
					$("#divMessage").css('color',"red").text("bind service provider failed"+data.e.message);
				}
		});
	
});

</script>
</html>
