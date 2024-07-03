package com.example.lesson07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lesson04.bo.StudentBO;
import com.example.lesson07.entity.StudentEntity;

@RequestMapping("/lesson07/ex01")
@RestController
public class Lesson07Ex01RestController {

	@Autowired
	private StudentBO studentBO;
	
	// Create
	@GetMapping("/create")
	public StudentEntity create() {
		String name = "동동동";
		String phoneNumber = "01098148357";
		String email = "abc@naver.com";
		String dreamJob = "부자";
		
		return studentBO.addStudent(name, phoneNumber, email, dreamJob);
	}
	
	// Update
	@GetMapping("/update")
	public StudentEntity update() {
		// id가 9번인 dreamJob을 변경(야구선수)
		return studentBO.updateStudentDreamJobById(9, "야구선수");
	}
	
	// delete
	@GetMapping("/delete")
	public String delete() {
		// id가 8인 데이터 삭제
		studentBO.deleteStudentById(8);
		return "삭제 성공";
	}
}
