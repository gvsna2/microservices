package com.nexiilabs.stp.invoice;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexiilabs.stp.bankaccount.BankAccountModel;
import com.nexiilabs.stp.bankaccount.TaxDetailsModel;
import com.nexiilabs.stp.resource.POModel;
import com.nexiilabs.stp.resource.RequirementsModel;
import com.nexiilabs.stp.user.CreateUserModel;
import com.nexiilabs.stp.user.UserResponseDTO;


@RestController
@RequestMapping(path = "/invoice")
public class InvoiceGenerationController {

	@Autowired
	private InvoiceGenerationService invoiceGenerationService;
	@Autowired
	Environment environment;

	@RequestMapping(path = "/getinvoice")
	public List<EditInvoiceDetailsResponseModel> getInvoiceRequests(HttpServletRequest request, HttpServletResponse response) {

		HttpSession userSession = request.getSession(false);
		List<EditInvoiceDetailsResponseModel> invoiceList = new ArrayList<EditInvoiceDetailsResponseModel>();
		try {
			if(userSession!=null){
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("Invoices")){
				invoiceList=invoiceGenerationService.getInvoiceRequestList(userModel.getUserId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoiceList;
	}
	//update invoice details befor sending invoice
	@RequestMapping(path = "/updateinvoicedetails")
	public UserResponseDTO setInvoice(@RequestParam("invoiceRequestId") int invoiceRequestId,
			@RequestParam("invoiceId") int invoiceId, @RequestParam("invoiceNumber") String invoiceNumber,
			@RequestParam("cGSTAmount") double cGSTAmount, @RequestParam("total") double total,
			@RequestParam("invoiceDate") String invoiceDate, @RequestParam("sGSTAmount") double sGSTAmount,
			@RequestParam("bankName") String bankName, @RequestParam("pONumber") String pONumber,
			@RequestParam("iGSTAmount") double iGSTAmount, @RequestParam("iFSCCode") String iFSCCode,
			@RequestParam("pODate") String pODate, @RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("vendorAddressLane1") String vendorAddressLane1,@RequestParam("vendorAddressLane2") String vendorAddressLane2,
			@RequestParam("vendorAddressState") String vendorAddressState,@RequestParam("vendorAddressPincode") String vendorAddressPincode,
			@RequestParam("supplierReferanceNumber") String supplierReferanceNumber,
			@RequestParam("bankAcNo") String bankAcNo, @RequestParam("project") int requirement,
			@RequestParam("quantity") int quantity, 
			@RequestParam("billingAddressLane1") String billingAddressLane1,@RequestParam("billingAddressLane2") String billingAddressLane2,
			@RequestParam("billingAddressState") String billingAddressState,@RequestParam("billingAddressPincode") String billingAddressPincode,
			@RequestParam("period") String period, @RequestParam("billingDays") int billingDays,
			@RequestParam("employID") int employID, @RequestParam("rate") double rate,
			@RequestParam("shippingAddressLane1") String shippingAddressLane1,@RequestParam("shippingAddressLane2") String shippingAddressLane2,
			@RequestParam("shippingAddressState") String shippingAddressState,@RequestParam("shippingAddressPincode") String shippingAddressPincode,
			@RequestParam("hSNSAC") String hSNSAC,
			@RequestParam("cGSTRate") double cGSTRate, @RequestParam("days") int days,
			@RequestParam("sGSTRate") double sGSTRate, @RequestParam("taxableValue") double taxableValue,
			@RequestParam("iGSTRate") double iGSTRate, @RequestParam("iGSTApplicable") int iGSTApplicable,
			@RequestParam("customerPan") String customerPan, @RequestParam("customerGstin") String customerGstin,
			@RequestParam("customerState") String customerState,
			@RequestParam("customerStateCode") String customerStateCode, @RequestParam("ourPan") String ourPan,
			@RequestParam("OurGstin") String ourGstin, @RequestParam("ourState") String ourState,
			@RequestParam("ourStateCode") String ourStateCode,@RequestParam("poId") int poId,@RequestParam("invoicePdfDate") String invoicePdfDate,
			@RequestParam("shipingPan") String shipingPan,@RequestParam("shippingGst") String shippingGst,@RequestParam("shippingState") String shippingState,
			@RequestParam("shippingCode") String shippingCode,HttpServletRequest request,HttpServletResponse response) {

		HttpSession userSession = request.getSession(false);
		InvoiceModel invoiceModel = new InvoiceModel();
		POModel poModel = new POModel();
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		NumberToWord numberToWord=new NumberToWord();
		try{
			if(userSession!=null){
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("Invoices")){
					total=Math.round(total);
					invoiceModel.setInvoiceId(invoiceId);
					invoiceModel.setInvoiceNumber(invoiceNumber);
					// cal.add(Calendar.DATE, 10);
					invoiceModel.setInvoiceDate(invoiceDate);
					invoiceModel.setPoNumber(pONumber);
					invoiceModel.setPoDate(numberToWord.stringToSqlDate(pODate));
					invoiceModel.setSupplierRefNumber(supplierReferanceNumber);
					/*invoiceModel.setBillingAddress(billingAddress);
					invoiceModel.setShippingAddress(shippingAddress);
					invoiceModel.setVendorAddress(vendorAddress);*/
					invoiceModel.setShipAddLane1(shippingAddressLane1);
					invoiceModel.setShipAddLane2(shippingAddressLane2);
					invoiceModel.setShipAddState(shippingAddressState);
					invoiceModel.setShipAddPincode(shippingAddressPincode);
					invoiceModel.setBillingAddLane1(billingAddressLane1);
					invoiceModel.setBillingAddLane2(billingAddressLane2);
					invoiceModel.setBillingAddState(billingAddressState);
					invoiceModel.setBillingAddPincode(billingAddressPincode);
					invoiceModel.setVendorAddLane1(vendorAddressLane1);
					invoiceModel.setVendorAddLane2(vendorAddressLane2);
					invoiceModel.setVendorAddState(vendorAddressState);
					invoiceModel.setVendorAddPincode(vendorAddressPincode);
					invoiceModel.setFkRequirementId(requirement);
					invoiceModel.setPeriod(period);
					invoiceModel.setFkEmployeeId(employID);
					invoiceModel.setHsnSac(hSNSAC);
					invoiceModel.setQuantity(quantity);
					invoiceModel.setBillingDays(billingDays);
					invoiceModel.setTotalWorkDays(days);
					invoiceModel.setRate(rate);
					invoiceModel.setTaxableAmount(taxableValue);
					invoiceModel.setCgst_rate(cGSTRate);
					invoiceModel.setCgstAmount(cGSTAmount);
					invoiceModel.setSgstRate(sGSTRate);
					invoiceModel.setSgstAmount(sGSTAmount);
					invoiceModel.setIgstRate(iGSTRate);
					invoiceModel.setIgstAmount(iGSTAmount);
					invoiceModel.setIgstApplicable(iGSTApplicable);
					invoiceModel.setTotalAmount(total);
					invoiceModel.setAccountHolderName(accountHolderName);
					invoiceModel.setBankName(bankName);
					invoiceModel.setAccountNumber(bankAcNo);
					invoiceModel.setIfscCode(iFSCCode);
					invoiceModel.setFkInvoiceRequestId(invoiceRequestId);
					invoiceModel.setCustomerPan(customerPan);
					invoiceModel.setCustomerGstin(customerGstin);
					invoiceModel.setCustomerState(customerState);
					invoiceModel.setCustomerStateCode(customerStateCode);
					invoiceModel.setOur_pan(ourPan);
					invoiceModel.setOurGstin(ourGstin);
					invoiceModel.setOurState(ourState);
					invoiceModel.setOurStateCode(ourStateCode);
					invoiceModel.setInvoicePdfDate(numberToWord.stringToSqlDate(invoicePdfDate));
					invoiceModel.setShippingPan(shipingPan);
					invoiceModel.setShippingGstin(shippingGst);
					invoiceModel.setShippingState(shippingState);
					invoiceModel.setShippingStateCode(shippingCode);
					
					poModel.setShippingAddressLane1(shippingAddressLane1);
					poModel.setShippingAddressLane2(shippingAddressLane2);
					poModel.setShippingAddressState(shippingAddressState);
					poModel.setShippingAddressPinCode(shippingAddressPincode);
					poModel.setBillingAddressLane1(billingAddressLane1);
					poModel.setBillingAddressLane2(billingAddressLane2);
					poModel.setBillingAddressState(billingAddressState);
					poModel.setBillingAddressPinCode(billingAddressPincode);
					poModel.setVendorAddressLane1(vendorAddressLane1);
					poModel.setVendorAddressLane2(vendorAddressLane2);
					poModel.setVendorAddressState(vendorAddressState);
					poModel.setVendorAddressPinCode(vendorAddressPincode);
					poModel.setPanNumber(customerPan);
					poModel.setGstIn(customerGstin);
					poModel.setState(customerState);
					poModel.setStateCode(customerStateCode);
					poModel.setShippingPanNumber(shipingPan);
					poModel.setShippingGstIn(shippingGst);
					poModel.setShippingState(shippingState);
					poModel.setShippingStateCode(shippingCode);
					
					poModel.setPoId(poId);
			
					invoiceModel = invoiceGenerationService.updateInvoiceDetails(invoiceModel, poModel);
					if (invoiceModel != null) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setMessage("Invoice details updated successfully");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Invoice details updation failed");
					}
				}else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Authentication require to access this");
				}
			}else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Login Required to Access this service");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return userResponseDTO;

	}

	//schedular for invoice request generation from po list
	@RequestMapping(path = "/schedularinvoice")
	//@Scheduled(cron="0 0 1 1 * ?")
	public UserResponseDTO schedularInvoice() {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormatForLastMonth = new SimpleDateFormat("yyyy-MM");
		Calendar now = Calendar.getInstance();
		LocalDate currentday = LocalDate.now();
		//int invoiceDay=01;
		//int invoiceMonth=now.get(Calendar.MONTH);
		int invoiceYear=now.get(Calendar.YEAR);
		String dateInString=currentday.withDayOfMonth(1).toString();
		String invoicePdfDate= currentday.minusMonths(1).withDayOfMonth(1).toString();
		UserResponseDTO userResponseDTO=new UserResponseDTO();
		RequirementsModel requirementsModel=null;
			try {
				String[] today = dateInString.split("-");
				String[] today1 = invoicePdfDate.split("-");
				double companyWorkingDays=28;
				int year = 0;
				year = invoiceYear % 100;
				// object declaration
				List<POModel> list = new ArrayList<POModel>();
				InvoiceRequestModel invoiceRequestModel =null;;
				InvoiceModel invoiceModel = null;
				InvoiceModel invoiceModel1 = null;
				InvoiceRequestModel invoiceRequestModel2 =null;
				BankAccountModel bankAccountModel = new BankAccountModel();
				TaxDetailsModel taxDetailsModel=null;
				NumberToWord numberToWord=new NumberToWord();
				bankAccountModel = invoiceGenerationService.getBankDetails();
				taxDetailsModel = invoiceGenerationService.getNexiiTaxDetails();
				if(bankAccountModel!=null&&taxDetailsModel!=null){
					POModel poModel = null;
					List<InvoiceRequestModel> listInvoiceRequestModel = new ArrayList<InvoiceRequestModel>();
					String cgstrate = environment.getProperty("app.cgstRate");
					String sgstrate = environment.getProperty("app.sgstRate");
					String hsnsac = environment.getProperty("app.hsnsac");
					double cgstRate=Double.parseDouble(cgstrate);
					double sgstRate=Double.parseDouble(sgstrate);
					//String igstRate = environment.getProperty("app.igstRate");
					
					String financialYear="";
					if (Integer.parseInt(today[1]) <= 3) {
						financialYear= (year - 1) + "-" + year;
				    } else {
				    	financialYear= year + "-" + (year + 1);
				    }
					//System.out.println();
					// list for pos
					list = invoiceGenerationService.getPOList(dateInString);
					Date dateone =null;
					Date datetwo =null;
					long daysafterexpire =0;
					Date date1 = null;
					Date date2 = null;
					double daysinmonth = 0;
					double cgstAmount=0;
					double sgstAmount=0;
					double igstAmount=0;
					String[] startMonth=null;
					String startMonth1=null;
					Date dateNew1=null;
					int lastMonth = 0;
					int lastMonthYear  =0;
					double perDayAmount=0;
					double taxableamount=0;
					String invoiceGenarateMonth=null;
					double permonthRate=0;
					double totalAmount=0;
						// invoice request details for every po
						if (!list.isEmpty()) {
							//System.out.println("po list size......................................" + list.size());
							for (int i = 0; i < list.size(); i++) {
								//System.err.println(i + ".................i value");
								poModel = new POModel();
								poModel=null;
								poModel = list.get(i);
								dateone = dateFormat.parse(dateInString);
								datetwo = dateFormat.parse(poModel.getEndDate());
							    daysafterexpire = datetwo.getTime() - dateone.getTime();
								if(daysafterexpire>0){
								try{
									invoiceRequestModel=new InvoiceRequestModel();
									//System.out.println(invoiceRequestModel.toString()+".....................line 228");
									//System.err.println(poModel.getPoId()+"................poid");
									//System.out.println(poModel.toString());
									invoiceRequestModel.setInvoiceMonth(Integer.parseInt(today[1]));
									invoiceRequestModel.setInvoiceYear(Integer.parseInt(today[0]));
									invoiceRequestModel.setFkPoId(poModel.getPoId());
									invoiceRequestModel.setInvoiceStatus(0);
									invoiceRequestModel.setInvoiceSentStatus(0);
				
									// invoice existence check
									if(poModel.getCreatedBy()!=0){
										invoiceRequestModel = invoiceGenerationService.getInvoiceRequestDetails(invoiceRequestModel,poModel);
										//System.err.println(invoiceRequestModel+"......line 238");
										if (invoiceRequestModel == null) {
											//System.err.println(invoiceRequestModel+"......line 240");
											invoiceRequestModel = new InvoiceRequestModel();
					
											// invoice count
											listInvoiceRequestModel = invoiceGenerationService.getCountOfInvoice(poModel);
											int invoicecount = listInvoiceRequestModel.size();
											// condition for first invoice generation
											if(poModel.getShippingAddressLane1()==null){
												poModel.setShippingAddressLane1(" ");
											}
											if(poModel.getShippingAddressLane2()==null){
												poModel.setShippingAddressLane2(" ");
											}
											if(poModel.getShippingAddressState()==null){
												poModel.setShippingAddressState(" ");
											}
											if(poModel.getShippingAddressPinCode()==null){
												poModel.setShippingAddressPinCode(" ");
											}
											if(poModel.getBillingAddressLane1()==null){
												poModel.setBillingAddressLane1(" ");
											}
											if(poModel.getBillingAddressLane2()==null){
												poModel.setBillingAddressLane2(" ");
											}
											if(poModel.getBillingAddressState()==null){
												poModel.setBillingAddressState(" ");
											}
											if(poModel.getBillingAddressPinCode()==null){
												poModel.setBillingAddressPinCode(" ");
											}
											if(poModel.getVendorAddressLane1()==null){
												poModel.setVendorAddressLane1(" ");
											}
											if(poModel.getVendorAddressLane2()==null){
												poModel.setVendorAddressLane2(" ");
											}
											if(poModel.getVendorAddressState()==null){
												poModel.setVendorAddressState(" ");
											}
											if(poModel.getVendorAddressPinCode()==null){
												poModel.setVendorAddressPinCode(" ");
											}
											if (invoicecount == 0) {
												//Date endDate = null;
												//double diffrencefortotaldays = 0;
												String[] startDate = poModel.getStartDate().split(" ");
												
												date1 = dateFormat.parse(startDate[0]);
												date2 = dateFormat.parse(dateInString);
												daysinmonth = date2.getTime() - date1.getTime();
												daysinmonth=daysinmonth/(1000*60*60*24);
												System.out.println(daysinmonth+"............daysinmonth from start date"+startDate[0]+"...........in date"+dateInString);
												
												System.out.println();
												if(daysinmonth>0){
													// setting values to invoiceRequestModel for first
													// invoice request
													invoiceRequestModel.setInvoiceName("NEXII/" + financialYear + "/"+ today1[1] + "/" + poModel.getPoNumber());
													invoiceRequestModel.setInvoiceStatus(0);
													invoiceRequestModel.setFkPoId(poModel.getPoId());
													invoiceRequestModel.setInvoiceMonth(Integer.parseInt(today[1]));
													invoiceRequestModel.setInvoiceYear(Integer.parseInt(today[0]));
													invoiceRequestModel.setInvoiceFileName(
															invoiceRequestModel.getInvoiceName()+ "-stp.pdf");
													invoiceRequestModel.setInvoiceFilePath(environment.getProperty("app.invoicefilepath"));
													invoiceRequestModel.setInvoiceSentStatus(0);
													invoiceRequestModel.setInvoiceCount(1);
													invoiceRequestModel.setRequestedBy(poModel.getCreatedBy());
													
													// saving in voice request
													invoiceRequestModel = invoiceGenerationService.generteInvoiceRequest(invoiceRequestModel,poModel);
													////System.out.println(invoiceRequestModel.toString());
													
													// days calculation for first invoice generation
													if(invoiceRequestModel!=null){
														//getting requirement project id
														requirementsModel=new RequirementsModel();
														requirementsModel=invoiceGenerationService.getRequirementProjectId(poModel.getEmployeeId());
														
														//amount calculation
														
														//companyWorkingDays=28;
														if(companyWorkingDays<daysinmonth){
															daysinmonth=companyWorkingDays;
														}
														startMonth=startDate[0].split("-");
														startMonth1=startMonth[0]+"-"+startMonth[1];
														lastMonth=0;
														lastMonth = now.get(Calendar.MONTH); // month indexing from zero
														lastMonthYear=0;
														lastMonthYear  = now.get(Calendar.YEAR);
														dateNew1=null;
														dateNew1= dateFormatForLastMonth.parse(lastMonthYear+"-"+lastMonth);
														invoiceGenarateMonth=null;
														invoiceGenarateMonth=dateFormatForLastMonth.format(dateNew1);
														System.out.println(invoiceGenarateMonth+".....invoiceGenarateMonth..."+startMonth1);
														if(!invoiceGenarateMonth.equals(startMonth1)){
															startDate[0]=invoiceGenarateMonth+"-01";
														}
														//if(poModel.getCurrency().equals("USA $")){
															//double d=findExchangeRateAndConvert("USD", "INR", 1000);
															perDayAmount=0;
															taxableamount=0;
															perDayAmount=poModel.getUnitPrice() / companyWorkingDays;
															taxableamount=perDayAmount*daysinmonth;
															System.out.println(poModel.getCurrency()+".....................poModel.getCurrency()");
															System.err.println(cgstRate+"............................cgstRate.......");
															if(poModel.getCurrency().equals("INR")){
																cgstRate=Double.parseDouble(cgstrate);
																sgstRate=Double.parseDouble(sgstrate);
																cgstAmount=((cgstRate * perDayAmount)/100)*daysinmonth;
																sgstAmount=((sgstRate * perDayAmount)/100)*daysinmonth;
																//igstAmount=0;
															}else{
																cgstRate=0;
																sgstRate=0;
															}
															totalAmount=0;
															totalAmount=taxableamount+cgstAmount+sgstAmount;
														System.out.println(cgstAmount+".....................................cgstAmount");
														//System.err.println("after bank details..........line 290");
														invoiceModel=null;
														invoiceModel = new InvoiceModel();
														// basic values setting to invoice table
														invoiceModel.setInvoiceNumber("NEXII/" + financialYear + "/"+ today1[1] + "/" + poModel.getPoNumber());
														// cal.add(Calendar.DATE, 10);
														invoiceModel.setInvoiceDate(dateInString);
														invoiceModel.setPoNumber(poModel.getPoNumber());
														invoiceModel.setPoDate(poModel.getRaisedOn());
														invoiceModel.setSupplierRefNumber(poModel.getSupplierRefNumber());
														invoiceModel.setInvoicePdfDate(invoicePdfDate);
														/*invoiceModel.setBillingAddress(poModel.getBillingAddress());
														invoiceModel.setShippingAddress(poModel.getShippingAddress());
														invoiceModel.setVendorAddress(poModel.getVendorAddress());*/
														invoiceModel.setShipAddLane1(poModel.getShippingAddressLane1());
														invoiceModel.setShipAddLane2(poModel.getShippingAddressLane2());
														invoiceModel.setShipAddState(poModel.getShippingAddressState());
														invoiceModel.setShipAddPincode(poModel.getShippingAddressPinCode());
														invoiceModel.setBillingAddLane1(poModel.getBillingAddressLane1());
														invoiceModel.setBillingAddLane2(poModel.getBillingAddressLane2());
														invoiceModel.setBillingAddState(poModel.getBillingAddressState());
														invoiceModel.setBillingAddPincode(poModel.getBillingAddressPinCode());
														invoiceModel.setVendorAddLane1(poModel.getVendorAddressLane1());
														invoiceModel.setVendorAddLane2(poModel.getVendorAddressLane2());
														invoiceModel.setVendorAddState(poModel.getVendorAddressState());
														invoiceModel.setVendorAddPincode(poModel.getVendorAddressPinCode());
														invoiceModel.setFkRequirementId(requirementsModel.getRequirementId());
														invoiceModel.setPeriod(numberToWord.dateConverter(startDate[0])+" to "+numberToWord.dateConverter(dateInString));
														invoiceModel.setFkEmployeeId(poModel.getEmployeeId());
														invoiceModel.setHsnSac(hsnsac);
														invoiceModel.setQuantity(1);
														invoiceModel.setBillingDays((int)daysinmonth);
														invoiceModel.setTotalWorkDays((int)companyWorkingDays);
														invoiceModel.setRate(poModel.getUnitPrice());
														invoiceModel.setTaxableAmount(taxableamount);
														invoiceModel.setCgst_rate(cgstRate);
														invoiceModel.setCgstAmount(cgstAmount);
														invoiceModel.setSgstRate(sgstRate);
														invoiceModel.setSgstAmount(sgstAmount);
														invoiceModel.setIgstRate(0);
														invoiceModel.setIgstAmount(0);
														invoiceModel.setIgstApplicable(0);
														invoiceModel.setTotalAmount(totalAmount);
														invoiceModel.setAccountHolderName(bankAccountModel.getAccountHolderName());
														invoiceModel.setBankName(bankAccountModel.getBankName());
														invoiceModel.setAccountNumber(bankAccountModel.getAccountNumber());
														invoiceModel.setIfscCode(bankAccountModel.getAccountIFSC());
														invoiceModel.setFkInvoiceRequestId(invoiceRequestModel.getInvoiceRequestId());
														invoiceModel.setCustomerPan(poModel.getPanNumber());
														invoiceModel.setCustomerGstin(poModel.getGstIn());
														invoiceModel.setCustomerState(poModel.getState());
														invoiceModel.setCustomerStateCode(poModel.getStateCode());
														invoiceModel.setOur_pan(taxDetailsModel.getPanNumber());
														invoiceModel.setOurGstin(taxDetailsModel.getGstIn());
														invoiceModel.setOurState(taxDetailsModel.getState());
														invoiceModel.setOurStateCode(taxDetailsModel.getStateCode());
														invoiceModel.setCreatedBy(poModel.getCreatedBy());
														invoiceModel.setShippingPan(poModel.getShippingPanNumber());
														invoiceModel.setShippingGstin(poModel.getShippingGstIn());
														invoiceModel.setShippingState(poModel.getShippingState());
														invoiceModel.setShippingStateCode(poModel.getShippingStateCode());
														//System.out.println(invoiceModel.toString()+".............................in line 332");
														// basic invoice details saving in invoice table
														invoiceModel = invoiceGenerationService.saveInvoiceDetails(invoiceModel);
														//System.out.println("invoice generation completed successfully..." + invoiceModel.toString());
														if (invoiceModel != null) {
															//System.err.println(invoiceModel.toString()+"......line 338");
															int updatevalue = invoiceGenerationService.updateInvoiceRequest(invoiceModel);
															invoiceModel=null;
															invoiceRequestModel=null;
															//System.err.println(updatevalue+"......line 342");
														}
														System.out.println("in if end ...........line 344");
														// if not first month invoice
													}else{
														System.err.println("invoice request model empty id not updated in invoice model");
													}
												}else{
													System.out.println("Start date is not yet reached");
												}
											} else {
												System.err.println("in else");
												int currentMonth=Integer.parseInt(today[1]);
												int currentYear=Integer.parseInt(today[0]);
												lastMonth=0;
												int lastMonthYears=0;
												if(currentMonth==1){
													lastMonth=12;
													lastMonthYears=currentYear-1;
												}else{
													lastMonth=currentMonth-1;
													lastMonthYears=currentYear;
												}
												invoiceRequestModel=new InvoiceRequestModel();
												invoiceRequestModel.setInvoiceMonth(lastMonth);
												invoiceRequestModel.setInvoiceYear(lastMonthYears);
												invoiceRequestModel.setFkPoId(poModel.getPoId());
												invoiceRequestModel.setInvoiceStatus(1);
												invoiceRequestModel.setInvoiceSentStatus(1);
												// getting last month invoice request for po
												InvoiceRequestModel invoiceRequestModelone = invoiceGenerationService.getInvoiceRequestDetails(invoiceRequestModel,
														poModel);
												//System.out.println(invoiceRequestModelone);
												if (invoiceRequestModelone != null) {
													// last month invoice details
													invoiceModel=null;
													invoiceModel=new InvoiceModel();
													invoiceModel = invoiceGenerationService.getLastInvoiceDetails(invoiceRequestModelone);
													//System.out.println(invoiceModel.toString());
													invoiceRequestModel2=null;
													invoiceRequestModel2 = new InvoiceRequestModel();
													// setting invoice..................
													invoiceRequestModel2.setInvoiceName("NEXII/" + financialYear + "/"+  today1[1]  + "/" + poModel.getPoNumber());
													invoiceRequestModel2.setInvoiceStatus(0);
													invoiceRequestModel2.setFkPoId(poModel.getPoId());
													invoiceRequestModel2.setInvoiceMonth(Integer.parseInt(today[1]));
													invoiceRequestModel2.setInvoiceYear(Integer.parseInt(today[0]));
													invoiceRequestModel2.setInvoiceFileName(
															invoiceRequestModel2.getInvoiceName() + "-stp.pdf");
													invoiceRequestModel2.setInvoiceFilePath(environment.getProperty("app.invoicefilepath"));
													invoiceRequestModel2.setInvoiceSentStatus(0);
													invoiceRequestModel2.setInvoiceCount(invoiceRequestModelone.getInvoiceCount() + 1);
													invoiceRequestModel2.setRequestedBy(poModel.getCreatedBy());
													// saving in voice request
													invoiceRequestModel2 = invoiceGenerationService.generteInvoiceRequest(invoiceRequestModel2, poModel);
													//System.out.println(invoiceRequestModel2.toString());
													// days calculation for first invoice generation
													date1 = null;
													date2 = null;
													//Date startDate = null;
													daysinmonth = 0;
													date1 = dateFormat.parse(invoiceModel.getInvoiceDate());
													date2 = dateFormat.parse(dateInString);
													daysinmonth = date2.getTime() - date1.getTime();
													daysinmonth=daysinmonth/(1000*60*60*24);
													//startDate=dateFormat.parse(poModel.getStartDate());
													if(companyWorkingDays<daysinmonth){
														daysinmonth=companyWorkingDays;
													}
													perDayAmount=0;
												    perDayAmount= poModel.getUnitPrice() / companyWorkingDays;
												    permonthRate=0;
													permonthRate=perDayAmount*daysinmonth;
													
													if(poModel.getCurrency().equals("INR")){
														cgstRate=Double.parseDouble(cgstrate);
														sgstRate=Double.parseDouble(sgstrate);
														cgstAmount=((invoiceModel.getCgst_rate()*perDayAmount)/100)*daysinmonth;
														sgstAmount=((invoiceModel.getSgstRate()*perDayAmount)/100)*daysinmonth;
														if(invoiceModel.getIgstRate()!=0&&invoiceModel.getCgst_rate()==0&&invoiceModel.getSgstRate()==0){
															cgstAmount=0;
															sgstAmount=0;
															igstAmount=((invoiceModel.getIgstRate()*perDayAmount)/100)*daysinmonth;
														}
													}else{
														cgstRate=0;
														sgstRate=0;
													}
													totalAmount=0;
													totalAmount=permonthRate+cgstAmount+sgstAmount+igstAmount;
													
													//double permonthamount = permonthRate+invoiceModel.getCgstAmount()+invoiceModel.getSgstAmount()+invoiceModel.getIgstAmount();
													
													// get bankdetails service
													//bankAccountModel = invoiceGenerationService.getBankDetails();
													invoiceModel1=null;
													invoiceModel1=new InvoiceModel();
													// basic values setting to invoice table
													invoiceModel1.setInvoiceNumber("NEXII/" + financialYear + "/"+ today1[1] + "/" + poModel.getPoNumber());
													invoiceModel1.setInvoiceDate(dateInString);
													invoiceModel1.setPoNumber(poModel.getPoNumber());
													invoiceModel1.setPoDate(poModel.getRaisedOn());
													invoiceModel1.setSupplierRefNumber(invoiceModel.getSupplierRefNumber());
													invoiceModel1.setInvoicePdfDate(invoicePdfDate);
													invoiceModel1.setShipAddLane1(poModel.getShippingAddressLane1());
													invoiceModel1.setShipAddLane2(poModel.getShippingAddressLane2());
													invoiceModel1.setShipAddState(poModel.getShippingAddressState());
													invoiceModel1.setShipAddPincode(poModel.getShippingAddressPinCode());
													invoiceModel1.setBillingAddLane1(poModel.getBillingAddressLane1());
													invoiceModel1.setBillingAddLane2(poModel.getBillingAddressLane2());
													invoiceModel1.setBillingAddState(poModel.getBillingAddressState());
													invoiceModel1.setBillingAddPincode(poModel.getBillingAddressPinCode());
													invoiceModel1.setVendorAddLane1(poModel.getVendorAddressLane1());
													invoiceModel1.setVendorAddLane2(poModel.getVendorAddressLane2());
													invoiceModel1.setVendorAddState(poModel.getVendorAddressState());
													invoiceModel1.setVendorAddPincode(poModel.getVendorAddressPinCode());
													invoiceModel1.setFkRequirementId(invoiceModel.getFkRequirementId());
													String[] lastinvoiceDate=invoiceModel.getInvoiceDate().split(" ");
													invoiceModel1.setPeriod(numberToWord.dateConverter(lastinvoiceDate[0])+" to "+numberToWord.dateConverter(dateInString));
													invoiceModel1.setFkEmployeeId(invoiceModel.getFkEmployeeId());
													invoiceModel1.setHsnSac(invoiceModel.getHsnSac());
													invoiceModel1.setQuantity(invoiceModel.getQuantity());
													invoiceModel1.setBillingDays((int) daysinmonth);
													invoiceModel1.setTotalWorkDays((int) daysinmonth);
													invoiceModel1.setRate(permonthRate);
													invoiceModel1.setTaxableAmount(permonthRate);
													invoiceModel1.setCgst_rate(invoiceModel.getCgst_rate());
													invoiceModel1.setCgstAmount(cgstAmount);
													invoiceModel1.setSgstRate(invoiceModel.getSgstRate());
													invoiceModel1.setSgstAmount(sgstAmount);
													invoiceModel1.setIgstRate(invoiceModel.getIgstRate());
													invoiceModel1.setIgstAmount(igstAmount);
													invoiceModel1.setIgstApplicable(invoiceModel.getIgstApplicable());
													invoiceModel1.setTotalAmount(totalAmount);
													invoiceModel1.setAccountHolderName(invoiceModel.getAccountHolderName());
													invoiceModel1.setBankName(invoiceModel.getBankName());
													invoiceModel1.setAccountNumber(invoiceModel.getAccountNumber());
													invoiceModel1.setIfscCode(invoiceModel.getIfscCode());
													invoiceModel1.setFkInvoiceRequestId(invoiceRequestModel2.getInvoiceRequestId());
													invoiceModel1.setCustomerPan(invoiceModel.getCustomerPan());
													invoiceModel1.setCustomerGstin(invoiceModel.getCustomerGstin());
													invoiceModel1.setCustomerState(invoiceModel.getCustomerState());
													invoiceModel1.setCustomerStateCode(invoiceModel.getCustomerStateCode());
													invoiceModel1.setOur_pan(invoiceModel.getOur_pan());
													invoiceModel1.setOurGstin(invoiceModel.getOurGstin());
													invoiceModel1.setOurState(invoiceModel.getOurState());
													invoiceModel1.setOurStateCode(invoiceModel.getOurStateCode());
													invoiceModel1.setCreatedBy(poModel.getCreatedBy());
													invoiceModel1.setShippingPan(poModel.getShippingPanNumber());
													invoiceModel1.setShippingGstin(poModel.getShippingGstIn());
													invoiceModel1.setShippingState(poModel.getShippingState());
													invoiceModel1.setShippingStateCode(poModel.getShippingStateCode());
													// basic invoice details saving in invoice table
													invoiceModel1 = invoiceGenerationService.saveInvoiceDetails(invoiceModel1);
													//System.out.println("invoice generation completed successfully..." + invoiceModel1.toString());
													if (invoiceModel != null) {
														int updatevalue = invoiceGenerationService.updateInvoiceRequest(invoiceModel1);
														invoiceModel1=null;
													}
					
												}else{
													System.out.println("invoice of this month...."+invoiceRequestModel.toString());
												}
											}
										} else {
											//System.err.println("in line 460................."+poModel.getPoId());
											System.out.println("already generated");
										}
									}else{
										System.out.println("po created_by not exist for.."+poModel.getPoNumber());
									}
				
									//System.out.println("for loop    .....line 465");
								}catch(Exception e){
								e.printStackTrace();
							
								}
								}else{
									
								}
							}
						} 
						//else {
							list = invoiceGenerationService.getExipredPOList(dateInString);
							if (!list.isEmpty()) {
								System.out.println("po list size......................................" + list.size());
								int invoicecount =0;
								String[] endDate = null;
								String[] lastInvoiceDate=null;
								double taxableValue=0;
								for (int i = 0; i < list.size(); i++) {
									System.err.println(i + ".................i value");
									poModel = new POModel();
									poModel=null;
									
									poModel = list.get(i);
									System.out.println(poModel.toString());
									System.out.println("today..............."+today[1]);
									invoiceRequestModel=new InvoiceRequestModel();
									invoiceModel=null;
									invoiceModel = new InvoiceModel();
									invoiceModel1 = null;
									invoiceModel1=new InvoiceModel();
									invoiceRequestModel2 =null;
									invoiceRequestModel2=new InvoiceRequestModel();
									bankAccountModel=null;
									bankAccountModel = new BankAccountModel();
									taxDetailsModel=null;
									taxDetailsModel=new TaxDetailsModel();
									invoiceRequestModel.setInvoiceMonth(Integer.parseInt(today[1]));
									invoiceRequestModel.setInvoiceYear(Integer.parseInt(today[0]));
									invoiceRequestModel.setFkPoId(poModel.getPoId());
									invoiceRequestModel.setInvoiceStatus(0);
									invoiceRequestModel.setInvoiceSentStatus(0);
			
									// invoice existence check
									invoiceRequestModel = invoiceGenerationService.getInvoiceRequestDetails(invoiceRequestModel,
											poModel);
									
									
									System.err.println(invoiceRequestModel);
									if (invoiceRequestModel == null) {
										invoiceRequestModel = new InvoiceRequestModel();
			
										// invoice count
										listInvoiceRequestModel = invoiceGenerationService.getCountOfInvoice(poModel);
										invoicecount = listInvoiceRequestModel.size();
										System.out.println(invoicecount);
										if(poModel.getShippingAddressLane1()==null){
											poModel.setShippingAddressLane1(" ");
										}
										if(poModel.getShippingAddressLane2()==null){
											poModel.setShippingAddressLane2(" ");
										}
										if(poModel.getShippingAddressState()==null){
											poModel.setShippingAddressState(" ");
										}
										if(poModel.getShippingAddressPinCode()==null){
											poModel.setShippingAddressPinCode(" ");
										}
										if(poModel.getBillingAddressLane1()==null){
											poModel.setBillingAddressLane1(" ");
										}
										if(poModel.getBillingAddressLane2()==null){
											poModel.setBillingAddressLane2(" ");
										}
										if(poModel.getBillingAddressState()==null){
											poModel.setBillingAddressState(" ");
										}
										if(poModel.getBillingAddressPinCode()==null){
											poModel.setBillingAddressPinCode(" ");
										}
										if(poModel.getVendorAddressLane1()==null){
											poModel.setVendorAddressLane1(" ");
										}
										if(poModel.getVendorAddressLane2()==null){
											poModel.setVendorAddressLane2(" ");
										}
										if(poModel.getVendorAddressState()==null){
											poModel.setVendorAddressState(" ");
										}
										if(poModel.getVendorAddressPinCode()==null){
											poModel.setVendorAddressPinCode(" ");
										}
										 if (invoicecount != 0) {
											 int currentMonth=Integer.parseInt(today[1]);
												int currentYear=Integer.parseInt(today[0]);
												lastMonth=0;
												int lastMonthYears=0;
												if(currentMonth==1){
													lastMonth=12;
													lastMonthYears=currentYear-1;
												}else{
													lastMonth=currentMonth-1;
													lastMonthYears=currentYear;
												}
												invoiceRequestModel.setInvoiceMonth(lastMonth);
												invoiceRequestModel.setInvoiceYear(lastMonthYears);
												invoiceRequestModel.setFkPoId(poModel.getPoId());
												invoiceRequestModel.setInvoiceStatus(1);
												invoiceRequestModel.setInvoiceSentStatus(1);
												// getting last month invoice request for po
												InvoiceRequestModel invoiceRequestModelone = invoiceGenerationService.getInvoiceRequestDetails(invoiceRequestModel,
														poModel);
												System.out.println(invoiceRequestModelone);
												if (invoiceRequestModelone != null) {
													// last month invoice details
													invoiceModel=null;
													invoiceModel=new InvoiceModel();
													invoiceModel = invoiceGenerationService.getLastInvoiceDetails(invoiceRequestModelone);
													//System.out.println(invoiceModel.toString());
													invoiceRequestModel2 = new InvoiceRequestModel();
													
													// setting invoice..................
													invoiceRequestModel2.setInvoiceName("NEXII/" + financialYear + "/"+ today1[1] + "/" + poModel.getPoNumber());
													invoiceRequestModel2.setInvoiceStatus(0);
													invoiceRequestModel2.setFkPoId(poModel.getPoId());
													invoiceRequestModel2.setInvoiceMonth(Integer.parseInt(today[1]));
													invoiceRequestModel2.setInvoiceYear(Integer.parseInt(today[0]));
													invoiceRequestModel2.setInvoiceFileName(
															invoiceRequestModel2.getInvoiceName()+ "-stp.pdf");
													invoiceRequestModel2.setInvoiceFilePath(environment.getProperty("app.invoicefilepath"));
													invoiceRequestModel2.setInvoiceSentStatus(0);
													invoiceRequestModel2.setInvoiceCount(invoiceRequestModelone.getInvoiceCount() + 1);
													invoiceRequestModel2.setRequestedBy(poModel.getCreatedBy());
													// saving in voice request
													invoiceRequestModel2 = invoiceGenerationService.generteInvoiceRequest(invoiceRequestModel2, poModel);
													//System.out.println(invoiceRequestModel2.toString());
													//days calculation for first invoice generation
													//SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
													date1 = null;
													date2 = null;
													endDate = null;
													lastInvoiceDate=null;
													daysinmonth = 0;
													invoiceModel1=null;
													invoiceModel1 = new InvoiceModel();
													date1 = dateFormat.parse(invoiceModel.getInvoiceDate());
													date2 = dateFormat.parse(dateFormat.format(poModel.getEndDate()));
													daysinmonth = date2.getTime() - date1.getTime();
													daysinmonth=daysinmonth/(1000*60*60*24);
													daysinmonth=daysinmonth+1;
													if(daysinmonth>companyWorkingDays){
														daysinmonth=companyWorkingDays;
													}
													endDate=poModel.getEndDate().split(" ");
													lastInvoiceDate=invoiceModel.getInvoiceDate().split(" ");
													perDayAmount=0;
												    perDayAmount=poModel.getUnitPrice() / companyWorkingDays;
												    permonthRate=0;
												    permonthRate = perDayAmount * companyWorkingDays;
												    taxableValue=0;
													taxableValue=perDayAmount*daysinmonth;
													cgstAmount=0;
													sgstAmount=0;
													igstAmount=0;
													if(poModel.getCurrency().equals("INR")){
														cgstRate=Double.parseDouble(cgstrate);
														sgstRate=Double.parseDouble(sgstrate);
														cgstAmount=((invoiceModel.getCgst_rate()*perDayAmount)/100)*daysinmonth;
														sgstAmount=((invoiceModel.getSgstRate()*perDayAmount)/100)*daysinmonth;
														if(invoiceModel.getIgstRate()!=0&&invoiceModel.getCgst_rate()==0&&invoiceModel.getSgstRate()==0){
															cgstRate=0;
															sgstRate=0;
															cgstAmount=0;
															sgstAmount=0;
															igstAmount=((invoiceModel.getIgstRate()*perDayAmount)/100)*daysinmonth;
														}
													}else{
														cgstRate=0;
														sgstRate=0;
													}
													double permonthamount=taxableValue+cgstAmount+sgstAmount+igstAmount;
													permonthamount=Math.round(permonthamount);
													// get bankdetails service
													// bankAccountModel = invoiceGenerationService.getBankDetails();
				
													// basic values setting to invoice table
													invoiceModel1.setInvoiceNumber("NEXII/" + financialYear + "/"+ today1[1] + "/" + poModel.getPoNumber());
													// cal.add(Calendar.DATE, 10);
													invoiceModel1.setInvoiceDate(dateInString);
													invoiceModel1.setPoNumber(poModel.getPoNumber());
													invoiceModel1.setPoDate(poModel.getRaisedOn());
													invoiceModel1.setSupplierRefNumber(invoiceModel.getSupplierRefNumber());
													invoiceModel1.setInvoicePdfDate(invoicePdfDate);
													invoiceModel1.setShipAddLane1(poModel.getShippingAddressLane1());
													invoiceModel1.setShipAddLane2(poModel.getShippingAddressLane2());
													invoiceModel1.setShipAddState(poModel.getShippingAddressState());
													invoiceModel1.setShipAddPincode(poModel.getShippingAddressPinCode());
													invoiceModel1.setBillingAddLane1(poModel.getBillingAddressLane1());
													invoiceModel1.setBillingAddLane2(poModel.getBillingAddressLane2());
													invoiceModel1.setBillingAddState(poModel.getBillingAddressState());
													invoiceModel1.setBillingAddPincode(poModel.getBillingAddressPinCode());
													invoiceModel1.setVendorAddLane1(poModel.getVendorAddressLane1());
													invoiceModel1.setVendorAddLane2(poModel.getVendorAddressLane2());
													invoiceModel1.setVendorAddState(poModel.getVendorAddressState());
													invoiceModel1.setVendorAddPincode(poModel.getVendorAddressPinCode());
													invoiceModel1.setFkRequirementId(invoiceModel.getFkRequirementId());
													invoiceModel1.setPeriod(numberToWord.dateConverter(lastInvoiceDate[0])+" to "+numberToWord.dateConverter(endDate[0]));
													invoiceModel1.setFkEmployeeId(invoiceModel.getFkEmployeeId());
													invoiceModel1.setHsnSac(invoiceModel.getHsnSac());
													invoiceModel1.setQuantity(invoiceModel.getQuantity());
													invoiceModel1.setBillingDays((int) daysinmonth);
													invoiceModel1.setTotalWorkDays((int) companyWorkingDays);
													invoiceModel1.setRate(permonthRate);
													invoiceModel1.setTaxableAmount(taxableValue);
													invoiceModel1.setCgst_rate(invoiceModel.getCgst_rate());
													invoiceModel1.setCgstAmount(cgstAmount);
													invoiceModel1.setSgstRate(invoiceModel.getSgstRate());
													invoiceModel1.setSgstAmount(sgstAmount);
													invoiceModel1.setIgstRate(invoiceModel.getIgstRate());
													invoiceModel1.setIgstAmount(igstAmount);
													invoiceModel1.setIgstApplicable(invoiceModel.getIgstApplicable());
													invoiceModel1.setTotalAmount(permonthamount);
													invoiceModel1.setAccountHolderName(invoiceModel.getAccountHolderName());
													invoiceModel1.setBankName(invoiceModel.getBankName());
													invoiceModel1.setAccountNumber(invoiceModel.getAccountNumber());
													invoiceModel1.setIfscCode(invoiceModel.getIfscCode());
													invoiceModel1.setFkInvoiceRequestId(invoiceRequestModelone.getInvoiceRequestId());
													invoiceModel1.setCustomerPan(invoiceModel.getCustomerPan());
													invoiceModel1.setCustomerGstin(invoiceModel.getCustomerGstin());
													invoiceModel1.setCustomerState(invoiceModel.getCustomerState());
													invoiceModel1.setCustomerStateCode(invoiceModel.getCustomerStateCode());
													invoiceModel1.setOur_pan(invoiceModel.getOur_pan());
													invoiceModel1.setOurGstin(invoiceModel.getOurGstin());
													invoiceModel1.setOurState(invoiceModel.getOurState());
													invoiceModel1.setOurStateCode(invoiceModel.getOurStateCode());
													invoiceModel1.setCreatedBy(poModel.getCreatedBy());
													invoiceModel1.setShippingPan(poModel.getShippingPanNumber());
													invoiceModel1.setShippingGstin(poModel.getShippingGstIn());
													invoiceModel1.setShippingState(poModel.getShippingState());
													invoiceModel1.setShippingStateCode(poModel.getShippingStateCode());
													// basic invoice details saving in invoice table
													invoiceModel1 = invoiceGenerationService.saveInvoiceDetails(invoiceModel1);
													System.out.println("invoice generation completed successfully..." + invoiceModel1.toString());
												if (invoiceModel != null) {
													int updatevalue = invoiceGenerationService.updateInvoiceRequest(invoiceModel1);
												    if(updatevalue!=0){
												    	updatevalue = invoiceGenerationService.updatePomodelExpiryDate(poModel);
												    }
												}
			
											}else{
												System.out.println("Invoice requsts are not there");
											}
										}else{
			
											
											date1 = null;
											date2 = null;
											//Date endDate = null;
											daysinmonth = 0;
											//double diffrencefortotaldays = 0;
											//System.out.println("line 276 ......."+poModel.getPoNumber());
											String[] startDate = poModel.getStartDate().split(" ");
											endDate=null;
											endDate=poModel.getEndDate().split(" ");
											String[] dayOfMonth=startDate[0].split("-");
											startMonth1=null;
											startMonth1=dayOfMonth[0]+"-"+dayOfMonth[1];
											String[] endDayOfMonth=endDate[0].split("-");
											String endMonth=endDayOfMonth[0]+"-"+endDayOfMonth[1];
											//now.add(Calendar.MONTH, -1);
											System.err.println("...............STARTED...............");
											lastMonth=0;
											lastMonth = now.get(Calendar.MONTH); // month indexing from zero
											System.out.println(lastMonth+"...........lastMonth");
											lastMonthYear=0;
											lastMonthYear  = now.get(Calendar.YEAR);
											System.out.println(lastMonth+"......lastMonthYear............"+lastMonthYear);
											dateNew1=null;
											dateNew1= dateFormatForLastMonth.parse(lastMonthYear+"-"+lastMonth);
											invoiceGenarateMonth=null;
											invoiceGenarateMonth=dateFormatForLastMonth.format(dateNew1);
											System.err.println("lastmonth......................"+dateNew1);
											System.out.println(invoiceGenarateMonth+"...........invoiceGenarateMonth.....+endMonth+....."+endMonth);
											System.out.println("date in string =="+dateInString);
											if(invoiceGenarateMonth.equals(endMonth)){
												System.out.println("month=..."+invoiceGenarateMonth);
												
												System.err.println(startMonth1+"..........startMonth...........endMonth....................."+endMonth);
												if(!startMonth1.equals(endMonth)){
													startDate[0]=endMonth+"-01";
												}
												System.err.println(startDate[0]+"..........startDate[0]................................");
												date1 = dateFormat.parse(startDate[0]);
												date2 = dateFormat.parse(endDate[0]);
												daysinmonth = date2.getTime() - date1.getTime();
												daysinmonth=daysinmonth/(1000*60*60*24);
												System.out.println(daysinmonth+"............daysinmonth from start date"+startDate[0]+"...........in date"+dateInString);
												
												System.out.println();
												daysinmonth=daysinmonth+1;
												if(daysinmonth>0){
													
													// setting values to invoiceRequestModel for first
													// invoice request
													invoiceRequestModel.setInvoiceName("NEXII/" + financialYear + "/"+  today1[1]  + "/" + poModel.getPoNumber());
													invoiceRequestModel.setInvoiceStatus(0);
													invoiceRequestModel.setFkPoId(poModel.getPoId());
													invoiceRequestModel.setInvoiceMonth(Integer.parseInt(today[1]));
													invoiceRequestModel.setInvoiceYear(Integer.parseInt(today[0]));
													invoiceRequestModel.setInvoiceFileName(
															invoiceRequestModel.getInvoiceName()+ "-stp.pdf");
													invoiceRequestModel.setInvoiceFilePath(environment.getProperty("app.invoicefilepath"));
													invoiceRequestModel.setInvoiceSentStatus(0);
													invoiceRequestModel.setInvoiceCount(1);
													invoiceRequestModel.setRequestedBy(poModel.getCreatedBy());
													
													// saving in voice request
													invoiceRequestModel = invoiceGenerationService.generteInvoiceRequest(invoiceRequestModel,poModel);
													////System.out.println(invoiceRequestModel.toString());
													
													// days calculation for first invoice generation
													if(invoiceRequestModel!=null){
														//getting requirement project id
														requirementsModel=new RequirementsModel();
														requirementsModel=invoiceGenerationService.getRequirementProjectId(poModel.getEmployeeId());
														
														//amount calculation
														
														//companyWorkingDays=28;
														if(companyWorkingDays<daysinmonth){
															daysinmonth=companyWorkingDays;
														}
														perDayAmount=0;
														perDayAmount=poModel.getUnitPrice() / companyWorkingDays;
														taxableamount=0;
														taxableamount=perDayAmount*daysinmonth;
														cgstAmount=0;
														sgstAmount=0;
														igstAmount=0;
														if(poModel.getCurrency().equals("INR")){
															cgstRate=Double.parseDouble(cgstrate);
															sgstRate=Double.parseDouble(sgstrate);
															cgstAmount=((cgstRate * perDayAmount)/100)*daysinmonth;
															sgstAmount=((sgstRate * perDayAmount)/100)*daysinmonth;
														}else{
															cgstRate=0;
															sgstRate=0;
														}
														totalAmount=0;
														totalAmount=taxableamount+cgstAmount+sgstAmount;
														bankAccountModel = invoiceGenerationService.getBankDetails();
														taxDetailsModel = invoiceGenerationService.getNexiiTaxDetails();
														//System.err.println("after bank details..........line 290");
														invoiceModel=null;
														invoiceModel = new InvoiceModel();
														// basic values setting to invoice table
														invoiceModel.setInvoiceNumber("NEXII/" + financialYear + "/"+today1[1] + "/" + poModel.getPoNumber());
														// cal.add(Calendar.DATE, 10);
														invoiceModel.setInvoiceDate(dateInString);
														invoiceModel.setPoNumber(poModel.getPoNumber());
														invoiceModel.setPoDate(poModel.getRaisedOn());
														invoiceModel.setSupplierRefNumber(poModel.getSupplierRefNumber());
														invoiceModel.setInvoicePdfDate(invoicePdfDate);
														invoiceModel.setShipAddLane1(poModel.getShippingAddressLane1());
														invoiceModel.setShipAddLane2(poModel.getShippingAddressLane2());
														invoiceModel.setShipAddState(poModel.getShippingAddressState());
														invoiceModel.setShipAddPincode(poModel.getShippingAddressPinCode());
														invoiceModel.setBillingAddLane1(poModel.getBillingAddressLane1());
														invoiceModel.setBillingAddLane2(poModel.getBillingAddressLane2());
														invoiceModel.setBillingAddState(poModel.getBillingAddressState());
														invoiceModel.setBillingAddPincode(poModel.getBillingAddressPinCode());
														invoiceModel.setVendorAddLane1(poModel.getVendorAddressLane1());
														invoiceModel.setVendorAddLane2(poModel.getVendorAddressLane2());
														invoiceModel.setVendorAddState(poModel.getVendorAddressState());
														invoiceModel.setVendorAddPincode(poModel.getVendorAddressPinCode());
														invoiceModel.setFkRequirementId(requirementsModel.getRequirementId());
														invoiceModel.setPeriod(numberToWord.dateConverter(startDate[0])+" to "+numberToWord.dateConverter(endDate[0]));
														invoiceModel.setFkEmployeeId(poModel.getEmployeeId());
														invoiceModel.setHsnSac(hsnsac);
														invoiceModel.setQuantity(1);
														invoiceModel.setBillingDays((int)daysinmonth);
														invoiceModel.setTotalWorkDays((int)companyWorkingDays);
														invoiceModel.setRate(poModel.getUnitPrice());
														invoiceModel.setTaxableAmount(taxableamount);
														invoiceModel.setCgst_rate(cgstRate);
														invoiceModel.setCgstAmount(cgstAmount);
														invoiceModel.setSgstRate(sgstRate);
														invoiceModel.setSgstAmount(sgstAmount);
														invoiceModel.setIgstRate(0);
														invoiceModel.setIgstAmount(0);
														invoiceModel.setIgstApplicable(0);
														invoiceModel.setTotalAmount(totalAmount);
														invoiceModel.setAccountHolderName(bankAccountModel.getAccountHolderName());
														invoiceModel.setBankName(bankAccountModel.getBankName());
														invoiceModel.setAccountNumber(bankAccountModel.getAccountNumber());
														invoiceModel.setIfscCode(bankAccountModel.getAccountIFSC());
														invoiceModel.setFkInvoiceRequestId(invoiceRequestModel.getInvoiceRequestId());
														invoiceModel.setCustomerPan(poModel.getPanNumber());
														invoiceModel.setCustomerGstin(poModel.getGstIn());
														invoiceModel.setCustomerState(poModel.getState());
														invoiceModel.setCustomerStateCode(poModel.getStateCode());
														invoiceModel.setOur_pan(taxDetailsModel.getPanNumber());
														invoiceModel.setOurGstin(taxDetailsModel.getPanNumber());
														invoiceModel.setOurState(taxDetailsModel.getState());
														invoiceModel.setOurStateCode(taxDetailsModel.getStateCode());
														invoiceModel.setCreatedBy(poModel.getCreatedBy());
														invoiceModel.setShippingPan(poModel.getShippingPanNumber());
														invoiceModel.setShippingGstin(poModel.getShippingGstIn());
														invoiceModel.setShippingState(poModel.getShippingState());
														invoiceModel.setShippingStateCode(poModel.getShippingStateCode());
														//System.out.println(invoiceModel.toString()+".............................in line 332");
														// basic invoice details saving in invoice table
														invoiceModel = invoiceGenerationService.saveInvoiceDetails(invoiceModel);
														//System.out.println("invoice generation completed successfully..." + invoiceModel.toString());
														if (invoiceModel != null) {
															//System.err.println(invoiceModel.toString()+"......line 338");
															int updatevalue = invoiceGenerationService.updateInvoiceRequest(invoiceModel);
															if(updatevalue!=0){
														    	updatevalue = invoiceGenerationService.updatePomodelExpiryDate(poModel);
														    }
															invoiceModel=null;
															invoiceRequestModel=null;
															//System.err.println(updatevalue+"......line 342");
														}
														System.out.println("in if end ...........line 344");
													
													}else{
														System.err.println("invoice request model empty id not updated in invoice model");
													}
												}else{
													System.out.println("Start date is not yet reached");
												}
											}else{
												int updatevalue1 = invoiceGenerationService.updatePomodelExpiryDate(poModel);
												System.err.println("updatevalue1==="+updatevalue1);
												System.out.println("Old invoices expiry status changed");
											}
										}
									} else {
			
										System.out.println("already generated");
									}
								}
							}else{
								System.out.println("No last month invoices");
							}
							userResponseDTO.setStatusCode(1);
							userResponseDTO.setMessage("No invoices to generate for this month");
							System.out.println("no invoices to generate");
				}else{
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Atleast one bank account and tax details required to generate invoice");
				}
				userResponseDTO.setStatusCode(1);
				userResponseDTO.setMessage("Invoice Generation completed");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return userResponseDTO;

	}

	@RequestMapping(path = "/genertepdfinvoice")
	public UserResponseDTO genertePdfInvoice(@RequestParam("invoiceRequestId") int invoiceRequestId,
			@RequestParam("invoiceId") int invoiceId, @RequestParam("invoiceNumber") String invoiceNumber,
			@RequestParam("cGSTAmount") double cGSTAmount, @RequestParam("total") double total,
			@RequestParam("invoiceDate") String invoiceDate, @RequestParam("sGSTAmount") double sGSTAmount,
			@RequestParam("bankName") String bankName, @RequestParam("pONumber") String poNumber,
			@RequestParam("iGSTAmount") double iGSTAmount, @RequestParam("iFSCCode") String iFSCCode,
			@RequestParam("pODate") String pODate, @RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("vendorAddressLane1") String vendorAddressLane1,@RequestParam("vendorAddressLane2") String vendorAddressLane2,
			@RequestParam("vendorAddressState") String vendorAddressState,@RequestParam("vendorAddressPincode") String vendorAddressPincode,
			@RequestParam("supplierReferanceNumber") String supplierReferanceNumber,
			@RequestParam("bankAcNo") String bankAcNo, @RequestParam("project") int requirement,
			@RequestParam("quantity") int quantity, 
			@RequestParam("billingAddressLane1") String billingAddressLane1,@RequestParam("billingAddressLane2") String billingAddressLane2,
			@RequestParam("billingAddressState") String billingAddressState,@RequestParam("billingAddressPincode") String billingAddressPincode,
			@RequestParam("period") String period, @RequestParam("billingDays") int billingDays,
			@RequestParam("employID") int employID, @RequestParam("rate") double rate,
			@RequestParam("shippingAddressLane1") String shippingAddressLane1,@RequestParam("shippingAddressLane2") String shippingAddressLane2,
			@RequestParam("shippingAddressState") String shippingAddressState,@RequestParam("shippingAddressPincode") String shippingAddressPincode,
			@RequestParam("hSNSAC") String hSNSAC,
			@RequestParam("cGSTRate") double cGSTRate, @RequestParam("days") int days,
			@RequestParam("sGSTRate") double sGSTRate, @RequestParam("taxableValue") double taxableValue,
			@RequestParam("iGSTRate") double iGSTRate, @RequestParam("iGSTApplicable") int iGSTApplicable,
			@RequestParam("customerPan") String customerPan, @RequestParam("customerGstin") String customerGstin,
			@RequestParam("customerState") String customerState,
			@RequestParam("customerStateCode") String customerStateCode, @RequestParam("ourPan") String ourPan,
			@RequestParam("OurGstin") String ourGstin, @RequestParam("ourState") String ourState,
			@RequestParam("ourStateCode") String ourStateCode, @RequestParam("empName") String empName,
			@RequestParam("projectName") String projectName,@RequestParam("currencyType") String currencyType,@RequestParam("invoicePdfDate") String invoicePdfDate,@RequestParam("clientName") String clientName,
			@RequestParam("shipingPan") String shipingPan,@RequestParam("shippingGst") String shippingGst,@RequestParam("shippingState") String shippingState,
			@RequestParam("shippingCode") String shippingCode,HttpServletRequest request,HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		UserResponseDTO userResponseDTO=new UserResponseDTO();
		try{
			if(userSession!=null){
				CreateUserModel createUserModel=(CreateUserModel) userSession.getAttribute("userModel");
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("Invoices")){
					InvoiceModel invoiceModel = new InvoiceModel();
					invoiceModel = new InvoiceModel();
					// basic values setting to invoice table
					invoiceModel.setInvoiceId(invoiceId);
					invoiceModel.setInvoiceNumber(invoiceNumber);
					invoiceModel.setInvoiceDate(invoiceDate);
					invoiceModel.setPoNumber(poNumber);
					invoiceModel.setPoDate(pODate);
					invoiceModel.setSupplierRefNumber(supplierReferanceNumber);
					invoiceModel.setInvoicePdfDate(invoicePdfDate);
					invoiceModel.setShipAddLane1(shippingAddressLane1);
					invoiceModel.setShipAddLane2(shippingAddressLane2);
					invoiceModel.setShipAddState(shippingAddressState);
					invoiceModel.setShipAddPincode(shippingAddressPincode);
					invoiceModel.setBillingAddLane1(billingAddressLane1);
					invoiceModel.setBillingAddLane2(billingAddressLane2);
					invoiceModel.setBillingAddState(billingAddressState);
					invoiceModel.setBillingAddPincode(billingAddressPincode);
					invoiceModel.setVendorAddLane1(vendorAddressLane1);
					invoiceModel.setVendorAddLane2(vendorAddressLane2);
					invoiceModel.setVendorAddState(vendorAddressState);
					invoiceModel.setVendorAddPincode(vendorAddressPincode);
					invoiceModel.setFkRequirementId(requirement);
					invoiceModel.setPeriod(period);
					invoiceModel.setFkEmployeeId(employID);
					invoiceModel.setHsnSac(hSNSAC);
					invoiceModel.setQuantity(quantity);
					invoiceModel.setBillingDays(billingDays);
					invoiceModel.setTotalWorkDays(days);
					invoiceModel.setRate(rate);
					invoiceModel.setTaxableAmount(taxableValue);
					invoiceModel.setCgst_rate(cGSTRate);
					invoiceModel.setCgstAmount(cGSTAmount);
					invoiceModel.setSgstRate(sGSTRate);
					invoiceModel.setSgstAmount(sGSTAmount);
					invoiceModel.setIgstRate(iGSTRate);
					invoiceModel.setIgstAmount(iGSTAmount);
					invoiceModel.setIgstApplicable(0);
					invoiceModel.setTotalAmount(total);
					invoiceModel.setAccountHolderName(accountHolderName);
					invoiceModel.setBankName(bankName);
					invoiceModel.setAccountNumber(bankAcNo);
					invoiceModel.setIfscCode(iFSCCode);
					invoiceModel.setFkInvoiceRequestId(invoiceRequestId);
					invoiceModel.setCustomerPan(customerPan);
					invoiceModel.setCustomerGstin(customerGstin);
					invoiceModel.setCustomerState(customerState);
					invoiceModel.setCustomerStateCode(customerStateCode);
					invoiceModel.setOur_pan(ourPan);
					invoiceModel.setOurGstin(ourGstin);
					invoiceModel.setOurState(ourState);
					invoiceModel.setOurStateCode(ourStateCode);
					invoiceModel.setCreatedBy(createUserModel.getUserId());
					String imagePath=environment.getProperty("app.invoicelogopath");
					String DEST=environment.getProperty("app.invoicefilepath");
					int printStatus=0;
					String status = invoiceGenerationService.genertePdfInvoice(invoiceModel,empName,projectName,currencyType,clientName,createUserModel.getUserId(),imagePath,DEST,printStatus);
					if(status=="Invoice generated successfully"){
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setMessage(status);
					}else{
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage(status);
					}
				}else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Authentication require to access this");
				}
			}else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Login Required to Access this service");
			}
		}catch(Exception e){
			e.printStackTrace();
			userResponseDTO.setStatusCode(0);
			userResponseDTO.setMessage("Exception occure");
		}
		return userResponseDTO;
	}
	@RequestMapping(path = "/downloadpdfinvoice")
	public ResponseEntity<?> DownloadPdfInvoice(@RequestParam("invoiceRequestId") int invoiceRequestId,
			@RequestParam("invoiceId") int invoiceId, @RequestParam("invoiceNumber") String invoiceNumber,
			@RequestParam("cGSTAmount") double cGSTAmount, @RequestParam("total") double total,
			@RequestParam("invoiceDate") String invoiceDate, @RequestParam("sGSTAmount") double sGSTAmount,
			@RequestParam("bankName") String bankName, @RequestParam("pONumber") String poNumber,
			@RequestParam("iGSTAmount") double iGSTAmount, @RequestParam("iFSCCode") String iFSCCode,
			@RequestParam("pODate") String pODate, @RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("vendorAddressLane1") String vendorAddressLane1,@RequestParam("vendorAddressLane2") String vendorAddressLane2,
			@RequestParam("vendorAddressState") String vendorAddressState,@RequestParam("vendorAddressPincode") String vendorAddressPincode,
			@RequestParam("supplierReferanceNumber") String supplierReferanceNumber,
			@RequestParam("bankAcNo") String bankAcNo, @RequestParam("project") int requirement,
			@RequestParam("quantity") int quantity, 
			@RequestParam("billingAddressLane1") String billingAddressLane1,@RequestParam("billingAddressLane2") String billingAddressLane2,
			@RequestParam("billingAddressState") String billingAddressState,@RequestParam("billingAddressPincode") String billingAddressPincode,
			@RequestParam("period") String period, @RequestParam("billingDays") int billingDays,
			@RequestParam("employID") int employID, @RequestParam("rate") double rate,
			@RequestParam("shippingAddressLane1") String shippingAddressLane1,@RequestParam("shippingAddressLane2") String shippingAddressLane2,
			@RequestParam("shippingAddressState") String shippingAddressState,@RequestParam("shippingAddressPincode") String shippingAddressPincode,
			@RequestParam("hSNSAC") String hSNSAC,
			@RequestParam("cGSTRate") double cGSTRate, @RequestParam("days") int days,
			@RequestParam("sGSTRate") double sGSTRate, @RequestParam("taxableValue") double taxableValue,
			@RequestParam("iGSTRate") double iGSTRate, @RequestParam("iGSTApplicable") int iGSTApplicable,
			@RequestParam("customerPan") String customerPan, @RequestParam("customerGstin") String customerGstin,
			@RequestParam("customerState") String customerState,
			@RequestParam("customerStateCode") String customerStateCode, @RequestParam("ourPan") String ourPan,
			@RequestParam("OurGstin") String ourGstin, @RequestParam("ourState") String ourState,
			@RequestParam("ourStateCode") String ourStateCode, @RequestParam("empName") String empName,
			@RequestParam("projectName") String projectName,@RequestParam("currencyType") String currencyType,@RequestParam("invoicePdfDate") String invoicePdfDate,
			@RequestParam("clientName") String clientName,@RequestParam("shipingPan") String shipingPan,@RequestParam("shippingGst") String shippingGst,@RequestParam("shippingState") String shippingState,
			@RequestParam("shippingCode") String shippingCode,HttpServletRequest request,HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		InvoiceRequestModel invoiceRequestModel=new InvoiceRequestModel();
		ResponseEntity<?> status =null;
		if(userSession!=null){
			CreateUserModel createUserModel=(CreateUserModel) userSession.getAttribute("userModel");
			List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
			if(menuList.contains("Invoices")){
				try{
					InvoiceModel invoiceModel = new InvoiceModel();
					invoiceModel = new InvoiceModel();
					invoiceModel.setInvoiceId(invoiceId);
					invoiceModel.setInvoiceNumber(invoiceNumber);
					invoiceModel.setInvoiceDate(invoiceDate);
					invoiceModel.setPoNumber(poNumber);
					invoiceModel.setPoDate(pODate);
					invoiceModel.setSupplierRefNumber(supplierReferanceNumber);
					invoiceModel.setShipAddLane1(shippingAddressLane1);
					invoiceModel.setShipAddLane2(shippingAddressLane2);
					invoiceModel.setShipAddState(shippingAddressState);
					invoiceModel.setShipAddPincode(shippingAddressPincode);
					invoiceModel.setBillingAddLane1(billingAddressLane1);
					invoiceModel.setBillingAddLane2(billingAddressLane2);
					invoiceModel.setBillingAddState(billingAddressState);
					invoiceModel.setBillingAddPincode(billingAddressPincode);
					invoiceModel.setVendorAddLane1(vendorAddressLane1);
					invoiceModel.setVendorAddLane2(vendorAddressLane2);
					invoiceModel.setVendorAddState(vendorAddressState);
					invoiceModel.setVendorAddPincode(vendorAddressPincode);
					invoiceModel.setFkRequirementId(requirement);
					invoiceModel.setPeriod(period);
					invoiceModel.setFkEmployeeId(employID);
					invoiceModel.setHsnSac(hSNSAC);
					invoiceModel.setQuantity(quantity);
					invoiceModel.setBillingDays(billingDays);
					invoiceModel.setTotalWorkDays(days);
					invoiceModel.setRate(rate);
					invoiceModel.setTaxableAmount(taxableValue);
					invoiceModel.setCgst_rate(cGSTRate);
					invoiceModel.setCgstAmount(cGSTAmount);
					invoiceModel.setSgstRate(sGSTRate);
					invoiceModel.setSgstAmount(sGSTAmount);
					invoiceModel.setIgstRate(iGSTRate);
					invoiceModel.setIgstAmount(iGSTAmount);
					invoiceModel.setIgstApplicable(0);
					invoiceModel.setTotalAmount(total);
					invoiceModel.setAccountHolderName(accountHolderName);
					invoiceModel.setBankName(bankName);
					invoiceModel.setAccountNumber(bankAcNo);
					invoiceModel.setIfscCode(iFSCCode);
					invoiceModel.setFkInvoiceRequestId(invoiceRequestId);
					invoiceModel.setCustomerPan(customerPan);
					invoiceModel.setCustomerGstin(customerGstin);
					invoiceModel.setCustomerState(customerState);
					invoiceModel.setCustomerStateCode(customerStateCode);
					invoiceModel.setOur_pan(ourPan);
					invoiceModel.setOurGstin(ourGstin);
					invoiceModel.setOurState(ourState);
					invoiceModel.setOurStateCode(ourStateCode);
					invoiceModel.setCreatedBy(createUserModel.getUserId());
					invoiceModel.setInvoicePdfDate(invoicePdfDate);
					String imagePath=environment.getProperty("app.invoicelogopath");
					String DEST=environment.getProperty("app.invoicefilepath");
					int printStatus=0;
					invoiceRequestModel = invoiceGenerationService.DownloadPdfInvoice(invoiceModel,empName,projectName,currencyType,clientName,createUserModel.getUserId(),imagePath,DEST,printStatus);
					
					File file = new File(invoiceRequestModel.getInvoiceFilePath());
					InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
					if (file.exists()) {
						String mediaType = Files.probeContentType(file.toPath());
						System.out.println("mediaType: " + mediaType.toString());
						return ResponseEntity.ok()
								.header("Content-disposition", "attachment; filename=\"" + file.getName() + "")
								.contentType(MediaType.parseMediaType(mediaType)).contentLength(file.length())
								.body(resource);
					} else {
						return new ResponseEntity<String>("File not found to download", HttpStatus.OK);
					}
			
		 		}catch(Exception e){
		 			e.printStackTrace();
		 		}
			}
			return new ResponseEntity<String>("Login required to access this", HttpStatus.ACCEPTED);
		
		}
		return new ResponseEntity<String>("", HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(path = "/downloadmyinvoice")
	public ResponseEntity<?> downloadMyInvoice(@RequestParam("requestId") int requestId,HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession userSession = request.getSession(false);
		if(userSession!=null){
			CreateUserModel createUserModel=(CreateUserModel) userSession.getAttribute("userModel");
			List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
			if(menuList.contains("Invoices")){
				InvoiceRequestModel invoiceRequestModel=new InvoiceRequestModel();
				invoiceRequestModel = invoiceGenerationService.downloadMyInvoice(requestId);
				try{
					System.err.println(invoiceRequestModel.getInvoiceFilePath());
					File file = new File(invoiceRequestModel.getInvoiceFilePath());
					
					InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
					if (file.exists()) {
						String mediaType = Files.probeContentType(file.toPath());
						System.out.println("mediaType: " + mediaType.toString());
						return ResponseEntity.ok()
								.header("Content-disposition", "attachment; filename=\"" + file.getName() + "")
								.contentType(MediaType.parseMediaType(mediaType)).contentLength(file.length())
								.body(resource);
					} else {
						return new ResponseEntity<String>("File not found to download", HttpStatus.OK);
					}
			
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			return new ResponseEntity<String>("", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("", HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(path = "/printpdfinvoice")
	public ResponseEntity<?>  printPdfInvoice(@RequestParam("invoiceRequestId") int invoiceRequestId,
			@RequestParam("invoiceId") int invoiceId, @RequestParam("invoiceNumber") String invoiceNumber,
			@RequestParam("cGSTAmount") double cGSTAmount, @RequestParam("total") double total,
			@RequestParam("invoiceDate") String invoiceDate, @RequestParam("sGSTAmount") double sGSTAmount,
			@RequestParam("bankName") String bankName, @RequestParam("pONumber") String poNumber,
			@RequestParam("iGSTAmount") double iGSTAmount, @RequestParam("iFSCCode") String iFSCCode,
			@RequestParam("pODate") String pODate, @RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("vendorAddressLane1") String vendorAddressLane1,@RequestParam("vendorAddressLane2") String vendorAddressLane2,
			@RequestParam("vendorAddressState") String vendorAddressState,@RequestParam("vendorAddressPincode") String vendorAddressPincode,
			@RequestParam("supplierReferanceNumber") String supplierReferanceNumber,
			@RequestParam("bankAcNo") String bankAcNo, @RequestParam("project") int requirement,
			@RequestParam("quantity") int quantity, 
			@RequestParam("billingAddressLane1") String billingAddressLane1,@RequestParam("billingAddressLane2") String billingAddressLane2,
			@RequestParam("billingAddressState") String billingAddressState,@RequestParam("billingAddressPincode") String billingAddressPincode,
			@RequestParam("period") String period, @RequestParam("billingDays") int billingDays,
			@RequestParam("employID") int employID, @RequestParam("rate") double rate,
			@RequestParam("shippingAddressLane1") String shippingAddressLane1,@RequestParam("shippingAddressLane2") String shippingAddressLane2,
			@RequestParam("shippingAddressState") String shippingAddressState,@RequestParam("shippingAddressPincode") String shippingAddressPincode,
			@RequestParam("hSNSAC") String hSNSAC,
			@RequestParam("cGSTRate") double cGSTRate, @RequestParam("days") int days,
			@RequestParam("sGSTRate") double sGSTRate, @RequestParam("taxableValue") double taxableValue,
			@RequestParam("iGSTRate") double iGSTRate, @RequestParam("iGSTApplicable") int iGSTApplicable,
			@RequestParam("customerPan") String customerPan, @RequestParam("customerGstin") String customerGstin,
			@RequestParam("customerState") String customerState,
			@RequestParam("customerStateCode") String customerStateCode, @RequestParam("ourPan") String ourPan,
			@RequestParam("OurGstin") String ourGstin, @RequestParam("ourState") String ourState,
			@RequestParam("ourStateCode") String ourStateCode, @RequestParam("empName") String empName,
			@RequestParam("projectName") String projectName,@RequestParam("currencyType") String currencyType,@RequestParam("invoicePdfDate") String invoicePdfDate,
			@RequestParam("clientName") String clientName,@RequestParam("shipingPan") String shipingPan,@RequestParam("shippingGst") String shippingGst,@RequestParam("shippingState") String shippingState,
			@RequestParam("shippingCode") String shippingCode, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		//ResponseEntity<?> status =null;
		if(userSession!=null){
			CreateUserModel createUserModel=(CreateUserModel) userSession.getAttribute("userModel");
			List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
			InvoiceRequestModel invoiceRequestModel=new InvoiceRequestModel();
			if(menuList.contains("Invoices")){
				try{
					InvoiceModel invoiceModel = new InvoiceModel();
					invoiceModel = new InvoiceModel();
					invoiceModel.setInvoiceId(invoiceId);
					invoiceModel.setInvoiceNumber(invoiceNumber);
					invoiceModel.setInvoiceDate(invoiceDate);
					invoiceModel.setPoNumber(poNumber);
					invoiceModel.setPoDate(pODate);
					invoiceModel.setSupplierRefNumber(supplierReferanceNumber);
					invoiceModel.setShipAddLane1(shippingAddressLane1);
					invoiceModel.setShipAddLane2(shippingAddressLane2);
					invoiceModel.setShipAddState(shippingAddressState);
					invoiceModel.setShipAddPincode(shippingAddressPincode);
					invoiceModel.setBillingAddLane1(billingAddressLane1);
					invoiceModel.setBillingAddLane2(billingAddressLane2);
					invoiceModel.setBillingAddState(billingAddressState);
					invoiceModel.setBillingAddPincode(billingAddressPincode);
					invoiceModel.setVendorAddLane1(vendorAddressLane1);
					invoiceModel.setVendorAddLane2(vendorAddressLane2);
					invoiceModel.setVendorAddState(vendorAddressState);
					invoiceModel.setVendorAddPincode(vendorAddressPincode);
					invoiceModel.setFkRequirementId(requirement);
					invoiceModel.setPeriod(period);
					invoiceModel.setFkEmployeeId(employID);
					invoiceModel.setHsnSac(hSNSAC);
					invoiceModel.setQuantity(quantity);
					invoiceModel.setBillingDays(billingDays);
					invoiceModel.setTotalWorkDays(days);
					invoiceModel.setRate(rate);
					invoiceModel.setTaxableAmount(taxableValue);
					invoiceModel.setCgst_rate(cGSTRate);
					invoiceModel.setCgstAmount(cGSTAmount);
					invoiceModel.setSgstRate(sGSTRate);
					invoiceModel.setSgstAmount(sGSTAmount);
					invoiceModel.setIgstRate(iGSTRate);
					invoiceModel.setIgstAmount(iGSTAmount);
					invoiceModel.setIgstApplicable(0);
					invoiceModel.setTotalAmount(total);
					invoiceModel.setAccountHolderName(accountHolderName);
					invoiceModel.setBankName(bankName);
					invoiceModel.setAccountNumber(bankAcNo);
					invoiceModel.setIfscCode(iFSCCode);
					invoiceModel.setFkInvoiceRequestId(invoiceRequestId);
					invoiceModel.setCustomerPan(customerPan);
					invoiceModel.setCustomerGstin(customerGstin);
					invoiceModel.setCustomerState(customerState);
					invoiceModel.setCustomerStateCode(customerStateCode);
					invoiceModel.setOur_pan(ourPan);
					invoiceModel.setOurGstin(ourGstin);
					invoiceModel.setOurState(ourState);
					invoiceModel.setOurStateCode(ourStateCode);
					invoiceModel.setCreatedBy(createUserModel.getUserId());
					invoiceModel.setInvoicePdfDate(invoicePdfDate);
					String imagePath=environment.getProperty("app.invoicelogopath");
					String DEST=environment.getProperty("app.invoicefilepath");
					int printStatus=1;
					invoiceRequestModel = invoiceGenerationService.DownloadPdfInvoice(invoiceModel,empName,projectName,currencyType,clientName,createUserModel.getUserId(),imagePath,DEST,printStatus);
					
					File file = new File(invoiceRequestModel.getInvoiceFilePath());
					InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
					if (file.exists()) {
						String mediaType = Files.probeContentType(file.toPath());
						System.out.println("mediaType: " + mediaType.toString());
						return ResponseEntity.ok()
								.header("Content-disposition", "inline; filename=\"" + file.getName() + "")
								.contentType(MediaType.parseMediaType(mediaType)).contentLength(file.length())
								.body(resource);
					} else {
						return new ResponseEntity<String>("File not found to download", HttpStatus.OK);
					}
					
		 		}catch(Exception e){
		 			e.printStackTrace();
		 		}
			}
			return new ResponseEntity<String>("", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("", HttpStatus.ACCEPTED);
	}
	
	// my invoices list
	@RequestMapping(path = "/myinvoices")
	public List<EditInvoiceDetailsResponseModel> getMyinvoices(HttpServletRequest request, HttpServletResponse response) {

		HttpSession userSession = request.getSession(false);
		List<EditInvoiceDetailsResponseModel> list=null;
		try{
			if(userSession!=null){
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("Invoices")){
					list=invoiceGenerationService.getMyInvoiceList(userModel.getUserId());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
		
	}
	// my invoices list
	@RequestMapping(path = "/myinvoicesfilter")
	public List<EditInvoiceDetailsResponseModel> getMyinvoiceslist(@RequestParam("monthandyear") String monthandyear,HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<EditInvoiceDetailsResponseModel> list=null;
		try{
			CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
			List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
			if(userSession!=null){
				if(menuList.contains("Invoices")){
					list=invoiceGenerationService.getMyInvoiceListFilter(userModel.getUserId(),monthandyear);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	// my sample
	@RequestMapping(path = "/sample")
	public  String getmail() {
		
		String s="0";
		/*FileStream fileStream;
        fileStream = new FileStream(@"C:\test.pdf", FileMode.CreateNew);

        c.convertFile(@"C:\test.html", fileStream);
        c.setPageHeight("297mm");
        c.setPageWidth("210mm");

        fileStream.Close();*/
		return s;
				
	}
	
	@RequestMapping(path = "/searchbank")
	public HashMap<String, BankAccountModel> searchBank(@RequestParam("term") String term,HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		try {
			if(userSession!=null){
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("Invoices")){
	                System.out.println("Data from ajax call " + term);
	                HashMap<String, BankAccountModel> map = invoiceGenerationService.getBankData(term);
	                return map;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
	        e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(path = "/searchpandetails")
	public HashMap<String, TaxDetailsModel> searchTaxDetails(@RequestParam("term") String term,HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
		try {
			if(userSession!=null){
				if(menuList.contains("Invoices")){
	                System.out.println("Data from ajax call " + term);
	                HashMap<String, TaxDetailsModel> map = invoiceGenerationService.searchTaxDEtails(term);
	                return map;
				}
			}
		} catch (Exception e) {
           System.err.println(e.getMessage());
           e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(path = "/freezeinvoice")
	public  UserResponseDTO freezeInvoice(@RequestParam("fkRequestId") String fkRequestId,@RequestParam("invoiceId") String invoiceId,HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession userSession = request.getSession(false);
		UserResponseDTO userResponseDTO=new UserResponseDTO();
		
		try{
			if(userSession!=null){
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("Invoices")){
					userResponseDTO=invoiceGenerationService.freezeInvoice(fkRequestId,invoiceId,userModel.getUserId());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return userResponseDTO;
				
	}
	
	
}