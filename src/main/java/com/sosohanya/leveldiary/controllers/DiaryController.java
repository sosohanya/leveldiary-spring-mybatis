package com.sosohanya.leveldiary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sosohanya.leveldiary.account.Account;
import com.sosohanya.leveldiary.account.MybatisAccountRepository;
import com.sosohanya.leveldiary.diary.Diary;
import com.sosohanya.leveldiary.diary.MybatisDiaryRepository;

@Controller
@RequestMapping("/diary")
public class DiaryController {
	
	@Autowired
	MybatisDiaryRepository diaryRepository;
	
	@Autowired
	MybatisAccountRepository accountRepository;

	@GetMapping("/list") //목록 
	public String list(Model model) {
		
		model.addAttribute("diaries", diaryRepository.findAll());
		model.addAttribute("count", diaryRepository.count());
		
		return "diary/list";
	}
	
	@GetMapping("/add") //추가 Form 
	public String add() {
		return "diary/add";
	}
	
	@PostMapping("/add") //추가 처리 
	public String addProcess(Diary diary){
		diary.setAccount(accountRepository.findByEmail("email1@email.com")); //차후 로그인한 유저로 바뀌겠지만 우선은 임시
		diaryRepository.save(diary);
		
		return "redirect:/diary/list";
	}
	
	@GetMapping("/{id}") //상세보기 
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("diary", diaryRepository.findById(id));
		
		return "diary/view";
	}
	
	@GetMapping("/{id}/modify") //수정 Form
	public String modify(@PathVariable Long id, Model model) {
		
		model.addAttribute("diary", diaryRepository.findById(id));
		
		return "diary/modify";
	}
	
	@PostMapping("/{id}/modify") //수정 처리 
	public String modifyProcess(@PathVariable Long id, Diary updateDiary) {
		Diary diary = diaryRepository.findById(id);
		diary.update(updateDiary);
		diaryRepository.update(diary);
		
		return String.format("redirect:/diary/%d", id) ;
	}
	
	@PostMapping("/{id}/delete") //삭제 처리 
	public String deleteProcess(@PathVariable Long id) {
		diaryRepository.deleteById(id);
		
		return "redirect:/diary/list";
	}
}
