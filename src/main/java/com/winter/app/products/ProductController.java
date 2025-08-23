package com.winter.app.products;

import java.util.List;

import com.winter.app.controller.HomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/products/*")
public class ProductController {

    private final HomeController homeController;
	@Autowired
	private ProductService productService;

    ProductController(HomeController homeController) {
        this.homeController = homeController;
    }
	
	@GetMapping("list")
	public void list(Model model) throws Exception {
		List<ProductVO> list = productService.list();
		model.addAttribute("list", list);
		
		System.out.println("products/list");
	}
	
	@GetMapping("detail")
	public void detail(ProductVO productVO, Model model) throws Exception {
		ProductVO result = productService.detail(productVO);
		model.addAttribute("vo", result);
		
		System.out.println("products/detail");
	}
	
	@GetMapping("add")
	public String add() {
		System.out.println("products/add - Get");
		
		return "products/product_form";
	}
	
	@PostMapping("add")
	public String add(ProductVO productVO, Model model) throws Exception {
		int result = productService.add(productVO);
		String url = "./list";
		String msg = "등록 실패";
		
		if(result != 0) {
			msg = "등록 성공";
			System.out.println("products/add - Post: 성공");
		}
		else {
			System.out.println("products/add - Post: 실패");
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		
		return "commons/result";
	}
	
	@GetMapping("update")
	public String update(ProductVO productVO, Model model) throws Exception {
		ProductVO result = productService.detail(productVO);
		model.addAttribute("vo", result);
		
		System.out.println(result);
		
		System.out.println("products/update - Get");
		
		return "products/product_form";
	}
	
//	@PostMapping("update")
//	public String update(ProductVO productVO, Model model, String str) throws Exception {
	@PostMapping("update")
	public ModelAndView update(ProductVO productVO, Model model, String str) throws Exception {
		int result = productService.update(productVO);
		String url = "./list";
		String msg = "수정 실패";
		
		if(result != 0) {
			msg = "수정 성공";
			System.out.println("products/update - Post: 성공");
		}
		else {
			System.out.println("products/update - Post: 실패");
		}
		
//		model.addAttribute("url", url);
//		model.addAttribute("msg", msg);

//		return "commons/result";
		
		// ModelAndView를 사용하는 방법
		ModelAndView mv = new ModelAndView(); // 객체를 생성하지 않고 매개변수로 받아올 수도 있음
		mv.addObject("msg", msg);
		mv.addObject("url", "./list");
		
		mv.setViewName("commons/result");
		
		return mv;
	}
	
	@PostMapping("delete")
	public String delete(ProductVO productVO, Model model) throws Exception {
		int result = productService.delete(productVO);
		String url = "./list";
		String msg = "삭제 실패";
		
		if(result != 0) {
			msg = "삭제 성공";
			System.out.println("products/delete - Post: 성공");
		}
		else {
			System.out.println("products/delete - Post: 실패");
		}
		
		model.addAttribute("url", url);
		model.addAttribute("msg", msg);
		
		return "commons/result";
	}
}
