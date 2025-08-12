package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("join")
	public String join() {
		System.out.println("member/join - get");
		
		return "member/join";
	}
	
	@PostMapping("join")
	public String join(MemberVO memberVO, MultipartFile profile, Model model) throws Exception {
		System.out.println("member/join - post");
		System.out.println(profile.getOriginalFilename());
		
		// form에 제출된 정보들을 Member dto 타입으로 가져와서
		
		// db에 데이터 삽입하고
		int result = memberService.join(memberVO, profile);
		
		String msg = "회원가입 실패";
		String url = "/";
		if(result > 0) {
			msg = "회원가입 성공";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		// 회원가입되었다는 창 띄운 다음
		
		// 메인페이지로 redirect
		
		return "commons/result";
	}
}
