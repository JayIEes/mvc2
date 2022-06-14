package kr.co.sist.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sist.service.DeptService;
import kr.co.sist.service.EmpService;
import kr.co.sist.service.LoginService;
import kr.co.sist.vo.DeptVO;
import kr.co.sist.vo.EmpVO;

/**
 * 파라메터 처리, 파라메터 유효성 검증, 관계유지 객체의 사용, service객체 사용
 * 처리결과를 scope 객체에 할당, view페이지명 설정, 이동방식 설정 
 * 모든 사원 정보와 부서 정보를 조회하여 scope 객체에 할당하는 일
 * @author user
 */
public class MainAction implements Action {

	private String url;
	private boolean forwardFlag;
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//업무 로직에 처리
		DeptService ds=new DeptService();
		EmpService es=new EmpService();
		
		List<DeptVO> listDept=ds.searchDept();
		List<EmpVO> listEmp=es.searchEmp(0);
		//System.out.println( listDept);
		//System.out.println( listEmp);
		
		//이동할 페이지 URL 설정 
		if(listDept==null||listEmp==null) {
			url="http://localhost/mvc_prj2/err/error_page.jsp";
			forwardFlag=false; //redirect로 갈거다
			return;
		}
		
		//view 페이지에서 값을 사용할 수 있도록 scope 객체에 설정 
		request.setAttribute("deptData", listDept);
		request.setAttribute("empData", listEmp);
		
		//성공했을 떄 이동할 페이지 설정 
		url="day0509/main_page.jsp";
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
