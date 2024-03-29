package com.gheewhiz;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		model.addAttribute("bug", bugTrackerService.getBug(bugId));
		return "bug";
	}

	@RequestMapping(value = { "/create-bug.html" }, method = RequestMethod.GET)
	public String handleCreateBug(HttpSession session,
			@RequestParam("productId") Integer productId, Model model) {
		model.addAttribute("product",bugTrackerService.getProduct(productId));
		model.addAttribute("states",bugTrackerService.getAllStates());
		model.addAttribute("resolutions",bugTrackerService.getAllResolutions());
		return "create-bug";
	}

	@RequestMapping(value = { "/create-bug.html" }, method = RequestMethod.POST)
	public String handleCreateBug(HttpSession session,
			@RequestParam("productId") Integer productId,
			@RequestParam("state") String state,
			@RequestParam("resolution") String resolution,
			@RequestParam("shortdesc") String shortdesc,
			@RequestParam("steps") String steps, Model model) {
		model.addAttribute("bug", bugTrackerService.createBug(productId,
					state, resolution, shortdesc, steps));
		 
		return "bug";
	}
    
	@RequestMapping(value = { "/create-comment.html" }, method = RequestMethod.GET)
	public String handleCreateComment(HttpSession session,
			@RequestParam("bugId") Integer bugId, Model model) {
		model.addAttribute("product",bugTrackerService.getBug(bugId));
		return "create-comment";
	}
	
	@RequestMapping(value = { "/create-comment.html" }, method = RequestMethod.POST)
	public String handleCreateComment(HttpSession session,
			@RequestParam("comment") String comment,
			@RequestParam("bugId") Integer bugId, Model model) {
		bugTrackerService.createComment(
				comment, bugId, getSessionAccount(session));
		model.addAttribute("bug", bugTrackerService.getBug(bugId));
		return "bug";
	}

	@RequestMapping(value = { "/products.html" })
	public String handleProducts(HttpSession session, Model model) {
		model.addAttribute("products", bugTrackerService.getProducts());
		return "products";
	}

	@RequestMapping(value = { "/create-product.html" }, method = RequestMethod.GET)
	public String handleCreateProduct(HttpSession session, Model model) {
		model.addAttribute("managerids",bugTrackerService.getManagerAccountsIds());
		return "create-product";
	}
	
	@RequestMapping(value = { "/create-product.html" }, method = RequestMethod.POST)
	public String handleCreateProduct(HttpSession session,
			@RequestParam("name") String name,
			@RequestParam("version") String version,
			@RequestParam("managerId") Integer managerId,
			Model model) {
		bugTrackerService
			.createProductCategory(name, version, managerId);
		model.addAttribute("products", bugTrackerService.getProducts());
		return "products";
	}
	
	@RequestMapping(value = { "/add-devprod.html" }, method = RequestMethod.GET)
	public String handleAddProdDev(HttpSession session, Model model) {
		model.addAttribute("productids",bugTrackerService.getProductsIds());
		model.addAttribute("devids",bugTrackerService.getDeveloperAccountsIds());
		return "add-devprod";
	}
	
	@RequestMapping(value = { "/add-devprod.html" }, method = RequestMethod.POST)
	public String handleAddProdDev(HttpSession session,
			@RequestParam("productId") Integer productId,
			@RequestParam("devId") Integer devId,
			Model model) {
		bugTrackerService
			.addDevProd(productId, devId);
		model.addAttribute("products", bugTrackerService.getProducts());
		return "products";
	}
	
	@RequestMapping(value = { "/add-qaprod.html" }, method = RequestMethod.GET)
	public String handleAddProdQA(HttpSession session, Model model) {
		model.addAttribute("productids",bugTrackerService.getProductsIds());
		model.addAttribute("qaids",bugTrackerService.getQAAccountsIds());
		return "add-qaprod";
	}
	
	@RequestMapping(value = { "/add-qaprod.html" }, method = RequestMethod.POST)
	public String handleAddProdQA(HttpSession session,
			@RequestParam("productId") Integer productId,
			@RequestParam("qaId") Integer qaId,
			Model model) {
		bugTrackerService
			.addQAProd(productId, qaId);
		model.addAttribute("products", bugTrackerService.getProducts());
		return "products";
	}
	
	@RequestMapping(value = { "/list-users.html"})
	public String handleListUsers(HttpSession session, Model model) {
		model.addAttribute("accounts", bugTrackerService.getAccounts());
		return "list-users";
	}	

	private Account getSessionAccount(HttpSession session) {
		return (Account) session.getAttribute("account");
	}
}
