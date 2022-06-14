package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	//필드
	@Autowired
	private GuestbookDao guestbookDao;
	
	//생성자
	
	
	//메소드-gs
	
	
	//메소드-일반
	//방명록 리스트
	public List<GuestbookVo> getList() {
		
		List<GuestbookVo> guestList = guestbookDao.getList();
		
		return guestList;
		
	}
	
	//방명록 등록
	public int guestInsert(GuestbookVo guestbookVo) {
		
		int count = guestbookDao.guestInsert(guestbookVo);
		
		return count;
		
	}
	
	//방명록 삭제
	public int guestDelete(int no, String password) {
		
		int count = guestbookDao.guestDelete(no, password);
		
		return count;
		
	}

}
