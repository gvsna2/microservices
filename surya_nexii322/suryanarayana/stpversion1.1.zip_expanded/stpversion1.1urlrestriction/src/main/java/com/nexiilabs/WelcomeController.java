package com.nexiilabs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String root(Map<String, Object> model) {
		return "login";
	}
	@RequestMapping("/login")
	public String login(Map<String, Object> model) {
		return "login";
	}
	@RequestMapping("/403")
	public String a403(Map<String, Object> model) {
		return "403";
	}
	@RequestMapping("/accounts")
	public String accounts(Map<String, Object> model) {
		return "accounts";
	}
	@RequestMapping("/active_resources")
	public String active_resources(Map<String, Object> model) {
		return "active_resources";
	}
	@RequestMapping("/add_resources")
	public String add_resources(Map<String, Object> model) {
		return "add_resources";
	}
	@RequestMapping("/bench")
	public String bench(Map<String, Object> model) {
		return "bench";
	}
	@RequestMapping("/changepw")
	public String changepw(Map<String, Object> model) {
		return "changepw";
	}
	@RequestMapping("/company_bankdetails")
	public String company_bankdetails(Map<String, Object> model) {
		return "company_bankdetails";
	}
	@RequestMapping("/company_taxdetails")
	public String company_taxdetails(Map<String, Object> model) {
		return "company_taxdetails";
	}
	@RequestMapping("/inactive_resources")
	public String inactive_resources(Map<String, Object> model) {
		return "inactive_resources";
	}
	@RequestMapping("/invoice_format")
	public String invoice_format(Map<String, Object> model) {
		return "invoice_format";
	}
	@RequestMapping("/my_invoices")
	public String my_invoices(Map<String, Object> model) {
		return "my_invoices";
	}
	@RequestMapping("/my_profile")
	public String my_profile(Map<String, Object> model) {
		return "my_profile";
	}
	@RequestMapping("/pwrecover")
	public String pwrecover(Map<String, Object> model) {
		return "pwrecover";
	}
	@RequestMapping("/request_invoices")
	public String request_invoices(Map<String, Object> model) {
		return "request_invoices";
	}
	@RequestMapping("/roles")
	public String roles(Map<String, Object> model) {
		return "roles";
	}
	@RequestMapping("/users")
	public String users(Map<String, Object> model) {
		return "users";
	}
	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		return "welcome";
	}
	@RequestMapping("/prospects-contacts")
	public String prospects_contacts(Map<String, Object> model) {
		return "prospects-contacts";
	}
	@RequestMapping("/prospects-myfunnel")
	public String prospects_myfunnel(Map<String, Object> model) {
		return "prospects-myfunnel";
	}
	@RequestMapping("/prospects-followups")
	public String prospects_followups(Map<String, Object> model) {
		return "prospects-followups";
	}
	@RequestMapping("/reports")
	public String reports(Map<String, Object> model) {
		return "reports";
	}

}