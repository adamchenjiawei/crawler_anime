package test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class SearchNameTest {
	public static void main(String[] args) throws Exception {
		HttpGet get = new HttpGet("http://www.chuixue.com");
		CloseableHttpClient createDefault = HttpClients.createDefault();
		
		CloseableHttpResponse response = createDefault.execute(get);
		if(response!=null){
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(entity,"GBK"));
			InputStream content = entity.getContent();
			InputStreamReader inputStreamReader = new InputStreamReader(content);
			
			FileWriter writer = new FileWriter("D://Demo//user.txt",true);
		}
	}
}
