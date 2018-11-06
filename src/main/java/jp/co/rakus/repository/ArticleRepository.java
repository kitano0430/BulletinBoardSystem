package jp.co.rakus.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.domain.Article;

/**
 * 記事リポジトリ.
 * 
 * @author maiko.kitano
 *
 */
@Repository
public class ArticleRepository {

	private static final RowMapper<Article> ARTICLEROWMAPPER = (rs, i) -> {

		Article article = new Article();

		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));

		return article;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 全件検索するSQL
	 * 
	 * @return 記事リスト
	 */
	public List<Article> findAll() {

		String sql = "SELECT id,name,content FROM articles ORDER BY id DESC";

		List<Article> articleList = template.query(sql, ARTICLEROWMAPPER);

		return articleList;
	}

	/**
	 * 記事を投稿するSQL
	 * 
	 * @param aritcle 記事
	 */
	public void insert(Article aritcle) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(aritcle);
		String insertSql = "INSERT INTO articles(name,content)" + "VALUES (:name,:content)";
		template.update(insertSql, param);

	}
	

	/**
	 * 記事を削除するSQL
	 * 
	 * @param articleId 記事id
	 */
	public void deleteById(int articleId) {
		String deleteSql = " DELETE FROM articles WHERE id =:id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", articleId);
		template.update(deleteSql, param);

	}

}
