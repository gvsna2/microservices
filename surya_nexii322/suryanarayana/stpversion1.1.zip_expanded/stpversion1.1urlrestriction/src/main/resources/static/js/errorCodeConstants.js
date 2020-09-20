
/**
 * @Author Trineesha.Mandapati
 * @Version 1.0
 * 
 * 
/************* CHANGE PASSWORD JS VALIDATION MESSAGES***************/

var VALIDATION_CURRENT_PASSWORD_EMPTY_ERROR="Current password field cannot be empty.";
var VALIDATION_NEW_PASSWORD_EMPTY_ERROR="New password field cannot be empty.";
var VALIDATION_NEW_PASSWORD_MINLENGTH_ERROR="Password should have at least 5 characters.";
var VALIDATION_NEW_PASSWORD_MAXLENGTH_ERROR="Password cannot exceed 25 characters.";
var VALIDATION_CONFIRM_PASSWORD_EMPTY_ERROR="Confirm password field cannot be empty.";
var VALIDATION_NEW_PASSWORD_SAME_ERROR="Current and new passwords cannot be same.";
var VALIDATION_CONFIRM_PASSWORD_MATCH_ERROR="New password and confirm password not matching.";
var VALIDATION_PASSWORDS_EMPTY="Please fill all the details to change password";

/************* FORGOT PASSWORD JS VALIDATION MESSAGES***************/

var VALIDATION_FORGOT_PASSWORD_EMAIL_EMPTY_ERROR="Email field cannot be empty.";
var VALIDATION_FORGOT_PASSWORD_EMAIL_VALIDTE_ERROR="Invalid email entered.";
var VALIDATION_FORGOT_PASSWORD_USER_EXIST_ERROR="Enter valid userid.";
var RUNTIME_ERROR="Error while processing the request:";

/************ ROLE JS VALIDATION MESSAGES**********************************/

var VALIDATION_ROLE_NAME_EMPTY_ERROR="Role cannot be empty.";
var VALIDATION_ROLE_NAME_MIN_LENGTH_ERROR="Enter valid role.Minimum length is 3 characters.";
var VALIDATION_ROLE_NAME_MAX_LENGTH_ERROR="Enter valid role.Maximum length is 30 characters.";
var VALIDATION_ROLE_NAME_NUMBER_ERROR="Enter valid role.";
var VALIDATION_PERMISSION_EMPTY_ERROR="Select permissions.";
var RUNTIME_ERROR="Error while processing the request:";

/****************USER JS VALIDATION MESSAGES****************************/
var VALIDATION_CREATEUSER_FIRST_NAME_EMPTY_ERROR="Firstname should not be empty.";
var VALIDATION_CREATEUSER_FIRST_NAME_LENGTH_ERROR="Firstname has to be more than 3 characters.";
var VALIDATION_CREATEUSER_FIRST_NAME_MAX_LENGTH_ERROR="Firstname is too long.Maximum length is 25 characters";
var VALIDATION_CREATEUSER_FIRST_NAME_SPECIALS_ERROR="Enter valid firstname.";
var VALIDATION_CREATEUSER_LAST_NAME_EMPTY_ERROR="Lastname should not be empty.";
var VALIDATION_CREATEUSER_LAST_NAME_SPECIALS_ERROR="Enter valid lastname.";
var VALIDATION_CREATEUSER_LAST_NAME_MAX_LENGTH_ERROR="Lastname is too long.Maximum length is 25 characters";
var VALIDATION_CREATEUSER_LAST_NAME_LENGTH_ERROR="Lastname has to be more than 3 characters.";
var VALIDATION_CREATEUSER_EMAIL_EMPTY_ERROR="Enter email.";
var VALIDATION_CREATEUSER_EMAIL_VALID_ERROR="Enter valid email.";
var VALIDATION_CREATEUSER_DESIGNATION_EMPTY_ERROR="Select role.";
var VALIDATION_CREATEUSER_REPORTING_EMPTY_ERROR="Select manager's name.";
var VALIDATION_CREATEUSER_LOCATION_EMPTY_ERROR="Select user's location.";
var VALIDATION_CREATEUSER_EMPLOYEENUM_EMPTY_ERROR="Enter employee number.";
var VALIDATION_EDITUSER_REPORTING_DELETED_ERROR="Your reporting manager has changed.Update reporting details.";
var VALIDATION_EDITUSER_DESIGNATION_DELETED_ERROR="Your role has changed.Update role details.";
var VALIDATION_EDITUSER_FIRST_NAME_EMPTY_ERROR="Enter Firstname.";
var VALIDATION_EDITUSER_FIRST_NAME_LENGTH_ERROR="Firstname has to be more than 5 characters.";
var VALIDATION_EDITUSER_FIRST_NAME_SPECIALS_ERROR="Enter valid firstname.";
var VALIDATION_EDITUSER_LAST_NAME_EMPTY_ERROR="Lastname should not be empty.";
var VALIDATION_EDITUSER_LAST_NAME_SPECIALS_ERROR="Enter valid lastname.";
var VALIDATION_EDITUSER_EMAIL_EMPTY_ERROR="Email cannot be empty.";
var VALIDATION_EDITUSER_EMAIL_VALID_ERROR="Enter valid email.";
var VALIDATION_EDITUSER_DESIGNATION_EMPTY_ERROR="Select role.";
var VALIDATION_EDITUSER_REPORTING_EMPTY_ERROR="Select manager name.";
var VALIDATION_EDITUSER_LOCATION_EMPTY_ERROR="Select user's location.";
var VALIDATION_EDITUSER_EMPLOYEENUM_EMPTY_ERROR="Enter employee number.";


/**************** ACCOUNT JS VALIDATION MESSAGES****************************/
var VALIDATION_CREATE_ACCOUNT_NAME_EMPTY_ERROR="Account Name should not be empty.";
var VALIDATION_CREATE_ACCOUNT_NAME_LENGTH_ERROR="Account Name has to be more than 3 characters.";
var VALIDATION_CREATE_ACCOUNT_NAME_MAX_LENGTH_ERROR="Account Name is too long.Maximum length is 25 characters";
var VALIDATION_CREATE_ACCOUNT_NAME_SPECIALS_ERROR="Enter valid Account Name.";

var VALIDATION_CREATE_ACCOUNT_LOCATION_EMPTY_ERROR="Location should not be empty.";
var VALIDATION_CREATE_ACCOUNT_LOCATION_SPECIALS_ERROR="Enter valid Location.";
var VALIDATION_CREATE_ACCOUNT_LOCATION_MAX_LENGTH_ERROR="Location is too long.Maximum length is 25 characters";
var VALIDATION_CREATE_ACCOUNT_LOCATION_MIN_LENGTH_ERROR="Location has to be more than 3 characters.";

var VALIDATION_CREATE_CUSTOMER_CONTACT_NUMBER_EMPTY_ERROR="Enter Contact Number.";
var VALIDATION_CREATE_CUSTOMER_CONTACT_NUMBER_VALID_ERROR="Enter valid Contact Number.";
var VALIDATION_CONTACT_LENGTH_ERROR="Contact number should contain minimum 8 digits"

var VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_EMPTY_ERROR="Contact Person name should not be empty.";
var VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_LENGTH_ERROR="Contact Person  name has to be more than 3 characters.";
var VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_MAX_LENGTH_ERROR="Contact Person name is too long.Maximum length is 25 characters";
var VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_SPECIALS_ERROR="Enter valid Contact Person name.";

var VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_EMPTY_ERROR="Billing Address should not be empty.";
var VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_SPECIALS_ERROR="Enter valid Billing Address.";
var VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_MAX_LENGTH_ERROR="Billing Address is too long.Maximum length is 100 characters";
var VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_MIN_LENGTH_ERROR="Billing Address has to be more than 3 characters.";
var VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_EMPTY_ERROR="Vendor Address should not be empty.";
var VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_SPECIALS_ERROR="Enter valid Vendor Address.";
var VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_MAX_LENGTH_ERROR="Vendor Address is too long.Maximum length is 100 characters";
var VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_MIN_LENGTH_ERROR="Vendor Address has to be more than 3 characters.";

var VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_LANE1_EMPTY_ERROR="Shipping Address Lane1 should not empty.";
var VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_LANE2_EMPTY_ERROR="Shipping Address Lane2 should not be empty.";
var VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_LANE1_EMPTY_ERROR="Billing Address Lane1 should not be empty.";
var VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_LANE2_EMPTY_ERROR="Billing Address Lane2 should not be empty.";
var VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_LANE1_EMPTY_ERROR="Vendor Address Lane1 should not be empty.";
var VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_LANE2_EMPTY_ERROR="Vendor Address Lane2 should not be empty.";
var VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_STATE_EMPTY_ERROR="Shipping Address State should not be empty.";
var VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_PIN_CODE_ERROR="Shipping Address Pin code should not be empty.";
var VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_STATE_EMPTY_ERROR="Billing Address State should not be empty.";
var VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_PIN_CODE_ERROR="Billing Address Pin code should not be empty.";
var VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_STATE_EMPTY_ERROR="Vendor Address State should not be empty.";
var VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_PIN_CODE_ERROR="Vendor Address Pin code should not be empty.";
/****************UPDATE INVOICE JS VALIDATION MESSAGES****************************/
var VALIDATION_VENDOR_ADDRESS_EMPTY_ERROR="Vendor address should not be empty.";
var VALIDATION_BILLING_ADDRESS_EMPTY_ERROR="Billing address should not be empty.";
var VALIDATION_SHIPPING_ADDRESS_EMPTY_ERROR="Shipping address should not be empty.";
var VALIDATION_OUR_PAN_EMPTY_ERROR="PAN Number should not be empty.";
var VALIDATION_OUR_GSTIN_EMPTY_ERROR="GSTIN should not be empty."
var VALIDATION_OUR_STATE_EMPTY_ERROR="State name should not be empty.";
var VALIDATION_OUR_STATE_CODE_EMPTY_ERROR="State code should not be empty."
var VALIDATION_CUSTOMER_PAN_EMPTY_ERROR="PAN Number should not be empty.";
var VALIDATION_CUSTOMER_GSTIN_EMPTY_ERROR="GSTIN should not be empty."
var VALIDATION_CUSTOMER_STATE_EMPTY_ERROR="State name should not be empty.";
var VALIDATION_CUSTOMER_STATE_CODE_EMPTY_ERROR="State code should not be empty."
var VALIDATION_PERIOD_EMPTY_ERROR="Billing peroid should not be empty."
var VALIDATION_HSNSAC_EMPTY_ERROR="HSNSAC should not be empty."
var VALIDATION_WORKING_DAYS_EMPTY_ERROR="Working days should not be empty."
var VALIDATION_BILLING_DAYS_EMPTY_ERROR="Billing days should not be empty."
var VALIDATION_QUANTITY_EMPTY_ERROR="Quantity should not be empty."
var VALIDATION_TAXABLE_VALUE_EMPTY_ERROR="Taxable value should not be empty."
var VALIDATION_CGST_RATE_EMPTY_ERROR="CGST rate should not be empty."
var VALIDATION_SGST_RATE_EMPTY_ERROR="SGST rate should not be empty."
var VALIDATION_CGST_AMOUNT_EMPTY_ERROR="CGST amount should not be empty."
var VALIDATION_SGST_AMOUNT_EMPTY_ERROR="SGST amount should not be empty."
var VALIDATION_IGST_RATE_EMPTY_ERROR="IGST rate should not be empty."
var VALIDATION_IGST_AMOUNT_EMPTY_ERROR="IGST amount should not be empty."
var VALIDATION_TOTAL_AMOUNT_BEFORE_TAX_EMPTY_ERROR="Tota amount before tax should not be empty."
var VALIDATION_TOTAL_AMOUNT_AFTER_TAX_EMPTY_ERROR="Tota amount after tax should not be empty."
var VALIDATION_ACCOUNT_HOLDER_NAME_EMPTY_ERROR="Account holder name should not be empty."
var VALIDATION_BANK_NAME_EMPTY_ERROR="Bank name should not be empty."
var VALIDATION_ACCOUNT_NUMBER_EMPTY_ERROR="Account number should not be empty."
var VALIDATION_IFSC_CODE_EMPTY_ERROR="IFSC code should not be empty."
	
var VALIDATION_INVOICE_NUMBER_EMPTY_ERROR="Invoice number should not be empty";
var VALIDATION_INVOICE_DATE_EMPTY_ERROR="Invoice date should not be empty";
var VALIDATION_SUPPLIER_REF_NUMBER_EMPTY_ERROR="Supplier reference number should not be empty";
var VALIDATION_PO_NUMBER_EMPTY_ERROR="Order number should not be empty";
var VALIDATION_ORDER_DATE_EMPTY_ERROR="Order date should not be empty";
var VALIDATION_SHIPPING_PAN_EMPTY_ERROR="Shipping address pan number should not be empty";
var VALIDATION_SHIPPING_GSTIN_EMPTY_ERROR="Shipping address GSTIN should not be empty";
var VALIDATION_SHIPPING_STATE_EMPTY_ERROR="Shipping address state should not be empty";
var VALIDATION_SHIPPING_STATECODE_EMPTY_ERROR="Shipping address state code should not be empty";
var VALIDATION_PROJECT_NAME_EMPTY_ERROR="Project name should not be empty";
var VALIDATION_EMPLOYEE_NAME_EMPTY_ERROR="Employee name should not be empty";
var VALIDATION_INVOICE_DATE_FORMAT_EMPTY_ERROR="Invoice date should be valid format";
var VALIDATION_ORDER_DATE_FORMAT_EMPTY_ERROR="Order date should be valid format";
var VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_EMPTY_ERROR="Shipping Address should not be empty.";
var VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_SPECIALS_ERROR="Enter valid Shipping Address.";
var VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_MAX_LENGTH_ERROR="Shipping Address is too long.Maximum length is 100 characters";
var VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_MIN_LENGTH_ERROR="Shipping Address has to be more than 3 characters.";

/*************************************RESOURCE JS VALIDATION MESSAGES*************************************/
var VALIDATION_CONTACT_EMPTY_ERROR="Contact Number should not be empty.";
var VALIDATION_CONTACT_LENGTH_ERROR="Contact Number should contain minimum 8 digits.";
var VALIDATION_CONTACT_SPECIALS_ERROR="Enter valid contact number.";
var VALIDATION_PROJECT_NAME_EMPTY_ERROR="Project Name should not be empty."
var VALIDATION_PROJECT_NAME_LENGTH_ERROR="Project name has to be more than 3 characters";
var VALIDATIONPROJECT_NAME_SPECIALS_ERROR="Enter valid Project Name";
var VALIDATION_PROJECT_NAME_MAX_LENGTH_ERROR="Project name is too Long.Maximum length is 25 characters";
var VALIDATION_PROJECT_DESC_EMPTY_ERROR="Project Description should not be empty";
var VALIDATION_PROJECT_DESC_LENGTH_ERROR="Project Description is too short";
var VALIDATION_hmName_EMPTY_ERROR="Hiring Manager Name should not be empty";
var VALIDATION_hmName_LENGTH_ERROR="Hiring Manager Name has to be more than 3 characters";
var VALIDATION_HM_EMAIL_EMPTY_ERROR="Hiring Manager Email should not be Empty";
var VALIDATION_HM_EMAIL_VALID_ERROR="Enter valid  Hiring Manager Email.";
var VALIDATION_HM_CONTACT_EMPTY_ERROR="Hiring Manager Contact Number should not be empty.";
var VALIDATION_HM_CONTACT_LENGTH_ERROR=" Hiring Manager Contact Number should contain minimum 8 digits.";
var VALIDATION_HM_CONTACT_SPECIALS_ERROR="Enter valid  Hiring Manager contact Number";
var VALIDATION_pmName_EMPTY_ERROR="Accounts Manager Name should not be empty";
var VALIDATION_pmName_LENGTH_ERROR="Accounts Manager Name has to be more than 3 characters";
var VALIDATION_PM_EMAIL_EMPTY_ERROR="Accounts Manager Email should not be Empty";
var VALIDATION_PM_EMAIL_VALID_ERROR="Enter valid  Accounts Manager Email.";
var VALIDATION_PM_CONTACT_EMPTY_ERROR="Accounts Manager Contact Number should not be empty.";
var VALIDATION_PM_CONTACT_LENGTH_ERROR=" Accounts Manager Contact Number should contain minimum 8 digits.";
var VALIDATION_PM_CONTACT_SPECIALS_ERROR="Enter valid  Accounts Manager contact Number";
var VALIDATION_LOCATION_EMPTY_ERROR="Enter Location.";
var VALIDATION_LOCATION_LENGTH_ERROR="Location should contain minimum 3 characters.";
var VALIDATION_LOCATION_SPECIALS_ERROR="Enter valid Location.";
var VALIDATION_SKILLSET_EMPTY_ERROR="Enter skillset."
var VALIDATION_RATE_CARD_EMPTY_ERROR="Rate card should not be empty.";
var VALIDATION_RATE_CARD_SPECIALS_ERROR="Enter valid rate";
var VALIDATION_DATE_EMPTY_ERROR="Select joining date.";
var VALIDATION_EXPERIENCE_EMPTY_ERROR="Enter Experience level.";
var VALIDATION_CUSTOMER_EMPTY_ERROR="Select customer";
var VALIDATION_PO_EMPTY_ERROR="Enter Po Number.";
var VALIDATION_PO_LENGTH_ERROR="Po Number should contain atleast 3 characters.";
var VALIDATION_PO_MAX_LENGTH_ERROR="Po Number is too long.Maximum length is 25 characters.";
var VALIDATION_SUPREF_EMPTY_ERROR="Enter Supplier Reference Number.";
var VALIDATION_SUPREF_LENGTH_ERROR="Supplier Reference  Number should contain atleast 3 characters.";
var VALIDATION_SUPREF_MAX_LENGTH_ERROR="Supplier Reference  Number is too long.Maximum length is 25 characters.";
var VALIDATION_CURRENCY_EMPTY_ERROR="Select Currency.";
var VALIDATION_PO_RAISED_ON_EMPTY_ERROR="Select PO Raised On Date.";
var VALIDATION_PO_RAISED_BY_EMPTY_ERROR="PO Raised by should not be empty.";
var VALIDATION_PO_RAISED_BY_LENGTH_ERROR="Raised By should contain atleast 3 characters";
var VALIDATION_PO_RAISED_BY_MAX_LENGTH_ERROR="Raised By is too long.Maximum length is 25 characters.";
var VALIDATION_DURATION_EMPTY_ERROR="Enter Duration.";
var  VALIDATION_DURATION_SPECIALS_ERROR="Enter valid Duration.";
var VALIDATION_DURATION_MAX_LIMIT="Enter Duration less than 24 months.";
var VALIDATION_START_DATE_EMPTY_ERROR="Select Start Date";
var VALIDATION_END_DATE_EMPTY_ERROR="Select End Date";
var VALIDATION_UNIT_PRICE_EMPTY_ERROR="Enter Unit Price.";
var VALIDATION_UNIT_PRICE_SPECIALS_ERROR="Enter valid Unit Price.";
var VALIDATION_REASSIGN_EMPTY_ERROR="Select the user"

/****************UPDATE INVOICE JS VALIDATION MESSAGES****************************/
var VALIDATION_VENDOR_ADDRESS_EMPTY_ERROR="Vendor address should not be empty.";
var VALIDATION_BILLING_ADDRESS_EMPTY_ERROR="Billing address should not be empty.";
var VALIDATION_SHIPPING_ADDRESS_EMPTY_ERROR="Shipping address should not be empty.";
var VALIDATION_OUR_PAN_EMPTY_ERROR="PAN Number should not be empty.";
var VALIDATION_OUR_GSTIN_EMPTY_ERROR="GSTIN should not be empty."
var VALIDATION_OUR_STATE_EMPTY_ERROR="State name should not be empty.";
var VALIDATION_OUR_STATE_CODE_EMPTY_ERROR="State code should not be empty."
var VALIDATION_CUSTOMER_PAN_EMPTY_ERROR="PAN Number should not be empty.";
var VALIDATION_CUSTOMER_GSTIN_EMPTY_ERROR="GSTIN should not be empty."
var VALIDATION_CUSTOMER_STATE_EMPTY_ERROR="State name should not be empty.";
var VALIDATION_CUSTOMER_STATE_CODE_EMPTY_ERROR="State code should not be empty."
var VALIDATION_PERIOD_EMPTY_ERROR="Billing peroid should not be empty."
var VALIDATION_HSNSAC_EMPTY_ERROR="HSNSAC should not be empty."
var VALIDATION_WORKING_DAYS_EMPTY_ERROR="Working days should not be empty."
var VALIDATION_BILLING_DAYS_EMPTY_ERROR="Billing days should not be empty."
var VALIDATION_QUANTITY_EMPTY_ERROR="Quantity should not be empty."
var VALIDATION_TAXABLE_VALUE_EMPTY_ERROR="Taxable value should not be empty."
var VALIDATION_CGST_RATE_EMPTY_ERROR="CGST rate should not be empty."
var VALIDATION_SGST_RATE_EMPTY_ERROR="SGST rate should not be empty."
var VALIDATION_CGST_AMOUNT_EMPTY_ERROR="CGST amount should not be empty."
var VALIDATION_SGST_AMOUNT_EMPTY_ERROR="SGST amount should not be empty."
var VALIDATION_IGST_RATE_EMPTY_ERROR="IGST rate should not be empty."
var VALIDATION_IGST_AMOUNT_EMPTY_ERROR="IGST amount should not be empty."
var VALIDATION_TOTAL_AMOUNT_BEFORE_TAX_EMPTY_ERROR="Tota amount before tax should not be empty."
var VALIDATION_TOTAL_AMOUNT_AFTER_TAX_EMPTY_ERROR="Tota amount after tax should not be empty."
var VALIDATION_ACCOUNT_HOLDER_NAME_EMPTY_ERROR="Account holder name should not be empty."
var VALIDATION_BANK_NAME_EMPTY_ERROR="Bank name should not be empty."
var VALIDATION_ACCOUNT_NUMBER_EMPTY_ERROR="Account number should not be empty."
var VALIDATION_IFSC_CODE_EMPTY_ERROR="IFSC code should not be empty."

/****************UPDATE MY PROFILE JS VALIDATION MESSAGES****************************/
var VALIDATION_NAME_EMPTY_ERROR="Name should not be empty.";
var VALIDATION_EMAIL_EMPTY_ERROR="Email should not be empty.";
var VALIDATION_CONTACT_EMPTY_ERROR="Contact number should not be empty.";
var VALIDATION_SKILL_EMPTY_ERROR="Skill set should not be empty.";
var VALIDATION_EXP_EMPTY_ERROR="Experience should not be empty.";

/************BANK ACCOUNT JS VALIDATION MESSAGES*****************************************/
var VALIDATION_BANKDETAILS_BANK_NAME_EMPTY_ERROR="Bank Name should not be empty.";
var VALIDATION_BANKDETAILS_ACCOUNT_HOLDER_NAME_EMPTY_ERROR="Account Holder Name should not be empty.";
var VALIDATION_BANKDETAILS_ACCOUNT_NUMBER_EMPTY_ERROR="Account Number should not be empty.";
var VALIDATION_BANKDETAILS_BRANCH_NAME_EMPTY_ERROR="Branch Name should not be empty.";
var VALIDATION_BANKDETAILS_ACCOUNT_NAME_EMPTY_ERROR="Account Name should not be empty.";
var VALIDATION_BANKDETAILS_IFSC_EMPTY_ERROR="IFSC code should not be empty.";
var VALIDATION_TAXDETAILS_PAN_EMPTY_ERROR="Pan number should not be empty.";
var VALIDATION_TAXDETAILS_STATE_NAME_EMPTY_ERROR="State Name should not be empty.";
var VALIDATION_TAXDETAILS_STATE_CODE_EMPTY_ERROR="State Code should not be empty.";
var VALIDATION_TAXDETAILS_GST_EMPTY_ERROR="GST should not be empty.";
var VALIDATION_BANKDETAILS_BANK_NAME_MIN_LENGTH_ERROR="Bank Name should contain minimum 3 characters.";
var VALIDATION_BANKDETAILS_BANK_NAME_MAX_LENGTH_ERROR="Bank Name Should not contain more than 25 characters.";
var VALIDATION_BANKDETAILS_ACCOUNT_HOLDER_NAME_MIN_LENGTH_ERROR="Accoun Holder Name should contain minimum of 3 characters.";
var VALIDATION_BANKDETAILS_ACCOUNT_HOLDER_NAME_MAX_LENGTH_ERROR="Account Holder Name Should not contain more than 25 characters";
var VALIDATION_BANKDETAILS_ACCOUNT_NUMBER_MIN_LENGTH_ERROR="Account Number should contain minimum of 5 characters.";
var VALIDATION_BANKDETAILS_ACCOUNT_NUMBER_MAX_LENGTH_ERROR="Account number should not contain more than 25 characters.";
var VALIDATION_BANKDETAILS_BRANCH_MIN_LENGTH_ERROR="Branch name should contain minimum of 3 characters.";
var VALIDATION_BANKDETAILS_BRANCH_MAX_LENGTH_ERROR="Branch Name should not contain more than characters.";
var VALIDATION_BANKDETAILS_IFSC_MIN_LENGTH_ERROR="Ifsc code should contain minimum 3 characters.";
var VALIDATION_BANKDETAILS_IFSC_MAX_LENGTH_ERROR="Ifsc code should  not contain more than 25 characters.";
var VALIDATION_TAXDETAILS_PAN_MIN_LENGTH_ERROR="Pan number should contain minimum of 10 characters.";
var VALIDATION_TAXDETAILS_PAN_MAX_LENGTH_ERROR="Pan number should not contain more than 20 characters.";
var VALIDATION_TAXDETAILS_STATE_NAME_MIN_LENGTH_ERROR="State name should contain minimum of 3 characters.";
var VALIDATION_TAXDETAILS_STATE_NAME_MAX_LENGTH_ERROR="State name should not contain more than 25 characters.";
var VALIDATION_TAXDETAILS_STATE_CODE_MIN_LENGTH_ERROR="State code should contain minimum of 2 characters.";
var VALIDATION_TAXDETAILS_STATE_CODE_MAX_LENGTH_ERROR="State code should not contain more than 10 characters."
var VALIDATION_TAXDETAILS_GST_SPECIAL_ERROR="Enter valid GST_IN.";
var VALIDATION_BANK_ACCOUNT_NUMBER_SPECIALS_ERROR="Enter valid account number.";
/************PROSPECT JS VALIDATION MESSAGES*****************************************/

var VALIDATION_PROSPECT_FIRST_NAME_EMPTY_ERROR="Prospect first name should not be empty";
var VALIDATION_PROSPECT_LAST_NAME_EMPTY_ERROR="Prospect last name should not be empty";
var VALIDATION_PROSPECT_PHONE_NUMBER_EMPTY_ERROR="Phone number should not be empty";
var VALIDATION_PROSPECT_PHONE_NUMBER_VALID_ERROR="Enter valid phone number";
var VALIDATION_PROSPECT_DESIGNATION_EMPTY_ERROR="Designation should not be empty";
var VALIDATION_PROSPECT_ADDRESS1_EMPTY_ERROR="Address should not be empty";
var VALIDATION_PROSPECT_ADDRESS2_EMPTY_ERROR="Address Should not be empty";
var VALIDATION_PROSPECT_INFORAMATION_EMPTY_ERROR="Please enter Prospect information";
var VALIDATION_PROSPECT_INFORAMATION_MAXLENGTH_ERROR="Inforamtion should not exceed 1000 characters";
var VALIDATION_PROSPECT_COMPANY="Select company";
var VALIDATION_PROSPECT_EMAIL_EMPTY_ERROR="Please enter valid email";
var VALIDATION_PROSPECT_MEETING_EMPTY_ERROR=" Select meeting type";
var VALIDATION_PROSPECT_SELECT_STAGE_EMPTY_ERROR="Select stage";
var VALIDATION_PROSPECT_CONTRACTEDON_EMPTY_ERROR="Select Contacted date";
var VALIDATION_PROSPECT_NEXTFOLLOWUP_EMPTY_ERROR=" Select next followup date";
var VALIDATION_PROSPECT_COMMENTS_EMPTY_ERROR="Please write comments";
var VALIDATION_CREATE_COMPANY_EMPTY_ERROR="Company name should not be empty.";
var VALIDATION_CREATE_COMPANY_LOCATION_EMPTY_ERROR="Location should not be empty.";
var VALIDATION_PROSPECT_COMMENTS_MAXLENGTH_ERROR="Comments should not exceed 1000 characters";



