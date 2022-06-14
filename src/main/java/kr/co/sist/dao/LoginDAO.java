package kr.co.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.util.cipher.DataDecrypt;
import kr.co.sist.vo.EmpVO;
import kr.co.sist.vo.LoginVO;

/**
 * @author user
 * DB작업 수행
 */
public class LoginDAO {

	private static LoginDAO lDAO;
	
	private LoginDAO() {
		
	}
	
	
	public static LoginDAO getInstance() {
		if(lDAO==null) {
			lDAO=new LoginDAO();
		}//end if
		
		return lDAO;
	}
	
	
	/**
	 * 아이디와 비밀번호를 입력받아 이름을 조회하는 일 
	 * @param lVO
	 * @return
	 * @throws SQLException
	 */
	public String selectLogin(LoginVO lVO) throws SQLException {

		String name="";
		
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DBCPConnection dbcp=DBCPConnection.getInstance();
		try {
			//1. JNDI 사용객체 생성
			//2. DAtaSource 얻기
			//3. Connection얻기
				con=dbcp.getConnection();
			//4. 쿼리문 생성객체 얻기
				//dynamic query : deptno가 0이라면 모든 부서 사원정보를 조회, 그렇지 않다면 입력된 부서정보 사원을 조회
				String selectLogin="select name from test_member where id=? and pass=?";
				pstmt=con.prepareStatement(selectLogin);
				
			//5. 바인드 변수 값 설정
				pstmt.setString(1, lVO.getId());
				pstmt.setString(2, lVO.getPassword());//암호화된 비번이 전달
				
			//6. 쿼리문 수행 후 결과 얻기
				rs=pstmt.executeQuery();
				
				if(rs.next()) {//아이디와 비밀번호가 일치했는가?
					name=rs.getString("name");
				} 
			}finally {
				//7. 연결 끊기
				dbcp.dbClose(rs, pstmt, con);
				
			}
		
		
		return name;
	}
	
}
