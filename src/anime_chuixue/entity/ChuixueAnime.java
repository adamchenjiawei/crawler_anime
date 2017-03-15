package anime_chuixue.entity;

public class ChuixueAnime {
	private String id;
	private String animeName;//名称
	private String chapter;//章节
	private int currentPage;//当前页码
	private int totalPage;//总页码
	private String imageUrl;//图片路径
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAnimeName() {
		return animeName;
	}
	public void setAnimeName(String animeName) {
		this.animeName = animeName;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
