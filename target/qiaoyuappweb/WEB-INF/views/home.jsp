<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<script src="resources/script/jquery-2.1.4.js"></script>
 <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
 <script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
		var ctx = "<%=request.getContextPath()%>";
</script>
<html>
<head>
	<title>Qiao Yu Logistics App</title>
	<style type="text/css">
	.row {
   	 margin-right: 350px;
   	 margin-left: 350px;
	}
	</style>
</head>
<body>
<div class="container text-center">
<div col-md-12 col-lg-12><h4 class="text-danger">${message}</h4></div>
<h1 class="text-primary text-center">
	Qiao Yu Logistics App  
</h1>

<div class="col-md-12 col-lg-12" style="height:40px;"></div>
<form id="loginForm" action="" method="post">
<div class="row">
	<div class="col-md-12 col-lg-12">
		<table class="table table-bordered table-hover" id="tab_questionsList">
			<tbody>
				<tr>
					
					<td class="col-md-12 col-lg-12">
						<input id ="userName" name ="userName" type="text" placeholder="User Name" class="form-control" />
					</td>
				</tr>
				<tr>
					<td class="col-md-12 col-lg-12">
						<input id="password" name ="password" type="password" maxlength="10" placeholder="Password" class="form-control" />
					</td>
				</tr>
				<tr>
					<td class="col-md-12 col-lg-12">
						<input id="login" type="submit" value="Login" class="btn btn-primary btn-sm btn-lg btn-block">
					</td>
				</tr>
			</tbody>
		</table>
		</div>
</div>
</form>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#login").click(function(){
		var userName=$("#userName").val();
		var password=$("#password").val();
		if(userName==='' || password==='')
			{
				alert("user name or password can not be null");
				return false;
			}
	 $("#loginForm").attr('action', ctx + '/loginApp');
   	 $("#loginForm").submit();
	});
});
</script>
</html>
