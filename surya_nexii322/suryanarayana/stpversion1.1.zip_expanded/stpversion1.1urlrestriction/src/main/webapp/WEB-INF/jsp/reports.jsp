<%@page import="com.nexiilabs.stp.authentication.UtilitiesController"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" session="false"%>

<%! 
	UtilitiesController utilitiesController=null;
	List<String> menu=null;
%>

<%
	HttpSession userSession = request.getSession(false);
	System.out.print("before session check");
	if(userSession != null ){
		System.out.print("session is not null");
		utilitiesController=new UtilitiesController();
		menu=(List<String>)userSession.getAttribute("menuPermissions");
		if(menu!=null){
		System.out.print("menu-------"+menu);
		boolean status= utilitiesController.authorizationsCheck("Reports",menu);
		System.out.print(status+"..................status");
		if(status==true){
	    	System.out.print("page allowed");
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>STP</title>

<!-- DataTables -->
    <link href="assets/plugins/datatables/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/plugins/datatables/buttons.bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/plugins/datatables/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/plugins/datatables/fixedHeader.bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/plugins/datatables/responsive.bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/plugins/datatables/scroller.bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/plugins/datatables/dataTables.colVis.css" rel="stylesheet" type="text/css"/>
    
    <link href="assets/plugins/datatables/fixedColumns.dataTables.min.css" rel="stylesheet" type="text/css"/>


    <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/core.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/components.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/icons.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/pages.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/responsive.css" rel="stylesheet" type="text/css"/>

	<link href="assets/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">

    
<!-- HTML5 Shiv and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

<script src="assets/js/modernizr.min.js"></script>

</head>

<body class="fixed-left">

<!-- Begin page -->
<div id="wrapper"> 
  
  <!-- Top Bar Start -->
  <div class="topbar"> 
    
    <!-- LOGO -->
    <div class="topbar-left">
      <div class="text-center"> <a  class="logo">STP</a> </div>
    </div>
    
    <!-- Button mobile view to collapse sidebar menu -->
    <div class="navbar navbar-default" role="navigation">
      <div class="container">
        <div class="">
          <div class="pull-left">
            <button class="button-menu-mobile open-left waves-effect waves-light"> <i class="md md-menu"></i> </button>
            <span class="clearfix"></span> </div>
          <ul class="nav navbar-nav navbar-right pull-right" id="notification">
                                <li class="dropdown pull-right top-menu-item-xs">
                                    <a href="#" class="dropdown-toggle profile waves-effect waves-light" data-toggle="dropdown" aria-expanded="true"><img src="assets/images/users/avatar-1.jpg" alt="user-img" class="img-circle"> </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="changepw"><i class="ti-key m-r-10 text-custom"></i> Change Password</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#"  onclick="logout();"><i class="ti-power-off m-r-10 text-danger"></i> Logout</a></li>
                                    </ul>
                                </li>
                            </ul>
        </div>
        <!--/.nav-collapse --> 
      </div>
    </div>
  </div>
  <!-- Top Bar End --> 
  
  <!-- ========== Left Sidebar Start ========== -->
  
  <div class="left side-menu">
    <div class="sidebar-inner slimscrollleft"> 
      <!--- Divider -->
      <div id="sidebar-menu">
       <%=utilitiesController.getMenuNavigationForActivePage("reports",menu) 
       %>
        <div class="clearfix"></div>
      </div>
      <div class="clearfix"></div>
    </div>
  </div>
  <!-- Left Sidebar End --> 
  
  <!-- ============================================================== --> 
  <!-- Start right Content here --> 
  <!-- ============================================================== -->
  <div class="content-page"> 
    <!-- Start content -->
    <div class="content">
      <div class="container"> 
        
        <!-- Page-Title -->
        <div class="row">
          <div class="col-sm-12">
            <h4 class="page-title">Reports</h4>
            <ol class="breadcrumb">
              <li class="active">Contacts &amp; Followups </li>
            </ol>
          </div>
        </div>
        
        
        <div class="row">
        <div class="col-md-12" style="margin-top:20px;">
        <div class="col-md-8">
                  <div class="form-group row">
                        <label class="col-md-2 pull-left text-left p-t-10">Select User</label>
                        <div class="col-md-6 pull-left">
                            <select class="form-control" id="propsectUser" onchange="selectedReports();">
                            </select>
                        </div>
                    </div>
                </div>
        </div>
        </div>
        					<div class="row">
        				<div class="col-md-12" style="margin-bottom:20px; margin-top:20px; border:dashed 1px #0f60ab;">
                            <h3 class="text-custom" style="margin-top:-15px;"><span class="p-l-r-10" style="background-color:#ebeff2;">Contacts Reports</span></h3>
							<div class="col-lg-2">
								<div class="card-box">
									<div class="bar-widget"><a  id="getTodayContactList" style="cursor: pointer;">
										<div class="table-box">
											<div class="table-detail text-center">
											   <h4 class="m-t-0 m-b-5"><b class=todayContactCount></b></h4>
											   <p class="text-muted m-b-0 m-t-0" id="todayCount">Today</p>
											</div>
										</div></a>
									</div>
								</div>
							</div>
							<script type="text/javascript">
							function showDiv(distab){
							document.getElementById(distab).style.display = 'block';
							}
							</script>
                            <div class="col-lg-2">
								<div class="card-box">
									<div class="bar-widget"><a id="getWeeklyContactList" style="cursor: pointer;">
										<div class="table-box">
											<div class="table-detail text-center">
											   <h4 class="m-t-0 m-b-5"><b class="weeklyContactCount"></b></h4>
											   <p class="text-muted m-b-0 m-t-0" id="weeklyCount">Weekly</p>
											</div>
										</div></a>
									</div>
								</div>
							</div>

                            <div class="col-lg-2">
								<div class="card-box">
									<div class="bar-widget"><a  id="getMonthlyContactList" style="cursor: pointer;">
										<div class="table-box">
											<div class="table-detail text-center">
											   <h4 class="m-t-0 m-b-5"><b class="monthlyContactCount"></b></h4>
											   <p class="text-muted m-b-0 m-t-0" id="monthlyCount">Monthly</p>
											</div>
										</div></a>
									</div>
								</div>
							</div>
                            
                            <div class="col-lg-6">
								<div class="card-box">
									<div class="bar-widget">
										<div class="table-box">
										<a  id="getFilteredDateContactList" style="cursor: pointer;">
											<div class="table-detail text-left col-md-4">
											   <h4 class="m-t-0 m-b-5"><b class="customContactCount"></b></h4>
											   <p class="text-muted m-b-0 m-t-0" id="customCount">Custom Date</p>
											</div>
											</a>
                                            <div class="table-detail text-right col-md-8">
                                           <div class="form-group m-b-0">
                                                <div class="input-daterange input-group col-md-12" id="date-range" onchange="getFilterDateContactCount();">
                                                	<span class="input-group-addon bg-custom b-0 text-white">Start</span>
                                                    <input type="text" class="form-control p-l-0 p-r-0" name="start" id="startDate" onchange="getFilterDateContactCount();" />
                                                    <span class="input-group-addon bg-custom b-0 text-white">End</span>
                                                    <input type="text" class="form-control p-l-0 p-r-0" name="end" id="endDate"  onchange="getFilterDateContactCount();"/>
                                                </div>
                                        </div>
                                            </div>
										</div>
									</div>
								</div>
							</div>
                            
            <div class="col-sm-12" id="distab" style="display:none;">
            <div class="card-box table-responsive">
              <table id="datatable-responsive" class="table table-striped table-bordered">
                <thead>
                  <tr>
                    <th>Prospect Name</th>
                    <th>Prospect Company</th>
                    <th>Prospect Email</th>
                    <th>Prospect Mobile</th>
                    <th>Created On</th>
                     <th>Created By</th>
                  </tr>
                </thead>
                <tbody id="prospectsList">
                </tbody>
              </table>
            </div>
          </div>
                            
                      </div>
						</div>
                        
                        
                        
        
          <div class="row">
        					<div class="col-md-12" style="margin-bottom:20px; margin-top:20px; border:dashed 1px #009933;">
                            <h3 class="text-success" style="margin-top:-15px;"><span class="p-l-r-10" style="background-color:#ebeff2;">Follow Up Reports</span></h3>
                            
                            
                            <div class="col-lg-3">
								<div class="card-box">
									<div class="bar-widget">
										<div class="table-box"><a id="todayUpdatedFollowupReportsList" style="cursor: pointer;">
											<div class="table-detail text-center pull-left">
											   <h4 class="m-t-04 text-success"><b class="todayUpdatedCount"></b></h4>
											   <p class="text-success m-b-0 m-t-0" id="todayContacted">Contacted</p>
											</div></a><a id="todayNotUpdatedFollowupReports" style="cursor: pointer;">
                                            <div class="table-detail text-center pull-right">
                                                <h4 class="m-t-04 text-custom"><b class="todayNotUpdatedCount"></b></h4>
											   <p class="text-custom m-b-0 m-t-0" id="todayNotContacted">Non Contacted</p>
                                            </div></a>
											<div class="table-detail text-center col-lg-12"><hr>
                                                <h4 class="m-t-0 m-b-5"><b>Today</b></h4>
                                            </div>
										</div>
									</div>
								</div>
							</div>
                            
                            
							<div class="col-lg-3">
								<div class="card-box">
									<div class="bar-widget">
										<div class="table-box"><a  id="weeklyUpdatedFollowupReports" style="cursor: pointer;">
											<div class="table-detail text-center pull-left">
													<h4 class="m-t-04 text-success"><b class="weeklyUpdatedCount"></b></h4>
											   <p class="text-success m-b-0 m-t-0" id="weeklyContacted">Contacted</p>
											</div></a><a  id="weeklyNotUpdatedFollowupReportsList" style="cursor: pointer;">
                                            <div class="table-detail text-center pull-right">
                                                <h4 class="m-t-04 text-custom"><b class="weeklyNotUpdatedCount"></b></h4>
											   <p class="text-custom m-b-0 m-t-0" id="weeklyNotContacted">Non Contacted</p>
                                            </div></a>
											<div class="table-detail text-center col-lg-12"><hr>
                                                <h4 class="m-t-0 m-b-5"><b>Weekly</b></h4>
                                            </div>
										</div>
									</div>
								</div>
							</div>
							<script type="text/javascript">
							function showDiv(distabl){
							document.getElementById(distabl).style.display = 'block';
							}
							</script>
                            <div class="col-lg-3">
								<div class="card-box">
									<div class="bar-widget">
										<div class="table-box"><a id="monthlyUpdatedFollowupReportsList" style="cursor: pointer;">
											<div class="table-detail text-center pull-left">
													<h4 class="m-t-04 text-success"><b class="monthlyUpdatedCount"></b></h4>
											   <p class="text-success m-b-0 m-t-0" id="monthlyContacted">Contacted</p>
											</div></a><a  id="monthlyNotUpdatedFollowupReportsList" style="cursor: pointer;">
                                            <div class="table-detail text-center pull-right">
                                                <h4 class="m-t-04 text-custom"><b class="monthlyNotUpdatedCount"></b></h4>
											   <p class="text-custom m-b-0 m-t-0" id="monthlyNotContacted">Non Contacted</p>
                                            </div></a>
											<div class="table-detail text-center col-lg-12"><hr>
                                                <h4 class="m-t-0 m-b-5"><b>Monthly</b></h4>
                                            </div>
										</div></a>
									</div>
								</div>
							</div>
                            <div class="col-lg-3">
								<div class="card-box">
									<div class="bar-widget">
										<div class="table-box"><a id="dateRangeUpdatedFollowupReportsList" style="cursor: pointer;">
											<div class="table-detail text-center pull-left">
													<h4 class="m-t-04 text-success"><b class="dateRangeUpdatedCount"></b></h4>
											   <p class="text-success m-b-0 m-t-0" id="dateRangeContacted">Contacted</p>
											</div></a><a id="dateRangeNotUpdatedFollowupReportsList" style="cursor: pointer;">
                                            <div class="table-detail text-center pull-right">
                                                <h4 class="m-t-04 text-custom"><b class="dateRangeNotUpdatedCount"></b></h4>
											   <p class="text-custom m-b-0 m-t-0" id="dateRangeNotContacted">Non Contacted</p>
                                            </div></a>
											<div class="table-detail text-center col-lg-12 p-l-0 p-r-0">
                                                <div class="form-group m-b-0 m-t-30">
                                                <div class="input-daterange input-group col-md-12" id="date-range1" >
                                                    <input type="text" class="form-control p-l-0 p-r-0" id="startDate1" onchange="dateRangeFollowupCounts();" name="start" placeholder="Start" />
                                                    <span class="input-group-addon bg-success b-0 text-white">To</span>
                                                    <input type="text" class="form-control p-l-0 p-r-0" id="endDate1" onchange="dateRangeFollowupCounts();" name="end" placeholder="End" />
                                                </div>
                                        </div>
                                            </div>
										</div></a>
									</div>
								</div>
							</div>
                            
            <div class="col-sm-12" id="distabl" style="display:none;">
            <div class="card-box table-responsive">
              <table id="datatable1-responsive" class="table table-striped table-bordered">
                <thead>
                  <tr>
                    <th>Prospect Name</th>
                    <th>Prospect Company</th>
                    <th>Prospect Email</th>
                    <th>Prospect Mobile</th>
                    <th>Created On</th>
                    <th>Stage</th>
                    <th>Meeting Type</th>
                    <th>Next Follow Up</th>
                    <th>Created By</th>
                    <th>History</th>
                  </tr>
                </thead>
                <tbody id="followupReportsList">
                 <!--  <tr class="gradeX">
                    <td>Name One</td>
                    <td>Company One</td>
                    <td>someone@company.com</td>
                    <td>8096237037</td>
                    <td>14-03-2018</td>
                    <td>First Stage</td>
                    <td>F2F</td>
                    <td>16-03-2018</td>
                    <td><a href="#custom-modal-history" class="btn btn-xs btn-inverse waves-effect waves-light m-l-10" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">History</a></td>
                  </tr> -->
                </tbody>
              </table>
            </div>
          </div>
                            
                      </div>
						</div>              
                        
                        
                        
        
        
        <!-- end Panel --> 
        
      </div>
      <!-- container --> 
      
    </div>
    <!-- content -->
    
    <footer class="footer"> © 2017. All rights reserved. </footer>
  </div>
  <!-- ============================================================== --> 
  <!-- End Right content here --> 
  <!-- ============================================================== --> 
  


<!-- Prospects History MODAL -->
 <div id="custom-modal-history" class="modal-demo">
    <button type="button" class="close" onclick="Custombox.close();"> <span>&times;</span><span class="sr-only">Close</span> </button>
    <h4 class="custom-modal-title">Prospects History</h4>
    <div class="custom-modal-text">
      <div class="panel-body">
        <div class="col-md-12">
          
          <div class="row" id="history">
          		<!-- <div class="col-md-12 text-left"><h4 class="text-success text-uppercase font-bold">Prospect Info</h4></div>
                <div class="col-md-12 text-left">
                    <p>Prospect Name: <span class="text-primary">uySDjdusduvygsdv</span></p>
                    <p>Prospect Email: <span class="text-primary">someone@company.com</span></p>
                    <p>Prospect Company: <span class="text-primary">Name Of Company</span></p>
                </div>
                <div class="col-md-12 text-left"><h4 class="text-success text-uppercase font-bold">Meeting History #1</h4>
                	<p>Stage: <span class="text-primary">First Level</span></p>
                    <p>Meeting Type: <span class="text-primary">Face To Face</span></p>
                    <p>Contacted On: <span class="text-primary">08-03-2018</span></p>
                    <p>Next Followup On: <span class="text-primary">10-03-2018</span></p>
                    <p>Comments: <span class="text-primary">h8wod8g8owq  9yhrwg 98ygry98eryg8 y98erg y9a8g.</span></p>
                </div>
                <div class="col-md-12 text-left"><h4 class="text-success text-uppercase font-bold">Meeting History #2</h4>
                	<p>Stage: <span class="text-primary">Next Level</span></p>
                    <p>Meeting Type: <span class="text-primary">Face To Face</span></p>
                    <p>Contacted On: <span class="text-primary">12-03-2018</span></p>
                    <p>Next Followup On: <span class="text-primary">14-03-2018</span></p>
                    <p>Comments: <span class="text-primary">h8wod8g8owq  9yhrwg 98ygry98eryg8 y98erg y9a8g.</span></p>
                </div> -->
              </div>
          
        <div class="row m-t-20">
          <div class="col-md-6 text-left">
            <button class="btn btn-default waves-effect" onclick="Custombox.close();">Cancel</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end Modal -->




</div>
<!-- END wrapper --> 

<script>
    var resizefunc = [];
</script>

<!-- jQuery  -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/detect.js"></script>
<script src="assets/js/fastclick.js"></script>
<script src="assets/js/jquery.slimscroll.js"></script>
<script src="assets/js/jquery.blockUI.js"></script>
<script src="assets/js/waves.js"></script>
<script src="assets/js/wow.min.js"></script>
<script src="assets/js/jquery.nicescroll.js"></script>
<script src="assets/js/jquery.scrollTo.min.js"></script>

<script src="assets/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="assets/plugins/datatables/dataTables.bootstrap.js"></script>

<script src="assets/plugins/datatables/dataTables.responsive.min.js"></script>
<script src="assets/plugins/datatables/responsive.bootstrap.min.js"></script>

<script src="assets/pages/datatables.init.js"></script>


<script src="assets/js/jquery.core.js"></script>
<script src="assets/js/jquery.app.js"></script>
<script type="text/javascript" src="js/urlgetter.js"></script>
<script type="text/javascript" src="js/errorCodeConstants.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/notifications.js"></script>
<script type="text/javascript" src="js/prospect.js"></script>
<script type="text/javascript" src="js/prospectReports.js"></script>
<script type="text/javascript" src="js/followupReports.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#datatable').dataTable();
        $('#datatable1').dataTable();
    });
</script>

<!--<script>
    function showtable(chooseone) {
         $('.boxtables').each(function(index) {
              if ($(this).attr("id") == chooseone) {
                   $(this).show(200);
              }
              else {
                   $(this).hide(600);
              }
         });
    }
</script>-->

<!-- Date-Picker --> 
<script src="assets/plugins/timepicker/bootstrap-timepicker.js"></script> 
<script src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script> 
<script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script> 
<script src="assets/plugins/clockpicker/js/bootstrap-clockpicker.min.js"></script> 
<script src="assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script> 
<script src="assets/pages/jquery.form-pickers.init.js"></script> 

<!-- Modal-Effect --> 
<script src="assets/plugins/custombox/js/custombox.min.js"></script> 
<script src="assets/plugins/custombox/js/legacy.min.js"></script>
<link href="assets/plugins/custombox/css/custombox.css" rel="stylesheet">
<script type="text/javascript">
	getNotificationPanel();
	usersListForProspects();
	window.onload = function() {
		setTimeout(function(){
			getContactCounts();
			followupCounts();
		},1000);
	}
	function selectedReports(){
		getContactCounts();
		followupCounts();
		$("#distab").css("display","none");
		$("#distabl").css("display","none");
		//document.getElementById(distabl).style.display = 'block';
		
	}
	
	
</script>
</body>
</html>
<%
	}else {
		System.out.print("permission denied");
%>
<jsp:forward page="403.jsp"></jsp:forward>   
<%
	} 
%>
<%
}else {
	System.out.print("session is null");
%>
<jsp:forward page="login.jsp"></jsp:forward>   
<%
} 
%>
<%
}else {
	System.out.print("session is null");
%>
<jsp:forward page="login.jsp"></jsp:forward>   
<%
} 
%>