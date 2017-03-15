package anime_chuixue.handler;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import anime_chuixue.entity.Chapter;

public class ChapterResultSetHandler implements ResultSetHandler{

	public List<Chapter> handler(ResultSet rs) throws Exception {
		List<Chapter> list = new ArrayList<>();
		Chapter chapter = null;
		while(rs.next()){
			chapter = new Chapter();
			chapter.setId(rs.getInt("id"));
			chapter.setAnime_name(rs.getString("anime_name"));
			chapter.setChapter_name(rs.getString("chapter_name"));
			chapter.setPosition(rs.getInt("position"));
			chapter.setUrl(rs.getString("url"));
			chapter.setHash_url(rs.getString("hash_url"));
			list.add(chapter);
		}
		return list;
	}

}
