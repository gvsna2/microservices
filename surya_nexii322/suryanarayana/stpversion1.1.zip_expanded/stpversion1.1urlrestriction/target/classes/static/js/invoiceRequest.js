/*******************************************************************************
 * @Author Trineesha.Mandapati
 * @Version 1.0
 * 
 * 
 * /************* GET INVOICE REQUEST FUNCTION
 ******************************************************************************/

function getInvoiceRequests() {
	// alert("get");
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
				type : "GET",
				url : appBasePath + "/invoice/getinvoice",
				beforeSend : function() {
					// alert("before getting roles list");
				},
				success : function(data) {
					$('#invoiceRequestList').html("");
					if (data.length > 0) {
						var invoiceDate=null;
						var poDate=null;
						$.each(data,function(index, item) {
							invoiceDate=item.invoicePdfDate.split(" ");
							poDate=item.pODate.split(" ");
							//alert(item.cGSTRate+"............item.cGSTRate");
							item.igstApplicable=0;
											var eachrow = "<tr class=\"gradeX\">"
													+ "<td>"+ item.invoiceNumber+ "</td>"
													+ "<td>"+ invoiceDate[0]+"</td>"
													+ "<td>"+ item.clientName+ "</td>"
													+ "<td>"+ item.project_Name+ "</td>"
													+ "<td>"+ item.employeeName+ "</td>"
													+ "<td>"+ item.pONumber+ "</td>"
													+ "<td>"+ poDate[0]+ "</td>"
													+ "<td>"+ item.clientLocation+ "</td>"
													+ "<td>"+ item.totalAmount+ "</td>"
													+ '<td class="actions"><a href="#" class="on-default text-info m-r-10" onclick=\"requestEdit(\''+ item.invoiceRequestId
													+ '\',\''+ item.invoiceId+ '\',\''+ item.invoiceNumber
													+ '\',\''+ invoiceDate[0]+ '\',\''+ item.pONumber
													+ '\',\''+ poDate[0]+ '\',\''+ item.supplierReferanceNumber
													+ '\',\''+ item.shipAddLane1+ '\',\''+ item.shipAddLane2
													+ '\',\''+ item.shipAddState+ '\',\''+ item.shipAddPincode
													+ '\',\''+ item.billingAddLane1+ '\',\''+ item.billingAddLane2
													+ '\',\''+ item.billingAddState+ '\',\''+ item.billingAddPincode
													+ '\',\''+ item.vendorAddLane1+ '\',\''+ item.vendorAddLane2
													+ '\',\''+ item.vendorAddState+ '\',\''+ item.vendorAddPincode
													+ '\',\''+ item.project_Name
													+ '\',\''+ item.period+ '\',\''+ item.employeeName
													+ '\',\''+ item.hSNSAC+ '\',\''+ item.quantity
													+ '\',\''+ item.billingDays+ '\',\''+ item.totalWorkingDays
													+ '\',\''+ item.rate+ '\',\''+ item.taxableValue
													+ '\',\''+ item.cGSTRate+ '\',\''+ item.cGSTAmount
													+ '\',\''+ item.sGSTRate+ '\',\''+ item.sGSTAmount
													+ '\',\''+ item.iGSTRate+ '\',\''+ item.iGSTAmount
													+ '\',\''+ item.igstApplicable+ '\',\''+ item.totalAmount
													+ '\',\''+ item.accountHolderName+ '\',\''+ item.bankName
													+ '\',\''+ item.bankAcNo+ '\',\''+ item.iFSCCode
													+ '\',\''+ item.customerPan
													+ '\',\''+ item.customerGstin+ '\',\''+ item.customerState
													+ '\',\''+ item.customerStateCode+ '\',\''+ item.ourPan
													+ '\',\''+ item.ourGstin+ '\',\''+ item.ourState
													+ '\',\''+ item.ourStateCode+ '\',\''+ item.employID+ '\',\''+ item.project
													+ '\',\''+ item.currencyType+ '\',\''+ item.fkPoId+ '\',\''+ item.invoiceDate+ '\',\''+ item.clientName
													+ '\',\''+ item.shippinPanNumber+ '\',\''+ item.shippingGstin+ '\',\''+ item.shippingState+ '\',\''+ item.shippingStateCode
													+ '\');\" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a"><i class="fa fa-pencil"></i></a><a href="#" onclick=\"invoiceView(\''+ item.invoiceRequestId
													+ '\',\''+ item.invoiceId+ '\',\''+ item.invoiceNumber
													+ '\',\''+ invoiceDate[0]+ '\',\''+ item.pONumber
													+ '\',\''+ poDate[0]+ '\',\''+ item.supplierReferanceNumber
													+ '\',\''+ item.shipAddLane1+ '\',\''+ item.shipAddLane2
													+ '\',\''+ item.shipAddState+ '\',\''+ item.shipAddPincode
													+ '\',\''+ item.billingAddLane1+ '\',\''+ item.billingAddLane2
													+ '\',\''+ item.billingAddState+ '\',\''+ item.billingAddPincode
													+ '\',\''+ item.vendorAddLane1+ '\',\''+ item.vendorAddLane2
													+ '\',\''+ item.vendorAddState+ '\',\''+ item.vendorAddPincode
													+ '\',\''+ item.project_Name
													+ '\',\''+ item.period+ '\',\''+ item.employeeName
													+ '\',\''+ item.hSNSAC+ '\',\''+ item.quantity
													+ '\',\''+ item.billingDays+ '\',\''+ item.totalWorkingDays
													+ '\',\''+ item.rate+ '\',\''+ item.taxableValue
													+ '\',\''+ item.cGSTRate+ '\',\''+ item.cGSTAmount
													+ '\',\''+ item.sGSTRate+ '\',\''+ item.sGSTAmount
													+ '\',\''+ item.iGSTRate+ '\',\''+ item.iGSTAmount
													+ '\',\''+ item.igstApplicable+ '\',\''+ item.totalAmount
													+ '\',\''+ item.accountHolderName+ '\',\''+ item.bankName
													+ '\',\''+ item.bankAcNo+ '\',\''+ item.iFSCCode
													+ '\',\''+ item.customerPan
													+ '\',\''+ item.customerGstin+ '\',\''+ item.customerState
													+ '\',\''+ item.customerStateCode+ '\',\''+ item.ourPan
													+ '\',\''+ item.ourGstin+ '\',\''+ item.ourState
													+ '\',\''+ item.ourStateCode+ '\',\''+ item.employID+ '\',\''+ item.project+ '\',\''+ item.currencyType+ '\',\''+ item.fkPoId+ '\',\''+ item.invoiceDate+ '\',\''+ item.clientName
													+ '\',\''+ item.shippinPanNumber+ '\',\''+ item.shippingGstin+ '\',\''+ item.shippingState+ '\',\''+ item.shippingStateCode
													+ '\');\" class="btn btn-success m-l-15" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">Generate</a></td>'
													+ "</tr>";

											$('#invoiceRequestList').append(eachrow);
										});
						$('#datatable-responsive').DataTable();
						/* alert("data fetched"); */
					} else {
						$('#invoiceRequestList').append("");
						$('#datatable-responsive').DataTable({
							"language": {
								"emptyTable": "No data found for invoice requests"
							}
						});
					}

				},
				error : function(req, status, msg) {

				}
			});
}

function requestEdit(fkInvoiceRequestId, invoiceId, invoiceNumber, invoicePdfDate, poNumber,poDate,
		supplierRefNumber,shipAddLane1,shipAddLane2,shipAddState,shipAddPincode,
		billingAddLane1,billingAddLane2,billingAddState,billingAddPincode,vendorAddLane1,
		vendorAddLane2,vendorAddState,vendorAddPincode,project_Name,period,employeeName,
		hsnSac,quantity,billingDays,totalWorkDays,rate,taxableAmount,cgst_rate,cgstAmount,sgstRate,sgstAmount,
		igstRate,igstAmount,igstApplicable,totalAmount,accountHolderName,bankName,accountNumber,ifscCode,
		customerPan,customerGstin,customerState,customerStateCode,our_pan,ourGstin,ourState,ourStateCode,employID,project,currencyType,fkPoId,invoiceDate,clientName,
		shippinPanNumber,shippingGstin,shippingState,shippingStateCode) {
	//logoutcheck();
	//alert(supplierRefNumber+"...supplierRefNumber");
	
	getInvoiceDetails(fkInvoiceRequestId, invoiceId, invoiceNumber, invoicePdfDate, poNumber,poDate,
			supplierRefNumber,shipAddLane1,shipAddLane2,shipAddState,shipAddPincode,
			billingAddLane1,billingAddLane2,billingAddState,billingAddPincode,vendorAddLane1,
			vendorAddLane2,vendorAddState,vendorAddPincode,project_Name,period,employeeName,
			hsnSac,quantity,billingDays,totalWorkDays,rate,taxableAmount,cgst_rate,cgstAmount,sgstRate,sgstAmount,
			igstRate,igstAmount,igstApplicable,totalAmount,accountHolderName,bankName,accountNumber,ifscCode,
			customerPan,customerGstin,customerState,customerStateCode,our_pan,ourGstin,ourState,ourStateCode,employID,project,currencyType,fkPoId,invoiceDate,clientName,
			shippinPanNumber,shippingGstin,shippingState,shippingStateCode);

	Custombox.open({
		target : "#custom-modal-edit",
		effect : "fadein"
	});
}


function getInvoiceDetails(fkInvoiceRequestId, invoiceId, invoiceNumber, invoicePdfDate, poNumber,poDate,
		supplierRefNumber,shipAddLane1,shipAddLane2,shipAddState,shipAddPincode,
		billingAddLane1,billingAddLane2,billingAddState,billingAddPincode,vendorAddLane1,
		vendorAddLane2,vendorAddState,vendorAddPincode,project_Name,period,employeeName,
		hsnSac,quantity,billingDays,totalWorkDays,rate,taxableAmount,cgst_rate,cgstAmount,sgstRate,sgstAmount,
		igstRate,igstAmount,igstApplicable,totalAmount,accountHolderName,bankName,accountNumber,ifscCode,
		customerPan,customerGstin,customerState,customerStateCode,our_pan,ourGstin,ourState,ourStateCode,employID,project,currencyType,fkPoId,invoiceDate
		,clientName,shippinPanNumber,shippingGstin,shippingState,shippingStateCode) {
	//alert("currencyType="+currencyType);
	/*var appBasePath=getAppBasePath(); 
	var url=appBasePath+"/invoice/editlistinvoicedetails?requestId="+requestid+"&invoiceId="+invoiceid+"&invoiceNumber="+invoiceNumber+"&invoiceDate="+invoiceDate+"&poNumber="+poNumber;
    $.ajax({
		type : "GET",
		url : url,
		success : function(data) {*/
	//alert(supplierRefNumber+"..............supplierRefNumber");
	
			 	$("#invoicerequestId").val(fkInvoiceRequestId);
				$("#invoiceId").val(invoiceId);
				$("#project_id").val(project);
				$("#emp_id").val(employID);
				$("#po_id").val(fkPoId);
				$("#currency_type").val(currencyType);
				$("#po_date").val(poDate);
				$("#invoice_number").val(invoiceNumber);
				//$("#invoice_number").prop("readonly", true);
				$("#invoice_date").val(invoiceDate);
				$("#invoice_pdf_date").val(invoicePdfDate);
				//$("#invoice_date").prop("readonly", true);
				$("#po_number").val(poNumber);
				//$("#po_number").prop("readonly", true);
				$("#order_date").val(poDate);
				//$("#order_date").prop("readonly", true);
				$("#supp_ref_number").val(supplierRefNumber);
				//$("#supp_ref_number").prop("readonly", true);
				$("#vendor_address_lane1").val(vendorAddLane1);
				$("#vendor_address_lane2").val(vendorAddLane2);
				$("#vendor_address_state").val(vendorAddState);
				$("#vendor_address_pincode").val(vendorAddPincode);
				if(our_pan=="null"){
					$("#our_pan").val("");
				}else{
					$("#our_pan").val(our_pan);
				}
				if(ourGstin=="null"){
					 $("#our_gstin").val("");
				}else{
					$("#our_gstin").val(ourGstin);
				}
				if(ourState=="null"){
					$("#our_state").val("");
				}else{
					$("#our_state").val(ourState);
				}
				if(ourStateCode=="null"){
					$("#our_state_code").val("");
				}else{
					$("#our_state_code").val(ourStateCode);
				}
				$("#client_name").val(clientName);
			    $("#billing_address_lane1").val(billingAddLane1);
			    $("#billing_address_lane2").val(billingAddLane2);
			    $("#billing_address_state").val(billingAddState);
			    $("#billing_address_pincode").val(billingAddPincode);
			    
			    if(customerPan=="null"){
			    	$("#customer_pan").val("");
				}else{
					$("#customer_pan").val(customerPan);
				}
			    if(customerGstin=="null"){
			    	$("#customer_gstin").val(""); 
				}else{
					$("#customer_gstin").val(customerGstin); 
				}
			    if(customerState=="null"){
			    	$("#customer_state").val("");
				}else{
					$("#customer_state").val(customerState);
				}
			    if(customerStateCode=="null"){
			    	$("#customer_state_code").val("");
				}else{
					$("#customer_state_code").val(customerStateCode); 
				}
			    
			    $("#our_pan1").val(shippinPanNumber);
			    $("#our_gstin1").val(shippingGstin);
			    $("#our_state1").val(shippingState);
			    $("#our_state_code1").val(shippingStateCode);
			    
			    $("#client_name1").val(clientName);
			    $("#shipping_address_lane1").val(shipAddLane1);
			    $("#shipping_address_lane2").val(shipAddLane2);
			    $("#shipping_address_state").val(shipAddState);
			    $("#shipping_address_pincode").val(shipAddPincode);
			    $("#project_name").val(project_Name);
			    //$("#project_name").prop("readonly", true);
			    $("#period").val(period);
			    $("#employee_name").val(employeeName);
			    //$("#employee_name").prop("readonly", true);
			    $("#working_days").val(totalWorkDays);
			    //$("#working_days").prop("readonly", true);
			    $("#billing_days").val(billingDays);
			    if(hsnSac=="null"){
			    	$("#hsn_sac").val("");
				}else{
					$("#hsn_sac").val(hsnSac);
				}
			    $("#quantity").val(quantity);
			    $("#rate").val(rate);
			    $("#taxable_value").val(taxableAmount);
			    $("#cgst_rate").val(cgst_rate);
			    $("#cgst_amount").val(cgstAmount);
			   // $("#cgst_amount").prop("readonly", true);
			    $("#sgst_rate").val(sgstRate);
		        $("#sgst_amount").val(sgstAmount);
		      //  $("#sgst_amount").prop("readonly", true);
		        $("#igst_rate").val(igstRate);
		        $("#igst_amount").val(igstAmount);
		      // $("#igst_amount").prop("readonly", true);
			    $("#total").val(totalAmount);
			    $("#acc_holder_name").val(accountHolderName);
			    $("#account_number").val(accountNumber);
			    $("#bank_name").val(bankName);
			    $("#ifsc_code").val(ifscCode);
			    var totalGst=0;
			    totalGst=(+cgstAmount)+(+sgstAmount)+(+igstAmount);
			    totalGst=totalGst.toFixed(2);
			    
			    $("#total_amount_before_tax").val(taxableAmount);
			    $("#cgst_amount1").val(cgstAmount);
			    $("#sgst_amount1").val(sgstAmount);
			    $("#igst_amount1").val(igstAmount);
			    $("#total_gst").val(totalGst);
			    $("#total_amount_after_tax").val(totalAmount);
			    $("#amount_in_words").show();
			    $("#amount_in_words").val(number2text(totalAmount));
			    //alert($("#amount_in_words").val());
			
		/*},
		error : function(req, status, msg) {

		}
	});*/

}

function invoiceView(fkInvoiceRequestId, invoiceId, invoiceNumber, invoicePdfDate, poNumber,poDate,
		supplierRefNumber,shipAddLane1,shipAddLane2,shipAddState,shipAddPincode,
		billingAddLane1,billingAddLane2,billingAddState,billingAddPincode,vendorAddLane1,
		vendorAddLane2,vendorAddState,vendorAddPincode,project_Name,period,employeeName,
		hsnSac,quantity,billingDays,totalWorkDays,rate,taxableAmount,cgst_rate,cgstAmount,sgstRate,sgstAmount,
		igstRate,igstAmount,igstApplicable,totalAmount,accountHolderName,bankName,accountNumber,ifscCode,
		customerPan,customerGstin,customerState,customerStateCode,our_pan,ourGstin,ourState,ourStateCode,empId,ProjectId,currencyType,fkPoId,invoiceDate,clientName,
		shippinPanNumber,shippingGstin,shippingState,shippingStateCode) {
	
		//logoutcheck();
				//alert("currencyType1........"+currencyType)
	
				$("#htmlView").html("");
				//€$
				//var invoiceDated=invoicePdfDate.split(" ");
				var words=number2text(totalAmount);
				var totalAmount1=""+totalAmount;
				var rate1=""+rate;
				var taxableAmount1=""+taxableAmount;
				if(currencyType=="USD"){
					words=words.replace("RUPEES","DOLLARS");
					totalAmount1="&#036;"+totalAmount;
					rate1=""+rate;
					taxableAmount1=""+taxableAmount;
				}else if(currencyType=="EUR"){
					words=words.replace("RUPEES","EUROS");
					totalAmount1="&#8364;"+totalAmount;
					rate1=""+rate;
					taxableAmount1=""+taxableAmount;
				}else{
					words=""+words;
					totalAmount1="Rs."+totalAmount;
					rate1=""+rate;
					taxableAmount1=""+taxableAmount;
				}
				
				
				var htmlView=" <button type='button' class='close' onclick='Custombox.close();'> " +
						"<span>&times;</span><span class='sr-only'>Close</span> </button>" +
						"<h4 class='custom-modal-title'>Invoice</h4><div class='custom-modal-text'>" +
						"<div class='panel-body'><div class='clearfix'> <div class='pull-right'>" +
						"<h4 class='text-right'><img src='assets/images/Invoice-logo.png' alt='Nexiilabs'>" +
						"</h4> </div></div><hr><div class='row'><div class='col-md-12'>" +
						"<div class='pull-left m-t-10 text-left'><address>" +
						"<strong>"+vendorAddLane1+"</strong><br>"+vendorAddLane2+
						"<br>"+vendorAddState+"<br>"+vendorAddPincode+"." +
						"<p class='m-t-10'>PAN No: <strong>"+our_pan+"</strong><br>" +
						"GSTIN: <strong>"+ourGstin+"</strong><br>" +
						"State: <strong>"+ourState+"</strong><br>" +
						" State Code: <strong>"+ourStateCode+"</strong></p></address></div>" +
						"<div class='pull-right m-t-10 text-right'>" +
						"<p>Invoice No: <strong id='strong'>"+invoiceNumber+"</strong><br>Dated: <strong>"+invoicePdfDate+"</strong></p>" +
						" <p>Buyer's Order No: <strong>"+poNumber+"</strong><br>Dated: <strong>"+poDate+"</strong></p>" +
						"<p>Supplier's Ref. No: <strong>"+supplierRefNumber+"</strong></p></div></div> </div>" +
						"<div class='m-h-20'></div> <div class='row'><div class='col-md-12'>" +
						"<div class='table-responsive table-bordered table-striped text-left'>" +
						"<table class='table'>" +
						"<thead>" +
						"<tr> " +
						"<td class='col-sm-6 info'>Bill To</td>" +
						"<td class='col-sm-6 info'>Ship To</td>" +
						"</tr>" +
						"</thead>" +
						"<tbody>" +
						"<tr>" +
						"<td class='col-sm-6'><strong>"+clientName+"</strong>" +
						"<br>"+billingAddLane1+
						"<br>"+billingAddLane2+
						"<br>"+billingAddState+
						"<br>"+billingAddPincode+"." +
						"<p class='m-t-10'>PAN No: <strong>"+customerPan+"</strong>" +
						"<br>GSTIN: <strong>"+customerGstin+"</strong>" +
						"<br>State: <strong>"+customerState+"</strong>" +
						"<br>State Code: <strong>"+customerStateCode+"</strong></p>" +
						"</td>" +
						"<td class='col-sm-6'><strong>"+clientName+"</strong>" +
						"<br>"+shipAddLane1+
						"<br>"+shipAddLane2+
						"<br>"+shipAddState+
						"<br>"+shipAddPincode+"." +
						"<p class='m-t-10'>PAN No: <strong>"+shippinPanNumber+"</strong>" +
						"<br>GSTIN: <strong>"+shippingGstin+"</strong>" +
						"<br>State: <strong>"+shippingState+"</strong>" +
						"<br>State Code: <strong>"+shippingStateCode+"</strong>" +
						"</p>" +
						"</td>" +
						"</tr>" +
						"</tbody> " +
						"</table>" +
						"</div>" +
						"</div> " +
						"</div>" +
						"<div class='m-h-40'></div>" +
						"<div class='row'>" +
						"<div class='col-md-12'>" +
						"<div class='table-responsive table-bordered text-left'>" +
						"<table class='table'>" +
						"<thead>" +
						"<tr>" +
						"<td class='info'>S.No.</td> " +
						"<td class='info col-md-2'>Description of services</td>" +
						"<td class='info'>HSN SAC</td>" +
						"<td class='info'>Qty</td>" +
						"<td class='info'>Rate</td> " +
						"<td class='info'>Taxable value</td> " +
						"<td class='info'>CGST Rate</td> " +
						"<td class='info'>CGST Amount</td>" +
						"<td class='info'>SGST Rate</td>" +
						"<td class='info'>SGST Amount</td> " +
						"<td class='info'>IGST Rate</td> " +
						"<td class='info'>IGST Amount</td>" +
						"<td class='info'>Total Amount.</td> " +
						"</tr>" +
						"</thead> " +
						"<tbody> " +
						/*"<tr> " +
						"<td class='brt'>01</td>" +
						"<td class='brt'>Project Name:<br>"+data.project_Name+"</td>" +
						"<td class='brt'>-</td>" +
						"<td class='brt'>-</td>" +
						"<td class='brt'>-</td>" +
						"<td class='brt'>-</td>" +
						"<td class='brt'>-</td>" +
						"<td class='brt'>-</td>" +
						"<td class='brt'>-</td>" +
						"<td class='brt'>-</td>" +
						"<td class='brt'>-</td>" +
						"<td class='brt'>-</td>" +
						"<td>-</td>" +
						"</tr>" +*/
						"<tr>" +
						"<td class='brt'>01</td>" +
						"<td class='brt'>Services for <br/>"+project_Name+"<br/>Resource Name: "+employeeName+"<br>Billing Period:"+period+"<br>Working Days:"+totalWorkDays+"<br>Billing Days:"+billingDays+"</td>" +
						"<td class='brt'>"+hsnSac+"</td> " +
						"<td class='brt'>"+quantity+"</td> " +
						"<td class='brt'>"+rate1+"</td>" +
						"<td class='brt'>"+taxableAmount1+"</td>" +
						"<td class='brt'>"+cgst_rate+"%</td>" +
						"<td class='brt'>"+cgstAmount+"</td>" +
						"<td class='brt'>"+sgstRate+"%</td>" +
						"<td class='brt'>"+sgstAmount+"</td>" +
						"<td class='brt'>"+igstRate+"%</td>" +
						"<td class='brt'>"+igstAmount+"</td>" +
						"<td>"+totalAmount+"</td>" +
						"</tr>" +
						"<tr>" +
						"<td colspan='3' class='brt text-right'>Total</td>" +
						"<td class='brt'>1</td>" +
						"<td class='brt'></td>" +
						"<td class='brt'></td>" +
						"<td class='brt'></td> " +
						"<td class='brt'></td> " +
						"<td class='brt'></td>" +
						"<td class='brt'></td>" +
						"<td class='brt'></td>" +
						"<td class='brt'></td>" +
						"<td>"+totalAmount1+"</td> " +
						"</tr>" +
						"</tbody> " +
						"</table>" +
						"</div>" +
						"</div>" +
						"</div>" +
						"<div class='m-h-40'></div>" +
						"<div class='row'>" +
						"<div class='col-md-12'>" +
						"<div class='col-md-6'>" +
						"<p class='text-left'><strong>Total Invoice Amount in Words</strong>" +
						"<br>"+words+"</p>" +
						"<p class='text-left'><strong>Bank Details</strong>" +
						"<br>Account Holder Name: <strong>"+accountHolderName+"</strong>" +
						"<br>Bank Name: <strong>"+bankName+"</strong>" +
						"<br>Bank Account Number: <strong>"+accountNumber+"</strong>" +
						"<br>IFSC: <strong>"+ifscCode+"</strong>" +
						"<br>" +
						"</p>" +
						"<p class='text-left'><strong>Declaraion</strong>" +
						"<br>We declare that this invoice shows the actual price of the services described and that all particulars are true and correct</p>" +
						"</div>" +
						"<div class='col-md-6'><div class='table-responsive table-bordered text-left'>" +
						"<table class='table m-b-0'>" +
						"<tr>" +
						"<td class='bg-purple text-white'>Total Amount before Tax</td>" +
						"<td>"+taxableAmount1+"</td>" +
						"</tr>" +
						"<tr>" +
						"<td class='bg-inverse text-white'>Add CGST</td>" +
						"<td>"+cgstAmount+"</td>" +
						"</tr>" +
						"<tr>" +
						"<td class='bg-inverse text-white'>Add SGST</td>" +
						"<td>"+sgstAmount+"</td>" +
						"</tr> " +
						"<tr>" +
						"<td class='bg-inverse text-white'>Add IGST</td>" +
						"<td>"+igstAmount+"</td>" +
						"</tr>" +
						"<tr>" +
						"<td class='bg-inverse text-white'>Total GST</td>" +
						"<td>"+((+cgstAmount)+(+sgstAmount)+(+igstAmount))+"</td>" +
						"</tr>" +
						"<tr>" +
						"<td class='bg-purple text-white'>Total Amount After Tax</td>" +
						"<td>"+totalAmount1+"</td>" +
						"</tr>" +
						"<tr>" +
						"<td colspan='2' class='bg-success text-white text-center'>" +
						"<strong>For Nexii IT Labs Private Limited</strong>" +
						"</td>" +
						"</tr>" +
						"</table>" +
						"</div>" +
						"</div>" +
						"</div>" +
						"</div>" +
						"<div class='m-h-40'></div>" +
						"<div class='hidden-print'>" +
						"<div class='pull-right'> <div id='printdata'></div> " +
						'<button class="ladda-button  btn btn-warning btn-info" data-style="slide-left" id="printInvoice" onclick=\"printInvoice(\''+ fkInvoiceRequestId
						+ '\',\''+ invoiceId+ '\',\''+ invoiceNumber
						+ '\',\''+ invoiceDate+ '\',\''+ poNumber
						+ '\',\''+ poDate+ '\',\''+ supplierRefNumber
						+ '\',\''+ shipAddLane1+ '\',\''+ shipAddLane2
						+ '\',\''+ shipAddState+ '\',\''+ shipAddPincode
						+ '\',\''+ billingAddLane1+ '\',\''+ billingAddLane2
						+ '\',\''+ billingAddState+ '\',\''+ billingAddPincode
						+ '\',\''+ vendorAddLane1+ '\',\''+ vendorAddLane2
						+ '\',\''+ vendorAddState+ '\',\''+ vendorAddPincode
						+ '\',\''+ project_Name
						+ '\',\''+ period+ '\',\''+ employeeName  
						+ '\',\''+ hsnSac+ '\',\''+ quantity
						+ '\',\''+ billingDays+ '\',\''+ totalWorkDays
						+ '\',\''+ rate+ '\',\''+ taxableAmount
						+ '\',\''+ cgst_rate+ '\',\''+ cgstAmount
						+ '\',\''+ sgstRate+ '\',\''+ sgstAmount
						+ '\',\''+ igstRate+ '\',\''+ igstAmount
						+ '\',\''+ igstApplicable+ '\',\''+ totalAmount
						+ '\',\''+ accountHolderName+ '\',\''+ bankName
						+ '\',\''+ accountNumber+ '\',\''+ ifscCode
						+ '\',\''+ customerPan
						+ '\',\''+ customerGstin+ '\',\''+ customerState
						+ '\',\''+ customerStateCode+ '\',\''+ our_pan
						+ '\',\''+ ourGstin+ '\',\''+ ourState
						+ '\',\''+ ourStateCode+ '\',\''+ empId+ '\',\''+ ProjectId
						+ '\',\''+ currencyType+ '\',\''+ fkPoId+ '\',\''+ invoicePdfDate+ '\',\''+ clientName
						+ '\',\''+ shippinPanNumber+ '\',\''+ shippingGstin+ '\',\''+ shippingState+ '\',\''+ shippingStateCode
						+ '\');\">Print Invoice</button>&nbsp;' +
						'<button class="ladda-button  btn btn-success" data-style="slide-left" id="downloadInvoice" onclick=\"downloadInvoice(\''+ fkInvoiceRequestId
						+ '\',\''+ invoiceId+ '\',\''+ invoiceNumber
						+ '\',\''+ invoiceDate+ '\',\''+ poNumber
						+ '\',\''+ poDate+ '\',\''+ supplierRefNumber
						+ '\',\''+ shipAddLane1+ '\',\''+ shipAddLane2
						+ '\',\''+ shipAddState+ '\',\''+ shipAddPincode
						+ '\',\''+ billingAddLane1+ '\',\''+ billingAddLane2
						+ '\',\''+ billingAddState+ '\',\''+ billingAddPincode
						+ '\',\''+ vendorAddLane1+ '\',\''+ vendorAddLane2
						+ '\',\''+ vendorAddState+ '\',\''+ vendorAddPincode
						+ '\',\''+ project_Name
						+ '\',\''+ period+ '\',\''+ employeeName  
						+ '\',\''+ hsnSac+ '\',\''+ quantity
						+ '\',\''+ billingDays+ '\',\''+ totalWorkDays
						+ '\',\''+ rate+ '\',\''+ taxableAmount
						+ '\',\''+ cgst_rate+ '\',\''+ cgstAmount
						+ '\',\''+ sgstRate+ '\',\''+ sgstAmount
						+ '\',\''+ igstRate+ '\',\''+ igstAmount
						+ '\',\''+ igstApplicable+ '\',\''+ totalAmount
						+ '\',\''+ accountHolderName+ '\',\''+ bankName
						+ '\',\''+ accountNumber+ '\',\''+ ifscCode
						+ '\',\''+ customerPan
						+ '\',\''+ customerGstin+ '\',\''+ customerState
						+ '\',\''+ customerStateCode+ '\',\''+ our_pan
						+ '\',\''+ ourGstin+ '\',\''+ ourState
						+ '\',\''+ ourStateCode+ '\',\''+ empId+ '\',\''+ ProjectId
						+ '\',\''+ currencyType+ '\',\''+ fkPoId+ '\',\''+ invoicePdfDate+ '\',\''+ clientName
						+ '\',\''+ shippinPanNumber+ '\',\''+ shippingGstin+ '\',\''+ shippingState+ '\',\''+ shippingStateCode
						+ '\');\">Download Invoice</button>&nbsp;' +
						'<button class="ladda-button  btn btn-info" data-style="slide-left" id="sendInvoice" onclick=\"sendInvoice(\''+ fkInvoiceRequestId
						+ '\',\''+ invoiceId+ '\',\''+ invoiceNumber
						+ '\',\''+ invoiceDate+ '\',\''+ poNumber
						+ '\',\''+ poDate+ '\',\''+ supplierRefNumber
						+ '\',\''+ shipAddLane1+ '\',\''+ shipAddLane2
						+ '\',\''+ shipAddState+ '\',\''+ shipAddPincode
						+ '\',\''+ billingAddLane1+ '\',\''+ billingAddLane2
						+ '\',\''+ billingAddState+ '\',\''+ billingAddPincode
						+ '\',\''+ vendorAddLane1+ '\',\''+ vendorAddLane2
						+ '\',\''+ vendorAddState+ '\',\''+ vendorAddPincode
						+ '\',\''+ project_Name
						+ '\',\''+ period+ '\',\''+ employeeName  
						+ '\',\''+ hsnSac+ '\',\''+ quantity
						+ '\',\''+ billingDays+ '\',\''+ totalWorkDays
						+ '\',\''+ rate+ '\',\''+ taxableAmount
						+ '\',\''+ cgst_rate+ '\',\''+ cgstAmount
						+ '\',\''+ sgstRate+ '\',\''+ sgstAmount
						+ '\',\''+ igstRate+ '\',\''+ igstAmount
						+ '\',\''+ igstApplicable+ '\',\''+ totalAmount
						+ '\',\''+ accountHolderName+ '\',\''+ bankName
						+ '\',\''+ accountNumber+ '\',\''+ ifscCode
						+ '\',\''+ customerPan
						+ '\',\''+ customerGstin+ '\',\''+ customerState
						+ '\',\''+ customerStateCode+ '\',\''+ our_pan
						+ '\',\''+ ourGstin+ '\',\''+ ourState
						+ '\',\''+ ourStateCode+ '\',\''+ empId+ '\',\''+ ProjectId
						+ '\',\''+ currencyType+ '\',\''+ fkPoId+ '\',\''+ invoicePdfDate+ '\',\''+ clientName
						+ '\',\''+ shippinPanNumber+ '\',\''+ shippingGstin+ '\',\''+ shippingState+ '\',\''+ shippingStateCode
						+ '\');\">Send Invoice</button>&nbsp;' +
						'<button class="ladda-button  btn btn-inverse" data-style="slide-left" id="freezeInvoice" onclick=\"freezeInvoice(\''+ fkInvoiceRequestId
						+ '\',\''+ invoiceId
						+ '\');\">Freeze Invoice</button>&nbsp;' +
						"<button class='btn btn-default waves-effect' onclick='Custombox.close();'>Cancel</button>" +
						" </div>" +
						"</div><span id='send_msg'></span>" +
						"</div>" +
						"<div style=\" vertical-align:top; text-align:center; height:59px; padding:5px;font-size:15px;\">" + 
						" <i>Computer Generated Invoice, No Signature required</i> " + 
						" </div>" + 
						"</div>";
				//alert(hsnSac);
				if(hsnSac=="null"){
					//alert("HANSAC should not be empty");
					$("#status_msg").show();
					$("#status_msg").css("color","red");
					$("#status_msg").html("HANSAC should not be empty");
					setTimeout(function(){
			    		$('#status_msg').html("");
						}, 10000);
					return false; 
				}else{
					$("#htmlView").html(htmlView);
					Custombox.open({
						target : "#custom-modal-generate",
						effect : "fadein"
					});
				}
			
}
function freezeInvoice(fkRequestId,invoiceId){
	var l = $('#freezeInvoice').ladda();
    l.ladda('start');
	var appBasePath=getAppBasePath();
	   $.ajax({
		   url: appBasePath+"/invoice/freezeinvoice?fkRequestId="+fkRequestId+"&invoiceId="+invoiceId,
		   dataType: "json",
		   success: function( data ) {
			   l.ladda('stop');
				//alert(data);
				if(data.statusCode==1){
				$("#send_msg").css("color","green");
				$("#send_msg").html(data.message);
				$("#datatable-responsive").dataTable().fnDestroy();
				getInvoiceRequests();
				getNotificationPanel();
				Custombox.close();
				}else{
					$("#send_msg").css("color","red");
					$("#send_msg").html(data.message);
				}
				
				
			},
			error : function(req, status, msg) {
				l.ladda('stop');
			}
	   });
	    
}

function sendInvoice(fkInvoiceRequestId, invoiceId, invoiceNumber, invoiceDate, poNumber,poDate,
		supplierRefNumber,shipAddLane1,shipAddLane2,shipAddState,shipAddPincode,
		billingAddLane1,billingAddLane2,billingAddState,billingAddPincode,vendorAddLane1,
		vendorAddLane2,vendorAddState,vendorAddPincode,project_Name,period,employeeName,
		hsnSac,quantity,billingDays,totalWorkDays,rate,taxableAmount,cgst_rate,cgstAmount,sgstRate,sgstAmount,
		igstRate,igstAmount,igstApplicable,totalAmount,accountHolderName,bankName,accountNumber,ifscCode,
		customerPan,customerGstin,customerState,customerStateCode,our_pan,ourGstin,ourState,ourStateCode,empId,
		ProjectId,currencyType,fkPoId,invoicePdfDate,clientName,shippinPanNumber,shippingGstin,shippingState,shippingStateCode){
	var l = $('#sendInvoice').ladda();
    l.ladda('start');
    
	var appBasePath=getAppBasePath();
	//return false;
    var url=appBasePath+"/invoice/genertepdfinvoice";
    
    var params = {
    		invoiceRequestId : fkInvoiceRequestId ,
    		invoiceId : invoiceId,
    		invoiceNumber : invoiceNumber ,
    		cGSTAmount : cgstAmount ,
    		total : totalAmount ,
    		invoiceDate : invoiceDate ,
    		sGSTAmount : sgstAmount ,
    		bankName : bankName ,
    		pONumber : poNumber ,
			iGSTAmount:igstAmount,
			iFSCCode:ifscCode,
			pODate : poDate ,
			accountHolderName : accountHolderName,
			vendorAddressLane1:vendorAddLane1,
			vendorAddressLane2:vendorAddLane2,
			vendorAddressState:vendorAddState,
			vendorAddressPincode:vendorAddPincode,
			supplierReferanceNumber:supplierRefNumber,
			bankAcNo:accountNumber,
			project:ProjectId,
			quantity:quantity,
			billingAddressLane1:billingAddLane1,
			billingAddressLane2:billingAddLane2,
			billingAddressState:billingAddState,
			billingAddressPincode:billingAddPincode,
			period:period,
			billingDays:billingDays,
			employID:empId,
			rate:rate,
			shippingAddressLane1:shipAddLane1,
			shippingAddressLane2:shipAddLane2,
			shippingAddressState:shipAddState,
			shippingAddressPincode:shipAddPincode,
			hSNSAC:hsnSac,
			cGSTRate:cgst_rate,
			days:totalWorkDays,
			sGSTRate:sgstRate,
			taxableValue:taxableAmount,
			iGSTRate:igstRate,
			iGSTApplicable:igstApplicable,
			customerPan:customerPan,
			customerGstin:customerGstin,
			customerState:customerState,
			customerStateCode:customerStateCode,
			ourPan:our_pan,
			OurGstin:ourGstin,
			ourState:ourState,
			ourStateCode:ourStateCode,
			empName:employeeName,
			projectName:project_Name,
			currencyType:currencyType,
			//poId:fkPoId,
			invoicePdfDate:invoicePdfDate,
			clientName:clientName,
			shipingPan:shippinPanNumber,
			shippingGst:shippingGstin,
			shippingState:shippingState,
			shippingCode:shippingStateCode
	};
    var queryParam = jQuery.param( params );
    
    //alert(url);
	$.ajax({
		type : "GET",
		url : url,
		data: queryParam,
		success : function(data) {
			l.ladda('stop');
			//alert(data);
			if(data.statusCode==1){
			$("#send_msg").css("color","green");
			$("#send_msg").html(data.message);
			$("#datatable-responsive").dataTable().fnDestroy();
			getInvoiceRequests();
			getNotificationPanel();
			Custombox.close();
			}else{
				$("#send_msg").css("color","red");
				$("#send_msg").html(data.message);
			}
			
			
		},
		error : function(req, status, msg) {
			l.ladda('stop');
		}
	});
}
function downloadInvoice(fkInvoiceRequestId, invoiceId, invoiceNumber, invoiceDate, poNumber,poDate,
		supplierRefNumber,shipAddLane1,shipAddLane2,shipAddState,shipAddPincode,
		billingAddLane1,billingAddLane2,billingAddState,billingAddPincode,vendorAddLane1,
		vendorAddLane2,vendorAddState,vendorAddPincode,project_Name,period,employeeName,
		hsnSac,quantity,billingDays,totalWorkDays,rate,taxableAmount,cgst_rate,cgstAmount,sgstRate,sgstAmount,
		igstRate,igstAmount,igstApplicable,totalAmount,accountHolderName,bankName,accountNumber,ifscCode,
		customerPan,customerGstin,customerState,customerStateCode,our_pan,ourGstin,ourState,ourStateCode,empId,ProjectId,currencyType,fkPoId,invoicePdfDate,clientName,
		shippinPanNumber,shippingGstin,shippingState,shippingStateCode){
	var l = $('#downloadInvoice').ladda();
    l.ladda('start');
    
	var appBasePath=getAppBasePath();
	//return false;
    var url=appBasePath+"/invoice/downloadpdfinvoice?invoiceRequestId="+fkInvoiceRequestId+"&invoiceId="+invoiceId+"&invoiceNumber="+invoiceNumber+"&cGSTAmount="+cgstAmount+"&total="+totalAmount
    				   +"&invoiceDate="+invoiceDate+"&sGSTAmount="+sgstAmount+"&bankName="+bankName+"&pONumber="+poNumber
    				   +"&iGSTAmount="+igstAmount+"&iFSCCode="+ifscCode+"&pODate="+poDate+"&accountHolderName="+accountHolderName
    				   +"&vendorAddressLane1="+vendorAddLane1+"&vendorAddressLane2="+vendorAddLane2
    				   +"&vendorAddressState="+vendorAddState+"&vendorAddressPincode="+vendorAddPincode
    				   +"&supplierReferanceNumber="+supplierRefNumber+"&bankAcNo="+accountNumber+"&project="+ProjectId+"&quantity="+quantity
    				   +"&billingAddressLane1="+billingAddLane1+"&billingAddressLane2="+billingAddLane2
    				   +"&billingAddressState="+billingAddState+"&billingAddressPincode="+billingAddPincode
    				   +"&period="+period+"&billingDays="+billingDays+"&employID="+empId+"&rate="+rate
    				   +"&shippingAddressLane1="+shipAddLane1+"&shippingAddressLane2="+shipAddLane2
    				   +"&shippingAddressState="+shipAddState+"&shippingAddressPincode="+shipAddPincode
    				   +"&hSNSAC="+hsnSac+"&cGSTRate="+cgst_rate+"&days="+totalWorkDays+"&sGSTRate="+sgstRate+"&taxableValue="+taxableAmount
    				   +"&iGSTRate="+igstRate+"&iGSTApplicable="+igstApplicable+"&customerPan="+customerPan+"&customerGstin="+customerGstin
    				   +"&customerState="+customerState+"&customerStateCode="+customerStateCode+"&ourPan="+our_pan+"&OurGstin="+ourGstin
    				   +"&ourState="+ourState+"&ourStateCode="+ourStateCode+"&empName="+employeeName+"&projectName="+project_Name+"&currencyType="+ currencyType+"&invoicePdfDate="+ invoicePdfDate+"&clientName="+ clientName
    				   +"&shipingPan="+shippinPanNumber+"&shippingGst="+shippingGstin+"&shippingState="+shippingState+"&shippingCode="+shippingStateCode;
    //alert(url);
    window.location.href = url;
    setTimeout(function(){
    	l.ladda('stop');
    	Custombox.close();
    	$("#datatable-responsive").dataTable().fnDestroy();
    	getInvoiceRequests();
    	getNotificationPanel();
		}, 5000);
	
}

function printInvoice(fkInvoiceRequestId, invoiceId, invoiceNumber, invoiceDate, poNumber,poDate,
		supplierRefNumber,shipAddLane1,shipAddLane2,shipAddState,shipAddPincode,
		billingAddLane1,billingAddLane2,billingAddState,billingAddPincode,vendorAddLane1,
		vendorAddLane2,vendorAddState,vendorAddPincode,project_Name,period,employeeName,
		hsnSac,quantity,billingDays,totalWorkDays,rate,taxableAmount,cgst_rate,cgstAmount,sgstRate,sgstAmount,
		igstRate,igstAmount,igstApplicable,totalAmount,accountHolderName,bankName,accountNumber,ifscCode,
		customerPan,customerGstin,customerState,customerStateCode,our_pan,ourGstin,ourState,ourStateCode,empId,ProjectId,currencyType,fkPoId,invoicePdfDate,clientName,
		shippinPanNumber,shippingGstin,shippingState,shippingStateCode){
	//var l = $('#downloadInvoice').ladda();
   // l.ladda('start');
    
	var appBasePath=getAppBasePath();
	//return false;
    var url=appBasePath+"/invoice/printpdfinvoice?invoiceRequestId="+fkInvoiceRequestId+"&invoiceId="+invoiceId+"&invoiceNumber="+invoiceNumber+"&cGSTAmount="+cgstAmount+"&total="+totalAmount
    				   +"&invoiceDate="+invoiceDate+"&sGSTAmount="+sgstAmount+"&bankName="+bankName+"&pONumber="+poNumber
    				   +"&iGSTAmount="+igstAmount+"&iFSCCode="+ifscCode+"&pODate="+poDate+"&accountHolderName="+accountHolderName
    				   +"&vendorAddressLane1="+vendorAddLane1+"&vendorAddressLane2="+vendorAddLane2
    				   +"&vendorAddressState="+vendorAddState+"&vendorAddressPincode="+vendorAddPincode
    				   +"&supplierReferanceNumber="+supplierRefNumber+"&bankAcNo="+accountNumber+"&project="+ProjectId+"&quantity="+quantity
    				   +"&billingAddressLane1="+billingAddLane1+"&billingAddressLane2="+billingAddLane2
    				   +"&billingAddressState="+billingAddState+"&billingAddressPincode="+billingAddPincode
    				   +"&period="+period+"&billingDays="+billingDays+"&employID="+empId+"&rate="+rate
    				   +"&shippingAddressLane1="+shipAddLane1+"&shippingAddressLane2="+shipAddLane2
    				   +"&shippingAddressState="+shipAddState+"&shippingAddressPincode="+shipAddPincode
    				   +"&hSNSAC="+hsnSac+"&cGSTRate="+cgst_rate+"&days="+totalWorkDays+"&sGSTRate="+sgstRate+"&taxableValue="+taxableAmount
    				   +"&iGSTRate="+igstRate+"&iGSTApplicable="+igstApplicable+"&customerPan="+customerPan+"&customerGstin="+customerGstin
    				   +"&customerState="+customerState+"&customerStateCode="+customerStateCode+"&ourPan="+our_pan+"&OurGstin="+ourGstin
    				   +"&ourState="+ourState+"&ourStateCode="+ourStateCode+"&empName="+employeeName+"&projectName="+project_Name+"&currencyType="+ currencyType+"&invoicePdfDate="+ invoicePdfDate+"&clientName="+ clientName
    				   +"&shipingPan="+shippinPanNumber+"&shippingGst="+shippingGstin+"&shippingState="+shippingState+"&shippingCode="+shippingStateCode;
   // location.href= url;
    var a=window.open(url,"", "");
    a.print();
	setTimeout(function(){
		$("#datatable-responsive").dataTable().fnDestroy();
    	getInvoiceRequests();
    	getNotificationPanel();
		}, 5000);
    
  
}

function updateInvoice(){
	amountCal();
	//alert("update");
	var igstApplicable=0;
	var requestid=$("#invoicerequestId").val();
	var currencyType=$("#currency_type").val();
	var invoiceid=$("#invoiceId").val();
	var invoiceNumber=$("#invoice_number").val().trim();
	var invoiceDate=$("#invoice_date").val().trim();
    var poNumber=$("#po_number").val().trim();
    var poDate=$("#po_date").val().trim();
    var supRefNumber=$("#supp_ref_number").val().trim();
    var project=$("#project_id").val();
    var period=$("#period").val().trim();
    var empId=$("#emp_id").val();
    var hsnSac=$("#hsn_sac").val().trim();
    var days=$("#working_days").val();
    var taxableValue=$("#taxable_value").val();
    var ourPan=$("#our_pan").val().trim();
    var ourGstin=$("#our_gstin").val();
    var igstAmount=$("#igst_amount1").val();
    var igstRate=$("#igst_rate").val();
    var cgstAmount=$("#cgst_amount1").val();
    var cgstRate=$("#cgst_rate").val();
    var sgstAmount=$("#sgst_amount1").val();
    var sgstRate=$("#sgst_rate").val();
    var quantity=$("#quantity").val().trim();
    var billingDays=$("#billing_days").val(); 
    var total=$("#total_amount_after_tax").val();
    var rate=$("#rate").val().trim();
    var ourState=$("#our_state").val().trim();
    var ourStateCode=$("#our_state_code").val().trim();
    var accHolderName=$("#acc_holder_name").val().trim();
    var accountNumber=$("#account_number").val().trim();
    var bankName=$("#bank_name").val().trim();
    var ifscCode=$("#ifsc_code").val().trim();
    var customerPan=$("#customer_pan").val().trim();
    var customerGstin=$("#customer_gstin").val().trim(); 
    var customerState=$("#customer_state").val().trim();
    var customerStateCode=$("#customer_state_code").val().trim();
    var vendorAddressLane1 = $("#vendor_address_lane1").val().trim();
    var vendorAddressLane2 = $("#vendor_address_lane2").val().trim();
    var vendorAddressState = $("#vendor_address_state").val().trim();
    var vendorAddressPincode = $("#vendor_address_pincode").val().trim();
    var billingAddressLane1=$("#billing_address_lane1").val().trim();
    var billingAddressLane2=$("#billing_address_lane2").val().trim();
    var billingAddressState=$("#billing_address_state").val().trim();
    var billingAddressPincode=$("#billing_address_pincode").val().trim();
    var shippingAddressLane1=$("#shipping_address_lane1").val().trim();
    var shippingAddressLane2=$("#shipping_address_lane2").val().trim();
    var shippingAddressState=$("#shipping_address_state").val().trim();
    var shippingAddressPincode=$("#shipping_address_pincode").val().trim();
    var poId=$("#po_id").val();
    var invoicePdfDate=$("#invoice_pdf_date").val();

    var ordeDate=$("#order_date").val();
    var shipingPan=$("#our_pan1").val();
    var shippingGst=$("#our_gstin1").val();
    var shippingState=$("#our_state1").val();
    var shippingCode=$("#our_state_code1").val();
    var projectNameHtml=$("#project_name").val();
    var employeeNameHtml= $("#employee_name").val();
   

    $('#update_msg').html("");
    $("#update_msg").css("color","red");
    
    if(vendorAddressLane1.trim().length==0){
    	$('#update_msg').html("");
    	$('#update_msg').html(VALIDATION_VENDOR_ADDRESS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(vendorAddressLane2.trim().length==0){
    	$('#update_msg').html("");
    	$('#update_msg').html(VALIDATION_VENDOR_ADDRESS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(vendorAddressState.trim().length==0){
    	$('#update_msg').html("");
    	$('#update_msg').html(VALIDATION_VENDOR_ADDRESS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(vendorAddressPincode.trim().length==0){
    	$('#update_msg').html("");
    	$('#update_msg').html(VALIDATION_VENDOR_ADDRESS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    
    if(invoiceNumber.trim().length==0){
    	$('#update_msg').html("");
    	$('#update_msg').html(VALIDATION_INVOICE_NUMBER_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(invoicePdfDate.trim().length==0){
    	$('#update_msg').html("");
    	$('#update_msg').html(VALIDATION_INVOICE_DATE_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    var date_regex = /^\d{4}\-\d{1,2}\-\d{1,2}$/ ;
    /*if(!(date_regex.test(invoicePdfDate)))
    {
    	$('#update_msg').html("");
    	$('#update_msg').html(VALIDATION_INVOICE_DATE_FORMAT_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
   */
    if(supRefNumber.trim().length==0){
    	$('#update_msg').html("");
    	$('#update_msg').html(VALIDATION_SUPPLIER_REF_NUMBER_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(poNumber.trim().length==0){
    	$('#update_msg').html("");
    	$('#update_msg').html(VALIDATION_PO_NUMBER_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(billingAddressLane1.trim().length==0){
    	$('#update_msg').html(VALIDATION_BILLING_ADDRESS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(billingAddressLane2.trim().length==0){
    	$('#update_msg').html(VALIDATION_BILLING_ADDRESS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(billingAddressState.trim().length==0){
    	$('#update_msg').html(VALIDATION_BILLING_ADDRESS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(billingAddressPincode.trim().length==0){
    	$('#update_msg').html(VALIDATION_BILLING_ADDRESS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(shippingAddressLane1.trim().length==0){
    	$('#update_msg').html(VALIDATION_SHIPPING_ADDRESS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(shippingAddressLane2.trim().length==0){
    	$('#update_msg').html(VALIDATION_SHIPPING_ADDRESS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(shippingAddressState.trim().length==0){
    	$('#update_msg').html(VALIDATION_SHIPPING_ADDRESS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(shippingAddressPincode.trim().length==0){
    	$('#update_msg').html(VALIDATION_SHIPPING_ADDRESS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    
    if(customerPan.trim().length==0){
    	$('#update_msg').html(VALIDATION_CUSTOMER_PAN_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(customerGstin.trim().length==0){
    	$('#update_msg').html(VALIDATION_CUSTOMER_GSTIN_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(customerState.trim().length==0){
    	$('#update_msg').html(VALIDATION_CUSTOMER_STATE_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(customerStateCode.trim().length==0){
    	$('#update_msg').html(VALIDATION_CUSTOMER_STATE_CODE_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(ourPan.trim().length==0){
    	$('#update_msg').html(VALIDATION_OUR_PAN_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(ourGstin.trim().length==0){
    	$('#update_msg').html(VALIDATION_OUR_GSTIN_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(ourState.trim().length==0){
    	$('#update_msg').html(VALIDATION_OUR_STATE_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(ourStateCode.trim().length==0){
    	$('#update_msg').html(VALIDATION_OUR_STATE_CODE_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(period.trim().length==0){
    	$('#update_msg').html(VALIDATION_PERIOD_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(hsnSac.trim().length==0){
    	$('#update_msg').html(VALIDATION_HSNSAC_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(days==0){
    	$('#update_msg').html(VALIDATION_WORKING_DAYS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(billingDays==0){
    	$('#update_msg').html(VALIDATION_BILLING_DAYS_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(quantity==0){
    	$('#update_msg').html(VALIDATION_QUANTITY_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(taxableValue==0){
    	$('#update_msg').html(VALIDATION_TAXABLE_VALUE_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(currencyType=="INR"){
    	if((cgstRate==0||sgstRate==0||cgstAmount==0||sgstAmount==0)){
    		if(igstRate==0 || igstAmount==0){
    
			    if(cgstRate==0){
			    	$('#update_msg').html(VALIDATION_CGST_RATE_EMPTY_ERROR);
			    	setTimeout(function(){
			    		$('#update_msg').html("");
						}, 10000);
					return false; 
			    }
			    if(sgstRate==0){
			    	$('#update_msg').html(VALIDATION_SGST_RATE_EMPTY_ERROR);
			    	setTimeout(function(){
			    		$('#update_msg').html("");
						}, 10000);
					return false; 
			    }
			    if(cgstAmount==0){
			    	$('#update_msg').html(VALIDATION_CGST_AMOUNT_EMPTY_ERROR);
			    	setTimeout(function(){
			    		$('#update_msg').html("");
						}, 10000);
					return false; 
			    }
			    if(sgstAmount==0){
			    	$('#update_msg').html(VALIDATION_SGST_AMOUNT_EMPTY_ERROR);
			    	setTimeout(function(){
			    		$('#update_msg').html("");
						}, 10000);
					return false; 
			    }
    		}else{
    		
    		}
    	}else{
    	/*if(igstRate==0){
        	$('#update_msg').html(VALIDATION_IGST_RATE_EMPTY_ERROR);
        	setTimeout(function(){
        		$('#update_msg').html("");
    			}, 10000);
    		return false; 
        }
        if(igstAmount==0){
        	$('#update_msg').html(VALIDATION_IGST_AMOUNT_EMPTY_ERROR);
        	setTimeout(function(){
        		$('#update_msg').html("");
    			}, 10000);
    		return false; 
        }*/
    	}
    }else{
    	
    }
    var totalbeforeTax=$("#total_amount_before_tax").val();
    if(totalbeforeTax==0){
    	$('#update_msg').html(VALIDATION_TOTAL_AMOUNT_BEFORE_TAX_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(total==0){
    	$('#update_msg').html(VALIDATION_TOTAL_AMOUNT_AFTER_TAX_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    
    if(accHolderName.trim().length==0){
    	$('#update_msg').html(VALIDATION_ACCOUNT_HOLDER_NAME_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(bankName.trim().length==0){
    	$('#update_msg').html(VALIDATION_BANK_NAME_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(accountNumber.trim().length==0){
    	$('#update_msg').html(VALIDATION_ACCOUNT_NUMBER_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(ifscCode.trim().length==0){
    	$('#update_msg').html(VALIDATION_IFSC_CODE_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(ordeDate.trim().length==0){
    	$('#update_msg').html(VALIDATION_ORDER_DATE_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
   /* if(!(date_regex.test(ordeDate)))
    {
    	$('#update_msg').html("");
    	$('#update_msg').html(VALIDATION_ORDER_DATE_FORMAT_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }*/
    if(shipingPan.trim().length==0){
    	$('#update_msg').html(VALIDATION_SHIPPING_PAN_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(shippingGst.trim().length==0){
    	$('#update_msg').html(VALIDATION_SHIPPING_GSTIN_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(shippingState.trim().length==0){
    	$('#update_msg').html(VALIDATION_SHIPPING_STATE_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(shippingCode.trim().length==0){
    	$('#update_msg').html(VALIDATION_SHIPPING_STATECODE_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(projectNameHtml.trim().length==0){
    	$('#update_msg').html(VALIDATION_PROJECT_NAME_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    if(employeeNameHtml.trim().length==0){
    	$('#update_msg').html(VALIDATION_EMPLOYEE_NAME_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#update_msg').html("");
			}, 10000);
		return false; 
    }
    
    var appBasePath=getAppBasePath();
  
    var params = {
    		invoiceRequestId : requestid ,
    		invoiceId : invoiceid,
    		invoiceNumber : invoiceNumber ,
    		cGSTAmount : cgstAmount ,
    		total : total ,
    		invoiceDate : invoiceDate ,
    		sGSTAmount : sgstAmount ,
    		bankName : bankName ,
    		pONumber : poNumber ,
			iGSTAmount:igstAmount,
			iFSCCode:ifscCode,
			pODate : ordeDate ,
			accountHolderName : accHolderName,
			vendorAddressLane1:vendorAddressLane1,
			vendorAddressLane2:vendorAddressLane2,
			vendorAddressState:vendorAddressState,
			vendorAddressPincode:vendorAddressPincode,
			supplierReferanceNumber:supRefNumber,
			bankAcNo:accountNumber,
			project:project,
			quantity:quantity,
			billingAddressLane1:billingAddressLane1,
			billingAddressLane2:billingAddressLane2,
			billingAddressState:billingAddressState,
			billingAddressPincode:billingAddressPincode,
			period:period,
			billingDays:billingDays,
			employID:empId,
			rate:rate,
			shippingAddressLane1:shippingAddressLane1,
			shippingAddressLane2:shippingAddressLane2,
			shippingAddressState:shippingAddressState,
			shippingAddressPincode:shippingAddressPincode,
			hSNSAC:hsnSac,
			cGSTRate:cgstRate,
			days:days,
			sGSTRate:sgstRate,
			taxableValue:taxableValue,
			iGSTRate:igstRate,
			iGSTApplicable:igstApplicable,
			customerPan:customerPan,
			customerGstin:customerGstin,
			customerState:customerState,
			customerStateCode:customerStateCode,
			ourPan:ourPan,
			OurGstin:ourGstin,
			ourState:ourState,
			ourStateCode:ourStateCode,
			poId:poId,
			invoicePdfDate:invoicePdfDate,
			shipingPan:shipingPan,
			shippingGst:shippingGst,
			shippingState:shippingState,
			shippingCode:shippingCode
	};
    
    var queryParam = jQuery.param( params );
    
    //alert(url);
    
    $.ajax({
		type : "GET",
		url : appBasePath+"/invoice/updateinvoicedetails",
		data:queryParam,
		success : function(data) {
			//alert(data.statusCode);
			if(data.statusCode==1){
				$("#update_msg").css("color","green");
				$("#update_msg").html(data.message);
				setTimeout(function(){
		    		$('#update_msg').html("");
					}, 10000);
			getInvoiceRequests();
			}else{
				$("#update_msg").css("color","red");
				$("#update_msg").html(data.message);
				setTimeout(function(){
		    		$('#update_msg').html("");
					}, 10000);
			}
			
			
		},
		error : function(req, status, msg) {

		}
	});
	
	
}


function getaccountnames(){
	//alert("hello"); 
	$("#result").html("");
	var appBasePath=getAppBasePath();
	    	var term=$("#acc_holder_name").val();
	    	var exp=new RegExp(term,"i");
	    	$.ajax({
		        url: appBasePath+"/invoice/searchbank?term="+term,
		        dataType: "json",
		         success: function( data ) {
		        	 $("#result").append("");
		        	 $.each(data,function(index, item) {
		        		// $("#result").html("");
		        		// $("#result").append('<li onclick="sample(\''+ item.accountHolderName+ '\');" class="list-group-item">'+item.accountHolderName+'</li>');
		        	 	$("#result").append('<li onclick="addbankDetails(\''+ item.accountHolderName+ '\',\''+ item.bankName+ '\',\''+ item.accountNumber+ '\',\''+ item.accountIFSC+ '\')\;" class="list-group-item">'+item.accountHolderName+"-"+item.bankName+'</li>');
		        		
		        	 });
		        	// $("#acc_holder_name").autocomplete($("#result").val());
		             
		        	 
		         },
		         error: function (data) {
		             alert('error!');
		           }

	        });
	    
	    }
function addbankDetails(accountHolderName,bankName,accountNumber,accountIFSC){
 
 //alert("val...."+value);
 $("#acc_holder_name").val(accountHolderName);
 $("#result").html("");
 $("#bank_name").val(bankName);
 $("#account_number").val(accountNumber);
 $("#ifsc_code").val(accountIFSC);
 
}
//pending....................pan our
function getpannames(){
	
	$("#panresult").html("");
	var appBasePath=getAppBasePath();
	    	var term=$("#our_pan").val();
	    	var exp=new RegExp(term,"i");
	    	$.ajax({
		        url: appBasePath+"/invoice/searchpandetails?term="+term,
		        dataType: "json",
		         success: function( data ) {
		        	// $("#panresult").append("");
		        	 $.each(data,function(index, item) {
		        		// $("#panresult").html("");
		        		// $("#panresult").append('<li onclick="sample(\''+ item.accountHolderName+ '\');" class="list-group-item">'+item.accountHolderName+'</li>');
		        	 	$("#panresult").append('<li onclick="addPanDetails(\''+ item.panNumber+ '\',\''+ item.gstIn+ '\',\''+ item.state+ '\',\''+ item.stateCode+ '\')\;" class="list-group-item">'+item.panNumber+"-"+item.gstIn+'</li>');
		        		
		        	 });
		        	// $("#acc_holder_name").autocomplete($("#result").val());
		             
		        	 
		         },
		         error: function (data) {
		             alert('error!');
		           }

	        });
	    
	    }
function addPanDetails(panNumber,gstIn,state,stateCode){
 
 //alert("val...."+panNumber);
 $("#our_pan").val(panNumber);
 $("#panresult").html("");
 $("#our_gstin").val(gstIn);
 $("#our_state").val(state);
 $("#our_state_code").val(stateCode);
 
}