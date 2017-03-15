package chuixue_swing.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.DigestUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import anime_chuixue.entity.AnimeImage;
import anime_chuixue.entity.Chapter;
import anime_chuixue.handler.AnimeImageResultSetHandler;
import anime_chuixue.handler.ChapterResultSetHandler;
import anime_chuixue.utils.CommonUtils;
import anime_chuixue.utils.JdbcTemplate;

@SuppressWarnings("deprecation")
public class ChuiXue_Anime implements PageProcessor{
	//白兔糖
	private String url = "http://www.chuixue.com/manhua/1878/";
	
	private Site site = Site.me()
			.setDomain("www.chuixue.com")
			.setSleepTime(800)
			.setTimeOut(10000)
			.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
			.setCycleRetryTimes(5)
			.addStartUrl(url);
	public static final String LIST_All = "^http://www\\.chuixue\\.com/\\w+/\\d+/$";
	public static final String ARTICLE  = "http://www\\.chuixue\\.com/\\w+/\\d+/\\d+\\.html";

	@Override
	public Site getSite() {
		return site;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void process(Page page) {
		if(page.getUrl().regex(LIST_All).match()){
			List<String> chapter_links = page.getHtml().xpath("//div[@id='play_0']//a/@href").all();
			List<String> chapter_names = page.getHtml().xpath("//div[@id='play_0']//a/text()").all();
			String anime_name = page.getHtml().xpath("//div[@class='title']/h1/text()").toString();
            for(int i = 0; i < chapter_links.size(); i++){
            	String chapter_name = chapter_names.get(i);
            	String chapter_link = chapter_links.get(i);
            	int position =  chapter_links.size() - i;
            	String hash_url = DigestUtils.shaHex(chapter_link);
            	
            	String sql_chapter ="SELECT * FROM chapters where hash_url='"+hash_url+"'";
    			List<Chapter> templateQuery = null;
    			templateQuery = JdbcTemplate.templateQuery(sql_chapter, new ChapterResultSetHandler());
            	if (templateQuery.size() == 0){
                	String sql = "INSERT INTO chapters VALUES(null,?,?,?,?,?)";
                	JdbcTemplate.templateDML(sql, anime_name, chapter_name, position, chapter_link, hash_url);
            	}
            }
			page.addTargetRequests(chapter_links);
		}else if(page.getUrl().regex(ARTICLE).match()){
			System.out.println(page.getUrl());
			String hash_url = DigestUtils.shaHex(page.getUrl().toString());
			String name = page.getHtml().xpath("//div/h1/a/text()").toString();
			System.out.println("名称:"+name);
			String chapter = page.getHtml().xpath("//div/h2/text()").toString();
			System.out.println("章节:"+chapter);
			//获取chapter ID
			String sql_chapter ="SELECT * FROM chapters where hash_url='"+hash_url+"'";
			List<Chapter> templateQuery = null;
			templateQuery = JdbcTemplate.templateQuery(sql_chapter, new ChapterResultSetHandler());
			int chapter_id = templateQuery.get(0).getId();
			
			String regExp = "var qTcms_S_m_murl_e=\".*\\\";var qTcms_S_m_murl_e2=?";
			Pattern pattern = Pattern.compile(regExp);
			Matcher matcher = pattern.matcher(page.getHtml().toString());
			boolean findRet = matcher.find();
			if(findRet){
				String all = matcher.group(0).replace("\";var qTcms_S_m_murl_e2=", "").replace("var qTcms_S_m_murl_e=\"", "");
				String[] imageUrl = CommonUtils.base64_decode(all).split("\\$qingtiandy\\$");
				int totalPage = imageUrl.length;
				System.out.println("该章节总共"+totalPage+"页");
				for (int currentPage=1;currentPage<=totalPage;currentPage++) {
					System.out.println("第"+ currentPage+"页："+imageUrl[currentPage-1]);
					//TODO
					//存入数据库
				   	String image_hash_url = DigestUtils.shaHex(imageUrl[currentPage-1]);
	            	String sql_anime_image ="SELECT * FROM anime_images where hash_url='"+image_hash_url+"'";
	    			List<AnimeImage> templateQuery_image = null;
	    			templateQuery_image = JdbcTemplate.templateQuery(sql_anime_image, new AnimeImageResultSetHandler());
	            	if (templateQuery_image.size() == 0){
	    				String sql = "INSERT INTO anime_images VALUES(null,?,?,?,?,?)";
	                	JdbcTemplate.templateDML(sql, currentPage,totalPage,imageUrl[currentPage-1], chapter_id, image_hash_url);
	            	}
				}
			}else{
				System.out.println("error");
			}
		}else{
			System.out.println("URL 匹配出错！！！！！！！！！！！！！！！！！！！！！");
		}
	}
	public static void main(String[] args) {
		Spider.create(new ChuiXue_Anime()).thread(5).run();
	}
}
