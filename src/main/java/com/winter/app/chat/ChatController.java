package com.winter.app.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/chat/*")
public class ChatController {
	
	@GetMapping("chat")
	public String chat() {
		return "chat/chat";
	}

}
