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
		boolean status= utilitiesController.authorizationsCheck("Resources",menu);
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

<link href="assets/plugins/dropzone/dropzone.css" rel="stylesheet" type="text/css" />
<!--Form Wizard-->
<link rel="stylesheet" type="text/css" href="assets/plugins/jquery.steps/css/jquery.steps.css" />

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
        <script src="https://oss.maxcdn.com/libs/reskpond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
<style>
.wizard > .content > .body label{
	margin-top:0px;
	}
</style>
<script src="assets/js/modernizr.min.js"></script>
</head>

<body class="fixed-left">

		<!-- Begin page -->
		<div id="wrapper">

            <!-- Top Bar Start -->
            <div class="topbar">

                <!-- LOGO -->
                <div class="topbar-left">
                    <div class="text-center">
                        <a  class="logo">STP</a>
                    </div>
                </div>

                <!-- Button mobile view to collapse sidebar menu -->
                <div class="navbar navbar-default" role="navigation">
                    <div class="container">
                        <div class="">
                            <div class="pull-left">
                                <button class="button-menu-mobile open-left waves-effect waves-light">
                                    <i class="md md-menu"></i>
                                </button>
                                <span class="clearfix"></span>
                            </div>
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
                       <%=utilitiesController.getMenuNavigationForActivePage("add_resources",menu) %>
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
								<h4 class="page-title">Add Resources</h4>
                                <ol class="breadcrumb">
                                  <li> <a href="#">Resources</a> </li>
                                  <li class="active"> Add Resources </li>
                                </ol>
							</div>
						</div>


                        

                  <div class="row">
                            <div class="col-md-12">
                            	<div class="card-box">
									<!-- <form id="basic-form" action="#"> -->
                                        <div id="basic-form-add-res">
                                            <h3>Basic Info</h3>
                                            <section>
                                             <div class="row"> 
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Employee First Name</label> 
                                            <input type="text" class="form-control" placeholder="Name Of Employ"  id="firstName"> 
                                            </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Employee Last Name</label> 
                                            <input type="text" class="form-control" placeholder="Name Of Employ" id="lastName"> 
                                            </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                            <div class="form-group"> 
                                            <label class="control-label pull-left">Email ID</label> 
                                            <input type="text" class="form-control" placeholder="Ex: appaji.godugula@nexiilabs.com" id="email">
                                            </div> 
                                            </div>
                                            </div> 
                                            <div class="row"> 
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Contact Number</label> 
                                            <input type="text" class="form-control" placeholder="Ex: 8096237037" id="contact"> 
                                            </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Skill Set</label> 
                                            <input type="text" class="form-control" placeholder="Ex: Skills" id="skillSet">
                                            </div> 
                                            </div>
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Experience</label> 
                                            <input type="text" class="form-control" placeholder="Ex: 12+ Years" id="experienceLevel"> 
                                            <input type="hidden" id="employee_Id">
                                            </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                                <div class="form-group">
                                                <label class="control-label pull-left">Joining Date</label>
                                                <div class="input-group col-md-12">
                                                <input type="text" class="form-control" placeholder="mm/dd/yyyy" id="datepicker-autoclose1" >
                                                <span class="input-group-addon bg-custom b-0 text-white"><i class="icon-calender"></i></span>
                                                </div>
                                                </div> 
                                                <!-- <button type="button" class="ladda-button  btn btn-info" data-style="slide-left" id="saveemp" onclick="createEmployee();">Save Basic Info</button>  -->
                                                <span id="empErrorMessage"></span>
                                            </div> 
                                            </div>
                                            </section>
                                            <!--Teb Completed-->
                                            <h3>Work/Project Info</h3>
                                            <section>
                                            <form>
                                            <div class="row"> 
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Project Name</label> 
                                            <input type="text" class="form-control" placeholder="Name Of Recruitment" id="project_name">
                                            </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                            <div class="form-group"> 
                                            <label class="control-label pull-left">Project Description</label> 
                                            <input type="text" class="form-control" placeholder="Description" id="project_desc"> 
                                            </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Hiring Manager Name</label> 
                                            <input type="text" class="form-control" placeholder="Name Of HM" id="hm_name"> 
                                            </div> 
                                            </div>
                                            </div>
                                            <div class="row"> 
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Hiring Manager Email</label>  
                                            <input type="text" class="form-control" placeholder="HM Email" id="hm_email"> 
                                            </div> 
                                            </div>
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Hiring Manager Contact</label> 
                                            <input type="text" class="form-control" placeholder="HM Contact Number" id="hm_contact"> 
                                            </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Accounts Manager Name</label>  
                                            <input type="text" class="form-control" placeholder="Name" id="pm_name"> 
                                            </div> 
                                            </div> 
                                            </div>
                                            <div class="row"> 
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Accounts Manager Email</label> 
                                            <input type="text" class="form-control" placeholder="Email" id="pm_email"> 
                                            </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Accounts Manager Contact</label>  
                                            <input type="text" class="form-control" placeholder="Contact" id="pm_contact"> 
                                           <!--  <span id="pmContactError" ></span> -->
                                            </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Customer</label>  
                                            <select class="form-control" id="customer_id">
                                                <option>Select Customer</option>
                                            </select>
                                            <input type="hidden" id="customer_Id">
                                            
                                            </div> 
                                            </div>
                                            </div>
                                            <div class="row">
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Location</label> 
                                            <input type="text" class="form-control" placeholder="Area"  id="location"> 
                                            </div> 
                                            </div>
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Rate Card</label> 
                                            <input type="text" class="form-control" placeholder="Rate" id="rate_card"> 
                                            </div> 
                                            </div> 
                                            </div>
                                            </form>
                                            <!--  <button type="button" class="ladda-button  btn btn-info" data-style="slide-left" id="saveproject" onclick="addProjectDetails();">Save Project Info</button>  -->
                                             <span id="projectErrorMessage"></span> 
                                            </section>
                                            <!--Teb Completed-->
                                            <h3>Upload SOW</h3>
                                            <section>
                                            	<div class="row">
                                                    <div class="col-md-6">
                                                    <div class="form-group">
                                                    <label class="control-label">Upload SOW</label><br> 
                                                    <form  method="POST" id="dropzone1" >
                                                    <input name="files" id="sowUpload" type="file" class="filestyle" data-classButton="btn btn-primary" data-input="false" data-classIcon="icon-plus" data-buttonText="Upload Files" multiple multiple accept=".doc,.pdf,.docx" />
                                                    <span id="sowError"></span>
                                                    </form>
                                                     </div>  
                                                    </div>
                                                     <div class="col-md-6 m-t-30">
                                               <!--      <button type="button" class="ladda-button btn btn-info" data-style="slide-left" id="savesow" onclick="uploadSowfile();">Save SOW Info</button>  -->
                                                    <span id="sowErrorMessage"></span> 
                                                   </div>
                                                </div>
                                            </section>
                                            <!--Teb Completed-->
                                            <h3>PO Details</h3>
                                            <section>
                                                <div class="row">
                                                <div class="col-md-4"> 
                                                <div class="form-group">
                                                <label class="control-label pull-left">PO Number</label> 
                                                <input type="text" class="form-control" placeholder="Ex: 0001" id="poNumber"> 
                                                <!--  <span id="poNumberError"></span> -->
                                                </div> 
                                                </div>
                                                <div class="col-md-4"> 
                                                <div class="form-group"> 
                                                <label class="control-label pull-left">Description</label> 
                                                <input type="text" class="form-control" placeholder="Ex:" id="description"> 
                                               <!--  <span id="descriptionError"></span> -->
                                                </div> 
                                                </div>
                                                <div class="col-md-4"> 
                                                <div class="form-group">
                                                <label class="control-label pull-left">Supplier Reference Number</label> 
                                                <input type="text" class="form-control" placeholder="Ex: 123456789" id="supplierRefNumber">
                                               <!--  <span id="suppRefError" ></span> -->
                                                </div> 
                                                </div>
                                               <!-- <div class="col-md-4"> 
                                                <div class="form-group">
                                                <label class="control-label pull-left">Currency</label>  
                                                <select class="selectpicker" data-style="btn-white" id="currency">
                                                    <option>Select Currency</option>
                                                    <option>USA &#036;</option>
                                                    <option>Indian &#8377;</option>
                                                    <option>EUR &#8364;</option>
                                                </select>
                                                </div> 
                                                </div>-->
                                                <div class="col-md-4"> 
                                                <div class="form-group">
                                                <label class="control-label pull-left">PO Raised On</label>
                                                <div class="input-group col-md-12">
                                                <input type="text" class="form-control" placeholder="mm/dd/yyyy" id="datepicker-autoclose">
                                                <span class="input-group-addon bg-custom b-0 text-white"><i class="icon-calender" ></i></span>
                                                </div>
                                                </div> 
                                                </div>
                                                <div class="col-md-4"> 
                                                <div class="form-group">
                                                <label class="control-label pull-left">Raised By</label> 
                                                <input type="text" class="form-control" placeholder="Ex: AppajiRao" id="raisedBy"> 
                                                </div> 
                                                </div>
                                                <div class="col-md-4"> 
                                                <div class="form-group">
                                                <label class="control-label pull-left">Duration In Months</label>  
                                                <input type="text" class="form-control" placeholder="Ex: 6Months" id="durationMonths" onkeyup="allowOnlyNumbers(event);"> 
                                                </div> 
                                                </div>
                                                <div class="col-md-12">
                                                <div class="col-md-6"> 
                                                <div class="form-group">
                                                    <label class="control-label pull-left">Contract Period</label><br>
                                                        <div class="input-daterange input-group col-md-12" id="date12">
                                                        	<span class="input-group-addon bg-custom b-0 text-white">Start</span>
                                                            <input type="text" class="form-control" name="start" id="startDate" onchange="endDateOnchange();"/>
                                                            <span id="startDateError"></span>
                                                            <span class="input-group-addon bg-custom b-0 text-white">End</span>
                                                            <input type="text" class="form-control" name="end" id="endDate" />
                                                        </div>
                                                </div>
                                                </div>
                                                 <div class="col-md-3"> 
                                                <div class="form-group">
                                                <label class="control-label pull-left">Currency</label>  
                                                <select class="selectpicker" data-style="btn-white" id="currency">
                                                    <option>Select Currency</option>
                                                     <option value="USD">USA &#036;</option>
                                                    <option value="INR">Indian &#8377;</option>
                                                    <option value="EUR">EUR &#8364;</option>
                                                </select>
                                                </div> 
                                                </div>
                                                <div class="col-md-3"> 
                                                <div class="form-group">
                                                <label class="control-label pull-left">Unit Price</label> 
                                                <input type="text" class="form-control" placeholder="Ex: 1000000" id="unitPrice"> 
                                                </div> 
                                                </div>
                                                </div>
                                                <div class="col-md-6">
                                                <div class="form-group">
                                                <label class="control-label">Upload PO</label><br> 
                                               <form method="POST"  id="dropzone"> 
                                                <input name="files" type="file" id="poUpload" class="filestyle" data-classButton="btn btn-primary" data-input="false" data-classIcon="icon-plus" data-buttonText="Upload Files" multiple accept=".doc,.pdf,.docx"/>
                                                </form>
                                                </div>
                                                </div>  
                                                </div>
                                                <div class="col-md-6 m-t-30">
                                               <!--  <button type="button" class="ladda-button  btn btn-info" data-style="slide-left" id="save" onclick="addPODetails();">Save PO Info</button>  -->
                                                 <span id="poErrorMessage"></span>
                                                 </div>
                                                </div>
                                            </section>
                                        </div>
                                    </form> 
                                    
                            	</div>
                        	</div>
                    	</div> <!-- end Panel -->
                        
                        
                        


                    </div> <!-- container -->
                               
                </div> <!-- content -->

                <footer class="footer">
                    © 2017. All rights reserved.
                </footer>

            </div>
            <!-- ============================================================== -->
            <!-- End content here -->
            <!-- ============================================================== -->
            
            

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
        <!----Ladda button----->
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
        <script type="text/javascript" src="assets/pages/jquery.form-advanced.init.js"></script>
         <script src="assets/plugins/bootstrap-filestyle/js/bootstrap-filestyle.min.js" type="text/javascript"></script> 
         <script type="text/javascript" src="js/urlgetter.js"></script>
  		 <script type="text/javascript" src="js/resource.js"></script>
    	 <script type="text/javascript" src="js/errorCodeConstants.js"></script>
          <script type="text/javascript" src="js/changePassword.js"></script>
          <script type="text/javascript" src="js/login.js"></script>
          <script type="text/javascript" src="js/notifications.js"></script>
   		<script type="text/javascript">
   		$("#basic-form-add-res").steps({
   		    headerTag: "h3",
   		    bodyTag: "section",
   		    transitionEffect: "slideLeft",
   		    autoFocus: true,
   			onStepChanging: function (event, currentIndex, newIndex) {
   				//$form_container.validate().settings.ignore = ":disabled,:hidden";
   				if(currentIndex > newIndex){
   					return true;
   				}else{
   					if(currentIndex==0){
   						return createEmployee();
   						//alert("submit basic info");
   						//return true;
   					}else if(currentIndex==1){
   						return addProjectDetails();
   						//alert("submit project info");
   						//return true;
   					}else if(currentIndex==2){
   						//alert("submit SOW info");
   						return uploadSowfile();
   						//return false;
   					}
   				}
   		    },
   		    onFinishing: function (event, currentIndex) {
   				//$form_container.validate().settings.ignore = ":disabled";
   				//alert("submit PO info");
   				return addPODetails();
   				//return true;
   		    },
   		    onFinished: function (event, currentIndex) {
   		       // alert("Submitted!");
   		    }
   		});
   		</script>
   		<script>
		//$(":file").filestyle({buttonText: "Upload Files"});
		$("#sowUpload").filestyle({buttonText: "Upload Sow Files"});
		$('#poUpload').filestyle({buttonText: "Upload PO Files"});
		</script>
   		<script type="text/javascript">
   		//logoutcheck();
   		getNotificationPanel();
   		//getMenuBarNavigation();
   		customerList();
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