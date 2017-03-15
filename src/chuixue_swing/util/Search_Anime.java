package chuixue_swing.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import chuixue_swing.model.Anime;

@SuppressWarnings("deprecation")
public class Search_Anime {
	
	private Search_Anime() {}
	private static Search_Anime instanse = null;
	
	public static Search_Anime getInstanse() {
		if(null==instanse){
			instanse = new Search_Anime();
		}
		return instanse;
	}
	
	private void search(String anime_name){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			//请求地址
			HttpPost httpost = new HttpPost("http://www.chuixue.com/search.asp");
			
			List <NameValuePair> parameter = new ArrayList <NameValuePair>();
			//参数值
			parameter.add(new BasicNameValuePair("key", anime_name));
			//设置表单提交编码
			try {
				httpost.setEntity(new UrlEncodedFormEntity(parameter, "GBK"));
				HttpResponse response = httpclient.execute(httpost);
				
				HttpEntity entity = response.getEntity();
				inputStream = entity.getContent();
				
				fileOutputStream = new FileOutputStream("./load/search.html");
				byte[] b = new byte[1024];
				while((inputStream.read(b)) != -1){
					fileOutputStream.write(b);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally {
			if(inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fileOutputStream!=null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			httpclient.getConnectionManager().shutdown();
		}
	} 
	
	public List<Anime> getAnimeInfo(String anime_name){
		search(anime_name);
		List<Anime> animes = new ArrayList<Anime>();
		try {
			File file = new File("./load/search.html");
			Document doc = Jsoup.parse(file, "gbk");
			Element body = doc.body();
			Element content = body.getElementById("dmList");
			Elements links = content.select("ul li dl dt a");
			Anime anime = null;
			for (Element link : links) {
				anime = new Anime();
				String linkHref = link.attr("href");
				String anime_name_link = link.attr("title");
				anime.setAddress(CommonUtil.url+linkHref);
				anime.setAnime_name(anime_name_link);
				animes.add(anime);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return animes;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		File file=new File("./load/search");
		InputStreamReader read = new InputStreamReader(new FileInputStream(file),"GBK");//考虑到编码格式
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		Anime anime = new Anime();
		while((lineTxt = bufferedReader.readLine()) != null){
			System.out.println(lineTxt);
		}
		read.close();
	}
}
