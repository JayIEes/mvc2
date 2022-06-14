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
		//1. JNDI ��밴ü ����
		//2. DAtaSource ���
		//3. Connection���
			con=dbcp.getConnection();
		//4. ������ ������ü ���
			String sql="select deptno,dname,loc from dept order by deptno desc";
			pstmt=con.prepareStatement(sql);
		//5. ���ε� ���� �� ����
		//6. ������ ���� �� ��� ���
			rs=pstmt.executeQuery();
			DeptVO dVO=null;
			while(rs.next()) {
				dVO=new DeptVO(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc"));
				list.add(dVO);
			}
		}finally {
			//7. ���� ����
			dbcp.dbClose(rs, pstmt, con);
			
		}
		
		
		return list;
	}//selectAllDept
}








