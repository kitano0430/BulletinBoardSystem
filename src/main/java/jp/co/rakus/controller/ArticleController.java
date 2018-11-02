package jp.co.rakus.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.domain.Article;
import jp.co.rakus.repository.ArticleRepository;

/**
 * 記事の投稿、削除を行うコントローラー.
 * 
 * @author maiko.kitano
 *
 */
@Controller
@RequestMapping("/Article")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	/**
	 * 記事投稿
	 * 
	 * @param model　モデル
	 * @return　記事投稿画面
	 */
	@RequestMapping("/index")
	public String index(Model model) {
	
		List<Article> articleList = articleRepository.findAll();
		
		model.addAttribute("articleList",articleList);
		
		return "bbs";
	}
	/**
	 * 掲示板に記事を反映
	 * 
	 * @param model　モデル
	 * @param name　名前
	 * @param content　コンテント
	 * @return　掲示板
	 *
	 */
	@RequestMapping("/addArticle")
	public String  addArticle(Model model,String name,String content){
		
		Article article = new Article();
		article.setName(name);
		article.setContent(content);
		articleRepository.insert(article);
		
		return index(model);
	}
//	@RequestMapping("/viewComment")
//	public String viewComment(Model model) {
		
	//	Comment comment = new Comment();
	//	articleRepository.findByArticleId(1);	
		
	//}

	

}
