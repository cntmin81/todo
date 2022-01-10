package io.github.cntmin81.mytodo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.cntmin81.mytodo.entity.Task;
import io.github.cntmin81.mytodo.entity.UserEntity;
import io.github.cntmin81.mytodo.repository.TaskRepository;
import io.github.cntmin81.mytodo.repository.UserRepository;

@Controller
public class TodoController {

	private static final Logger log = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("/")
	public String index() {
		return "redirect:tasklist";
	}

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@GetMapping("/tasklist")
	public String taskList(Authentication authentication, Model model) {
//		String username = "";
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if (principal instanceof UserDetails) {
//			username = ((UserDetails) principal).getUsername();
//		} else {
//			username = principal.toString();
//		}

		Iterable<Task> taskList = taskRepository.findAll();
		Iterable<Task> hasDoneTaskList = taskRepository.findByHasDone(true);
		Iterable<Task> hasNotDoneTaskList = taskRepository.findByHasDone(false);
		log.info(hasDoneTaskList.toString());
		log.info(hasNotDoneTaskList.toString());
		model.addAttribute("taskList", taskList);
		model.addAttribute("hasDoneTaskList", hasDoneTaskList);
		model.addAttribute("todayTaskList", hasNotDoneTaskList);
		return "tasklist";
	}

	@PostMapping("/addtask")
	public String addTask(@RequestParam("task") String task) {
		log.info(task);
		taskRepository.save(new Task(task, false));
		return "redirect:tasklist";
	}

	@PostMapping("/updatetask")
	public String updateTask(@RequestParam("id") Long id) {
		log.info(id.toString());
		Optional<Task> tempTask = taskRepository.findById(id);
		tempTask.ifPresent(dbtask -> {
			if (dbtask.getHasDone()) {
				dbtask.setHasDone(false);
			} else {
				dbtask.setHasDone(true);
			}
			taskRepository.save(dbtask);
		});
		return "redirect:tasklist";
	}

	@GetMapping("/signup")
	public String signupView() {
		return "signup";
	}

	@PostMapping("/signup")
	public String signupLogic(@RequestParam("name") String name, @RequestParam("password") String password) {
		log.info("usernane : " + name);
		log.info("password : " + password);
		UserEntity user = new UserEntity(name, passwordEncoder.encode(password), "user");
		userRepository.save(user);
		return "redirect:login";
	}
}
