package anime_chuixue.handler;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import anime_chuixue.entity.ChuixueAnime;

public class AnimeResultSetHandler implements ResultSetHandler{

	public List<ChuixueAnime> handler(ResultSet rs) throws Exception {
		List<ChuixueAnime> list = new ArrayList<>();
		ChuixueAnime anime = null;
		while(rs.next()){
			anime = new ChuixueAnime();
			anime.setId(rs.getString("id"));
			anime.setAnimeName(rs.getString("anime_name"));
			anime.setChapter(rs.getString("chapter"));
			anime.setCurrentPage(rs.getInt("current_page"));
			anime.setTotalPage(rs.getInt("total_page"));
			anime.setImageUrl(rs.getString("image_url"));
			list.add(anime);
		}
		return list;
	}

}
