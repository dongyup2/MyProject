package jdbc;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBcon {
	
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			
			InitialContext initCtx = new InitialContext();
			Context ctx = (Context) initCtx.lookup("java:comp/env");
			DataSource source = (DataSource) ctx.lookup("dbcp.mydb");

			// 커넥션 풀을 통해 커넥션 객체 얻기
			con = source.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
