package kr.co.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.vo.DeptVO;

public class DeptDAO {
	
	private static DeptDAO dDAO;
	
	private DeptDAO() {
		
	}
	
	public static DeptDAO getInstance() {
		if(dDAO==null) {
			dDAO=new DeptDAO();
		}//end if
		
		return dDAO;
	}//getInstance
	
	
	public List<DeptVO> selectAllDept() throws SQLException{
		
		List<DeptVO> list=new ArrayList<DeptVO>();
		
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
			String sql="select deptno,dname,loc from dept order by deptno desc";
			pstmt=con.prepareStatement(sql);
		//5. 바인드 변수 값 설정
		//6. 쿼리문 수행 후 결과 얻기
			rs=pstmt.executeQuery();
			DeptVO dVO=null;
			while(rs.next()) {
				dVO=new DeptVO(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc"));
				list.add(dVO);
			}
		}finally {
			//7. 연결 끊기
			dbcp.dbClose(rs, pstmt, con);
			
		}
		
		
		return list;
	}//selectAllDept
}








