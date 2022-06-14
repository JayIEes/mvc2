package kr.co.sist.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sist.service.DeptService;
import kr.co.sist.service.EmpService;
import kr.co.sist.vo.DeptVO;
import kr.co.sist.vo.EmpVO;

/**
 * cmd=L001
 * ����Ǹ� �α��� ������ �̵�.
 * �ܼ� ������ �̵�
 * �̵��� ��������� �̵� ��ĸ� ����
 * action�� ���� �Ķ���� ó��, ��������, ���������� ����, �̵���� ���� 
 * @author user
 */
public class LoginAction implements Action {

	private String url;
	private boolean forwardFlag;
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�α��� ������ �̵� 
		url="login2/login_form.jsp"; //�������� �빮�ڿ���� windows�� ��ҹ��ڸ� �������� ���ؼ� 
		//�̵���� ���� 
		forwardFlag=true;
	}

	@Override
	public String moveURL() {
		return url;
	}

	@Override
	public boolean forwardFlag() {
		return forwardFlag;
	}

}
