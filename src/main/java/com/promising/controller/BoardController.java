package com.promising.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.promising.domain.BoardVO;
import com.promising.domain.PageMaker;
import com.promising.domain.PageVO;
import com.promising.repository.BoardRepository;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/board")
@Log
public class BoardController {
	
	@Autowired
	private BoardRepository repo;
	
	@GetMapping("/register")
	public void registerGet(@ModelAttribute("vo")BoardVO vo) { // 궁금증은 나중에
		
	}
	
	@PostMapping("/register")
	public String registerPost(@ModelAttribute("vo")BoardVO vo) {
		repo.save(vo);
		return "redirect:/board/list";
	}
	
	@PostMapping("/delete/{bno}")
	public String delete(@PathVariable("bno") Long bno) { // bno는 왜 long 타입?
		repo.deleteById(bno);
		return "redirect:/board/list";
	}
	
	// 리스트형태
	/*
	@GetMapping("/detail/{bno}")
	public String detail(@PathVariable("bno") Long bno,Model model) {
		System.out.println(bno);
		List<BoardVO> list=new ArrayList<>();
		list.add(repo.findById(bno).get());
		model.addAttribute("list",list);
		return "board/detail";
		
	}
	*/
	
	/*
	@GetMapping("/detail/{bno}")
	public String detail(@PathVariable("bno") Long bno, Model model) {
		System.out.println(bno);
		BoardVO vo= repo.findById(bno).get();
		model.addAttribute("vo",vo);
		System.out.println("bno : "+ bno);
		return "board/detail";
		
	}
	*/
	
	@GetMapping("/detail")
	public void detail(Long bno,@ModelAttribute("pageVO") PageVO vo, Model model) {
		repo.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
	}
	
 	@GetMapping("/list")
	public void list(PageVO pvo, Model model) {
 		Pageable page = pvo.makePageable(0, "bno");
 		Page<BoardVO> result = repo.findAll(repo.makePredicate(pvo.getType(), pvo.getKeyword()),page);
 		model.addAttribute("result", new PageMaker(result));
 		
 		log.info(" " + page);

//		List<BoardVO> list = repo.findAll();
//		model.addAttribute("list", list);
//		return "board/list";
	}
	
	@GetMapping("/modify/{bno}")
	public String modify(@PathVariable("bno") Long bno,Model model) {
		 BoardVO vo=repo.findById(bno).get();
		 model.addAttribute("vo",vo);
		return "board/modify";
	}
	@PostMapping("/modify/{bno}")
	public String modifyProc(@PathVariable("bno") Long bno,@ModelAttribute("vo")BoardVO vo,Model model) {
		repo.save(vo);
		return "redirect:/board/detail/{bno}";
	}

}


