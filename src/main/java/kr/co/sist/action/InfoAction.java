package kr.co.sist.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sist.service.EmpService;
import kr.co.sist.vo.EmpVO;

/**
 * 모든 사원 정보와 부서 정보를 조회하여 scope 객체에 할당하는 일
 * @author user
 */
public class InfoAction implements Action {

	
	private String url;
	private boolean forwardFlag;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//입력된 부서번호 받기
		String paramDeptno=request.getParameter("deptno");
		
		//웹 파라메터 처리
		try {
			int deptno=Integer.parseInt(paramDeptno);
			//System.out.println(deptno);
			//입력된 부서번호를 사용하여 부서에 해당하는 사원정보 조회
			EmpService es=new EmpService();
			List<EmpVO> listEmp=es.searchEmp(deptno);
			request.setAttribute("empData", listEmp);
			
			//조회된 결과가 있을 때 보여줄 페이지 설정
			url="day0509/search_emp.jsp";
			forwardFlag=true;//redirect
			
		}catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			url="http://localhost/mvc_prj2/err/error_page.jsp";
			forwardFlag=false;//redirect
		}//end catch
		
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
