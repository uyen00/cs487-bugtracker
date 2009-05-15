package com.gheewhiz;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModerationController {
	@Autowired
	BugTrackerService bugTrackerService;

	public void setBugTrackerService(BugTrackerService bugTrackerService) {
		this.bugTrackerService = bugTrackerService;
	}

	@RequestMapping(value = { "/moderate.html" })
	public String handleModerate(HttpSession session, Model model) {
		if (!isAuthorized(session)) {
			return "unauthorized";
		}
		return "moderate";
	}

	@RequestMapping(value = "/useradmin.html", method = RequestMethod.GET)
	public String handleUserAdmin(HttpSession session, Model model) {
		if (!isAuthorized(session)) {
			return "unauthorized";
		}
		return "useradmin";
	}

	@RequestMapping(value = { "/useradmin.html" }, method = RequestMethod.POST)
	public String handleCreateAccount(
			@RequestParam("screenName") String screenName,
			@RequestParam("password") String password, 
			@RequestParam("entitlement") String entitlement,
			HttpSession session,
			Model model) {
		if (!isAuthorized(session)) {
			return "unauthorized";
		}
		
		model.addAttribute("newUser", bugTrackerService.createAccount(screenName, password, 
				Collections.singleton(new Entitlement(entitlement.trim()))));
		return "useradmin";
	}
	
	public boolean isAuthorized(HttpSession session) {
		return ((Account) session.getAttribute("account"))
				.isEntitledWIthAdmin();
	}

}
