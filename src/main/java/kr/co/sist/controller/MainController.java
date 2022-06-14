package kr.co.sist.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sist.action.Action;
import kr.co.sist.action.InfoAction;
import kr.co.sist.action.LoginAction;
import kr.co.sist.action.LoginProcessAction;
import kr.co.sist.action.MainAction;

@SuppressWarnings("serial")
public class MainController extends HttpServlet {
	//요청 URL을 가지고 Action클래스를 찾아서 실행할 때 사용하기 위한 Map
	private static Map<String, Action> map;
	
	
	//static 영역 : class가 실행되면 호출하지 않더라도 가장먼저 자동 호출되는 영역
	//별도의 호출 작업을 하지 않아도 된다.
	static {
		//영역:method안의 코드 작성
		map=new HashMap<String, Action>();
		
		//키(cmd)는 요청했을 때 입력받아 Action을 찾아서 실행함.
		map.put("M001", new MainAction()); //메인 액션, 부서정보, 사원 정보
		map.put("I001", new InfoAction()); //부서에 해당하는 사원정보만 조회
		map.put("L001", new LoginAction()); //로그인 폼
		map.put("L002", new LoginProcessAction()); //로그인 처리
	}
	
	
	
	
	//최초요청이나 get방식의 요청이 있다면 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost로 가서 처리한다.
		doPost(request,response);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		
		//Action을 찾아서 실행하기 위한 cmd를 받는다
		String cmd=request.getParameter("cmd");
		if(cmd==null) {//최초 호출되었을 때
			cmd="M001"; 
		}
		
		//Map에서 cmd에 해당하는 Action을 얻는다.
		Action action=map.get(cmd);
		if(action==null) { // 존재하지 않는 cmd가 입력되면 
			action=map.get("M001"); //cmd를 외부에서 조작하면 메인화면을 보여준다.
		}
		//객체 다형성 : 구현 클래스가 일을 수행.
		action.execute(request, response);//파라메터 처리
		String url=action.moveURL();//보여줄 페이지명 얻기
		boolean moveFlag=action.forwardFlag();//view 페이지로 이동방식 얻기
		
		//설정정보를 입력하여 페이지 이동 
		movePage(request, response, url, moveFlag);
		
	}
	
	
	/**
	 * 페이지 이동
	 * @param request
	 * @param response
	 * @param moveURL
	 * @param forwardFlag
	 * @throws ServletException
	 * @throws IOException
	 */
	private void movePage(HttpServletRequest request,HttpServletResponse response, String moveURL, boolean forwardFlag)
			throws ServletException,IOException {
		
		if(forwardFlag) {//forward로 이동
			 RequestDispatcher rd=request.getRequestDispatcher(moveURL);
			 rd.forward(request, response);
			 
		}else {//redirect로 이동
			response.sendRedirect(moveURL);
			
		}//end else
		
		
	}//movePage

}
