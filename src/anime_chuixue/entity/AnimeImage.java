package anime_chuixue.entity;

public class AnimeImage {

	private int id;
	private int current_page; //当前页数
	private int total_page; //总页数
	private String image_url; //图片路径
	private int chapter_id;
	private String hash_url;
	private Chapter chapter;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCurrent_page() {
		return current_page;
	}
	public void setCurrent_page(int current_page) {
		this.current_page = current_page;
	}
	public int getTotal_page() {
		return total_page;
	}
	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public int getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(int chapter_id) {
		this.chapter_id = chapter_id;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public String getHash_url() {
		return hash_url;
	}
	public void setHash_url(String hash_url) {
		this.hash_url = hash_url;
	}
	
	
}
