package kr.co.sist.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import kr.co.sist.dao.DeptDAO;
import kr.co.sist.dao.EmpDAO;
import kr.co.sist.dao.LoginDAO;
import kr.co.sist.util.cipher.DataDecrypt;
import kr.co.sist.util.cipher.DataEncrypt;
import kr.co.sist.vo.DeptVO;
import kr.co.sist.vo.EmpVO;
import kr.co.sist.vo.LoginVO;

/**
 * 업무로직(간단한 연산, 암.복호화,,)에 대한 처리, DB사용
 * @author user
 */
public class LoginService {

	public String loginProcess(LoginVO lVO){
		String name="";
		
		//비밀번호 MD5 일방향 해쉬로 암호화
		lVO.setPassword(sha(lVO.getPassword()));
		
		//DB작업 수행
		LoginDAO lDAO=LoginDAO.getInstance();
		try {
			name=lDAO.selectLogin(lVO); // 암호화된 이름 또는 ""
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!"".equals(name)) {
			name=dec(name);
		}
		
		System.out.println("----------name is ------>" +dec(name));
		return name;
	}//searchEmp
	
	
	public String dec(String name) {
		
		
			try {
				DataDecrypt dd = new DataDecrypt("test123412341234");
				name=dd.decryption(name);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}catch(NoSuchAlgorithmException ne) {
				ne.printStackTrace();
			}catch(GeneralSecurityException e) {
				e.printStackTrace();
			}
		//aaaaaaaaaaaaaaaa123
			return name;
	}
	

	public String sha(String password) {
		String data="";
		try {
			data=DataEncrypt.messageDigest("MD5", password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return data;
	}//sha
}
