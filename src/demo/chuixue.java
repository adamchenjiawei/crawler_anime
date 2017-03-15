package demo;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@SuppressWarnings("deprecation")
public class chuixue implements PageProcessor{
	
	private Site site = Site.me().setDomain("www.autohome.com.cn").addStartUrl("http://www.autohome.com.cn/3060/0/0-0-1-0/");
	public static final String LIST_All = "http://www\\.autohome\\.com\\.cn/\\d+/\\d+/0-0-1-0\\/";
	public static final String ARTICLE  = "http://www\\.autohome\\.com\\.cn/\\w+/\\d+/\\d+\\.html";
	
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		//判断当前抓取的是否是入口url（正则）
		if(page.getUrl().regex(LIST_All).match()){
			
	    //获取所需要的所有url链接
		//第一步 先从起始的url抓取， 这是汽车之家的文章列表 抓取列表
		//第一种方法获取列表 通过正则匹配出url链接 (快捷但是如果所需的url格式不统一时不是很好获取url)
		List<String> links = page.getHtml().links().regex("http://www.autohome.com.cn/\\w+/\\d+/\\d+.html").all();
		//第二种方法通过xpath匹配 （验证xpath 可以使用 firefox的插件 path Checker）
		List<String> links_two = page.getHtml().xpath("//div[@class='newpic']//a/@href").all();
		//将链接放入待抓取列表
		page.addTargetRequests(links_two);
		
		//判断是否是文章的url
		}else if(page.getUrl().regex(ARTICLE).match()){
		//处理需要抓取的内容
	      String title = page.getHtml().xpath("//div[@id='articlewrap']/h1/text()").toString();
		
	      //存储
	      System.out.println("title:"+title);
		}
		
		
	}
	
	public static void main(String[] args) {
		Spider.create(new chuixue()).run();
	}

}
