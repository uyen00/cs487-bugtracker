package com.gheewhiz;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/useradmin.html")
	public String handleUserAdmin(HttpSession session, Model model) {
		if (!isAuthorized(session)) {
			return "unauthorized";
		}
		// model.addAttribute("emailMap", wootubeService.getEmailMap());
		// model.addAttribute("screenNameMap",
		// wootubeService.getScreenNameMap());
		return "useradmin";
	}

	@RequestMapping("/createaccount.html")
	public String handleCreateAccount(
			@RequestParam("memberId") Integer memberId,
			@RequestParam("screenName") String screenName,
			@RequestParam("memberType") String memberType,
			@RequestParam("password") String password, HttpSession session,
			Model model) {
		if (!isAuthorized(session)) {
			return "unauthorized";
		}
		bugTrackerService.createAccount(screenName, password,
				null);
		// model.addAttribute("emailMap", wootubeService.getEmailMap());
		// model.addAttribute("screenNameMap",
		// wootubeService.getScreenNameMap());
		return "useradmin";
	}

	public boolean isAuthorized(HttpSession session) {
		return ((Account) session.getAttribute("account"))
				.isEntitledWIthAdmin();
	}

}
