<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Order Handling Main Page</title>
	<jsp:include page="include/menubar.jsp" flush="true" />
	<style type="text/css">
	.row{
		margin-right: 250px;
   		margin-left: 250px;
	}
	</style>
</head>
<body>
<div id="divMessage"></div>
<div class="col-md-12 col-lg-12" style="height:30px;"></div>
<h4 class="text-primary text-center">Order Search</h4>
<div class="col-md-12 col-lg-12" style="height:10px;"></div>
<div class="container text-center">

<div class="row">
	<div class="col-md-12 col-lg-12">
	<div class="col-md-3 col-lg-3"><h5>Service Provider:</h5></div>
		<div class="col-md-3 col-lg-3"><select id="deliveryProvider" >
			<option value="">Select...</option>
    		<option value="Sun Feng">Sun Feng</option>
    		<option value="UPS">UPS</option>
    		<option value="Fedex">Fedex</option>
    		<option value="DHL">DHL</option>
    		<option value="BSI">BSI</option>
    		<option value="BSI">EMS</option>
    	</select></div>
		<div class="col-md-3 col-lg-3"><input id ="clientFirstName" type="text" placeholder="First Name" class="form-control" /></div>
		<div class="col-md-3 col-lg-3"><input id ="clientLastName" type="text" placeholder="Last Name" class="form-control" /></div>
	</div>
	<div class="col-md-12 col-lg-12" style="height:30px;"></div>
	<div class="col-md-12 col-lg-12">
		<div class="col-md-12 col-lg-12"><input id="searchOrder" type="submit" value="Search" class="btn btn-primary btn-md pull-right"></div>
	</div>
</div>
<div class="col-md-12 col-lg-12" style="height:60px;"></div>
<div id="operatorDiv" class="col-md-12 col-lg-12">
		<table class="table table-bordered table-hover" id="tab_questionsList">
		<thead>
				<tr>
					<th class="text-center col-md-1 col-lg-1">
						<h5>Id</h5>
					</th>
					<th class="text-center col-md-2 col-lg-2">
						<h5>Name</h5>
					</th>
					<th class="text-center col-md-5 col-lg-5">
						<h5>Shipping Address</h5>
					</th>
					<th class="text-center col-md-3 col-lg-3">
						<h5>Delivery Provider</h5>
					</th>
					<th class="text-center col-md-1 col-lg-1">
						<h5>Status</h5>
					</th>
				</tr>
		</thead>
			<tbody id="orderContent">
			</tbody>
		</table>
		</div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#searchOrder").click(function(){
		$("#divMessage").text('');
		var clientFirstName = $("#clientFirstName").val();
		var clientLastName = $("#clientLastName").val();
		var deliveryProvider = $("#deliveryProvider").val();
		
		if(clientFirstName==='' && clientLastName==='')  
			{
			if(deliveryProvider ==='')
				{
				alert("please input delivery provider");
				return false;
				}
			
			}
		else
			{
			if(!(clientFirstName!=='' && clientLastName!==''))
				{
				alert("please input client name");
				return false;
		
				}
			}
		
		 $.ajax({ 
 			method: 'GET',
				url: ctx + '/orderSearchMain', 
				data: {clientFirstName:clientFirstName,clientLastName:clientLastName,deliveryProvider:deliveryProvider},
				dataType: 'json'
 		})
 		.done(function(data)
 		{
 			if(data.length==0)
 				{
 					$("#divMessage").css("color","red").text("There is no result for the criteria you input");
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
		content += '<tr><td class="text-center"><a id="orderMantain'+data[j].id+'">'+data[j].id+'</a></td> <td class="text-center">'+data[j].client.firstName+' '+data[j].client.lastName+'</td><td class="text-center">'+data[j].client.shippingAddress+'</td><td class="text-center">'+data[j].serviceProvider+'</td><td class="text-center">'+data[j].status+'</td></tr>';   
	}
	$("#orderContent").html(content);
}

$("#operatorDiv table").on("click", "a[id*='orderMantain']", function(){
	var orderId = this.id.substr(12);
	window.location.href=ctx + "/orderMantain?id="+orderId; 
});

</script>
</html>
