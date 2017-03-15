package chuixue_swing;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import chuixue_swing.model.Anime;
import chuixue_swing.util.CommonUtil;


public class CopyOfChuiXue_Anime {
	
	public static void main(String[] args) {
		try {

			File file = new File("./load/search.html");
			Document doc = Jsoup.parse(file, "gbk");
			Element body = doc.body();
			String anime_name = "白兔糖";
			Element content = body.getElementById("dmList");
//			Elements links = content.getElementsByTag("a");
			
			Elements links = content.select("ul li dl dt a");
			for(Element link : links){
				System.out.println(link.attr("href"));	
				System.out.println(link.attr("title"));		
			}
//			Anime anime = new Anime();
//			for (Element link : links) {
//				String linkHref = link.attr("href");
//				String linkText = link.text();
//				if(anime_name.equalsIgnoreCase(linkText)){
//					anime.setAnime_name(linkText);
//					anime.setAddress(CommonUtil.url+linkHref);
//				}
//				System.out.println("linkHref:"+linkHref+"  ,linkText:"+linkText);
//			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
