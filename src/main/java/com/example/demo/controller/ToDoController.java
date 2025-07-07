package com.example.demo.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Titles;
import com.example.demo.model.Account;
import com.example.demo.repository.TitlesRepository;

@Controller
public class ToDoController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	TitlesRepository titlesRepository;

	@GetMapping("/todo")
	public String index(Model model) {

		List<Titles> titlesList = titlesRepository.findByUserId(account.getId());
		model.addAttribute("titlesList", titlesList);

		return "todo";
	}

	@GetMapping("/todo/detail")
	public String detail() {
		return "detail";
	}

	@GetMapping("/todo/new")
	public String todonew() {
		return "";
	}

}
