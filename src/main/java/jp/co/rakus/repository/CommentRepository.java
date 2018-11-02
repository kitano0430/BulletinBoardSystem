package jp.co.rakus.repository;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.rakus.domain.Comment;


@Repository
public class CommentRepository {
	
	private static final RowMapper<Comment> COMMENTROWMAPPER = (rs, i) -> {
		Comment comment = new Comment();
		comment.setId(rs.getInt("id"));
		comment.setName(rs.getString("name"));
		comment.setContent(rs.getString("content"));
		comment.setArticleId(rs.getInt("article_id"));

		return comment;
	};
	
	private NamedParameterJdbcTemplate template;
	
	public List<Comment> findByArticleId(int articleId){
		
		String sql = "SELECT id,name,content,article_id FROM comments WHERE article_id=:id ORDER BY article_id ;";
		
		List<Comment> commentList = template.query(sql, COMMENTROWMAPPER);
		
		return commentList;
	}
	

	public void insert(Comment comment) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		String insertSql = "INSERT INTO comments(id,name,content,article_id)" + "VALUES (:id,:name,:content,:article_id)";
		template.update(insertSql, param);
	}

	public void deleteByArticleId(Comment articleId) {
		String deleteSql = "DELETE FROM comments WHERE article_id =:article_id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(deleteSql, param);

	}
	
	

}
