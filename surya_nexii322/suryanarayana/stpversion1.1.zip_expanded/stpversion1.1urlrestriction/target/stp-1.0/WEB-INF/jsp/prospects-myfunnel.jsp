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
		boolean status= utilitiesController.authorizationsCheck("Prospects",menu);
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
<!-- Plugins css-->
<link href="assets/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput.css" rel="stylesheet" />
<link href="assets/plugins/switchery/css/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/bootstrap-touchspin/css/jquery.bootstrap-touchspin.min.css" rel="stylesheet" />
<!--Form Wizard-->
<link rel="stylesheet" type="text/css" href="assets/plugins/jquery.steps/css/jquery.steps.css" />

<!-- Plugin Css-->
<link rel="stylesheet" href="assets/plugins/magnific-popup/css/magnific-popup.css" />
<link rel="stylesheet" href="assets/plugins/jquery-datatables-editable/datatables.css" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/core.css" rel="stylesheet" type="text/css" />
<link href="assets/css/components.css" rel="stylesheet" type="text/css" />
<link href="assets/css/icons.css" rel="stylesheet" type="text/css" />
<link href="assets/css/pages.css" rel="stylesheet" type="text/css" />
<link href="assets/css/responsive.css" rel="stylesheet" type="text/css" />
<link href="assets/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">
<link href="assets/plugins/bootstrap-datetimepicker/jquery.datetimepicker.css" rel="stylesheet">
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
      <div class="text-center"> <a class="logo">STP</a> </div>
    </div>
    
    <!-- Button mobile view to collapse sidebar menu -->
    <div class="navbar navbar-default" role="navigation">
      <div class="container">
        <div class="">
          <div class="pull-left">
            <button class="button-menu-mobile open-left waves-effect waves-light"> <i class="md md-menu"></i> </button>
            <span class="clearfix"></span> </div>
          <ul class="nav navbar-nav navbar-right pull-right" id="notification">
                                <li id="logout" class="dropdown top-menu-item-xs">
                                    <a href="#" class="dropdown-toggle profile waves-effect waves-light" data-toggle="dropdown" aria-expanded="true"><img src="assets/images/users/avatar-1.jpg" alt="user-img" class="img-circle"> </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="changepw"><i class="ti-key m-r-10 text-custom"></i> Change Password</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#" onclick="logout();"><i class="ti-power-off m-r-10 text-danger"></i> Logout</a></li>
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
        <%=utilitiesController.getMenuNavigationForActivePage("Prospects",menu) %>
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
            <h4 class="page-title">My Funnel</h4>
            <ol class="breadcrumb">
              <li class="active">Prospects </li>
              <li class="active"> My Funnel </li>
            </ol>
          </div>
        </div>
         <div class="row">
        <div class="col-md-12" style="margin-top:20px;">
        <div class="col-md-8">
                  <div class="form-group row">
                        <label class="col-md-2 pull-left text-left p-t-10">Select User</label>
                        <div class="col-md-6 pull-left">
                            <select class="form-control" id="propsectUser" onchange="onChangeForDropdown();">
                            </select>
                        </div>
                    </div>
                </div>
        </div>
        </div>
        
        <div class="row">
                            <div class="tabs-vertical-env"> 
                                <ul class="nav tabs-vertical" style="border-left:1px solid #CCC; border-top:1px solid #CCC; border-bottom:1px solid #CCC;">
                                    <li class="active tab">
                                        <a href="#prospects" data-toggle="tab" aria-expanded="false"> 
                                            <span class="visible-xs"><i class="fa fa-home"></i></span> 
                                            <span class="hidden-xs">Prospects</span> 
                                        </a> 
                                    </li> 
                                    <li class="tab"> 
                                        <a href="#active-prospects" data-toggle="tab" aria-expanded="false"> 
                                            <span class="visible-xs"><i class="fa fa-user"></i></span> 
                                            <span class="hidden-xs">Active Prospects</span> 
                                        </a> 
                                    </li> 
                                     <li class="tab"> 
                                        <a href="#closed-prospects" data-toggle="tab" aria-expanded="false"> 
                                            <span class="visible-xs"><i class="fa fa-user"></i></span> 
                                            <span class="hidden-xs">Closed Prospects</span> 
                                        </a> 
                                    </li> 
                                </ul> 
                                <div class="tab-content" style="width:100%;"> 
                                    <div class="tab-pane active" id="prospects"> 
                                    </div> 
                                    <div class="tab-pane" id="active-prospects">
                                    </div> 
                                     <div class="tab-pane" id="closed-prospects">
                                    </div> 
                                </div> 
                            </div> 
                        </div>
        
        
        <!-- end Panel --> 
        
      </div>
      <!-- container --> 
      
    </div>
    <!-- content -->
    
    <footer class="footer"> Â© 2017. All rights reserved. </footer>
  </div>
  <!-- ============================================================== --> 
  <!-- End Right content here --> 
  <!-- ============================================================== --> 

  
  <!-- Prospects Updates MODAL -->
  <div id="custom-modal-update" class="modal-demo">
    <button type="button" class="close" onclick="Custombox.close();"> <span>&times;</span><span class="sr-only">Close</span> </button>
    <h4 class="custom-modal-title">Prospects Update</h4>
    <div class="custom-modal-text">
      <div class="panel-body">
        <div class="col-md-12">
          
          <div class="row">
          		<div class="col-md-12 text-left"><h4 class="text-success text-uppercase font-bold">Prospect Info</h4></div>
               <div class="col-md-4">
                  <div class="form-group">
                    <label class="control-label pull-left">First Name</label>
                    <input id="updateprospectfName" type="text" class="form-control" placeholder="Name One" required>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group">
                    <label class="control-label pull-left">Last Name</label>
                    <input id="updateprospectlName" type="text" class="form-control" placeholder="Name two" required>
                  </div>
                </div>
                
                <div class="col-md-4">
                  <div class="form-group">
                    <label class="control-label pull-left">Email</label>
                    <input type="email" id="email" class="form-control" placeholder="someone@company.com" required>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group input-group">
                    <label class="control-label pull-left">Company</label>
                    <input id="companyName" type="text" class="form-control" placeholder="Company One">
                    <!--<select class="form-control">
                        <option>Select</option>
                        <option>Company One</option>
                        <option>Company Two</option>
		            </select>-->
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group">
                    <label class="control-label pull-left">Phone Number</label>
                    <input id="updatephnumber" type="text" class="form-control" placeholder="9876543210" required>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group">
                    <label class="control-label pull-left">Alternate Number</label>
                    <input id="updatealternumber" type="text" class="form-control" placeholder="9876543210">
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group">
                    <label class="control-label pull-left">Designation</label>
                    <input id="updatedesignation" type="text" class="form-control" placeholder="designation" required>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group">
                    <label class="control-label pull-left">Additional Information</label>
                    <textarea rows="3" class="form-control" id="updateinformation"></textarea>
                  </div>
                </div>
                <div class="col-md-4">
                  <div class="form-group">
                   <label class="control-label pull-left">Address</label>
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane1" maxlength="75" id="updateaddlane1">
                      	<input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane2" maxlength="75" id="updateaddlane2">
                   </div>
                </div>
                <div class="col-md-12 text-left"><h4 class="text-success text-uppercase font-bold">Meeting Status</h4></div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">Stage</label>
                    <select class="form-control" id="stageName" onchange="stageOnchange();">
                        <option>Select Stage</option>
                      <!--   <option>First Level</option>
                        <option>Next Level</option>
                        <option>Final</option>
                        <option>Presentation</option>
                        <option>Demo</option>
                        <option>NDA Submitted</option>
                        <option>Aggreement In Progress</option>
                        <option>Closed</option>--> 
		            </select>
                  </div>
                </div>
                
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">Meeting Type</label>
                    <select class="form-control" id="meetingtypeName">
                        <option>Select Type</option>
                     <!-- <option>Face To Face</option>
                        <option>Phone</option>
                        <option>Web</option>
                        <option>eMail</option>-->  
		            </select>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">Contacted On</label>
                    <input type="text" class="form-control" value="" id="datetimepicker"/>
                  </div>
                </div>
           
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left" id="nextFollowupLabel">Next Followup On</label>
                     <input type="text" class="form-control" value="" id="datetimepicker1"/>
                  </div>
                </div>
                <div class="col-md-12">
                  <div class="form-group">
                    <label class="control-label pull-left">Comments</label>
                    <textarea rows="3" class="form-control" id="comments"></textarea>
                  </div>
                </div>
              </div>
          
        <div class="row m-t-20">
          <div class="col-md-6 text-left">
            <button class="btn btn-primary waves-effect" id="updatebtn">Update</button>
            <button class="btn btn-default waves-effect" onclick="Custombox.close();">Cancel</button>
          </div>
          <span id="prospectUpdateErrorMessage"></span>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end Modal -->
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
<script src="assets/js/jquery.core.js"></script> 
<script src="assets/js/jquery.app.js"></script> 
<script src="assets/pages/jquery.widgets.js"></script>
<!-- Page Specific JS Libraries --> 
<script src="assets/plugins/dropzone/dropzone.js"></script> 

<!--Form Wizard--> 
<script src="assets/plugins/jquery.steps/js/jquery.steps.min.js" type="text/javascript"></script> 
<script type="text/javascript" src="assets/plugins/jquery-validation/js/jquery.validate.min.js"></script> 

<!--wizard initialization--> 
<script src="assets/pages/jquery.wizard-init.js" type="text/javascript"></script> 

<!-- Date-Picker --> 
<script src="assets/plugins/timepicker/bootstrap-timepicker.js"></script> 
<script src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script> 
<script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script> 
<script src="assets/plugins/clockpicker/js/bootstrap-clockpicker.min.js"></script> 
<script src="assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script> 
<script src="assets/pages/jquery.form-pickers.init.js"></script> 




<!-- Examples --> 
<script src="assets/plugins/magnific-popup/js/jquery.magnific-popup.min.js"></script> 
<script src="assets/plugins/jquery-datatables-editable/jquery.dataTables.js"></script> 
<script src="assets/plugins/datatables/dataTables.bootstrap.js"></script> 
<script src="assets/plugins/tiny-editable/mindmup-editabletable.js"></script> 
<script src="assets/plugins/tiny-editable/numeric-input-example.js"></script> 
<script src="assets/pages/datatables.editable.init.js"></script> 
<script>
$('#mainTable').editableTableWidget().numericInputExample().find('td:first').focus();
</script> 

<!-- Modal-Effect --> 
<script src="assets/plugins/custombox/js/custombox.min.js"></script> 
<script src="assets/plugins/custombox/js/legacy.min.js"></script>
<link href="assets/plugins/custombox/css/custombox.css" rel="stylesheet">

<!-- Multi-Select -->
<link href="assets/plugins/multiselect/css/multi-select.css"  rel="stylesheet" type="text/css" />
<link href="assets/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
<link href="assets/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" />
<script type="text/javascript" src="assets/plugins/multiselect/js/jquery.multi-select.js"></script> 
<script type="text/javascript" src="assets/plugins/jquery-quicksearch/jquery.quicksearch.js"></script> 
<script src="assets/plugins/select2/js/select2.min.js" type="text/javascript"></script> 
<script src="assets/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script> 
<script src="assets/plugins/bootstrap-filestyle/js/bootstrap-filestyle.min.js" type="text/javascript"></script> 
<script type="text/javascript" src="assets/pages/jquery.form-advanced.init.js"></script>

<script>
$(":file").filestyle({buttonText: "Upload Files"});
</script>

<script src="assets/plugins/bootstrap-datetimepicker/jquery.datetimepicker.js"></script>
<script>
$('#datetimepicker').datetimepicker({ 
	maxDate: new Date() 
	});
$('#datetimepicker, #datetimepicker1').datetimepicker({
dayOfWeekStart : 1,
lang:'en',
//disabledDates:['1986/01/08','1986/01/09','1986/01/10'],
//startDate:	'1986/01/05'
});
//var date = $('#datetimepicker').datepicker({ dateFormat: 'yyyy-mm-dd hh:MM:ss' }).val();
//$('#datetimepicker').datetimepicker({value:'2015/04/15 05:03',step:10});
</script>
<script type="text/javascript" src="js/urlgetter.js"></script>
<script type="text/javascript" src="js/errorCodeConstants.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/notifications.js"></script>
<script type="text/javascript" src="js/prospect.js"></script>
<script>
function onChangeForDropdown(){
	myfunnelProspectsList();
	myFunnelAllFollowupList();
	myfunnelClosedProspectsList();
}
   getNotificationPanel();
   usersListForProspects();
   stageList();
   setTimeout(function(){
	   myfunnelProspectsList();
	   myFunnelAllFollowupList();
	   myfunnelClosedProspectsList();
	},1000);
   meetingTypesList();
   customersList();
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