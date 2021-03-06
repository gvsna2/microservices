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
		boolean status= utilitiesController.authorizationsCheck("Bank Accounts",menu);
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
<!-- Ladda buttons css -->
<link href="assets/plugins/ladda-buttons/css/ladda-themeless.min.css" rel="stylesheet" type="text/css" />
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
       <%=utilitiesController.getMenuNavigationForActivePage("company_bankdetails",menu) %>
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
            <h4 class="page-title">NexiiLabs Bank Details</h4>
            <ol class="breadcrumb">
              <li class="active">Bank Details </li>
              <li class="active"> NexiiLabs Bank Details </li>
            </ol>
          </div>
        </div>
        
        
        
        <div class="row">
          <div class="col-lg-12">
            
            <div class="card-box col-md-12">
                    <div class="col-sm-6">
                    <h4 class="m-t-0 header-title"><b>Create Account Details</b></h4>
                    <form class="form-horizontal" role="form"  data-parsley-validate novalidate>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Bank Name*</label>
                            <div class="col-sm-7">
                                <input type="text" required parsley-type="text" class="form-control" id="bankName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Account Holder Name*</label>
                            <div class="col-sm-7">
                                <input type="text" required parsley-type="text" class="form-control" id="accountHolderName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Account Number*</label>
                            <div class="col-sm-7">
                                <input type="text" required parsley-type="text" class="form-control" id="accountNumber">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Branch Name*</label>
                            <div class="col-sm-7">
                                <input type="text" required parsley-type="text" class="form-control" id="branchName">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="col-sm-4 control-label">IFSC Code*</label>
                            <div class="col-sm-7">
                                <input type="text" required parsley-type="text" class="form-control" id="ifscCode">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-4 col-sm-8">
                                <button type="button" class="ladda-button  btn btn-info" data-style="slide-left" id="createaccount" onclick="addBankAccount();">
                                    Create
                                </button>
                                <span id="bankErrorMessage"></span>
                            </div>
                        </div>
                    </form>
                    </div>
                    
                    <h4 class="m-t-0 header-title"><b>Bank Accounts</b></h4>
                    <div class="col-sm-6" id=banklist>
                        
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
  
  <!-- Edit Customer MODAL -->
  <div id="custom-modal-edit" class="modal-demo">
			    <button type="button" class="close" onclick="Custombox.close();">
			        <span>&times;</span><span class="sr-only">Close</span>
			    </button>
			    <h4 class="custom-modal-title">Edit Details</h4>
			    <div class="custom-modal-text">
			        <div class="panel-body">
                        <div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">Bank Name</label> 
                        <input type="text" class="form-control" placeholder="Bank Name" id="updatebankName"> 
                        </div> 
                        </div>
                        <div class="col-md-6"> 
                        <div class="form-group"> 
                        <label class="control-label pull-left">Account Holder Name</label> 
                        <input type="text" class="form-control" placeholder="Account Holder Name" id="updateaccountHolderName"> 
                        </div> 
                        </div> 
                        </div> 
 						<div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">Account Number</label> 
                        <input type="text" class="form-control" placeholder="Account Number" id="updateaccountNumber"> 
                        </div> 
                        </div> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">Branch Name</label>  
                        <input type="text" class="form-control" placeholder="Branch Name" id="updatebranchName"> 
                        </div> 
                        </div> 
                        </div>
                        
                        <div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">IFSC Code</label> 
                        <input type="text" class="form-control" placeholder="IFSC Code" id="updateifscCode"> 
                        </div> 
                        </div> 
                        </div>
 						<div class="row m-t-20">
                            <div class="col-md-12 text-right">
                                <button class="btn btn-primary waves-effect" id="updateAccount" >Save Changes</button>
                                <button class="btn btn-default waves-effect" onclick="Custombox.close();">Cancel</button>
                            </div>
                              <span id="updatebankErrorMessage"></span>
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
<!-- Ladda buttons  -->
 <script src="assets/plugins/ladda-buttons/js/spin.min.js"></script>
<script src="assets/plugins/ladda-buttons/js/ladda.min.js"></script>
<script src="assets/plugins/ladda-buttons/js/ladda.jquery.min.js"></script>
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
 <script type="text/javascript" src="js/urlgetter.js"></script>
 <script type="text/javascript" src="js/errorCodeConstants.js"></script>
  <script type="text/javascript" src="js/login.js"></script>
 <script type="text/javascript" src="js/bankAccount.js"></script>
 <script type="text/javascript" src="js/notifications.js"></script>
 <script>
 //logoutcheck();
getNotificationPanel();
 //getMenuBarNavigation();
 getBankAcountList();
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