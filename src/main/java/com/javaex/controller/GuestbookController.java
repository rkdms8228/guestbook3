package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	//필드
	
	
	//생성자
	
	
	//메소드-gs
	
	
	//메소드-일반
	//방명록 리스트
	@RequestMapping(value="/addList", method={RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		
		System.out.println("GuestbookController>addList()");
		
		//dao를 통해서 리스트를 가져오기
		GuestbookDao guestbookDao = new GuestbookDao();
		List<GuestbookVo> guestList = guestbookDao.getList();
		
		//ds 데이터 보내기 --> request attribute에 넣는다
		model.addAttribute("guestList", guestList);

		return "/WEB-INF/views/addList.jsp";
		
	}
	
	//방명록 등록
	@RequestMapping(value="/add", method={RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		
		System.out.println("GuestbookController>add()");
		
		System.out.println(guestbookVo);
		
		GuestbookDao guestbookDao = new GuestbookDao();
		int count = guestbookDao.guestInsert(guestbookVo);
		System.out.println(count);

		return "redirect:/addList";
		
	}
	
	//방명록 삭제폼
	@RequestMapping(value="/deleteForm/{no}", method={RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@PathVariable("no") int no, Model model) {
		
		System.out.println("GuestbookController>deleteForm()");
		
		GuestbookDao guestbookDao = new GuestbookDao();
		GuestbookVo guestbookVo = guestbookDao.oneGuest(no);
		
		model.addAttribute("guestbookVo", guestbookVo);
		
		return "/WEB-INF/views/deleteForm.jsp";
		
	}
	
	//방명록 삭제
	@RequestMapping(value="/delete/{no}", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable("no") int no, @RequestParam("password") String password) {
		
		System.out.println("GuestbookController>delete()");
		
		GuestbookDao guestbookDao = new GuestbookDao();
		int count = guestbookDao.guestDelete(no, password);
		System.out.println(count);
		
		return "redirect:/addList";
		
	}

}
