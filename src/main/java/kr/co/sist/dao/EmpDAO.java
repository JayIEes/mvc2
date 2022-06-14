package kr.co.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.co.sist.vo.DeptVO;
import kr.co.sist.vo.EmpVO;

/**
 * DB�۾��� ���� 
 * @author user
 */
public class EmpDAO {

	private static EmpDAO eDAO;
	
	private EmpDAO() {
		
	}
	
	public static EmpDAO getInstance() {
		if(eDAO==null) {
			eDAO=new EmpDAO();
		}//end if
		
		return eDAO;
	}//getInstance
	
	
	public List<EmpVO> selectEmp(int deptno) throws SQLException{
		
		List<EmpVO> list=new ArrayList<EmpVO>();
		
		DBCPConnection dbcp=DBCPConnection.getInstance();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			//1. JNDI ��밴ü ����
			//2. DAtaSource ���
			//3. Connection���
				con=dbcp.getConnection();
			//4. ������ ������ü ���
				//dynamic query : deptno�� 0�̶�� ��� �μ� ��������� ��ȸ, �׷��� �ʴٸ� �Էµ� �μ����� ����� ��ȸ
				StringBuilder selectEmp=new StringBuilder();
				
				selectEmp
				.append(" select empno,ename,mgr,sal,comm,deptno,hiredate,job ")
				.append(" from emp");
				
				if(deptno!=0) {
					selectEmp.append("	where deptno=?	");
				}
				
				selectEmp.append("	order by empno");
				
				pstmt=con.prepareStatement(selectEmp.toString());
			//5. ���ε� ���� �� ����
				if(deptno!=0) {
					pstmt.setInt(1,deptno);
				}
				
			//6. ������ ���� �� ��� ���
				rs=pstmt.executeQuery();
				
				EmpVO eVO=null;
				
				while(rs.next()) {
					eVO=new EmpVO(rs.getInt("empno"),rs.getInt("mgr"),rs.getInt("sal"),
							rs.getInt("comm"),rs.getInt("deptno"),rs.getString("ename"),
							rs.getString("job"),rs.getDate("hiredate"));
					list.add(eVO);
				} 
			}finally {
				//7. ���� ����
				dbcp.dbClose(rs, pstmt, con);
				
			}
		
		return list;
		
	}//selectEmp
	
}






