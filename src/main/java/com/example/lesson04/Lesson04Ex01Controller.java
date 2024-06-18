package com.example.lesson04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lesson04.bo.UserBO;
import com.example.lesson04.domain.User;

@RequestMapping("/lesson04/ex01")
@Controller // html 화면의 경우 @Responsebody 사용 X
public class Lesson04Ex01Controller {
	
	@Autowired
	private UserBO userBO;

	// 회원가입 화면
	// http://localhost/lesson04/ex01/add-user-view (view 붙어있으면 html인 걸로 생각(학원에서))
	@RequestMapping(path = "/add-user-view", method = RequestMethod.GET) // get 방식으로만 요청받기
	public String addUserView() { // 리턴되는 String은 주소
		return "lesson04/addUser"; // html view 경로, 처음 '/'랑 ".html"안붙임
	}
	
	// 회원가입 진행 => DB 저장 => 결과 화면
	// html 폼에서 가입 버튼 누르면 여기로 옴
	@PostMapping("/add-user") // Get으로 요청하면 405 오류 뜸
	public String addUser(
			@RequestParam("name") String name, 
			@RequestParam("yyyymmdd") String yyyymmdd, 
			@RequestParam("email") String email, 
			@RequestParam(value = "introduce", required = false) String introduce) {
		
		// DB 저장 - insert
		userBO.addUser(name, yyyymmdd, email, introduce);
		
		// 결과 화면
		return "lesson04/afterAddUser";
	}
	
	// 최근 가입자 보여주는 화면
	// http://localhost/lesson04/ex01/latest-user-view (html)
	@GetMapping("/latest-user-view")
	public String latestUserView(Model model) { // Model: view 화면에서 데이터를 꺼내 쓸 수 있는 객체
		// DB select (최신 가입자 1명)
		User user = userBO.getLatestUser();
		
		// Model 주머니에 담는다. => HTML에서 꺼내쓴다.
		model.addAttribute("result", user); // (키, 실제 데이터)
		model.addAttribute("title", "최근 가입한 유저 정보!");
		
		// 화면 이동
		return "lesson04/latestUser";
	}
}
