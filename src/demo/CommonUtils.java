package demo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class CommonUtils implements PageProcessor{
	private String webSite;//需要爬取的网站
	private String listAll;//需要的地址(正则)
	private String content;//需要的内容(正则)
	private String xpathProperty;//(是否xpath查找)
	private String xpathContent;
	
	private String result;
	public CommonUtils(String webSite,String listAll,String content){
		this.webSite = webSite;
		this.listAll = listAll;
		this.content = content;
	}
	public CommonUtils(String webSite,String listAll,String content,String xpathProperty,String xpathContent){
		this.webSite = webSite;
		this.listAll = listAll;
		this.content = content;
		this.xpathProperty = xpathProperty;
		this.xpathContent = xpathContent;
	}
	
	private Site site = Site.me().addStartUrl(webSite);
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		if(page.getUrl().regex(listAll).match()){//判断当前抓取的是否是入口url（正则）
			List<String> links = null;
			if(StringUtils.isNotBlank(xpathProperty)){
				//通过xpath匹配 （验证xpath 可以使用 firefox的插件 path Checker）
				links = page.getHtml().xpath(xpathProperty).all();
			}else{
				//通过正则匹配出url链接 (快捷但是如果所需的url格式不统一时不是很好获取url)
				links = page.getHtml().links().regex("").all();
			}
			//将链接放入待抓取列表
			page.addTargetRequests(links);
		}else if(page.getUrl().regex(content).match()){//判断是否是需要的内容的url
			//处理需要抓取的内容
			String result = page.getHtml().xpath(xpathContent).toString();
			System.out.println(result);
		}
	}
	
	public static void main(String[] args) {
		String webSite = "http://www.autohome.com.cn/3060/0/0-0-1-0/";
		String listAll = "http://www\\.autohome\\.com\\.cn/\\d+/\\d+/0-0-1-0\\/";
		String content = "http://www\\.autohome\\.com\\.cn/\\w+/\\d+/\\d+\\.html";
		String xpathProperty = "//div[@class='newpic']//a/@href";
		String xpathContent ="//div[@id='articlewrap']/h1/text()";
//		CommonUtils commonUtils = new CommonUtils(webSite, listAll, content, xpathProperty, xpathContent);
//		System.out.println(commonUtils);
//		if(commonUtils!=null){
			Spider.create(new CommonUtils(webSite, listAll, content, xpathProperty, xpathContent)).run();
//		}
	}
	
	@Override
	public String toString() {
		return "CommonUtils [webSite=" + webSite + ", listAll=" + listAll
				+ ", content=" + content + ", xpathProperty=" + xpathProperty
				+ ", xpathContent=" + xpathContent + ", result=" + result
				+ ", site=" + site + "]";
	}
	
}
