package anime_chuixue.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import anime_chuixue.handler.ResultSetHandler;

public class JdbcTemplate {
	//私有化构造器，不能直接new
	private JdbcTemplate(){}

	/**
	 * 数据库的DML操作------------------增 删 改
	 * @param sql  操作数据库的语句，由调用者决定
	 * @param params DML操作需要的参数，由调用者决定(可变参数要放在最后)
	 * @return      受影响的行数
	 */
	public static int templateDML(String sql,Object...params){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement(sql);
			for(int index = 0; index < params.length;index++){
				ps.setObject(index+1, params[index]);
			}
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, ps, null);
		}
		return 0;
	}
	
	/**
	 * 	查询DQL操作
	 * @param sql 需要执行的sql'语句
	 * @param rsh 结果集处理器
	 * @param params 操作需要的参数，由调用者决定(可变参数要放在最后)
	 * @return 返回查询的结果集
	 */
	@SuppressWarnings({ "rawtypes" })
	public static List templateQuery(String sql,ResultSetHandler rsh,Object...params){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs  = null;
		try {
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement(sql);
			for(int index = 0; index< params.length; index++){
				ps.setObject(index + 1, params[index]);
			}
			rs = ps.executeQuery();
			return rsh.handler(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
		//static <T> List<T> emptyList() 返回空的列表（不可变的）。 
		return Collections.emptyList();
	}

}
