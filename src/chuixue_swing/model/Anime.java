package chuixue_swing.model;

public class Anime {
	private String anime_name;
	private int chapter;
	private String address;
	public String getAnime_name() {
		return anime_name;
	}
	public void setAnime_name(String anime_name) {
		this.anime_name = anime_name;
	}
	public int getChapter() {
		return chapter;
	}
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Anime [anime_name=" + anime_name + ", chapter=" + chapter
				+ ", address=" + address + "]";
	}
	
}
