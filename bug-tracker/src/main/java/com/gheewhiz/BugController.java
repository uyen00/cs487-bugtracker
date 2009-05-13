package com.gheewhiz;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BugController {
	@Autowired
	BugTrackerService bugTrackerService;

	public void setBugTrackerService(BugTrackerService bugTrackerService) {
		this.bugTrackerService = bugTrackerService;
	}
	
	@RequestMapping(value = { "/bugs.html" })
	public String handleBugs(HttpSession session, Integer productId, Model model) {
		model.addAttribute("bugs", bugTrackerService.getBugs(productId));
		return "bugs";
	}
	
	@RequestMapping(value = { "/bug.html" })
	public String handleBug(HttpSession session, Integer bugId, Model model) {
		model.addAttribute("bugs", bugTrackerService.getBug(bugId));
		return "bug";
	}
	
	@RequestMapping(value = { "/create-bug.html" })
	public String handleCreateBug(HttpSession session, Integer productId, String state, String resolution, java.util.Date opened, String shortdesc, String steps, Set<Comment> comments, Model model) {
		model.addAttribute("create-bug", bugTrackerService.createBug(productId, state, resolution, opened, shortdesc, steps, comments));
		return "create-bug";
	}
	
	@RequestMapping(value = { "/create-comment.html" })
	public String handleCreateComment(HttpSession session, String comment, Integer bugId, Account commenter, Model model) {
		model.addAttribute("create-comment", bugTrackerService.createComment(comment, bugId, commenter));
		return "create-comment";
	}
	
	@RequestMapping(value = { "/products.html" })
	public String handleProducts(HttpSession session, Model model) {
		model.addAttribute("products", bugTrackerService.getProducts());
		return "products";
	}
	
	@RequestMapping(value = { "/create-product.html" })
	public String handleCreateProduct(HttpSession session, String name, String version, Account manager, Set<Account> qa, Set<Account> developers, Model model) {
		model.addAttribute("create-product", bugTrackerService.createProductCategory(name, version, manager, qa, developers));
		return "create-product";
	}
}
