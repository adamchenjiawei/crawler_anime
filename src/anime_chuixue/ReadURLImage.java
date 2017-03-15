package anime_chuixue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import anime_chuixue.entity.AnimeImage;
import anime_chuixue.handler.ChapterJoinAnimeImageResultSetHandler;
import anime_chuixue.utils.JdbcTemplate;

public class ReadURLImage {
	public static InputStream getInputStream(String imageURL,int num){
		InputStream inputStream=null;  
		HttpURLConnection httpURLConnection=null;  
		if(num==2){
			return inputStream;
		}
		try{  
			URL url=new URL(imageURL);  
			if(url!=null){  
				httpURLConnection=(HttpURLConnection) url.openConnection();  
				httpURLConnection.setConnectTimeout(3000);  
				httpURLConnection.setRequestMethod("GET");  
				int responseCode = httpURLConnection.getResponseCode();  
				if(responseCode==200){  
					inputStream=httpURLConnection.getInputStream();  
				}else{
					for (int i = num; i < 3; i++) {
						inputStream = getInputStream(imageURL,i);
						if(inputStream!=null){
							return inputStream;
						}
					}
				}  
			}  
		}catch(Exception e){  
			e.printStackTrace();  
		}  
		return inputStream;  
	}  

	public static void saveImage(AnimeImage anime){  
		InputStream inputStream=getInputStream(anime.getImage_url(),0);
		FileOutputStream fileOutputStream=null;
		if(inputStream==null){
			System.err.println("没有保存成功的image的url:"+anime.getImage_url());
		}else{
			byte[] data=new byte[1024*1024];  
			int len=0;
			try{
				// windows 上路径
//				String dir = "E:\\anime\\"+anime.getChapter().getAnime_name()+"\\pos_"+anime.getChapter().getPosition()+"_"+anime.getChapter().getChapter_name()+"\\";
				//mac 上路径
				String dir = "/Users/adam/Documents/娱乐/漫画/即使如此我还是喜欢你/"+anime.getChapter().getAnime_name()+"/pos_"+anime.getChapter().getPosition()+"_"+anime.getChapter().getChapter_name()+"/";
				File file = new File(dir);
				if(file.exists()){//判断是否有这个文件夹
					fileOutputStream=new FileOutputStream(dir+anime.getCurrent_page()+".jpg");  
					while((len=inputStream.read(data))!=-1){  
						fileOutputStream.write(data,0,len);   
					}  
				}else{
					file.mkdirs();//创建多层目录
					if(file.exists()){
						fileOutputStream=new FileOutputStream(dir+anime.getCurrent_page()+".jpg");  
						while((len=inputStream.read(data))!=-1){  
							fileOutputStream.write(data,0,len);   
						} 
					}
				}
			}catch(Exception e){  
				e.printStackTrace();  
			}finally{  
				try {
					if(inputStream!=null){
						inputStream.close(); 
					}
					if(null!=fileOutputStream){
						fileOutputStream.close();
					}
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		}
	}  

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String anime_name = "即使如此我还是喜欢你";
		String sql ="select * from chapters c join anime_images as a on c.id = a.`chapter_id`  where anime_name = '"+anime_name+"' and position >= 269 order by position asc";
		List<AnimeImage> templateQuery = null;
		templateQuery = JdbcTemplate.templateQuery(sql, new ChapterJoinAnimeImageResultSetHandler());
		System.out.println(templateQuery.size());
		if(null!=templateQuery && templateQuery.size()>0){
			for (AnimeImage anime : templateQuery) {
				System.out.println(anime.getCurrent_page() +"/"+ anime.getChapter().getChapter_name());
				saveImage(anime);
			}
		}
	}
}
