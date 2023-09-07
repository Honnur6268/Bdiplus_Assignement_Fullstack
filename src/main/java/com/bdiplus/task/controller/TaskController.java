package com.bdiplus.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bdiplus.task.model.Task;
import com.bdiplus.task.service.TaskService;

@Controller
@RequestMapping("/api/task")
public class TaskController {

	@Autowired
	private TaskService service;

	@GetMapping({ "/all", "viewTaskList" })
	public String viewAllTasks(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("list", service.getAllTasks());
		model.addAttribute("message", message);

		return "ViewTaskList";
	}

	@GetMapping("addTask")
	public String addTask(Model model) {
		model.addAttribute("task", new Task());

		return "/AddTask";
	}

	@PostMapping("saveTask")
	public String saveTask(Task task, RedirectAttributes redirectAttributes) {
		if (service.saveTask(task)) {
			redirectAttributes.addFlashAttribute("message", "Save Success");
			return "redirect:/api/task/all";
		}

		redirectAttributes.addFlashAttribute("message", "Save Failure");
		return "redirect:/addTask";
	}

	@GetMapping("/updateTaskStatus/{id}")
	public String updateTaskStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (service.updateTaskStatus(id)) {
			redirectAttributes.addFlashAttribute("message", "Update Success");
			return "redirect:/api/task/all";
		}

		redirectAttributes.addFlashAttribute("message", "Update Failure");
		return "redirect:/api/task/all";
	}

	@GetMapping("/deleteTask/{id}")
	public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		if (service.deletTaskById(id)) {
			redirectAttributes.addFlashAttribute("message", "Delete Success");
			return "redirect:/api/task/all";
		}

		redirectAttributes.addFlashAttribute("message", "Delete Failure");
		return "redirect:/api/task/all";
	}

	@GetMapping("/editTask/{id}")
	public String editTask(@PathVariable Long id, Model model) {
		model.addAttribute("task", service.getTaskById(id));

		return "/UpdateTask";
	}

	@PostMapping("updateTask")
	public String updateTaskItem(Task task, RedirectAttributes redirectAttributes) {
		if (service.updateTask(task)) {
			redirectAttributes.addFlashAttribute("message", "Edit Success");
			return "redirect:/api/task/all";
		}

		redirectAttributes.addFlashAttribute("message", "Edit Failure");
		return "redirect:/editTask/" + task.getId();
	}

	@GetMapping("/{id}")
	public Task getTaskById(@PathVariable Long id) {

		Task taskById = service.getTaskById(id);

		return taskById;
	}
}
