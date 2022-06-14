package kr.co.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DBCPConnection {

	private static DBCPConnection fDAO;
	
	
	private DBCPConnection() {
		
	}
	
	
	public static DBCPConnection getInstance() {
		if(fDAO==null) {
			fDAO=new DBCPConnection();
		}//end if
		return fDAO;
	}//getInstance
	
	
	
	public Connection getConnection() throws SQLException{
		Connection con=null;
		
		try {
		//1. JNDI 사용 객체 생성
		Context ctx=new InitialContext();
		
		//2. DBCP를 찾아 DataSource 얻기
		DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/mvc2");
		
		//3. 커넥션 얻기
		con=ds.getConnection();
		
		}catch(NamingException ne) {
			ne.printStackTrace();
		}//end catch
		
		return con;
	}//getConnection
	
	
	
	public void dbClose(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException{
		if(rs!=null) {rs.close();}//end if
		if(pstmt!=null) {pstmt.close();}//end if
		if(con!=null) {con.close();}//end if
	}//dbClose
	
}//class





