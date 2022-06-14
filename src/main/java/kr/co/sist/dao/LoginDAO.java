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
 * DB�۾� ����
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
	 * ���̵�� ��й�ȣ�� �Է¹޾� �̸��� ��ȸ�ϴ� �� 
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
			//1. JNDI ��밴ü ����
			//2. DAtaSource ���
			//3. Connection���
				con=dbcp.getConnection();
			//4. ������ ������ü ���
				//dynamic query : deptno�� 0�̶�� ��� �μ� ��������� ��ȸ, �׷��� �ʴٸ� �Էµ� �μ����� ����� ��ȸ
				String selectLogin="select name from test_member where id=? and pass=?";
				pstmt=con.prepareStatement(selectLogin);
				
			//5. ���ε� ���� �� ����
				pstmt.setString(1, lVO.getId());
				pstmt.setString(2, lVO.getPassword());//��ȣȭ�� ����� ����
				
			//6. ������ ���� �� ��� ���
				rs=pstmt.executeQuery();
				
				if(rs.next()) {//���̵�� ��й�ȣ�� ��ġ�ߴ°�?
					name=rs.getString("name");
				} 
			}finally {
				//7. ���� ����
				dbcp.dbClose(rs, pstmt, con);
				
			}
		
		
		return name;
	}
	
}
