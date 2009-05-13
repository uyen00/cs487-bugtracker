package com.gheewhiz;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class BugController {
	@Autowired
	BugTrackerService bugTrackerService;

	public void setBugTrackerService(BugTrackerService bugTrackerService) {
		this.bugTrackerService = bugTrackerService;
	}
	
	@RequestMapping(value = { "/bugs.html" })
	public String handleBugs(HttpSession session, Model model) {
		return "bugs";
	}
	
	@RequestMapping(value = { "/bug.html" })
	public String handleBug(HttpSession session, Model model) {
		return "bug";
	}
	
	@RequestMapping(value = { "/create-bug.html" })
	public String handleCreateBug(HttpSession session, Model model) {
		return "create-bug";
	}
	
	@RequestMapping(value = { "/create-comment.html" })
	public String handleCreateComment(HttpSession session, Model model) {
		return "create-comment";
	}
	
	@RequestMapping(value = { "/products.html" })
	public String handleProducts(HttpSession session, Model model) {
		model.addAttribute("products", bugTrackerService.getProducts());
		return "products";
	}
}
