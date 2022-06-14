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
 * ��������(������ ����, ��.��ȣȭ,,)�� ���� ó��, DB���
 * @author user
 */
public class LoginService {

	public String loginProcess(LoginVO lVO){
		String name="";
		
		//��й�ȣ MD5 �Ϲ��� �ؽ��� ��ȣȭ
		lVO.setPassword(sha(lVO.getPassword()));
		
		//DB�۾� ����
		LoginDAO lDAO=LoginDAO.getInstance();
		try {
			name=lDAO.selectLogin(lVO); // ��ȣȭ�� �̸� �Ǵ� ""
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
