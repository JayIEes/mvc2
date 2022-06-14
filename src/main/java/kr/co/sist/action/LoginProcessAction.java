package kr.co.sist.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.sist.service.DeptService;
import kr.co.sist.service.EmpService;
import kr.co.sist.service.LoginService;
import kr.co.sist.vo.DeptVO;
import kr.co.sist.vo.EmpVO;
import kr.co.sist.vo.LoginVO;

/**
 * cmd=L002
 * ����Ǹ� �Էµ� ���̵�, ��� �޾Ƽ� �α��ο� ���� �۾� ����
 * action�� ���� �Ķ���� ó��, ��������, ���������� ����, �̵���� ���� 
 * @author user
 */
public class LoginProcessAction implements Action {

	private String url;
	private boolean forwardFlag;
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���̵�� ����� �޴´�.
		String id=request.getParameter("id");
		String pass=request.getParameter("passwd"); 
		
		LoginVO lVO=new LoginVO(id,pass);
		
		//Service ��ü ���
		LoginService ls=new LoginService();
		String name=ls.loginProcess(lVO);//��ȣȭ�� �̸��� ���´�.
		
		//���̵�� ��й�ȣ�� ���ǿ� �ֱ�
		HttpSession session=request.getSession();
		session.setAttribute("sessionName", name);
		session.setAttribute("sessionId", lVO.getId());
		
		//�α��� ���â���� �̵� 
		url="login2/login_result.jsp";
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
