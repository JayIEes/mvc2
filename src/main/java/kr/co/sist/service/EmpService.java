package kr.co.sist.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.sist.dao.DeptDAO;
import kr.co.sist.dao.EmpDAO;
import kr.co.sist.vo.DeptVO;
import kr.co.sist.vo.EmpVO;

/**
 * ��������(������ ����, ��.��ȣȭ,,)�� ���� ó��, DB���
 * @author user
 */
public class EmpService {

	public List<EmpVO> searchEmp(int deptno){
		List<EmpVO> list=null;
		
		EmpDAO eDAO=EmpDAO.getInstance();
		
		try {
			list=eDAO.selectEmp(deptno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}//searchEmp
	
	private int chkDeptno(int deptno) {
		if(deptno<0) {
			deptno=0;
		}
		
		return deptno;
	}
	
	
}
