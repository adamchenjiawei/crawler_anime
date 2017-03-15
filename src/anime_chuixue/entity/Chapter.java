package anime_chuixue.entity;

public class Chapter {

	private int id;
	private String anime_name; //漫画名称
	private String chapter_name; //章节名
	private int position; //位置
	private String url; 
	private String hash_url;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnime_name() {
		return anime_name;
	}
	public void setAnime_name(String anime_name) {
		this.anime_name = anime_name;
	}
	public String getChapter_name() {
		return chapter_name;
	}
	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHash_url() {
		return hash_url;
	}
	public void setHash_url(String hash_url) {
		this.hash_url = hash_url;
	}
	
}
