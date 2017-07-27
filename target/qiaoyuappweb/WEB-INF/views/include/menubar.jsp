<html>
<head>
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
<script src="resources/script/jquery-2.1.4.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
		var ctx = "<%=request.getContextPath()%>";
</script>
</head>
<body>
<div><h3 class="text-primary">Qiao Yu Logistics App</h3></div>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
    <div>
        <ul class="nav navbar-nav">
            <!-- <li><a href="<%=request.getContextPath()%>/goToFileUpload"><h5 class="text-info">Order File Upload</h5></a></li> -->
           
           <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <h5 class="text-info">Order<b class="caret"></b></h5> 
                </a>
                <ul class="dropdown-menu">
                     <li><a href="<%=request.getContextPath()%>/goOrderMainPage"><h5 class="text-info">Order Sorting</h5></a></li>
                     <li><a href="<%=request.getContextPath()%>/goOrderSearch"><h5 class="text-info">Order Search</h5></a></li>
                     <li><a href="<%=request.getContextPath()%>/goOrderStatistics"><h5 class="text-info">Order Statistics</h5></a></li>
                </ul>
            </li>
           
           
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <h5 class="text-info">Case Test<b class="caret"></b></h5> 
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#"><h5 class="text-info">Case Test 1</h5></a></li>
                    <li><a href="#"><h5 class="text-info">Case Test 2</h5></a></li>
                    <li><a href="#"><h5 class="text-info">Case Test 3</h5></a></li>
                    <li class="divider"></li>
                    <li><a href="#"><h5 class="text-info">Case Test 4</h5></a></li>
                </ul>
            </li>
        </ul>
    </div>
    </div>
</nav>
</body>
</html>