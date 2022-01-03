package io.github.cntmin81.mytodo.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.cntmin81.mytodo.dto.TaskRequest;
import io.github.cntmin81.mytodo.entity.Task;
import io.github.cntmin81.mytodo.repository.TaskRepository;

@Controller
public class TodoController {

	private static final Logger log = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@GetMapping("/tasklist")
	public String taskList(@ModelAttribute TaskRequest taskRequest, Model model) {
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
	public String addTask(@ModelAttribute TaskRequest taskRequest, Model model) {
		log.info(taskRequest.toString());
		taskRepository.save(new Task(taskRequest.getTask(), false));
		return "redirect:tasklist";
	}

	@PostMapping("/updatetask")
	public String updateTask(@ModelAttribute TaskRequest taskRequest, Model model) {
		log.info(taskRequest.toString());
		Optional<Task> tempTask = taskRepository.findById(taskRequest.getId());
		tempTask.ifPresent(task -> {
			if (task.getHasDone()) {
				task.setHasDone(false);	
			} else {
				task.setHasDone(true);
			}
			taskRepository.save(task);
		});
		return "redirect:tasklist";
	}
}
