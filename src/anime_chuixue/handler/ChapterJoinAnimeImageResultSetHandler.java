package anime_chuixue.handler;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import anime_chuixue.entity.AnimeImage;
import anime_chuixue.entity.Chapter;

public class ChapterJoinAnimeImageResultSetHandler implements ResultSetHandler{

	public List<AnimeImage> handler(ResultSet rs) throws Exception {
		List<AnimeImage> list = new ArrayList<>();
		AnimeImage anime_image = null;
		while(rs.next()){
			anime_image = new AnimeImage();
			anime_image.setId(rs.getInt("id"));
			anime_image.setCurrent_page(rs.getInt("current_page"));
			anime_image.setTotal_page(rs.getInt("total_page"));
			anime_image.setImage_url(rs.getString("image_url"));
			anime_image.setChapter_id(rs.getInt("chapter_id"));
			anime_image.setHash_url(rs.getString("hash_url"));
			Chapter chapter = new Chapter();
			chapter.setAnime_name(rs.getString("anime_name"));
			chapter.setChapter_name(rs.getString("chapter_name"));
			chapter.setPosition(rs.getInt("position"));
			chapter.setUrl(rs.getString("url"));
			anime_image.setChapter(chapter);
			list.add(anime_image);
		}
		return list;
	}

}
