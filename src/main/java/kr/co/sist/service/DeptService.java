package kr.co.sist.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.sist.dao.DeptDAO;
import kr.co.sist.vo.DeptVO;

/**
 * ��������(������ ����, ��.��ȣȭ,,)�� ���� ó��, DB���
 * @author user
 */
public class DeptService {

	public List<DeptVO> searchDept(){
		List<DeptVO> list=null;
		
		DeptDAO dDAO=DeptDAO.getInstance();
		
		try {
			list=dDAO.selectAllDept();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
}
