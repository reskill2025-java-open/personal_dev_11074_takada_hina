package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/login") //ログイン画面を表示
	public String index() {

		session.invalidate();

		return "login";
	}

	@GetMapping("/logout") //ログアウトする
	public String logout() {
		return "login";
	}

	@PostMapping("/login")
	public String index(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "password", defaultValue = "") String password, Model model) {

		if (name.equals("") || password.equals("")) {
			//名前とパスワードが必ず入力されている。

			model.addAttribute("msg", "名前とパスワードを入力してください。");

			return "login";

		} else {

			User user = userRepository.findByNameAndPassword(name, password);

			if (user != null) {

				//ログイン成功処理

				account.setId(user.getId());//userIdをセット
				account.setName(user.getName());
				return "redirect:/todo";

			} else {

				//ログイン失敗

				model.addAttribute("msg", "ログインに失敗しました。");
				return "login";
			}

		}

	}

	@GetMapping("/users/new")
	public String users() {
		return "new";
	}

	@PostMapping("/users/add")
	public String add(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "password", defaultValue = "") String password,
			Model model) {

		if (name.equals("") || password.equals("")) {
			model.addAttribute("msg", "名前とパスワードを入力してください。");
			//名前とパスワードのどちらか空であることを防ぐ。

			return "new";
		} else if (name.length() >= 20 || password.length() >= 20) {

			model.addAttribute("msg", "名前とパスワードは20文字以内で入力してください。");
			//文字数オーバーでエラーが出るのを防ぐ。

			return "new";

		} else if (userRepository.findByNameAndPassword(name, password) != null) {

			model.addAttribute("msg", "この名前とパスワードは使用できません。");
			//同じ名前とパスワードが登録されるのを防ぐ。

			return "new";

		} else {

			User user = new User();
			user.setName(name);
			user.setPassword(password);

			userRepository.save(user);//登録完了

			return "redirect:/login";
		}
	}

}
