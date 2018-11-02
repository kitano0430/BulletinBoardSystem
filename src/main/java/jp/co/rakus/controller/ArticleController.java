package jp.co.rakus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.domain.Article;
import jp.co.rakus.repository.ArticleRepository;

@Controller
@RequestMapping("/Article")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@RequestMapping("/index")
	public String index(Model model) {
		
		List<Article> articleList = articleRepository.findAll();
		
		model.addAttribute("articleList",articleList);
		
		
		return "chat";
	}
	

}
