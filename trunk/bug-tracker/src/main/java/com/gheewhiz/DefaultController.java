package com.gheewhiz;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DefaultController {
	@Autowired
	BugTrackerService bugTrackerService;

	public void setBugTrackerService(BugTrackerService bugTrackerService) {
		this.bugTrackerService = bugTrackerService;
	}

	@RequestMapping( { "/bug-tracker.html", "/index.html" })
	public String handleIndex(
			@RequestParam(value = "descriptor", required = false) String descriptor,
			HttpSession session, Model model) {
		return "bug-tracker";
	}

	@RequestMapping(value = { "/login.html" }, method = RequestMethod.POST)
	public String processLogin(@RequestParam("screenName") String screenName,
			@RequestParam("password") String password, HttpSession session,
			Model model) {
		Account account = bugTrackerService.authenticate(screenName, password);
		if (account == null) {
			model.addAttribute("authFail", Boolean.TRUE);
			return "login";
		}
		session.setAttribute("account", account);
		return handleIndex(null, session, model);
	}

	@RequestMapping(value = { "/login.html" }, method = RequestMethod.GET)
	public String processLogin(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/logout.html" })
	public String handleLogout(HttpSession session) {
		session.setAttribute("account", null);
		return "login";
	}

	@RequestMapping(value = { "/update-password.html" }, method = RequestMethod.POST)
	public String processPersonal(
			@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("verifyNewPass") String verifyNewPass,
			HttpSession session, Model model) {
		Account account = (Account) session.getAttribute("account");
		String message = null;
		if (!account.getPassword().equals(oldPassword.trim())) {
			message = "The old password entered was invalid";
		} else if (!newPassword.equals(verifyNewPass)) {
			message = "The new passwords do not match";
		} else {
			bugTrackerService.updateAccount(account);
			message = "Password successfully changed";
		}
		model.addAttribute("message", message);
		return "personal";
	}

	@RequestMapping(value = { "/personal.html" }, method = RequestMethod.GET)
	public String handlePersonal(HttpSession session, Model model) {
		return "personal";
	}
}
