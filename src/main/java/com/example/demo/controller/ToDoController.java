package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Task;
import com.example.demo.entity.Titles;
import com.example.demo.model.Account;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.TitlesRepository;

@Controller
public class ToDoController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	TitlesRepository titlesRepository;

	@Autowired
	TaskRepository taskRepository;

	@GetMapping("/todo") //todo一覧を表示
	public String index(Model model) {

		List<Titles> titlesList = titlesRepository.findByUserId(account.getId());
		model.addAttribute("titlesList", titlesList);

		return "todo";
	}

	@GetMapping("/delete/{id}") //todo一覧を削除
	public String delete(
			@PathVariable("id") Integer id,
			Model model) {

		titlesRepository.deleteById(id);

		return "redirect:/todo";
	}

	@GetMapping("/deleteTask/{id}") //taskを削除
	public String deleteTask(
			@PathVariable("id") Integer id,
			@RequestParam("titleId") Integer titleId,
			Model model) {

		taskRepository.deleteById(id);

		return "redirect:/todo/detail/" + titleId;
	}

	@GetMapping("/todo/detail/{id}") //todo詳細を表示
	public String detail(
			@PathVariable("id") Integer id, Model model) {

		List<Titles> titlesList = titlesRepository.findByUserId(account.getId());
		model.addAttribute("titlesList", titlesList);

		List<Task> tasksList = taskRepository.findByTitleId(id);
		model.addAttribute("tasksList", tasksList);

		return "detail";
	}

	@GetMapping("/todo/edit") //編集画面を表示
	public String edit(
			@RequestParam("id") Integer id,
			@RequestParam("title") String title,
			@RequestParam("deadline") LocalDate deadline,
			@RequestParam("titleContents") String titleContents,
			Model model) {

		model.addAttribute("id", id);
		model.addAttribute("title", title);
		model.addAttribute("deadline", deadline);
		model.addAttribute("titleContents", titleContents);

		return "edit";
	}

	@PostMapping("todo/update") //編集完了
	public String update(

			@RequestParam("id") Integer id,
			@RequestParam("title") String title,
			@RequestParam("deadline") LocalDate deadline,
			@RequestParam("titleContents") String titleContents,

			Model model) {

		Titles titles = titlesRepository.findById(id).get();

		titles.setTitleContents(titleContents);
		titles.setTitle(title);
		titles.setDeadline(deadline);

		titlesRepository.save(titles);

		System.out.println(account.getId());
		List<Titles> titlesList = titlesRepository.findByUserId(account.getId());
		model.addAttribute("titlesList", titlesList);

		//		System.out.println("titles=" + titles);

		return "todo";
	}

	@GetMapping("/todo/new") //ToDo新規追加画面を表示する
	public String addToDo() {
		return "addToDo";
	}

}
