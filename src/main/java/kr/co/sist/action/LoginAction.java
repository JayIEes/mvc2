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
 * 실행되면 로그인 폼으로 이동.
 * 단순 페이지 이동
 * 이동할 페이지명과 이동 방식만 설정
 * action은 웹의 파라메터 처리, 관계유지, 뷰페이지를 설정, 이동방식 설정 
 * @author user
 */
public class LoginAction implements Action {

	private String url;
	private boolean forwardFlag;
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 폼으로 이동 
		url="login2/login_form.jsp"; //폴더명이 대문자였어요 windows가 대소문자를 구분하지 못해서 
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
