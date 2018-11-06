package jp.co.rakus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.domain.Article;
import jp.co.rakus.domain.Comment;
import jp.co.rakus.form.CommentForm;
import jp.co.rakus.repository.ArticleRepository;
import jp.co.rakus.repository.CommentRepository;

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

	@Autowired
	private CommentRepository commentRepository;

	@ModelAttribute
	public CommentForm setUpCommentform() {
		return new CommentForm();

	}

	/**
	 * 記事投稿
	 * 
	 * @param model モデル
	 * @return 記事投稿画面
	 */
	@RequestMapping("/index")
	public String index(Model model) {

		List<Article> articleList = articleRepository.findAll();

		for (Article article : articleList) {

			Integer articleId = article.getId();

			List<Comment> commentList = commentRepository.findByArticleId(articleId);
			article.setCommentList(commentList);
		}

		model.addAttribute("articleList", articleList);
		return "bbs";

	}

	/**
	 * 掲示板に記事を反映
	 * 
	 * @param model   モデル
	 * @param name    名前
	 * @param content コンテント
	 * @return 掲示板
	 *
	 */
	@RequestMapping("/addArticle")
	public String addArticle(Model model, String name, String content) {

		Article article = new Article();
		article.setName(name);
		article.setContent(content);
		articleRepository.insert(article);

		return "redirect:/Article/index";
	}

	/**
	 * コメントを表示
	 * 
	 * @param model　モデル
	 * @return　掲示板
	 */
	@RequestMapping("/viewComment")
	public String viewComment(Model model) {

		List<Comment> commentList = commentRepository.findByArticleId(1);
		model.addAttribute("commentList", commentList);

		return "bbs";

	}

	/**
	 * コメント投稿
	 * 
	 * @param commentForm　コメントフォーム
	 * @param model　モデル
	 * @return　掲示板
	 */
	@RequestMapping("/addComment")
	public String addComment(CommentForm commentForm, Model model) {

		int valueOfArticleId = Integer.parseInt(commentForm.getArticlelId());

		Comment comment = new Comment();
		comment.setArticleId(valueOfArticleId);
		comment.setName(commentForm.getName());
		comment.setContent(commentForm.getContent());

		commentRepository.insert(comment);

		return "redirect:/Article/index";

	}

	/**
	 * 記事削除
	 * 
	 * @param articlelId　記事id
	 * @param model モデル
	 * @return　掲示板
	 */
	@RequestMapping("/deleteComment")
	public String deleteComment(String articlelId, Model model) {

		int valueOfArticleId = Integer.parseInt(articlelId);

		commentRepository.deleteByArticleId(valueOfArticleId);
		articleRepository.deleteById(valueOfArticleId);

		return "redirect:/Article/index";

	}

}
