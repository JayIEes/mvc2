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
 * 실행되면 입력된 아이디, 비번 받아서 로그인에 대한 작업 수행
 * action은 웹의 파라메터 처리, 관계유지, 뷰페이지를 설정, 이동방식 설정 
 * @author user
 */
public class LoginProcessAction implements Action {

	private String url;
	private boolean forwardFlag;
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//아이디와 비번을 받는다.
		String id=request.getParameter("id");
		String pass=request.getParameter("passwd"); 
		
		LoginVO lVO=new LoginVO(id,pass);
		
		//Service 객체 사용
		LoginService ls=new LoginService();
		String name=ls.loginProcess(lVO);//복호화된 이름이 나온다.
		
		//아이디와 비밀번호를 세션에 넣기
		HttpSession session=request.getSession();
		session.setAttribute("sessionName", name);
		session.setAttribute("sessionId", lVO.getId());
		
		//로그인 결과창으로 이동 
		url="login2/login_result.jsp";
		//이동방식 설정 
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
