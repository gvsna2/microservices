package com.nexiilabs.stp.invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.nexiilabs.stp.authentication.FlasMailConfig;
import com.nexiilabs.stp.bankaccount.BankAccountModel;
import com.nexiilabs.stp.bankaccount.TaxDetailsModel;
import com.nexiilabs.stp.resource.POModel;
import com.nexiilabs.stp.resource.RequirementsModel;
import com.nexiilabs.stp.user.UserResponseDTO;

@Transactional
@Repository
public class InvoiceGenerationDaoImpl implements InvoiceGenerationDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<POModel> getPOList(String date) {
		List<POModel> list = new ArrayList<POModel>();
		try {
			String hql = "FROM POModel where endDate>=:date and expiryStatus=0 and deleteStatus=0";
			Query query = entityManager.createQuery(hql);
			query.setParameter("date", date);
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BankAccountModel getBankDetails() {
		BankAccountModel bankAccountModel = new BankAccountModel();
		String hql = "FROM BankAccountModel WHERE deleteStatus=:deleteStatus and isActive=:isActive";
		Query query = entityManager.createQuery(hql);
		query.setParameter("deleteStatus", 0);
		query.setParameter("isActive", 1);
		List<BankAccountModel> list = query.getResultList();
		if (!list.isEmpty()) {
			return bankAccountModel = list.get(0);
		}

		return null;
	}
	@Override
	public InvoiceRequestModel getInvoiceRequestDetails(InvoiceRequestModel invoiceRequestModel, POModel poModel) {
		System.err.println("last month..................."+invoiceRequestModel.toString());
		System.err.println("last month..................."+poModel.toString());
		String hql = "FROM InvoiceRequestModel WHERE invoiceMonth=:invoiceMonth and fkPoId=:fkPoId and invoiceYear=:invoiceYear and invoiceStatus=:invoiceStatus and invoiceSentStatus=:invoiceSentStatus";
		List<InvoiceRequestModel> list = new ArrayList<InvoiceRequestModel>();
		Query query = entityManager.createQuery(hql);
		query.setParameter("invoiceMonth", invoiceRequestModel.getInvoiceMonth());
		query.setParameter("fkPoId", invoiceRequestModel.getFkPoId());
		query.setParameter("invoiceYear", invoiceRequestModel.getInvoiceYear());
		query.setParameter("invoiceStatus", invoiceRequestModel.getInvoiceStatus());
		query.setParameter("invoiceSentStatus", invoiceRequestModel.getInvoiceSentStatus());
		list = query.getResultList();
		if (!list.isEmpty()) {
			return invoiceRequestModel = list.get(0);
		}
		return null;
	}

	@Override
	public List<InvoiceRequestModel> getCountOfInvoice(POModel poModel) {
		String hql = "FROM InvoiceRequestModel WHERE fkPoId=:fkPoId and generatedOn<=:generatedOn";
		List<InvoiceRequestModel> list = new ArrayList<InvoiceRequestModel>();
		try {

			// InvoiceRequestModel invoiceRequestModel=new
			// InvoiceRequestModel();
			Query query = entityManager.createQuery(hql);
			query.setParameter("fkPoId", poModel.getPoId());
			query.setParameter("generatedOn", poModel.getEndDate());
			list = query.getResultList();
			System.err.println("invoice request list size.........." + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public InvoiceRequestModel generteInvoiceRequest(InvoiceRequestModel invoiceRequestModel, POModel poModel) {
		try {
			entityManager.persist(invoiceRequestModel);
			System.err.println("invoiceRequestModel save..................." + invoiceRequestModel.toString());
			return invoiceRequestModel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public InvoiceModel saveInvoiceDetails(InvoiceModel invoiceModel) {
		try {
			entityManager.persist(invoiceModel);
			//System.err.println("invoiceModel save........................." + invoiceModel.toString());
			if(invoiceModel.getInvoiceId()==0){
				String hql ="DELETE FROM stp_invoice_request where invoice_request_id="+invoiceModel.getFkInvoiceRequestId();
				int update = entityManager.createNativeQuery(hql).executeUpdate();
			}
			return invoiceModel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InvoiceModel getLastInvoiceDetails(InvoiceRequestModel invoiceRequestModel) {

		String hql = "FROM InvoiceModel WHERE fkInvoiceRequestId=:fkInvoiceRequestId ORDER BY  invoiceDate DESC";
		InvoiceModel invoiceModel = new InvoiceModel();
		List<InvoiceModel> list = new ArrayList<InvoiceModel>();
		try {
			System.out.println(invoiceRequestModel.toString());
			Query query = entityManager.createQuery(hql);
			query.setParameter("fkInvoiceRequestId", invoiceRequestModel.getInvoiceRequestId());
			System.out.println(query.toString());
			list = query.getResultList();
			if (!list.isEmpty()) {
				System.err.println("invoice request list size.........." + list.size());
				return invoiceModel = list.get(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InvoiceModel updateInvoiceDetails(InvoiceModel invoiceModel, POModel poModel) {
		
		try {
			
			if (invoiceModel != null) {
				
				String hql = "UPDATE stp_invoice SET invoice_number = '" + invoiceModel.getInvoiceNumber()
				+ "',invoice_date = '" + invoiceModel.getInvoiceDate() + "'," + "po_number = '" + invoiceModel.getPoNumber()
				+ "',po_date = '" + invoiceModel.getPoDate() + "',supplier_ref_number = '"+ invoiceModel.getSupplierRefNumber()
				+ "',ship_add_lane1 = '" + invoiceModel.getShipAddLane1()+ "',ship_add_lane2 = '" + invoiceModel.getShipAddLane2()
				+ "',ship_add_state = '" + invoiceModel.getShipAddState()+ "',ship_add_pincode = '" + invoiceModel.getBillingAddPincode()
				+ "',billing_add_lane1 = '" + invoiceModel.getBillingAddLane1()+ "',billing_add_lane2 = '" + invoiceModel.getBillingAddLane2()
				+ "',billing_add_state = '" + invoiceModel.getBillingAddState()+ "',billing_add_pincode = '" + invoiceModel.getBillingAddPincode()
				+ "',vendor_add_lane1 = '" + invoiceModel.getVendorAddLane1()+ "',vendor_add_lane2 = '" + invoiceModel.getVendorAddLane2()
				+ "',vendor_add_state = '" + invoiceModel.getVendorAddState()+ "',vendor_add_pincode = '" + invoiceModel.getVendorAddPincode()
				+ "',account_holder_name = '" + invoiceModel.getAccountHolderName()
				+ "',fk_requirement_id = " + invoiceModel.getFkRequirementId() + "," + "period = '"
				+ invoiceModel.getPeriod() + "',fk_employee_id = " + invoiceModel.getFkEmployeeId()
				+ ",hsn_sac ='" + invoiceModel.getHsnSac() + "' ," + "quantity = "
				+ invoiceModel.getQuantity() + ",billing_days =" + invoiceModel.getBillingDays()
				+ " ,total_work_days =" + invoiceModel.getTotalWorkDays()
				+ " ,rate = " + invoiceModel.getRate() + "," + "taxable_amount = "
				+ invoiceModel.getTaxableAmount() + ",cgst_rate = " + invoiceModel.getCgst_rate()
				+ ",cgst_amount =" + invoiceModel.getCgstAmount() + " ," + "sgst_rate = "
				+ invoiceModel.getSgstRate() + ",sgst_amount =" + invoiceModel.getSgstAmount()
				+ ",igst_rate = " + invoiceModel.getIgstRate() + ",igst_amount = "
				+ invoiceModel.getIgstAmount() + "," + "igst_applicable = " + invoiceModel.getIgstApplicable()
				+ ",total_amount = " + invoiceModel.getTotalAmount() + "," + "bank_name = '"
				+ invoiceModel.getBankName() + "',account_number = '" + invoiceModel.getAccountNumber()
				+ "',ifsc_code ='" + invoiceModel.getIfscCode() + "' ," + "fk_invoice_request_id = "
				+ invoiceModel.getFkInvoiceRequestId() + ",customer_pan ='" + invoiceModel.getCustomerPan()
				+ "' ,customer_gstin ='" + invoiceModel.getCustomerGstin()
				+ "' ,customer_state ='" + invoiceModel.getCustomerState() + "'," + "customer_state_code = '"
				+ invoiceModel.getCustomerStateCode() + "',our_pan = '" + invoiceModel.getOur_pan()
				+ "',our_gstin ='" + invoiceModel.getOurGstin() + "' ," + "our_state = '"
				+ invoiceModel.getOurState() + "',our_state_code ='" + invoiceModel.getOurStateCode() + "',invoice_pdf_date ='" + invoiceModel.getInvoicePdfDate()
				+ "',shipping_pan ='" + invoiceModel.getShippingPan() + "',shipping_gstin ='" + invoiceModel.getShippingGstin()
				+ "',shipping_state ='" + invoiceModel.getShippingState() + "',shipping_state_code ='" + invoiceModel.getShippingStateCode()
				+ "' WHERE invoice_id =" + invoiceModel.getInvoiceId();
				
				int update = entityManager.createNativeQuery(hql).executeUpdate();
				if(update!=0){
					hql = "UPDATE stp_po SET ship_add_lane1 ='" + invoiceModel.getShipAddLane1()
					+"',ship_add_lane2 ='" + invoiceModel.getShipAddLane2()
					+"',ship_add_state ='" + invoiceModel.getShipAddState()
					+"',ship_add_pincode ='" + invoiceModel.getShipAddPincode()
					+"',billing_add_lane1 ='" + invoiceModel.getBillingAddLane1()
					+"',billing_add_lane2 ='" + invoiceModel.getBillingAddLane2()
					+"',billing_add_state ='" + invoiceModel.getBillingAddState()
					+"',billing_add_pincode ='" + invoiceModel.getBillingAddPincode()
					+"',vendor_add_lane1 ='" + invoiceModel.getVendorAddLane1()
					+"',vendor_add_lane2 ='" + invoiceModel.getVendorAddLane2()
					+"',vendor_add_state ='" + invoiceModel.getVendorAddState()
					+"',vendor_add_pincode ='" + invoiceModel.getVendorAddPincode()
					+"',shipping_pan_number ='" + invoiceModel.getShippingPan()
					+"',shipping_gst_in ='" + invoiceModel.getShippingGstin()
					+"',shipping_state_name ='" + invoiceModel.getShippingState()
					+"',shipping_state_code ='" + invoiceModel.getShippingStateCode()
					+"',pan_number ='" + invoiceModel.getCustomerPan()
					+"',gst_in ='" + invoiceModel.getCustomerGstin()
					+"',state_name ='" + invoiceModel.getCustomerState()
					+"',state_code ='" + invoiceModel.getCustomerStateCode()
					+"',po_number ='" + invoiceModel.getPoNumber()
					+"',raised_on ='" + invoiceModel.getPoDate()
					+"' WHERE po_id =" + poModel.getPoId();
					
				    update = entityManager.createNativeQuery(hql).executeUpdate();
				    
				    hql = "UPDATE stp_invoice_request SET invoice_name ='" + invoiceModel.getInvoiceNumber()
					+"' WHERE invoice_request_id =" + invoiceModel.getFkInvoiceRequestId();
			
				    update = entityManager.createNativeQuery(hql).executeUpdate();
				}
				
				
			}
			return invoiceModel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invoiceModel=null;
	}

	@Override
	public int updateInvoiceRequest(InvoiceModel invoiceModel) {
		int update=0;
		try{
			
			if (invoiceModel != null) {
				String hql = "UPDATE stp_invoice_request SET fk_invoice_id =" + invoiceModel.getInvoiceId()
					+ " WHERE invoice_request_id =" + invoiceModel.getFkInvoiceRequestId();
			
				update = entityManager.createNativeQuery(hql).executeUpdate();
			}
			return update;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}

	/*@Override
	public AddressModel getCommAddress() {
		AddressModel addressModel = new AddressModel();
		String hql = "FROM AddressModel where deleteStatus=:deleteStatus";
		Query query = entityManager.createQuery(hql);
		query.setParameter("deleteStatus", 0);
		List<AddressModel> list = query.getResultList();
		if (!list.isEmpty()) {
			return addressModel = list.get(0);
		}

		return null;
	}*/

	@Override
	public List<POModel> getExipredPOList(String format) {
		List<POModel> list = new ArrayList<POModel>();
		try {
			String hql = "FROM POModel where endDate<=:date and expiryStatus=0 and deleteStatus=0";
			Query query = entityManager.createQuery(hql);
			query.setParameter("date", format);
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updatePomodelExpiryDate(POModel poModel) {
		int update=0;
		try{
			
			if (poModel != null) {
				String hql = "UPDATE stp_po SET expiry_status =1 WHERE po_id =" + poModel.getPoId();
			
				update = entityManager.createNativeQuery(hql).executeUpdate();
			}
			return update;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return update;
	}

	/*public EditInvoiceDetailsResponseModel getInvoiceeditList(InvoiceModel invoiceModel) {
		EditInvoiceDetailsResponseModel editInvoiceDetailsResponseModel=null;
		try{
				String hql = "SELECT si.invoice_id,si.invoice_number,si.invoice_date,si.po_number,si.po_date,si.supplier_ref_number,"+
						"	si.ship_add_lane1,si.ship_add_lane2,si.ship_add_state,si.ship_add_pincode,"+
						"	si.billing_add_lane1,si.billing_add_lane2,si.billing_add_state,si.billing_add_pincode,"+
						"	si.vendor_add_lane1,si.vendor_add_lane2,si.vendor_add_state,si.vendor_add_pincode,"+
						"	si.fk_requirement_id,si.period,si.fk_employee_id,si.hsn_sac,si.quantity,si.billing_days,si.total_work_days," + 
						"	si.rate,si.taxable_amount,si.cgst_rate,si.cgst_amount,si.sgst_rate,si.sgst_amount,si.igst_rate,si.igst_amount," + 
						"	si.igst_applicable,si.total_amount,si.account_holder_name,si.bank_name,si.account_number,si.ifsc_code,si.fk_invoice_request_id," + 
						"	si.customer_pan,si.customer_gstin,si.customer_state,si.customer_state_code,si.our_pan,si.our_gstin,si.our_state," + 
						"	si.our_state_code,si.invoice_sentstatus,se.emp_name,sr.project_name FROM stp_invoice si,stp_requirement sr,stp_employee se WHERE si.invoice_id="+invoiceModel.getInvoiceId()+" and " + 
						"	si.fk_invoice_request_id="+invoiceModel.getFkInvoiceRequestId()+" and si.invoice_date='"+invoiceModel.getInvoiceDate()+"' and si.invoice_number='"+invoiceModel.getInvoiceNumber()+"' " + 
						"	and si.po_number='"+invoiceModel.getPoNumber()+"' and sr.requirement_id=si.fk_requirement_id and se.employee_id=si.fk_employee_id ORDER BY si.invoice_id";
				List<Object> invoiceList = entityManager.createNativeQuery(hql).getResultList();
				if (invoiceList.size() > 0) {
					Iterator<Object> itr = invoiceList.iterator();
					while (itr.hasNext()) {
						Object[] obj = (Object[]) itr.next();
						editInvoiceDetailsResponseModel = new EditInvoiceDetailsResponseModel();
						editInvoiceDetailsResponseModel.setInvoiceId(Integer.parseInt(String.valueOf(obj[0])));
						editInvoiceDetailsResponseModel.setInvoiceNumber(String.valueOf(obj[1]));
						editInvoiceDetailsResponseModel.setInvoiceDate(String.valueOf(obj[2]));
						editInvoiceDetailsResponseModel.setpONumber(String.valueOf(obj[3]));
						editInvoiceDetailsResponseModel.setpODate(String.valueOf(obj[4]));
						editInvoiceDetailsResponseModel.setSupplierReferanceNumber(String.valueOf(obj[5]));
						editInvoiceDetailsResponseModel.setShipAddLane1(String.valueOf(obj[6]));
						editInvoiceDetailsResponseModel.setShipAddLane2(String.valueOf(obj[7]));
						editInvoiceDetailsResponseModel.setShipAddState(String.valueOf(obj[8]));
						editInvoiceDetailsResponseModel.setShipAddPincode(String.valueOf(obj[9]));
						editInvoiceDetailsResponseModel.setBillingAddLane1(String.valueOf(obj[10]));
						editInvoiceDetailsResponseModel.setBillingAddLane2(String.valueOf(obj[11]));
						editInvoiceDetailsResponseModel.setBillingAddState(String.valueOf(obj[12]));
						editInvoiceDetailsResponseModel.setBillingAddPincode(String.valueOf(obj[13]));
						editInvoiceDetailsResponseModel.setVendorAddLane1(String.valueOf(obj[14]));
						editInvoiceDetailsResponseModel.setVendorAddLane2(String.valueOf(obj[15]));
						editInvoiceDetailsResponseModel.setVendorAddState(String.valueOf(obj[16]));
						editInvoiceDetailsResponseModel.setVendorAddPincode(String.valueOf(obj[17]));
						editInvoiceDetailsResponseModel.setProject(Integer.parseInt(String.valueOf(obj[18])));
						editInvoiceDetailsResponseModel.setPeriod(String.valueOf(obj[19]));
						editInvoiceDetailsResponseModel.setEmployID(Integer.parseInt(String.valueOf(obj[20])));
						editInvoiceDetailsResponseModel.sethSNSAC(String.valueOf(obj[21]));
						editInvoiceDetailsResponseModel.setQuantity(Integer.parseInt(String.valueOf(obj[22])));
						editInvoiceDetailsResponseModel.setBillingDays(Integer.parseInt(String.valueOf(obj[23])));
						editInvoiceDetailsResponseModel.setTotalWorkingDays(Integer.parseInt(String.valueOf(obj[24])));
						editInvoiceDetailsResponseModel.setRate(Double.parseDouble(String.valueOf(obj[25])));
						editInvoiceDetailsResponseModel.setTaxableValue(Double.parseDouble(String.valueOf(obj[26])));
						editInvoiceDetailsResponseModel.setcGSTRate(Double.parseDouble(String.valueOf(obj[27])));
						editInvoiceDetailsResponseModel.setcGSTAmount(Double.parseDouble(String.valueOf(obj[28])));
						editInvoiceDetailsResponseModel.setsGSTRate(Double.parseDouble(String.valueOf(obj[29])));
						editInvoiceDetailsResponseModel.setsGSTAmount(Double.parseDouble(String.valueOf(obj[30])));
						editInvoiceDetailsResponseModel.setiGSTRate(Double.parseDouble(String.valueOf(obj[31])));;
						editInvoiceDetailsResponseModel.setiGSTAmount(Double.parseDouble(String.valueOf(obj[32])));
						editInvoiceDetailsResponseModel.setiGSTApplicable(Integer.parseInt(String.valueOf(obj[33])));
						editInvoiceDetailsResponseModel.setTotalAmount(Double.parseDouble(String.valueOf(obj[34])));
						editInvoiceDetailsResponseModel.setAccountHolderName(String.valueOf(obj[35]));
						editInvoiceDetailsResponseModel.setBankName(String.valueOf(obj[36]));
						editInvoiceDetailsResponseModel.setBankAcNo(String.valueOf(obj[37]));
						editInvoiceDetailsResponseModel.setiFSCCode(String.valueOf(obj[38]));
						editInvoiceDetailsResponseModel.setInvoiceRequestId(Integer.parseInt(String.valueOf(obj[39])));
						editInvoiceDetailsResponseModel.setCustomerPan(String.valueOf(obj[40]));
						editInvoiceDetailsResponseModel.setCustomerGstin(String.valueOf(obj[41]));
						editInvoiceDetailsResponseModel.setCustomerState(String.valueOf(obj[42]));
						editInvoiceDetailsResponseModel.setCustomerStateCode(String.valueOf(obj[43]));
						editInvoiceDetailsResponseModel.setOurPan(String.valueOf(obj[44]));
						editInvoiceDetailsResponseModel.setOurGstin(String.valueOf(obj[45]));
						editInvoiceDetailsResponseModel.setOurState(String.valueOf(obj[46]));
						editInvoiceDetailsResponseModel.setOurStateCode(String.valueOf(obj[47]));
						editInvoiceDetailsResponseModel.setInvoiceSentStatus(Integer.parseInt(String.valueOf(obj[48])));
						editInvoiceDetailsResponseModel.setEmployeeName(String.valueOf(obj[49]));
						editInvoiceDetailsResponseModel.setProject_Name(String.valueOf(obj[50]));
					}
				}
				//System.err.println(editInvoiceDetailsResponseModel.toString()+".in dao............editInvoiceDetailsResponseModel");
				return editInvoiceDetailsResponseModel;
		}catch(Exception e){
			
		}
		return null;
	}
	*/
	@Override
	public String generteInvoiceAndSend(InvoiceModel invoiceModel, InvoiceRequestModel invoiceRequestModel,RequirementsModel requirementsModel,int userId) {
		String status=null;
		try {
			if (invoiceModel != null) {
				invoiceRequestModel.setGeneratedBy(userId);
				String hql = "UPDATE stp_invoice_request SET invoice_file_name='"
						+ invoiceRequestModel.getInvoiceFileName() + "', invoice_file_path='"
						+ invoiceRequestModel.getInvoiceFilePath() + "',generated_on='"
						+ invoiceRequestModel.getGeneratedOn() + "',generated_by="+invoiceRequestModel.getGeneratedBy()+", invoice_status =" + invoiceRequestModel.getInvoiceStatus()
						+ " WHERE fk_invoice_id=" + invoiceModel.getInvoiceId() + " and invoice_request_id ="
						+ invoiceModel.getFkInvoiceRequestId();
				entityManager.createNativeQuery(hql).executeUpdate();
				System.out.println(invoiceRequestModel.toString());
				//String hmname = requirementsModel.getHmName();
				//String hmmail = requirementsModel.getHmEmail();
				//String pmmail = requirementsModel.getPmEmail();
				
				if (invoiceRequestModel.getInvoiceRequestId() != 0) {
						
						//System.out.println("hmname=...."+hmname+"........hmmail..........."+hmmail+"................pmmail......"+pmmail);
						invoiceRequestModel = entityManager.find(InvoiceRequestModel.class, invoiceRequestModel.getInvoiceRequestId());
						FlasMailConfig flasMailConfig=getflasMailConfig();
						
						boolean flag=InvoicePDFMail.sendInvoiceMail(flasMailConfig,requirementsModel, invoiceRequestModel);
						System.err.println(flag);
						if(flag){
							invoiceRequestModel.setInvoiceSentStatus(1);
							invoiceRequestModel.setSentBy(userId);
							invoiceRequestModel.setSentOn(invoiceRequestModel.getGeneratedOn());
							 hql = "UPDATE stp_invoice_request SET sent_on='"
									+ invoiceRequestModel.getSentOn() + "',sent_by="+invoiceRequestModel.getSentBy()+", invoice_sent_status =" + invoiceRequestModel.getInvoiceSentStatus()
									+ " WHERE fk_invoice_id=" + invoiceModel.getInvoiceId() + " and invoice_request_id ="
									+ invoiceModel.getFkInvoiceRequestId();
							int update=entityManager.createNativeQuery(hql).executeUpdate();
							System.out.println(invoiceRequestModel.toString());
							
							if(update!=0){
								invoiceModel.setInvoiceSentStatus(1);
								invoiceModel.setCreatedBy(userId);
								 hql = "UPDATE stp_invoice SET invoice_sentstatus="
										+ invoiceModel.getInvoiceSentStatus()+",created_by="+invoiceModel.getCreatedBy()+" WHERE invoice_id=" + invoiceModel.getInvoiceId() + " and fk_invoice_request_id ="
										+ invoiceModel.getFkInvoiceRequestId();
								update=entityManager.createNativeQuery(hql).executeUpdate();
								System.out.println(invoiceRequestModel.toString());
								status="Invoice generated successfully";
								return status;
							}else{
								status="Invoice request status update fails";
							}
							
						}else{
							invoiceRequestModel.setInvoiceStatus(0);
							status="Invoice sending fail due to wrong mail ids -"+requirementsModel.getHmEmail()+" and "+requirementsModel.getPmEmail();
						}
					
				}else{
					status="Invoice request id not present";
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public FlasMailConfig getflasMailConfig() {
		FlasMailConfig flasMailConfig =null;
		try{
			String hql="FROM FlasMailConfig";
			Query query = entityManager.createQuery(hql);
			List<FlasMailConfig> listflasMailConfig = query.getResultList();
			return flasMailConfig=listflasMailConfig.get(0);
		}catch(Exception e){
			return flasMailConfig;
		}
		
	}

	@Override
	public List<EditInvoiceDetailsResponseModel> getMyInvoiceList(int id) {
		EditInvoiceDetailsResponseModel editInvoiceDetailsResponseModel=null;
		List<EditInvoiceDetailsResponseModel> list=new ArrayList<EditInvoiceDetailsResponseModel>();
		try{
			
			String hql="SELECT si.invoice_id,si.invoice_number,si.invoice_date,si.po_number,si.po_date,si.supplier_ref_number," + 
					"	si.ship_add_lane1,si.ship_add_lane2,si.ship_add_state,si.ship_add_pincode,"+
					"	si.billing_add_lane1,si.billing_add_lane2,si.billing_add_state,si.billing_add_pincode,"+
					"	si.vendor_add_lane1,si.vendor_add_lane2,si.vendor_add_state,si.vendor_add_pincode,"+
					"   si.fk_requirement_id,si.period,si.fk_employee_id," + 
					"	si.hsn_sac,si.quantity,si.billing_days,si.total_work_days,si.rate,si.taxable_amount,si.cgst_rate,si.cgst_amount," + 
					"	si.sgst_rate,si.sgst_amount,si.igst_rate,si.igst_amount,si.igst_applicable,si.total_amount," + 
					"	si.account_holder_name,si.bank_name,si.account_number,si.ifsc_code,si.fk_invoice_request_id,si.customer_pan," + 
					"	si.customer_gstin,si.customer_state,si.customer_state_code,si.our_pan,si.our_gstin,si.our_state," + 
					"	si.our_state_code,si.invoice_sentstatus,se.emp_name,sr.project_name,po.currency,po.po_id,si.invoice_pdf_date,sc.customer_id,sc.customer_name,sc.customer_location,si.shipping_pan,si.shipping_gstin,si.shipping_state,si.shipping_state_code FROM stp_invoice si,stp_requirement sr,stp_customer sc," + 
					"	stp_employee se,stp_invoice_request sir,stp_po po WHERE po.po_number=si.po_number and si.freeze_status=1 and sir.invoice_request_id=si.invoice_id and sr.requirement_id=si.fk_requirement_id and " + 
					"	se.employee_id=si.fk_employee_id and po.fk_employee_id=si.fk_employee_id and sc.customer_id=sr.fk_customer_id and si.created_by in (SELECT user_id FROM stp_user " + 
					"   WHERE reporting_hierarchy like '%,"+id+",%' or reporting_hierarchy like '"+id+",%' or reporting_hierarchy like '%,"+id+"'" +
					"	or reporting_hierarchy like '"+id+"' and delete_status=0) order by si.invoice_date";
			
			/*String hql="SELECT si.invoice_id,si.invoice_number,si.invoice_date,si.po_number,si.po_date,si.supplier_ref_number," + 
					"	si.ship_add_lane1,si.ship_add_lane2,si.ship_add_state,si.ship_add_pincode,"+
					"	si.billing_add_lane1,si.billing_add_lane2,si.billing_add_state,si.billing_add_pincode,"+
					"	si.vendor_add_lane1,si.vendor_add_lane2,si.vendor_add_state,si.vendor_add_pincode,"+
					"   si.fk_requirement_id,si.period,si.fk_employee_id," + 
					"	si.hsn_sac,si.quantity,si.billing_days,si.total_work_days,si.rate,si.taxable_amount,si.cgst_rate,si.cgst_amount," + 
					"	si.sgst_rate,si.sgst_amount,si.igst_rate,si.igst_amount,si.igst_applicable,si.total_amount," + 
					"	si.account_holder_name,si.bank_name,si.account_number,si.ifsc_code,si.fk_invoice_request_id,si.customer_pan," + 
					"	si.customer_gstin,si.customer_state,si.customer_state_code,si.our_pan,si.our_gstin,si.our_state," + 
					"	si.our_state_code,si.invoice_sentstatus,se.emp_name,sr.project_name,po.currency,si.invoice_pdf_date FROM stp_invoice si,stp_requirement sr," + 
					"	stp_employee se,stp_po po WHERE po.po_number=si.po_number and po.fk_employee_id=si.fk_employee_id and si.freeze_status=1 and sr.requirement_id=si.fk_requirement_id and " + 
					"	se.employee_id=si.fk_employee_id and si.created_by in (SELECT user_id FROM stp_user " + 
					"	WHERE reporting_hierarchy like '%,"+id+",%' or reporting_hierarchy like '"+id+",%' or reporting_hierarchy like '%,"+id+"' " + 
					"	or reporting_hierarchy like '"+id+"' and delete_status=0)";*/
			List<Object> invoiceList = entityManager.createNativeQuery(hql).getResultList();
			if (invoiceList.size() > 0) {
				NumberToWord numberToWord=new NumberToWord();
				Iterator<Object> itr = invoiceList.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					editInvoiceDetailsResponseModel = new EditInvoiceDetailsResponseModel();
					editInvoiceDetailsResponseModel.setInvoiceId(Integer.parseInt(String.valueOf(obj[0])));
					editInvoiceDetailsResponseModel.setInvoiceNumber(String.valueOf(obj[1]));
					editInvoiceDetailsResponseModel.setInvoiceDate(String.valueOf(obj[2]));
					editInvoiceDetailsResponseModel.setpONumber(String.valueOf(obj[3]));
					editInvoiceDetailsResponseModel.setpODate(numberToWord.dateConverter(String.valueOf(obj[4])));
					editInvoiceDetailsResponseModel.setSupplierReferanceNumber(String.valueOf(obj[5]));
					editInvoiceDetailsResponseModel.setShipAddLane1(String.valueOf(obj[6]));
					editInvoiceDetailsResponseModel.setShipAddLane2(String.valueOf(obj[7]));
					editInvoiceDetailsResponseModel.setShipAddState(String.valueOf(obj[8]));
					editInvoiceDetailsResponseModel.setShipAddPincode(String.valueOf(obj[9]));
					editInvoiceDetailsResponseModel.setBillingAddLane1(String.valueOf(obj[10]));
					editInvoiceDetailsResponseModel.setBillingAddLane2(String.valueOf(obj[11]));
					editInvoiceDetailsResponseModel.setBillingAddState(String.valueOf(obj[12]));
					editInvoiceDetailsResponseModel.setBillingAddPincode(String.valueOf(obj[13]));
					editInvoiceDetailsResponseModel.setVendorAddLane1(String.valueOf(obj[14]));
					editInvoiceDetailsResponseModel.setVendorAddLane2(String.valueOf(obj[15]));
					editInvoiceDetailsResponseModel.setVendorAddState(String.valueOf(obj[16]));
					editInvoiceDetailsResponseModel.setVendorAddPincode(String.valueOf(obj[17]));
					editInvoiceDetailsResponseModel.setProject(Integer.parseInt(String.valueOf(obj[18])));
					editInvoiceDetailsResponseModel.setPeriod(String.valueOf(obj[19]));
					editInvoiceDetailsResponseModel.setEmployID(Integer.parseInt(String.valueOf(obj[20])));
					editInvoiceDetailsResponseModel.sethSNSAC(String.valueOf(obj[21]));
					editInvoiceDetailsResponseModel.setQuantity(Integer.parseInt(String.valueOf(obj[22])));
					editInvoiceDetailsResponseModel.setBillingDays(Integer.parseInt(String.valueOf(obj[23])));
					editInvoiceDetailsResponseModel.setTotalWorkingDays(Integer.parseInt(String.valueOf(obj[24])));
					editInvoiceDetailsResponseModel.setRate(Double.parseDouble(String.valueOf(obj[25])));
					editInvoiceDetailsResponseModel.setTaxableValue(Double.parseDouble(String.valueOf(obj[26])));
					editInvoiceDetailsResponseModel.setcGSTRate(Double.parseDouble(String.valueOf(obj[27])));
					editInvoiceDetailsResponseModel.setcGSTAmount(Double.parseDouble(String.valueOf(obj[28])));
					editInvoiceDetailsResponseModel.setsGSTRate(Double.parseDouble(String.valueOf(obj[29])));
					editInvoiceDetailsResponseModel.setsGSTAmount(Double.parseDouble(String.valueOf(obj[30])));
					editInvoiceDetailsResponseModel.setiGSTRate(Double.parseDouble(String.valueOf(obj[31])));;
					editInvoiceDetailsResponseModel.setiGSTAmount(Double.parseDouble(String.valueOf(obj[32])));
					editInvoiceDetailsResponseModel.setiGSTApplicable(Integer.parseInt(String.valueOf(obj[33])));
					editInvoiceDetailsResponseModel.setTotalAmount(Double.parseDouble(String.valueOf(obj[34])));
					editInvoiceDetailsResponseModel.setAccountHolderName(String.valueOf(obj[35]));
					editInvoiceDetailsResponseModel.setBankName(String.valueOf(obj[36]));
					editInvoiceDetailsResponseModel.setBankAcNo(String.valueOf(obj[37]));
					editInvoiceDetailsResponseModel.setiFSCCode(String.valueOf(obj[38]));
					editInvoiceDetailsResponseModel.setInvoiceRequestId(Integer.parseInt(String.valueOf(obj[39])));
					editInvoiceDetailsResponseModel.setCustomerPan(String.valueOf(obj[40]));
					editInvoiceDetailsResponseModel.setCustomerGstin(String.valueOf(obj[41]));
					editInvoiceDetailsResponseModel.setCustomerState(String.valueOf(obj[42]));
					editInvoiceDetailsResponseModel.setCustomerStateCode(String.valueOf(obj[43]));
					editInvoiceDetailsResponseModel.setOurPan(String.valueOf(obj[44]));
					editInvoiceDetailsResponseModel.setOurGstin(String.valueOf(obj[45]));
					editInvoiceDetailsResponseModel.setOurState(String.valueOf(obj[46]));
					editInvoiceDetailsResponseModel.setOurStateCode(String.valueOf(obj[47]));
					editInvoiceDetailsResponseModel.setInvoiceSentStatus(Integer.parseInt(String.valueOf(obj[48])));
					editInvoiceDetailsResponseModel.setEmployeeName(String.valueOf(obj[49]));
					editInvoiceDetailsResponseModel.setProject_Name(String.valueOf(obj[50]));
					editInvoiceDetailsResponseModel.setCurrencyType(String.valueOf(obj[51]));
					editInvoiceDetailsResponseModel.setFkPoId(Integer.parseInt(String.valueOf(obj[52])));
					editInvoiceDetailsResponseModel.setInvoicePdfDate(numberToWord.dateConverter(String.valueOf(obj[53])));
					editInvoiceDetailsResponseModel.setFkClientId(Integer.parseInt(String.valueOf(obj[54])));
					editInvoiceDetailsResponseModel.setClientName(String.valueOf(obj[55]));
					editInvoiceDetailsResponseModel.setClientLocation(String.valueOf(obj[56]));
					editInvoiceDetailsResponseModel.setShippinPanNumber(String.valueOf(obj[57]));
					editInvoiceDetailsResponseModel.setShippingGstin(String.valueOf(obj[58]));
					editInvoiceDetailsResponseModel.setShippingState(String.valueOf(obj[59]));
					editInvoiceDetailsResponseModel.setShippingStateCode(String.valueOf(obj[60]));
					list.add(editInvoiceDetailsResponseModel);
				}
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();;
		}
		return null;
	}
	

	@Override
	public RequirementsModel getProjectRequirementName(InvoiceModel invoiceModel) {
		RequirementsModel requirementsModel=new RequirementsModel();
		try{
			String hql = "FROM RequirementsModel where requirementId=:requirementId";
			Query query = entityManager.createQuery(hql);
			query.setParameter("requirementId", invoiceModel.getFkRequirementId());
			List<RequirementsModel> list = query.getResultList();
			if(!list.isEmpty()){
				requirementsModel=list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();;
		}
		return requirementsModel;
	}

	@Override
	public RequirementsModel getRequirementProjectId(int employeeId) {
		RequirementsModel requirementsModel=new RequirementsModel();
		try{
			String hql = "FROM RequirementsModel where fkEmployeeId=:fkEmployeeId";
			Query query = entityManager.createQuery(hql);
			query.setParameter("fkEmployeeId", employeeId);
			List<RequirementsModel> list = query.getResultList();
			if(!list.isEmpty()){
				requirementsModel=list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();;
		}
		return requirementsModel;
	}

	@Override
	public HashMap<String, BankAccountModel> getBankData(String term) {
		HashMap<String, BankAccountModel> map=new HashMap<String, BankAccountModel>();
		List<BankAccountModel> list = new ArrayList<BankAccountModel>();
		BankAccountModel bankAccountModel=null;
		String hql="FROM BankAccountModel b WHERE accountHolderName LIKE '%"+term+"%' AND deleteStatus=0";
		Query query = entityManager.createQuery(hql);
		list = query.getResultList();
		//System.out.println("list size"+list.size());
		for (int i=0;i<list.size();i++){
			bankAccountModel=new BankAccountModel();
			bankAccountModel=(BankAccountModel)list.get(i);
			
			//System.out.println(bankAccountModel.toString());
			map.put(bankAccountModel.getBankName(), bankAccountModel);
			
			
		}
		//System.out.println();
		return map;
	}

	@Override
	public TaxDetailsModel getNexiiTaxDetails() {
		TaxDetailsModel taxDetailsModel = new TaxDetailsModel();
		String hql = "FROM TaxDetailsModel WHERE deleteStatus=:deleteStatus and isActive=:isActive";
		Query query = entityManager.createQuery(hql);
		query.setParameter("deleteStatus", 0);
		query.setParameter("isActive", 1);
		List<TaxDetailsModel> list = query.getResultList();
		if (!list.isEmpty()) {
			return taxDetailsModel = list.get(0);
		}

		return null;
	}

	@Override
	public HashMap<String, TaxDetailsModel> searchTaxDEtails(String term) {
		HashMap<String, TaxDetailsModel> map=new HashMap<String, TaxDetailsModel>();
		List<TaxDetailsModel> list = new ArrayList<TaxDetailsModel>();
		TaxDetailsModel taxDetailsModel=null;
		String hql="FROM TaxDetailsModel WHERE panNumber LIKE '%"+term+"%' AND deleteStatus=0";
		Query query = entityManager.createQuery(hql);
		list = query.getResultList();
		for (int i=0;i<list.size();i++){
			taxDetailsModel=new TaxDetailsModel();
			taxDetailsModel=list.get(i);
			
			//System.out.println(bankAccountModel.toString());
			map.put(taxDetailsModel.getGstIn(), taxDetailsModel);
		}
		//System.out.println();
		return map;
	}
	
	@Override
	public int getInvoiceRequestCount() {
		String hql = "FROM InvoiceRequestModel WHERE invoiceStatus=0";
		int count=0;
		try {
			Query query = entityManager.createQuery(hql);
			List<Object> list = query.getResultList();
			count=list.size();
			System.err.println("invoice request list size.........." + list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<EditInvoiceDetailsResponseModel> getInvoiceRequestList(int userId) {
		EditInvoiceDetailsResponseModel editInvoiceDetailsResponseModel=null;
		List<EditInvoiceDetailsResponseModel> list=new ArrayList<EditInvoiceDetailsResponseModel>();
		try{
			String hql="SELECT si.invoice_id,si.invoice_number,si.invoice_date,si.po_number,si.po_date,si.supplier_ref_number," + 
					"	si.ship_add_lane1,si.ship_add_lane2,si.ship_add_state,si.ship_add_pincode,"+
					"	si.billing_add_lane1,si.billing_add_lane2,si.billing_add_state,si.billing_add_pincode,"+
					"	si.vendor_add_lane1,si.vendor_add_lane2,si.vendor_add_state,si.vendor_add_pincode,"+
					"   si.fk_requirement_id,si.period,si.fk_employee_id," + 
					"	si.hsn_sac,si.quantity,si.billing_days,si.total_work_days,si.rate,si.taxable_amount,si.cgst_rate,si.cgst_amount," + 
					"	si.sgst_rate,si.sgst_amount,si.igst_rate,si.igst_amount,si.igst_applicable,si.total_amount," + 
					"	si.account_holder_name,si.bank_name,si.account_number,si.ifsc_code,si.fk_invoice_request_id,si.customer_pan," + 
					"	si.customer_gstin,si.customer_state,si.customer_state_code,si.our_pan,si.our_gstin,si.our_state," + 
					"	si.our_state_code,si.invoice_sentstatus,se.emp_name,sr.project_name,po.currency,po.po_id,si.invoice_pdf_date,sc.customer_id,sc.customer_name,sc.customer_location,si.shipping_pan,si.shipping_gstin,si.shipping_state,si.shipping_state_code FROM stp_invoice si,stp_requirement sr,stp_customer sc," + 
					"	stp_employee se,stp_invoice_request sir,stp_po po WHERE po.po_number=si.po_number and si.freeze_status=0 and sir.invoice_request_id=si.invoice_id and sr.requirement_id=si.fk_requirement_id and " + 
					"	se.employee_id=si.fk_employee_id and po.fk_employee_id=si.fk_employee_id and sc.customer_id=sr.fk_customer_id order by si.invoice_date";
			List<Object> invoiceList = entityManager.createNativeQuery(hql).getResultList();
			if (invoiceList.size() > 0) {
				NumberToWord numberToWord=new NumberToWord();
				Iterator<Object> itr = invoiceList.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					editInvoiceDetailsResponseModel = new EditInvoiceDetailsResponseModel();
					editInvoiceDetailsResponseModel.setInvoiceId(Integer.parseInt(String.valueOf(obj[0])));
					editInvoiceDetailsResponseModel.setInvoiceNumber(String.valueOf(obj[1]));
					editInvoiceDetailsResponseModel.setInvoiceDate(String.valueOf(obj[2]));
					editInvoiceDetailsResponseModel.setpONumber(String.valueOf(obj[3]));
					editInvoiceDetailsResponseModel.setpODate(numberToWord.dateConverter(String.valueOf(obj[4])));
					editInvoiceDetailsResponseModel.setSupplierReferanceNumber(String.valueOf(obj[5]));
					editInvoiceDetailsResponseModel.setShipAddLane1(String.valueOf(obj[6]));
					editInvoiceDetailsResponseModel.setShipAddLane2(String.valueOf(obj[7]));
					editInvoiceDetailsResponseModel.setShipAddState(String.valueOf(obj[8]));
					editInvoiceDetailsResponseModel.setShipAddPincode(String.valueOf(obj[9]));
					editInvoiceDetailsResponseModel.setBillingAddLane1(String.valueOf(obj[10]));
					editInvoiceDetailsResponseModel.setBillingAddLane2(String.valueOf(obj[11]));
					editInvoiceDetailsResponseModel.setBillingAddState(String.valueOf(obj[12]));
					editInvoiceDetailsResponseModel.setBillingAddPincode(String.valueOf(obj[13]));
					editInvoiceDetailsResponseModel.setVendorAddLane1(String.valueOf(obj[14]));
					editInvoiceDetailsResponseModel.setVendorAddLane2(String.valueOf(obj[15]));
					editInvoiceDetailsResponseModel.setVendorAddState(String.valueOf(obj[16]));
					editInvoiceDetailsResponseModel.setVendorAddPincode(String.valueOf(obj[17]));
					editInvoiceDetailsResponseModel.setProject(Integer.parseInt(String.valueOf(obj[18])));
					editInvoiceDetailsResponseModel.setPeriod(String.valueOf(obj[19]));
					editInvoiceDetailsResponseModel.setEmployID(Integer.parseInt(String.valueOf(obj[20])));
					editInvoiceDetailsResponseModel.sethSNSAC(String.valueOf(obj[21]));
					editInvoiceDetailsResponseModel.setQuantity(Integer.parseInt(String.valueOf(obj[22])));
					editInvoiceDetailsResponseModel.setBillingDays(Integer.parseInt(String.valueOf(obj[23])));
					editInvoiceDetailsResponseModel.setTotalWorkingDays(Integer.parseInt(String.valueOf(obj[24])));
					editInvoiceDetailsResponseModel.setRate(Double.parseDouble(String.valueOf(obj[25])));
					editInvoiceDetailsResponseModel.setTaxableValue(Double.parseDouble(String.valueOf(obj[26])));
					editInvoiceDetailsResponseModel.setcGSTRate(Double.parseDouble(String.valueOf(obj[27])));
					editInvoiceDetailsResponseModel.setcGSTAmount(Double.parseDouble(String.valueOf(obj[28])));
					editInvoiceDetailsResponseModel.setsGSTRate(Double.parseDouble(String.valueOf(obj[29])));
					editInvoiceDetailsResponseModel.setsGSTAmount(Double.parseDouble(String.valueOf(obj[30])));
					editInvoiceDetailsResponseModel.setiGSTRate(Double.parseDouble(String.valueOf(obj[31])));;
					editInvoiceDetailsResponseModel.setiGSTAmount(Double.parseDouble(String.valueOf(obj[32])));
					editInvoiceDetailsResponseModel.setiGSTApplicable(Integer.parseInt(String.valueOf(obj[33])));
					editInvoiceDetailsResponseModel.setTotalAmount(Double.parseDouble(String.valueOf(obj[34])));
					editInvoiceDetailsResponseModel.setAccountHolderName(String.valueOf(obj[35]));
					editInvoiceDetailsResponseModel.setBankName(String.valueOf(obj[36]));
					editInvoiceDetailsResponseModel.setBankAcNo(String.valueOf(obj[37]));
					editInvoiceDetailsResponseModel.setiFSCCode(String.valueOf(obj[38]));
					editInvoiceDetailsResponseModel.setInvoiceRequestId(Integer.parseInt(String.valueOf(obj[39])));
					editInvoiceDetailsResponseModel.setCustomerPan(String.valueOf(obj[40]));
					editInvoiceDetailsResponseModel.setCustomerGstin(String.valueOf(obj[41]));
					editInvoiceDetailsResponseModel.setCustomerState(String.valueOf(obj[42]));
					editInvoiceDetailsResponseModel.setCustomerStateCode(String.valueOf(obj[43]));
					editInvoiceDetailsResponseModel.setOurPan(String.valueOf(obj[44]));
					editInvoiceDetailsResponseModel.setOurGstin(String.valueOf(obj[45]));
					editInvoiceDetailsResponseModel.setOurState(String.valueOf(obj[46]));
					editInvoiceDetailsResponseModel.setOurStateCode(String.valueOf(obj[47]));
					editInvoiceDetailsResponseModel.setInvoiceSentStatus(Integer.parseInt(String.valueOf(obj[48])));
					editInvoiceDetailsResponseModel.setEmployeeName(String.valueOf(obj[49]));
					editInvoiceDetailsResponseModel.setProject_Name(String.valueOf(obj[50]));
					editInvoiceDetailsResponseModel.setCurrencyType(String.valueOf(obj[51]));
					editInvoiceDetailsResponseModel.setFkPoId(Integer.parseInt(String.valueOf(obj[52])));
					editInvoiceDetailsResponseModel.setInvoicePdfDate(numberToWord.dateConverter(String.valueOf(obj[53])));
					editInvoiceDetailsResponseModel.setFkClientId(Integer.parseInt(String.valueOf(obj[54])));
					editInvoiceDetailsResponseModel.setClientName(String.valueOf(obj[55]));
					editInvoiceDetailsResponseModel.setClientLocation(String.valueOf(obj[56]));
					editInvoiceDetailsResponseModel.setShippinPanNumber(String.valueOf(obj[57]));
					editInvoiceDetailsResponseModel.setShippingGstin(String.valueOf(obj[58]));
					editInvoiceDetailsResponseModel.setShippingState(String.valueOf(obj[59]));
					editInvoiceDetailsResponseModel.setShippingStateCode(String.valueOf(obj[60]));
					list.add(editInvoiceDetailsResponseModel);
				}
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();;
		}
		return null;
	}

	@Override
	public InvoiceRequestModel downloadInvoice(InvoiceModel invoiceModel, InvoiceRequestModel invoiceRequestModel,
			RequirementsModel requirementsModel, int userId) {
		try {
			if (invoiceModel != null) {
				invoiceRequestModel.setGeneratedBy(userId);
				String hql = "UPDATE stp_invoice_request SET invoice_file_name='"
						+ invoiceRequestModel.getInvoiceFileName() + "', invoice_file_path='"
						+ invoiceRequestModel.getInvoiceFilePath() + "',generated_on='"
						+ invoiceRequestModel.getGeneratedOn() + "',generated_by="+invoiceRequestModel.getGeneratedBy()+", invoice_status =" + invoiceRequestModel.getInvoiceStatus()
						+ " WHERE fk_invoice_id=" + invoiceModel.getInvoiceId() + " and invoice_request_id ="
						+ invoiceModel.getFkInvoiceRequestId();
				entityManager.createNativeQuery(hql).executeUpdate();
				System.out.println(invoiceRequestModel.toString());
				if (invoiceRequestModel.getInvoiceRequestId() != 0) {
						invoiceRequestModel = entityManager.find(InvoiceRequestModel.class, invoiceRequestModel.getInvoiceRequestId());
						if(invoiceRequestModel!=null){
							invoiceRequestModel.setInvoiceSentStatus(1);
							invoiceRequestModel.setSentBy(userId);
							invoiceRequestModel.setSentOn(invoiceRequestModel.getGeneratedOn());
							 hql = "UPDATE stp_invoice_request SET sent_on='"
									+ invoiceRequestModel.getSentOn() + "',sent_by="+invoiceRequestModel.getSentBy()+", invoice_sent_status =" + invoiceRequestModel.getInvoiceSentStatus()
									+ " WHERE fk_invoice_id=" + invoiceModel.getInvoiceId() + " and invoice_request_id ="
									+ invoiceModel.getFkInvoiceRequestId();
							int update=entityManager.createNativeQuery(hql).executeUpdate();
							System.out.println(invoiceRequestModel.toString());
							
							if(update!=0){
								invoiceModel.setInvoiceSentStatus(1);
								invoiceModel.setCreatedBy(userId);
								 hql = "UPDATE stp_invoice SET invoice_sentstatus="
										+ invoiceModel.getInvoiceSentStatus()+",created_by="+invoiceModel.getCreatedBy()+" WHERE invoice_id=" + invoiceModel.getInvoiceId() + " and fk_invoice_request_id ="
										+ invoiceModel.getFkInvoiceRequestId();
								update=entityManager.createNativeQuery(hql).executeUpdate();
								System.out.println(invoiceRequestModel.toString());
								//status="Invoice generated successfully";
								System.out.println(invoiceRequestModel.getInvoiceFilePath());
								return invoiceRequestModel;
							}else{
								//status="Invoice request status update fails";
							}
							
						}else{
							//invoiceRequestModel.setInvoiceStatus(0);
							//status="Invoice sending fail";
						}
					
				}else{
					//status="Invoice request id not present";
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InvoiceRequestModel downloadMyInvoice(int requestId) {
		InvoiceRequestModel invoiceRequestModel=new InvoiceRequestModel();
		invoiceRequestModel.setInvoiceRequestId(requestId);
		invoiceRequestModel = entityManager.find(InvoiceRequestModel.class, invoiceRequestModel.getInvoiceRequestId());
		return invoiceRequestModel;
	}

	@Override
	public List<EditInvoiceDetailsResponseModel> getMyinvoceFilterList(int userId, String monthandyear) {
		EditInvoiceDetailsResponseModel editInvoiceDetailsResponseModel=null;
		List<EditInvoiceDetailsResponseModel> list=new ArrayList<EditInvoiceDetailsResponseModel>();
		try{

			String hql="";
			if(monthandyear.equals("all")){
				hql="SELECT si.invoice_id,si.invoice_number,si.invoice_date,si.po_number,si.po_date,si.supplier_ref_number," + 
					"	si.ship_add_lane1,si.ship_add_lane2,si.ship_add_state,si.ship_add_pincode,"+
					"	si.billing_add_lane1,si.billing_add_lane2,si.billing_add_state,si.billing_add_pincode,"+
					"	si.vendor_add_lane1,si.vendor_add_lane2,si.vendor_add_state,si.vendor_add_pincode,"+
					"   si.fk_requirement_id,si.period,si.fk_employee_id," + 
					"	si.hsn_sac,si.quantity,si.billing_days,si.total_work_days,si.rate,si.taxable_amount,si.cgst_rate,si.cgst_amount," + 
					"	si.sgst_rate,si.sgst_amount,si.igst_rate,si.igst_amount,si.igst_applicable,si.total_amount," + 
					"	si.account_holder_name,si.bank_name,si.account_number,si.ifsc_code,si.fk_invoice_request_id,si.customer_pan," + 
					"	si.customer_gstin,si.customer_state,si.customer_state_code,si.our_pan,si.our_gstin,si.our_state," + 
					"	si.our_state_code,si.invoice_sentstatus,se.emp_name,sr.project_name,si.invoice_pdf_date,si.shipping_pan,si.shipping_gstin,si.shipping_state,si.shipping_state_code FROM stp_invoice si,stp_requirement sr," + 
					"	stp_employee se WHERE  si.invoice_sentstatus=1 and po.fk_employee_id=si.fk_employee_id and sr.requirement_id=si.fk_requirement_id and " + 
					"	se.employee_id=si.fk_employee_id and si.created_by in (SELECT user_id FROM stp_user " + 
					"	WHERE reporting_hierarchy like '%,"+userId+",%' or reporting_hierarchy like '"+userId+",%' or reporting_hierarchy like '%,"+userId+"' " + 
					"	or reporting_hierarchy like '"+userId+"' and delete_status=0)";
			}else{
				hql="SELECT si.invoice_id,si.invoice_number,si.invoice_date,si.po_number,si.po_date,si.supplier_ref_number," + 
						"	si.ship_add_lane1,si.ship_add_lane2,si.ship_add_state,si.ship_add_pincode,"+
						"	si.billing_add_lane1,si.billing_add_lane2,si.billing_add_state,si.billing_add_pincode,"+
						"	si.vendor_add_lane1,si.vendor_add_lane2,si.vendor_add_state,si.vendor_add_pincode,"+
						"   si.fk_requirement_id,si.period,si.fk_employee_id," + 
						"	si.hsn_sac,si.quantity,si.billing_days,si.total_work_days,si.rate,si.taxable_amount,si.cgst_rate,si.cgst_amount," + 
						"	si.sgst_rate,si.sgst_amount,si.igst_rate,si.igst_amount,si.igst_applicable,si.total_amount," + 
						"	si.account_holder_name,si.bank_name,si.account_number,si.ifsc_code,si.fk_invoice_request_id,si.customer_pan," + 
						"	si.customer_gstin,si.customer_state,si.customer_state_code,si.our_pan,si.our_gstin,si.our_state," + 
						"	si.our_state_code,si.invoice_sentstatus,se.emp_name,sr.project_name,si.invoice_pdf_date,si.shipping_pan,si.shipping_gstin,si.shipping_state,si.shipping_state_code FROM stp_invoice si,stp_requirement sr," + 
						"	stp_employee se WHERE si.invoice_date like '%"+monthandyear+"%' and si.invoice_sentstatus=1 and sr.requirement_id=si.fk_requirement_id and " + 
						"	se.employee_id=si.fk_employee_id and si.created_by in (SELECT user_id FROM stp_user " + 
						"	WHERE reporting_hierarchy like '%,"+userId+",%' or reporting_hierarchy like '"+userId+",%' or reporting_hierarchy like '%,"+userId+"' " + 
						"	or reporting_hierarchy like '"+userId+"' and delete_status=0)";
			}
			List<Object> invoiceList = entityManager.createNativeQuery(hql).getResultList();
			if (invoiceList.size() > 0) {
				NumberToWord numberToWord=new NumberToWord();
				Iterator<Object> itr = invoiceList.iterator();
				while (itr.hasNext()) {
					Object[] obj = (Object[]) itr.next();
					editInvoiceDetailsResponseModel = new EditInvoiceDetailsResponseModel();
					editInvoiceDetailsResponseModel.setInvoiceId(Integer.parseInt(String.valueOf(obj[0])));
					editInvoiceDetailsResponseModel.setInvoiceNumber(String.valueOf(obj[1]));
					editInvoiceDetailsResponseModel.setInvoiceDate(String.valueOf(obj[2]));
					editInvoiceDetailsResponseModel.setpONumber(String.valueOf(obj[3]));
					editInvoiceDetailsResponseModel.setpODate(numberToWord.dateConverter(String.valueOf(obj[4])));
					editInvoiceDetailsResponseModel.setSupplierReferanceNumber(String.valueOf(obj[5]));
					editInvoiceDetailsResponseModel.setShipAddLane1(String.valueOf(obj[6]));
					editInvoiceDetailsResponseModel.setShipAddLane2(String.valueOf(obj[7]));
					editInvoiceDetailsResponseModel.setShipAddState(String.valueOf(obj[8]));
					editInvoiceDetailsResponseModel.setShipAddPincode(String.valueOf(obj[9]));
					editInvoiceDetailsResponseModel.setBillingAddLane1(String.valueOf(obj[10]));
					editInvoiceDetailsResponseModel.setBillingAddLane2(String.valueOf(obj[11]));
					editInvoiceDetailsResponseModel.setBillingAddState(String.valueOf(obj[12]));
					editInvoiceDetailsResponseModel.setBillingAddPincode(String.valueOf(obj[13]));
					editInvoiceDetailsResponseModel.setVendorAddLane1(String.valueOf(obj[14]));
					editInvoiceDetailsResponseModel.setVendorAddLane2(String.valueOf(obj[15]));
					editInvoiceDetailsResponseModel.setVendorAddState(String.valueOf(obj[16]));
					editInvoiceDetailsResponseModel.setVendorAddPincode(String.valueOf(obj[17]));
					editInvoiceDetailsResponseModel.setProject(Integer.parseInt(String.valueOf(obj[18])));
					editInvoiceDetailsResponseModel.setPeriod(String.valueOf(obj[19]));
					editInvoiceDetailsResponseModel.setEmployID(Integer.parseInt(String.valueOf(obj[20])));
					editInvoiceDetailsResponseModel.sethSNSAC(String.valueOf(obj[21]));
					editInvoiceDetailsResponseModel.setQuantity(Integer.parseInt(String.valueOf(obj[22])));
					editInvoiceDetailsResponseModel.setBillingDays(Integer.parseInt(String.valueOf(obj[23])));
					editInvoiceDetailsResponseModel.setTotalWorkingDays(Integer.parseInt(String.valueOf(obj[24])));
					editInvoiceDetailsResponseModel.setRate(Double.parseDouble(String.valueOf(obj[25])));
					editInvoiceDetailsResponseModel.setTaxableValue(Double.parseDouble(String.valueOf(obj[26])));
					editInvoiceDetailsResponseModel.setcGSTRate(Double.parseDouble(String.valueOf(obj[27])));
					editInvoiceDetailsResponseModel.setcGSTAmount(Double.parseDouble(String.valueOf(obj[28])));
					editInvoiceDetailsResponseModel.setsGSTRate(Double.parseDouble(String.valueOf(obj[29])));
					editInvoiceDetailsResponseModel.setsGSTAmount(Double.parseDouble(String.valueOf(obj[30])));
					editInvoiceDetailsResponseModel.setiGSTRate(Double.parseDouble(String.valueOf(obj[31])));;
					editInvoiceDetailsResponseModel.setiGSTAmount(Double.parseDouble(String.valueOf(obj[32])));
					editInvoiceDetailsResponseModel.setiGSTApplicable(Integer.parseInt(String.valueOf(obj[33])));
					editInvoiceDetailsResponseModel.setTotalAmount(Double.parseDouble(String.valueOf(obj[34])));
					editInvoiceDetailsResponseModel.setAccountHolderName(String.valueOf(obj[35]));
					editInvoiceDetailsResponseModel.setBankName(String.valueOf(obj[36]));
					editInvoiceDetailsResponseModel.setBankAcNo(String.valueOf(obj[37]));
					editInvoiceDetailsResponseModel.setiFSCCode(String.valueOf(obj[38]));
					editInvoiceDetailsResponseModel.setInvoiceRequestId(Integer.parseInt(String.valueOf(obj[39])));
					editInvoiceDetailsResponseModel.setCustomerPan(String.valueOf(obj[40]));
					editInvoiceDetailsResponseModel.setCustomerGstin(String.valueOf(obj[41]));
					editInvoiceDetailsResponseModel.setCustomerState(String.valueOf(obj[42]));
					editInvoiceDetailsResponseModel.setCustomerStateCode(String.valueOf(obj[43]));
					editInvoiceDetailsResponseModel.setOurPan(String.valueOf(obj[44]));
					editInvoiceDetailsResponseModel.setOurGstin(String.valueOf(obj[45]));
					editInvoiceDetailsResponseModel.setOurState(String.valueOf(obj[46]));
					editInvoiceDetailsResponseModel.setOurStateCode(String.valueOf(obj[47]));
					editInvoiceDetailsResponseModel.setInvoiceSentStatus(Integer.parseInt(String.valueOf(obj[48])));
					editInvoiceDetailsResponseModel.setEmployeeName(String.valueOf(obj[49]));
					editInvoiceDetailsResponseModel.setProject_Name(String.valueOf(obj[50]));
					editInvoiceDetailsResponseModel.setInvoicePdfDate(numberToWord.dateConverter(String.valueOf(obj[51])));
					list.add(editInvoiceDetailsResponseModel);
				}
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();;
		}
		return null;
	}

	@Override
	public UserResponseDTO freezeInvoice(String fkRequestId, String invoiceId, int userId) {
		UserResponseDTO userResponseDTO=new UserResponseDTO();
		String hql = "UPDATE stp_invoice SET freeze_status=1,created_by="+userId+" WHERE invoice_id=" + invoiceId + " and fk_invoice_request_id ="
				+ fkRequestId;
		int update=entityManager.createNativeQuery(hql).executeUpdate();
		if(update!=0){
			userResponseDTO.setStatusCode(1);
			userResponseDTO.setMessage("Invoice freezed and moved to myinvoice");
		}else{
			userResponseDTO.setStatusCode(1);
			userResponseDTO.setMessage("Invoice unable to freeze");
		}
		return userResponseDTO;
	}
}