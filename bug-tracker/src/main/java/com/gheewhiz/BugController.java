package com.gheewhiz;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BugController {
	@Autowired
	BugTrackerService bugTrackerService;

	public void setBugTrackerService(BugTrackerService bugTrackerService) {
		this.bugTrackerService = bugTrackerService;
	}

	@RequestMapping(value = { "/bugs.html" })
	public String handleBugs(HttpSession session,
			@RequestParam("productId") Integer productId, Model model) {
		model.addAttribute("product", bugTrackerService.getProduct(productId));
		model.addAttribute("bugs", bugTrackerService.getBugs(productId));
		return "bugs";
	}

	@RequestMapping(value = { "/bug.html" })
	public String handleBug(HttpSession session,
			@RequestParam("bugId") Integer bugId, Model model) {
		model.addAttribute("bugs", bugTrackerService.getBug(bugId));
		return "bug";
	}

	@RequestMapping(value = { "/create-bug.html" })
	public String handleCreateBug(HttpSession session,
			@RequestParam("productId") Integer productId,
			@RequestParam("state") String state,
			@RequestParam("resolution") String resolution,
			@RequestParam("shortdesc") String shortdesc,
			@RequestParam("steps") String steps,
			@RequestParam("comments") Set<Comment> comments, Model model) {
		model.addAttribute("create-bug", bugTrackerService.createBug(productId,
				state, resolution, shortdesc, steps, comments));
		return "create-bug";
	}

	@RequestMapping(value = { "/create-comment.html" })
	public String handleCreateComment(HttpSession session,
			@RequestParam("comment") String comment,
			@RequestParam("bugId") Integer bugId, Model model) {
		model.addAttribute("create-comment", bugTrackerService.createComment(
				comment, bugId, getSessionAccount(session)));
		return "create-comment";
	}

	@RequestMapping(value = { "/products.html" })
	public String handleProducts(HttpSession session, Model model) {
		model.addAttribute("products", bugTrackerService.getProducts());
		return "products";
	}

	@RequestMapping(value = { "/create-product.html" })
	public String handleCreateProduct(HttpSession session,
			@RequestParam("name") String name,
			@RequestParam("version") String version,
			@RequestParam("managerId") Integer managerId,
			Model model) {
		//model.addAttribute("create-product", bugTrackerService
				//.createProductCategory(name, version, manager, qa, developers));
		return "create-product";
	}

	private Account getSessionAccount(HttpSession session) {
		return (Account) session.getAttribute("account");
	}
}