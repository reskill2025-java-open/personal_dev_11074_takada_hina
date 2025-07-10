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

		//		List<Titles> titlesList = titlesRepository.findById(id);
		//		model.addAttribute("titlesList", titlesList);

		//sessionに管理されたLoginIｄと同じIDのToDo一覧のリストを作る
		List<Titles> titleList = titlesRepository.findByUserId(account.getId());

		//進捗度の更新
		for (Titles title : titleList) {
			int tasksProgress = 0;
			int todoProgress = 0;
			List<Task> taskList = taskRepository.findByTitleId(title.getId());
			for (Task task : taskList) {
				tasksProgress += task.getTaskProgress();
			}
			todoProgress = tasksProgress * 100 / (taskList.size() * 5);
			title.setTitleProgress(todoProgress);
			titlesRepository.save(title);
		}

		Titles title = new Titles();
		title = titlesRepository.findById(id).get();
		model.addAttribute("title", title);

		List<Task> tasksList = taskRepository.findByTitleIdOrderById(id);
		model.addAttribute("tasksList", tasksList);

		return "detail";
	}

	@GetMapping("/todo/change/progress") //進捗度を更新する
	public String changeProgress(
			@RequestParam("id") Integer id,
			@RequestParam("progress") Integer progress, //selectで飛んできたprogressをキャッチ
			@RequestParam("titleId") Integer titleId,
			Model model) {

		Task task = taskRepository.findById(id).get();//taskテーブルからidで探してtaskに入れる
		task.setTaskProgress(progress);//とってきたidの元の進捗をselectで選ばれた進捗に変更
		taskRepository.save(task);//taskに保存

		List<Task> taskList = null;
		taskList = taskRepository.findByTitleIdOrderById(titleId);
		//titleIdで指定して探してきたtaskをtaskListに入れる？

		int progresssSum = 0;//各タスクの進捗の合計
		int progressAll = taskList.size() * 5;//taskListの中の個数(タスク数)×5
		int progressResult = 0;

		for (Task i : taskList) {//拡張for文//タスク全体の進捗を合計

			/*
			 * ループが始まると、リストに入っている
			 * 各 Task オブジェクトを順に変数 i に取り出して処理
			 */

			progresssSum = progresssSum + i.getTaskProgress();

		}

		progressResult = (progresssSum * 100) / progressAll;
		Titles title = titlesRepository.findById(titleId).get();

		title.setTitleProgress(progressResult);

		titlesRepository.save(title);

		return "redirect:/todo/detail/" + titleId;
	}

	@GetMapping("/todo/edit/{id}") //todoの編集画面を表示
	public String edit(
			@PathVariable("id") Integer titleId,
			Model model) {

		Titles title = titlesRepository.findById(titleId).get();
		List<Task> tasksList = taskRepository.findByTitleId(titleId);

		model.addAttribute("title", title);
		model.addAttribute("taskList", tasksList);

		return "edit";
	}

	@PostMapping("todo/update") //編集完了//まだタスク追加できない
	public String update(

			@RequestParam(name = "id", defaultValue = "") Integer id, //追加
			@RequestParam(name = "userId", defaultValue = "") Integer userId, //追加
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "deadline", defaultValue = "") LocalDate deadline,
			@RequestParam(name = "titleProgress", defaultValue = "") Integer titleProgress, //追加
			@RequestParam(name = "titleContents", defaultValue = "") String titleContents,
			@RequestParam(name = "taskTitle", defaultValue = "") String taskTitle[], //taskの名前
			@RequestParam(name = "taskId", defaultValue = "0") Integer taskId[], //タスクid
			@RequestParam(name = "addTasks", defaultValue = "") String addTask[], //

			Model model) {

		Titles titles = new Titles();//新しく上書きして新規挿入

		System.out.println("Id=" + id);
		System.out.println("Id=" + id);

		titles.setId(id);
		titles.setUserId(userId);
		titles.setTitle(title);
		titles.setDeadline(deadline);
		titles.setTitleProgress(titleProgress);
		titles.setTitleContents(titleContents);

		titlesRepository.save(titles);

		//もとのタスクを編集する
		for (int i = 0; i < taskId.length; i++) {

			System.out.println("既存編集for文に入ってる？");

			Task task = taskRepository.findById(taskId[i]).get();

			System.out.println("ああああああ");

			task.setTaskTitle(taskTitle[i]);

			System.out.println("あ");

			taskRepository.save(task);
		}

		//新しいタスクの追加
		for (int i = 0; i < addTask.length; i++) {

			System.out.println("編集for文に入ってる？");

			if (!addTask[i].equals("")) {

				System.out.println("編集if文に入ってる？");

				Task newTask = new Task(titles.getId(), 0, addTask[i]);
				taskRepository.save(newTask);
			}
		}

		//		List<Titles> titlesList = titlesRepository.findByUserId(account.getId());
		//		model.addAttribute("titlesList", titlesList);

		List<Task> taskList = null;
		taskList = taskRepository.findByTitleIdOrderById(titles.getId());

		return "redirect:/todo";
	}

	@GetMapping("/todo/new") //ToDo新規追加画面を表示する
	public String addToDo() {
		return "addToDo";
	}

	@PostMapping("/todo/add") //新規追加処理
	public String addToDo(

			@RequestParam(name = "id", defaultValue = "") Integer id,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "deadline", defaultValue = "") LocalDate deadline,
			@RequestParam(name = "titleContents", defaultValue = "") String titleContents,
			@RequestParam(name = "titleProgress", defaultValue = "") Integer titleProgress,
			@RequestParam(name = "taskTitle", defaultValue = "") String taskTitle,
			@RequestParam(name = "taskProgress", defaultValue = "") Integer taskProgress,
			@RequestParam(name = "tasks", defaultValue = "") String tasks[],
			@RequestParam(name = "taskId", defaultValue = "0") Integer taskId[],
			@RequestParam(name = "addTasks", defaultValue = "") String addTasks[],
			Model model) {

		Titles titles = new Titles();

		titles.setId(id);
		titles.setUserId(account.getId());
		titles.setTitle(title);
		titles.setDeadline(deadline);
		titles.setTitleContents(titleContents);
		titles.setTitleProgress(titleProgress);

		titlesRepository.save(titles);

		//新しいタスクの追加
		for (int i = 0; i < addTasks.length; i++) {

			System.out.println("新規追加for文に入ってる？");

			if (!addTasks[i].equals("")) {

				System.out.println("新規追加if文に入ってる？");

				Task newTask = new Task(titles.getId(), 0, addTasks[i]);
				taskRepository.save(newTask);

				List<Task> taskList = null;
				taskList = taskRepository.findByTitleIdOrderById(titles.getId());

			}
		}

		return "redirect:/todo";
	}

}
