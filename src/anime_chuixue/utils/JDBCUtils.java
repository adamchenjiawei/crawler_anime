package anime_chuixue.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class JDBCUtils {
	public JDBCUtils(){}
	private static DataSource dataSource;
	
	static{
		try{
			Properties p = new Properties();
			InputStream inputStream 
				= Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
			p.load(inputStream);
			dataSource = BasicDataSourceFactory.createDataSource(p);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConn(){
		try {
			return dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs){
		try {
			if(rs !=null){
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(ps != null){
					ps.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if(conn != null){
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
