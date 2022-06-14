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
 * DB작업만 수행 
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
			//1. JNDI 사용객체 생성
			//2. DAtaSource 얻기
			//3. Connection얻기
				con=dbcp.getConnection();
			//4. 쿼리문 생성객체 얻기
				//dynamic query : deptno가 0이라면 모든 부서 사원정보를 조회, 그렇지 않다면 입력된 부서정보 사원을 조회
				StringBuilder selectEmp=new StringBuilder();
				
				selectEmp
				.append(" select empno,ename,mgr,sal,comm,deptno,hiredate,job ")
				.append(" from emp");
				
				if(deptno!=0) {
					selectEmp.append("	where deptno=?	");
				}
				
				selectEmp.append("	order by empno");
				
				pstmt=con.prepareStatement(selectEmp.toString());
			//5. 바인드 변수 값 설정
				if(deptno!=0) {
					pstmt.setInt(1,deptno);
				}
				
			//6. 쿼리문 수행 후 결과 얻기
				rs=pstmt.executeQuery();
				
				EmpVO eVO=null;
				
				while(rs.next()) {
					eVO=new EmpVO(rs.getInt("empno"),rs.getInt("mgr"),rs.getInt("sal"),
							rs.getInt("comm"),rs.getInt("deptno"),rs.getString("ename"),
							rs.getString("job"),rs.getDate("hiredate"));
					list.add(eVO);
				} 
			}finally {
				//7. 연결 끊기
				dbcp.dbClose(rs, pstmt, con);
				
			}
		
		return list;
		
	}//selectEmp
	
}






