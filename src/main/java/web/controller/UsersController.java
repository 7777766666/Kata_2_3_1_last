package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;
import web.models.User;

import javax.validation.Valid;


@Controller
//	@RequestMapping("/users")
public class UsersController {

	private final UserDAO userDAO;

	@Autowired
	public UsersController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("users", userDAO.index());
		return "users/index";
	}

	@GetMapping("/users/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		model.addAttribute("user", userDAO.show(id));
		return "users/show";
	}

	@GetMapping("/users/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "users/new";
	}

	@PostMapping()
	public String create(@ModelAttribute("user") @Valid User user,
						 BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "users/new";

		userDAO.save(user);
		return "redirect:/";
	}

	@GetMapping("/users/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userDAO.show(id));
		return "users/edit";
	}

	@PatchMapping("/users/{id}")
	public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
						 @PathVariable("id") int id) {
		if (bindingResult.hasErrors())
			return "users/edit";

		userDAO.update(id, user);
		return "redirect:/";
	}

	@DeleteMapping("/users/{id}")
	public String delete(@PathVariable("id") int id) {
		userDAO.delete(id);
		return "redirect:/";
	}
}