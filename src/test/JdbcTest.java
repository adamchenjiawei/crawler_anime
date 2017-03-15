package test;

import java.sql.Connection;

import anime_chuixue.utils.JDBCUtils;

public class JdbcTest {
	public static void main(String[] args) throws Exception {
		Connection conn = JDBCUtils.getConn();
		System.out.println(conn);
		conn.close();
	}
}
