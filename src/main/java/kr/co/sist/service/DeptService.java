package kr.co.sist.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.sist.dao.DeptDAO;
import kr.co.sist.vo.DeptVO;

/**
 * 업무로직(간단한 연산, 암.복호화,,)에 대한 처리, DB사용
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
