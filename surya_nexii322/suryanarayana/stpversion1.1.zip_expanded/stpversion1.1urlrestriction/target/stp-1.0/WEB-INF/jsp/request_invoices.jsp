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
			boolean status= utilitiesController.authorizationsCheck("Invoices",menu);
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
        <script src="https://oss.maxcdn.com/libs/reskpond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

<script src="assets/js/modernizr.min.js"></script>
<style>
.datepicker>div {
    display: block;
}
.custombox-modal-container.custombox-modal-container-fadein {
	width: 980px !important;
}
.brt {
	border-right: 1px solid #ebeff2;
}
</style>
</head>

<body class="fixed-left">

<!-- Begin page -->
<div class="wi-moon-alt-waning-gibbous-1" id="wrapper"> 
  
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
         <%=utilitiesController.getMenuNavigationForActivePage("request_invoices",menu) %>
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
            <h4 class="page-title">Request Invoices</h4>
            <ol class="breadcrumb">
              <li><a href="#">Invoices</a></li>
              <li class="active"> Request Invoices</li>
            </ol>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <div class="card-box table-responsive">
              <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                <thead>
                  <tr>
                    <th>Invoice Number</th>
                    <th>Invoice Date</th>
                    <th>Client Name</th>
                    <th>Project Name</th>
                    <th>Resource Name</th>
                    <th>PO Number</th>
                    <th>PO Date</th>
                    <th>Location</th>
                    <th>Amount</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody id="invoiceRequestList">
                   <!-- <tr class="gradeX">
                    <td>INV_001</td>
                    <td><h4 class="text-purple m-t-0">NexiiLabs Pvt. Ltd.</h4>
                      <div style="word-break:break-word; width:250px;">Unit No: 01-08,1st Floor, Block-B, Cyber Pearl, Madhapur, Hyderabad, AP, Pin: 500 081.India</div></td>
                    <td>03-01-2018</td>
                    <td>PO_003</td>
                    <td>03-01-2018</td>
                    <td><a href="#custom-modal-edit" class="on-default text-info m-r-10" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a"><i class="fa fa-pencil"></i></a> <a href="#custom-modal-generate" class="btn btn-success m-l-15" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">Generate</a></td>
                  </tr>  -->
                </tbody>
              </table>
              <span id="status_msg" style="align:center;"></span>
            </div>
          </div>
          <!-- end: page --> 
          
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
  
  <!-- Generate Invoice MODAL -->
  <div id="custom-modal-edit" class="modal-demo">
    <button type="button" class="close" onclick="Custombox.close();"> <span>&times;</span><span class="sr-only">Close</span> </button>
    <h4 class="custom-modal-title">Invoice</h4>
    <div class="custom-modal-text">
      <div class="panel-body">
        <div class="clearfix">
          <div class="pull-right">
            <h4 class="text-right"><img src="assets/images/Invoice-logo.png" alt="Nexiilabs"></h4>
          </div>
        </div>
        <hr>
        <div class="row">
          <div class="col-md-12">
          <span id="invoicerequestId" style="display:none;"></span>
          <span id="invoiceId" style="display:none;"></span>
          <span id="currency_type" style="display:none;"></span>
          <span id="po_id" style="display:none;"></span>
          <span id="po_date" style="display:none;"></span>
          <span id="invoice_date" style="display:none;"></span>
            <div class="pull-left m-t-10 text-left col-md-6">
              <address>
             <!--   <strong>Nexii IT Labs Pvt. Ltd</strong><br>
              <textarea class="form-control" id="vendor_address" rows="4">
                      </textarea>-->
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <!-- <label class="col-md-4 text-left p-l-0 p-t-10 control-label">Vendor Address Lane1</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="vendor address lane1" id="vendor_address_lane1" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <!-- <label class="col-md-4 text-left p-l-0 p-t-10 control-label">Vendor Address Lane2</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="vendor address lane2" id="vendor_address_lane2" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <!-- <label class="col-md-4 text-left p-l-0 p-t-10 control-label">Vendor Address State</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="state" id="vendor_address_state" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 p-l-0">
                        <!-- <label class="col-md-4 text-left p-l-0 p-t-10 control-label">Vendor Address Pincode</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="pincode" id="vendor_address_pincode" type="text">
                        </div>
                      </div>
                      
                   
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">PAN</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="AAECN0261B" id="our_pan" onkeyup="getpannames();" type="text">
                          <ul class="list-group" id="panresult"></ul>
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">GSTIN</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="36AAECN0261B1Z1" id="our_gstin" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">State</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="Telangana" id="our_state" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">State Code</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="36" id="our_state_code" type="text">
                        </div>
                      </div>
              </address>
            </div>
            <div class="pull-right m-t-10 text-right col-md-6">
            			<div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">Invoice No</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="NEXII/17-18/11/0000" id="invoice_number" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">Dated</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="2017-20-12" id="invoice_pdf_date" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">Buyer's Order No</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="00001" id="po_number" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">Order Dated</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="2017-20-12" id="order_date" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">Supplier's Ref. No</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="00001" id="supp_ref_number" type="text">
                        </div>
                      </div>
            </div>
          </div>
        </div>
        <div class="m-h-20"></div>
        <div class="row">
          <div class="col-md-12">
            <div class="table-responsive table-bordered table-striped text-left">
              <table class="table">
                <thead>
                  <tr>
                    <td class="col-sm-6 info">Bill To</td>
                    <td class="col-sm-6 info">Ship To</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="col-sm-6 brt"><strong></strong><br>
                     <!--  <textarea class="form-control" rows="4" id="billing_address">
                      </textarea>-->
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <!-- <label class="col-md-4 text-left p-l-0 p-t-10 control-label">PAN</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="clientName" id="client_name" type="text">
                        </div>
                      </div>
                       <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <!-- <label class="col-md-4 text-left p-l-0 p-t-10 control-label">PAN</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="billing address lane1" id="billing_address_lane1" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <!-- <label class="col-md-4 text-left p-l-0 p-t-10 control-label">GSTIN</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="billing address lane2" id="billing_address_lane2" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                       <!--  <label class="col-md-4 text-left p-l-0 p-t-10 control-label">State</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="State" id="billing_address_state" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 p-l-0">
                       <!--  <label class="col-md-4 text-left p-l-0 p-t-10 control-label">State Code</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="pincode" id="billing_address_pincode" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">PAN</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="AAECN0261B" id="customer_pan" onchange="setcustomerPan();" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">GSTIN</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="36AAECN0261B1Z1" id="customer_gstin" onchange="setcustomerGstin();" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">State</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="Telangana" id="customer_state" onchange="setcustomerState();" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">State Code</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="36" id="customer_state_code" onchange="setcustomerStateCode();" type="text">
                        </div>
                      </div>
                      </td>
                    <td class="col-sm-6"><strong></strong><br>
                   <!--   <textarea class="form-control" rows="4" id="shipping_address">
                      </textarea>-->
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <!-- <label class="col-md-4 text-left p-l-0 p-t-10 control-label">PAN</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="Client Name" id="client_name1" type="text">
                        </div>
                      </div>
                       <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <!-- <label class="col-md-4 text-left p-l-0 p-t-10 control-label">PAN</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="shipping address lane1" id="shipping_address_lane1" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                       <!--  <label class="col-md-4 text-left p-l-0 p-t-10 control-label">GSTIN</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="shipping address lane2"  id="shipping_address_lane2" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <!-- <label class="col-md-4 text-left p-l-0 p-t-10 control-label">State</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="State" id="shipping_address_state" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 p-l-0">
                        <!-- <label class="col-md-4 text-left p-l-0 p-t-10 control-label">State Code</label> -->
                        <div class="col-md-8">
                          <input class="form-control" placeholder="Pincode" id="shipping_address_pincode" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">PAN</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="AAECN0261B" id="our_pan1" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">GSTIN</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="36AAECN0261B1Z1" id="our_gstin1" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 m-b-0 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">State</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="Telangana" id="our_state1" type="text">
                        </div>
                      </div>
                      <div class="form-group col-md-12 m-t-10 p-l-0">
                        <label class="col-md-4 text-left p-l-0 p-t-10 control-label">State Code</label>
                        <div class="col-md-8">
                          <input class="form-control" placeholder="36" id="our_state_code1" type="text">
                        </div>
                      </div>  
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="m-h-40"></div>
        <div class="row">
          <div class="col-md-12">
            <div class="table-responsive table-bordered text-left">
              <table class="table">
                <thead>
                  <tr>
                    <td class="info">S.No.</td>
                    <td class="info col-md-2">Description of services</td>
                    <td class="info">HSN SAC</td>
                    <td class="info">Qty</td>
                    <td class="info">Rate</td>
                    <td class="info">Taxable value</td>
                    <td class="info">CGST Rate</td>
                    <td class="info">CGST Amount</td>
                    <td class="info">SGST Rate</td>
                    <td class="info">SGST Amount</td>
                    <td class="info">IGST Rate</td>
                    <td class="info">IGST Amount</td>
                    <td class="info">Total Rs.</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="brt">01</td>
                    <td class="brt">
                    <input class="form-control" placeholder="Some text value..." value="Services for" type="text" style="padding:7px 2px;" readonly>
                    <div class="form-group">
                      <label class="control-label pull-left">Project Name</label>
                      <span id="project_id" style="display:none;"></span>
                      <input type="text" class="form-control" id="project_name" placeholder="xxxxxxx">
                    </div>
                    <div class="form-group">
                      <label class="control-label pull-left">billing Period</label>
                      <input type="text" class="form-control" id="period" placeholder="xxxxxxx">
                    </div>
                    <!-- <div class="form-group">
                      <label class="control-label pull-left">Resources</label>
                      <input type="text" class="form-control" id="" placeholder="xxxxxxx">
                    </div> -->
                    <div class="form-group">
                      <label class="control-label pull-left">Employee Name</label>
                      <span id="emp_id" style="display:none;"></span>
                      <input type="text" class="form-control" id="employee_name" placeholder="xxxxxxx">
                    </div>
                    <div class="form-group">
                      <label class="control-label pull-left">Working Days</label>
                      <input type="text" class="form-control" id="working_days" onchange="totalWorkingDaysAmountCal();" placeholder="xxxxxxx">
                    </div>
                    <div class="form-group">
                      <label class="control-label pull-left">billing Days</label>
                      <input type="text" class="form-control" id="billing_days" onchange="amountCal();" placeholder="xxxxxxx">
                    </div>
                    </td>
                    <td class="brt"><input class="form-control" placeholder="Some text value..." id="hsn_sac" type="text" style="padding:7px 2px;"></td>
                    <td class="brt"><input class="form-control" placeholder="Some text value..." id="quantity" type="text" style="padding:7px 2px;"></td>
                    <td class="brt"><input class="form-control" placeholder="Some text value..." id="rate" type="text" style="padding:7px 2px;"></td>
                    <td class="brt"><input class="form-control" placeholder="Some text value..." id="taxable_value" type="text" style="padding:7px 2px;"></td>
                    <td class="brt"><input class="form-control" placeholder="Some text value..." id="cgst_rate" type="text" style="padding:7px 2px;"></td>
                    <td class="brt"><input class="form-control" placeholder="Some text value..." id="cgst_amount" type="text" style="padding:7px 2px;"></td>
                    <td class="brt"><input class="form-control" placeholder="Some text value..." id="sgst_rate" type="text" style="padding:7px 2px;"></td>
                    <td class="brt"><input class="form-control" placeholder="Some text value..." id="sgst_amount" type="text" style="padding:7px 2px;"></td>
                    <td class="brt"><input class="form-control" placeholder="Some text value..." id="igst_rate" onchange="igstCalculations();" type="text" style="padding:7px 2px;"></td>
                    <td class="brt"><input class="form-control" placeholder="Some text value..." id="igst_amount" type="text" style="padding:7px 2px;"></td>
                    <td><input class="form-control" placeholder="Some text value..." id="total" type="text" style="padding:7px 2px;"></td>
                  </tr>
                  <tr>
                    <td colspan="3" class="brt text-right">Total</td>
                    <td class="brt">1</td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td></td>
                  </tr
                >
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="m-h-40"></div>
        <div class="row">
          <div class="col-md-12">
            <div class="col-md-6">
              <!-- <p class="text-left"> <strong>Total Invoice Amount in Words</strong><br>
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx </p> -->
                <span id="amount_in_words" ></span>
              <p class="text-left">
              <h4 class="text-left text-success"><strong>Bank Details</strong></h4>
              <div class="form-group col-md-12 p-l-0">
                <label class="col-md-5 text-left p-l-0 p-t-10 control-label">Account Holder Name</label>
                <div class="col-md-7">
                  <input class="form-control" placeholder="Some text value..." id="acc_holder_name" onkeyup="getaccountnames();" type="text">
               	  <ul class="list-group" id="result"></ul>
                </div>
                
              </div>
              <div class="form-group col-md-12 p-l-0">
                <label class="col-md-5 text-left p-l-0 p-t-10 control-label">Bank Name</label>
                <div class="col-md-7">
                  <input class="form-control" placeholder="Some text value..." id="bank_name" type="text">
                </div>
              </div>
              <div class="form-group col-md-12 p-l-0">
                <label class="col-md-5 text-left p-l-0 p-t-10 control-label">Bank Account Number</label>
                <div class="col-md-7">
                  <input class="form-control" placeholder="Some text value..." id="account_number" type="text">
                </div>
              </div>
              <div class="form-group col-md-12 p-l-0">
                <label class="col-md-5 text-left p-l-0 p-t-10 control-label">IFSC</label>
                <div class="col-md-7">
                  <input class="form-control" placeholder="Some text value..." id="ifsc_code" type="text">
                </div>
              </div>
              </p>
              <p class="text-left"> <strong>Declaraion</strong><br>
                We declare that this invoice shows the actual price of the services described and that all particulars are true and correct </p>
            </div>
            <div class="col-md-6">
              <div class="table-responsive table-bordered text-left">
                <table class="table m-b-0">
                  <tr>
                    <td class="bg-purple text-white middle-align p-t-10">Total Amount before Tax</td>
                    <td><input type="text" class="form-control" id="total_amount_before_tax" placeholder="XXXXXXXXX"></td>
                  </tr>
                  <tr>
                    <td class="bg-inverse text-white middle-align p-t-10">Add CGST</td>
                    <td><input type="text" class="form-control" id="cgst_amount1" placeholder="XXXXXXXXX"></td>
                  </tr>
                  <tr>
                    <td class="bg-inverse text-white middle-align p-t-10">Add SGST</td>
                    <td><input type="text" class="form-control" id="sgst_amount1" placeholder="XXXXXXXXX"></td>
                  </tr>
                  <tr>
                    <td class="bg-inverse text-white middle-align p-t-10">Add IGST</td>
                    <td><input type="text" class="form-control" id="igst_amount1" placeholder="XXXXXXXXX"></td>
                  </tr>
                  <tr>
                    <td class="bg-inverse text-white middle-align p-t-10">Total GST</td>
                    <td><input type="text" class="form-control" id="total_gst" placeholder="XXXXXXXXX"></td>
                  </tr>
                  <tr>
                    <td class="bg-purple text-white middle-align">Total Amount After Tax</td>
                    <td><input type="text" class="form-control" id="total_amount_after_tax" placeholder="XXXXXXXXX"></td>
                  </tr>
                  <tr>
                    <td colspan="2" class="bg-success text-white text-center"><strong>For Nexii IT Labs Private Limited</strong></td>
                  </tr>
                </table>
              </div>
            </div>
          </div>
        </div>
        <div class="m-h-40"></div>
        <div class="hidden-print">
          <div class="pull-right">
            <button class="btn btn-primary waves-effect" onclick="updateInvoice();">Update Invoice</button>
            <button class="btn btn-default waves-effect" onclick="Custombox.close();">Cancel</button>
          </div>
        </div>
        <span id="update_msg"></span>
      </div>
    </div>
  </div>
  <!-- end Modal --> 
  
  <!-- Edit Employ MODAL --> 
  <div id="custom-modal-generate" class="modal-demo">
    <!-- <button type="button" class="close" onclick="Custombox.close();"> <span>&times;</span><span class="sr-only">Close</span> </button>
    <h4 class="custom-modal-title">Edit Details</h4>
    <div class="custom-modal-text">
      <div class="panel-body">
        <div class="row"> 
          Start Row
          <div class="col-md-4">
            <div class="form-group">
              <label class="control-label pull-left">Invoice Number</label>
              <input type="text" class="form-control" placeholder="INV_001">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Invoice Date</label>
              <div class="input-group col-md-12">
                <input type="text" class="form-control" placeholder="mm/dd/yyyy" id="datepicker-autoclose">
                <span class="input-group-addon bg-custom b-0 text-white"><i class="icon-calender"></i></span> </div>
              input-group 
            </div>
            <div class="form-group">
              <label class="control-label pull-left">PO Number</label>
              <input type="text" class="form-control" placeholder="PO_003">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">PO Date</label>
              <div class="input-group col-md-12">
                <input type="text" class="form-control" placeholder="mm/dd/yyyy" id="datepicker-autoclose1">
                <span class="input-group-addon bg-custom b-0 text-white"><i class="icon-calender"></i></span> </div>
              input-group 
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Supplier Referance Number</label>
              <input type="text" class="form-control" placeholder="123456789">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Requirement</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Period</label>
              <input type="text" class="form-control" placeholder="6 Months">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Employ ID</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">HSN SAC</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Days</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Taxable Value</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
          </div>
          
          Column Separated
          <div class="col-md-4">
            <div class="form-group">
              <label class="control-label pull-left">CGST Amount</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">SGST Amount</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">IGST Amount</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Account Holder Name</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Bank Ac. No.</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Quantity</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Billing Days</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Rate</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">CGST Rate</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">SGST Rate</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">IGST Rate</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
          </div>
          Column Separated
          <div class="col-md-4">
            <div class="form-group">
              <label class="control-label pull-left">Total</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Bank Name</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">IFSC Code</label>
              <input type="text" class="form-control" placeholder="Ex:">
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Vendor Address</label>
              <textarea class="form-control" rows="4"></textarea>
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Billing Address</label>
              <textarea class="form-control" rows="4"></textarea>
            </div>
            <div class="form-group">
              <label class="control-label pull-left">Shipping Address</label>
              <textarea class="form-control" rows="4"></textarea>
            </div>
          </div>
          End Row 
        </div>
        <div class="row m-t-20">
          <div class="col-md-12 text-right">
            <button class="btn btn-primary waves-effect">Save Changes</button>
            <button class="btn btn-default waves-effect" onclick="Custombox.close();">Cancel</button>
          </div>
        </div>
      </div>
    </div> -->
    <span id="htmlView"></span>
   <!--   <button type="button" class="close" onclick="Custombox.close();"> <span>&times;</span><span class="sr-only">Close</span> </button>
    <h4 class="custom-modal-title">Invoice</h4>
    <div class="custom-modal-text">
      <div class="panel-body">
        <div class="clearfix"> 
          <div class="pull-right">
            <h4 class="text-right"><img src="assets/images/Invoice-logo.png" alt="Nexiilabs"></h4>
          </div>
        </div>
        <hr>
        <div class="row">
          <div class="col-md-12">
            <div class="pull-left m-t-10 text-left">
              <address>
              <strong>Nexii IT Labs Pvt. Ltd</strong><br>
              01-08, 1st Floor, Block-B, Cyber Pearl,<br>Hitechcity, Madhapur, Hyderabad-500081,<br>Telangana, INDIA.
              <p class="m-t-10">PAN No: <strong>AAECN0261B</strong><br>
              GSTIN: <strong>36AAECN0261B1Z1</strong><br>
              State Code: <strong>36</strong></p>
              </address>
            </div>
            <div class="pull-right m-t-10 text-right">
              <p>Invoice No: <strong id="strong"></strong><br>Dated: <strong>12/20/2017</strong></p>
              <p>Buyer's Order No: <strong>00001</strong><br>Dated: <strong>12/11/2017</strong></p>
              <p>Supplier's Ref. No: <strong>00001</strong></p>
            </div>
          </div>
        </div>
        <div class="m-h-20"></div>
        <div class="row">
          <div class="col-md-12">
            <div class="table-responsive table-bordered table-striped text-left">
              <table class="table">
                <thead>
                  <tr>
                    <td class="col-sm-6 info">Bill To</td>
                    <td class="col-sm-6 info">Ship To</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="col-sm-6">
                    <strong>Nexii IT Labs Pvt. Ltd</strong><br>
                      01-08, 1st Floor, Block-B, Cyber Pearl,<br>Hitechcity, Madhapur, Hyderabad-500081,<br>Telangana, INDIA.
                      <p class="m-t-10">PAN No: <strong>AAECN0261B</strong><br>
                      GSTIN: <strong>36AAECN0261B1Z1</strong><br>
                      State Code: <strong>36</strong></p>
					</td>
                    <td class="col-sm-6">
                    <strong>Nexii IT Labs Pvt. Ltd</strong><br>
                      01-08, 1st Floor, Block-B, Cyber Pearl,<br>Hitechcity, Madhapur, Hyderabad-500081,<br>Telangana, INDIA.
                      <p class="m-t-10">PAN No: <strong>AAECN0261B</strong><br>
                      GSTIN: <strong>36AAECN0261B1Z1</strong><br>
                      State Code: <strong>36</strong></p>
					</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
		<div class="m-h-40"></div>
        <div class="row">
          <div class="col-md-12">
            <div class="table-responsive table-bordered text-left">
              <table class="table">
                <thead>
                  <tr>
                    <td class="info">S.No.</td>
                    <td class="info col-md-2">Description of services</td>
                    <td class="info">HSN SAC</td>
                    <td class="info">Qty</td>
                    <td class="info">Rate</td>
                    <td class="info">Taxable value</td>
                    <td class="info">CGST Rate</td>
                    <td class="info">CGST Amount</td>
                    <td class="info">SGST Rate</td>
                    <td class="info">SGST Amount</td>
                    <td class="info">IGST Rate</td>
                    <td class="info">IGST Amount</td>
                    <td class="info">Total Rs.</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="brt">01</td>
                    <td class="brt">Services for Dummy text dont read this.</td>
                    <td class="brt">998313</td>
                    <td class="brt">1</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td class="brt">02</td>
                    <td class="brt">Resources for Dummy text dont read this.</td>
                    <td class="brt">998313</td>
                    <td class="brt">1</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td colspan="3" class="brt text-right">Total</td>
                    <td class="brt">2</td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td></td>
                  </tr
                ></tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="m-h-40"></div>
        <div class="row">
        <div class="col-md-12">
        <div class="col-md-6">
        <p class="text-left">
        <strong>Total Invoice Amount in Words</strong><br>
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        </p>
        <p class="text-left">
        <strong>Bank Details</strong><br>
        Account Holder Name: <strong>00001</strong><br>
        Bank Name: <strong>00001</strong><br>
        Bank Account Number: <strong>00001</strong><br>
        IFSC: <strong>00001</strong><br>
        </p>
         <p class="text-left">
        <strong>Declaraion</strong><br>
        We declare that this invoice shows the actual price of the services described and that all particulars are true and correct
        </p>
        </div>
        <div class="col-md-6">
        <div class="table-responsive table-bordered text-left">
              <table class="table m-b-0">
                  <tr>
                    <td class="bg-purple text-white">Total Amount before Tax</td>
                    <td>xxxxxx</td>
                  </tr>
                  <tr>
                    <td class="bg-inverse text-white">Add CGST</td>
                    <td>xxxxxx</td>
                  </tr>
                  <tr>
                    <td class="bg-inverse text-white">Add SGST</td>
                    <td>xxxxxx</td>
                  </tr>
                  <tr>
                    <td class="bg-inverse text-white">Add IGST</td>
                    <td>xxxxxx</td>
                  </tr>
                  <tr>
                    <td class="bg-inverse text-white">Total GST</td>
                    <td>xxxxxx</td>
                  </tr>
                  <tr>
                    <td class="bg-purple text-white">Total Amount After Tax</td>
                    <td>xxxxxx</td>
                  </tr>
                  <tr>
                  <td colspan="2" class="bg-success text-white text-center"><strong>For Nexii IT Labs Private Limited</strong></td>
                  </tr>
              </table>
            </div>
        </div>
        </div>
        </div>
         <div class="m-h-40"></div>
        <div class="hidden-print">
          <div class="pull-right"> <button class="btn btn-primary waves-effect">Send Invoice</button>
            <button class="btn btn-default waves-effect" onclick="Custombox.close();">Cancel</button> </div>
        </div>
      </div>
    </div> -->
  </div>
  <!-- end Modal --> 
  
</div>
<!-- END wrapper --> 
<script>
            var resizefunc = [];
        </script> 

<!-- jQuery  --> 
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>  -->
<script src="assets/js/jquery.min1.js"></script>
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

<!-- Ladda buttons  -->
<script src="assets/plugins/ladda-buttons/js/spin.min.js"></script>
<script src="assets/plugins/ladda-buttons/js/ladda.min.js"></script>
<script src="assets/plugins/ladda-buttons/js/ladda.jquery.min.js"></script>

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
<script type="text/javascript" src="assets/pages/jquery.form-advanced.init.js"></script>
   <script type="text/javascript" src="js/invoiceRequest.js"></script>
        <script type="text/javascript" src="js/errorCodeConstants.js"></script>
        <script type="text/javascript" src="js/urlgetter.js"></script>
        <script type="text/javascript" src="js/changePassword.js"></script>
        <script type="text/javascript" src="js/login.js"></script>
        <script type="text/javascript" src="js/myinvoices.js"></script>
        <script type="text/javascript" src="js/notifications.js"></script>
         <script type="text/javascript">
         function sample(){
        	 alert("alert");
         }
         function totalWorkingDaysAmountCal(){
        	 var rate=$("#rate").val();
        	 var totalDays=$("#working_days").val();
        	 var billingdays=$("#billing_days").val();
        	 if(totalDays<billingdays){
        		 billingdays=totalDays;
        		 $("#billing_days").val("");
        		 $("#billing_days").val(billingdays);
        	 }
        	 var unitPrice=rate/totalDays;
        	 var taxablevalue=unitPrice*billingdays;
        	 taxablevalue=taxablevalue.toFixed(2);
        	 var cGSTrate= $("#cgst_rate").val();
			 var sGSTrate= $("#sgst_rate").val();
			 var iGSTrate= $("#igst_rate").val();
			 var currencyType=$("#currency_type").val();
			 var cGSTAmount=0;
			 var sGSTAmount=0;
			 var iGSTAmount=0;
			 if(currencyType=="INR"){
				 cGSTAmount=((cGSTrate*unitPrice)/100)*billingdays;
				 cGSTAmount= cGSTAmount.toFixed(2);
				 sGSTAmount=((sGSTrate*unitPrice)/100)*billingdays;
				 sGSTAmount= sGSTAmount.toFixed(2);
				 if(iGSTrate!=0&&cGSTrate==0&&sGSTrate){
					 cGSTAmount=0;
					 sGSTAmount=0;
					 iGSTAmount=((iGSTrate*unitPrice)/100)*billingdays;
	        	 }
			 }else{
				 cGSTrate=0;
				 sGSTrate=0;
				 iGSTrate=0;
				 cGSTAmount=0;
				 sGSTAmount=0;
				 iGSTAmount=0;
			 }
			 $("#taxable_value").val("");
			 $("#taxable_value").val(taxablevalue);
			 $("#cgst_amount").val("");
			 $("#sgst_amount").val("");
			 $("#igst_amount").val("");
			 $("#cgst_amount1").val("");
			 $("#sgst_amount1").val("");
			 $("#igst_amount1").val("");
			 $("#cgst_rate").val("");
			 $("#sgst_rate").val("");
			 $("#igst_rate").val("");
			 $("#cgst_amount").val(cGSTAmount);
			 $("#sgst_amount").val(sGSTAmount);
			 $("#igst_amount").val(iGSTAmount);
			 $("#cgst_amount1").val(cGSTAmount);
			 $("#sgst_amount1").val(sGSTAmount);
			 $("#igst_amount1").val(iGSTAmount);
			 $("#cgst_rate").val(cGSTrate);
			 $("#sgst_rate").val(sGSTrate);
			 $("#igst_rate").val(iGSTrate);
			 
			 $("#total_amount_before_tax").val("");
			 $("#total_amount_before_tax").val(taxablevalue);
        	 var totalAmount=(+taxablevalue)+(+cGSTAmount)+(+sGSTAmount)+(+iGSTAmount);
        	 totalAmount= totalAmount.toFixed(2);
        	 var totalGst=(+cGSTAmount)+(+sGSTAmount)+(+iGSTAmount);
        	 totalGst=totalGst.toFixed(2);
        	 $("#total_gst").val("");
			 $("#total_gst").val(totalGst);
        	 $("#total_amount_after_tax").val("");
        	 $("#total_amount_after_tax").val(totalAmount);
        	 $("#total").val("");
			 $("#total").val(totalAmount);
         }
         function amountCal(){
        	 var rate=$("#rate").val();
        	 var totalDays=$("#working_days").val();
        	 var billingdays=$("#billing_days").val();
        	 var unitPrice=rate/totalDays;
        	 var taxablevalue=unitPrice*billingdays;
        	 taxablevalue=taxablevalue.toFixed(2);
        	 var cGSTrate= $("#cgst_rate").val();
        	 //alert("cgst.."+cGSTrate);
			 var sGSTrate= $("#sgst_rate").val();
			 var iGSTrate= $("#igst_rate").val();
			 var currencyType=$("#currency_type").val();
			 var cGSTAmount=0;
			 var sGSTAmount=0;
			 var iGSTAmount=0;
			 if(currencyType=="INR"){
			 
				 cGSTAmount=((cGSTrate*unitPrice)/100)*billingdays;
				 cGSTAmount= cGSTAmount.toFixed(2);
				 sGSTAmount=((sGSTrate*unitPrice)/100)*billingdays;
				 sGSTAmount= sGSTAmount.toFixed(2);
				 $("#igst_rate").val("");
				 $("#igst_rate").val(0);
				 if(iGSTrate!=0&&cGSTrate==0&&sGSTrate==0){
					 cGSTAmount=0;
					 sGSTAmount=0;
					 iGSTAmount=((iGSTrate*unitPrice)/100)*billingdays;
					 iGSTAmount=iGSTAmount.toFixed(2);
					 $("#igst_rate").val(iGSTrate);
	        	 }else{
	        		 iGSTrate=0;
	        		 iGSTAmount=0;
	        	 }
			 }else{
				 cGSTrate=0;
				 sGSTrate=0;
				 iGSTrate=0;
				 cGSTAmount=0;
				 sGSTAmount=0;
				 iGSTAmount=0;
			 }
			 $("#taxable_value").val("");
			 $("#taxable_value").val(taxablevalue);
			 $("#cgst_amount").val("");
			 $("#sgst_amount").val("");
			 //$("#igst_rate").val("");
			 $("#igst_amount").val("");
			 $("#cgst_amount1").val("");
			 $("#sgst_amount1").val("");
			 $("#igst_amount1").val("");
			 $("#cgst_rate").val("");
			 $("#sgst_rate").val("");
			 $("#igst_rate").val("");
			 $("#cgst_amount").val(cGSTAmount);
			 $("#sgst_amount").val(sGSTAmount);
			 $("#igst_amount").val(iGSTAmount);
			 $("#cgst_amount1").val(cGSTAmount);
			 $("#sgst_amount1").val(sGSTAmount);
			 $("#igst_amount1").val(iGSTAmount);
			 $("#cgst_rate").val(cGSTrate);
			 $("#sgst_rate").val(sGSTrate);
			 $("#igst_rate").val(iGSTrate);
			 
			 $("#total_amount_before_tax").val("");
			 $("#total_amount_before_tax").val(taxablevalue);
        	 var totalAmount=(+taxablevalue)+(+cGSTAmount)+(+sGSTAmount)+(+iGSTAmount);
        	 totalAmount= totalAmount.toFixed(2);
        	 var totalGst=(+cGSTAmount)+(+sGSTAmount)+(+iGSTAmount);
        	 totalGst=totalGst.toFixed(2);
        	 $("#total_gst").val("");
			 $("#total_gst").val(totalGst);
        	 $("#total_amount_after_tax").val("");
        	 $("#total_amount_after_tax").val(totalAmount);
        	 $("#total").val("");
			 $("#total").val(totalAmount);
        	 
         }
         function igstCalculations(){
        	 
         	 var igstRate=$("#igst_rate").val();
         	 var currencyType=$("#currency_type").val();
         	 if(currencyType=="INR"){
	        	 if(igstRate!=0){
	        		 //alert("igst");
	        		 var rate=$("#rate").val();
	            	 var totalDays=$("#working_days").val();
	            	 var billingdays=$("#billing_days").val();
	            	 var unitPrice=rate/totalDays;
	            	 var taxablevalue=unitPrice*billingdays;
	            	 taxablevalue= taxablevalue.toFixed(2);
	    			 var iGSTAmount=((igstRate*unitPrice)/100)*billingdays;
	    			 //alert("iGSTAmount..............."+iGSTAmount);
	    			 iGSTAmount=iGSTAmount.toFixed(2);
	    			 
	    			 $("#cgst_rate").val("");
	    			 $("#sgst_rate").val("");
	    			 $("#cgst_amount").val("");
	    			 $("#sgst_amount").val("");
	    			 $("#cgst_amount").val("");
	    			 $("#sgst_amount").val("");
	    			 $("#igst_amount").val("");
	    			 $("#cgst_amount1").val("");
	    			 $("#sgst_amount1").val("");
	    			 $("#igst_amount1").val("");
	    			 
	    			 $("#cgst_rate").val(0);
	    			 $("#sgst_rate").val(0);
	    			 $("#cgst_amount").val(0);
	    			 $("#sgst_amount").val(0);
	    			 $("#igst_amount").val(iGSTAmount);
	    			 $("#cgst_amount1").val(0);
	    			 $("#sgst_amount1").val(0);
	    			 $("#igst_amount1").val(iGSTAmount);
	    			 //alert("iGSTAmount.."+iGSTAmount);
	    			 $("#total_amount_before_tax").val("");
	    			 $("#total_amount_before_tax").val(taxablevalue);
	            	 var totalAmount=(+taxablevalue)+(+iGSTAmount);
	            	 totalAmount= totalAmount.toFixed(2);
	            	 var totalGst=iGSTAmount;
	            	 $("#total_gst").val("");
	    			 $("#total_gst").val(totalGst);
	            	 $("#total_amount_after_tax").val("");
	            	 $("#total_amount_after_tax").val(totalAmount);
	            	 $("#total").val("");
	    			 $("#total").val(totalAmount);
	    			 //alert(totalAmount);
	        	 }
        	 }
         }
         /* function totalGST(){
        	 $("#total_gst").val("");
        	 $("#taxable_value").val("");
        	   var cGSTAmount= $("#cgst_amount1").val();
			   var sGSTAmount= $("#sgst_amount1").val();
			   var iGSTAmount= $("#igst_amount1").val();
			   $("#cgst_amount").val(cGSTAmount);
			   $("#sgst_amount").val(sGSTAmount);
			   $("#igst_amount").val(iGSTAmount);
			   $("#total_gst").val((+cGSTAmount)+(+sGSTAmount)+(+iGSTAmount));
			   $("#taxable_value").val((+cGSTAmount)+(+sGSTAmount)+(+iGSTAmount));
        	 
         }
         function totalAmount(){
        	 $("#total_amount_after_tax").val("");
        	 $("#total").val("");
        	   var totalAmountbeforeTax= $("#total_amount_before_tax").val();
			   var cGSTAmount= $("#cgst_amount1").val();
			   var sGSTAmount= $("#sgst_amount1").val();
			   var iGSTAmount= $("#igst_amount1").val();
			   //var totalGst= $("#total_gst").val();
			   $("#total_amount_after_tax").val((+totalAmountbeforeTax)+(+cGSTAmount)+(+sGSTAmount)+(+iGSTAmount));
			   $("#total").val((+totalAmountbeforeTax)+(+cGSTAmount)+(+sGSTAmount)+(+iGSTAmount));
        	 
         }*/
         function setcustomerPan(){
        	 var pan=$("#customer_pan").val();
        	 $("#our_pan1").val("");
        	 $("#our_pan1").val(pan);
         }
         function setcustomerGstin(){
        	 var gst=$("#customer_gstin").val("");
        	 $("#our_gstin1").val("");
        	 $("#our_gstin1").val(gst);
         }
         function setcustomerState(){
        	 var state=$("#customer_state").val();
        	 $("#our_state1").val("");
        	 $("#our_state1").val(state);
         }
         function setcustomerStateCode(){
        	 var stateCode=$("#customer_state_code").val();
        	 $("#our_state_code1").val("");
        	 $("#our_state_code1").val(stateCode);
         } 
         $(document).ready(function () {
        	//alert("loading");
        	//logoutcheck();
        	getNotificationPanel();
        	//getMenuBarNavigation();
         	getInvoiceRequests();
	     });
         
         
         
         
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