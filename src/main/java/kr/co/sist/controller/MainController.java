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
	//��û URL�� ������ ActionŬ������ ã�Ƽ� ������ �� ����ϱ� ���� Map
	private static Map<String, Action> map;
	
	
	//static ���� : class�� ����Ǹ� ȣ������ �ʴ��� ������� �ڵ� ȣ��Ǵ� ����
	//������ ȣ�� �۾��� ���� �ʾƵ� �ȴ�.
	static {
		//����:method���� �ڵ� �ۼ�
		map=new HashMap<String, Action>();
		
		//Ű(cmd)�� ��û���� �� �Է¹޾� Action�� ã�Ƽ� ������.
		map.put("M001", new MainAction()); //���� �׼�, �μ�����, ��� ����
		map.put("I001", new InfoAction()); //�μ��� �ش��ϴ� ��������� ��ȸ
		map.put("L001", new LoginAction()); //�α��� ��
		map.put("L002", new LoginProcessAction()); //�α��� ó��
	}
	
	
	
	
	//���ʿ�û�̳� get����� ��û�� �ִٸ� 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost�� ���� ó���Ѵ�.
		doPost(request,response);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		
		//Action�� ã�Ƽ� �����ϱ� ���� cmd�� �޴´�
		String cmd=request.getParameter("cmd");
		if(cmd==null) {//���� ȣ��Ǿ��� ��
			cmd="M001"; 
		}
		
		//Map���� cmd�� �ش��ϴ� Action�� ��´�.
		Action action=map.get(cmd);
		if(action==null) { // �������� �ʴ� cmd�� �ԷµǸ� 
			action=map.get("M001"); //cmd�� �ܺο��� �����ϸ� ����ȭ���� �����ش�.
		}
		//��ü ������ : ���� Ŭ������ ���� ����.
		action.execute(request, response);//�Ķ���� ó��
		String url=action.moveURL();//������ �������� ���
		boolean moveFlag=action.forwardFlag();//view �������� �̵���� ���
		
		//���������� �Է��Ͽ� ������ �̵� 
		movePage(request, response, url, moveFlag);
		
	}
	
	
	/**
	 * ������ �̵�
	 * @param request
	 * @param response
	 * @param moveURL
	 * @param forwardFlag
	 * @throws ServletException
	 * @throws IOException
	 */
	private void movePage(HttpServletRequest request,HttpServletResponse response, String moveURL, boolean forwardFlag)
			throws ServletException,IOException {
		
		if(forwardFlag) {//forward�� �̵�
			 RequestDispatcher rd=request.getRequestDispatcher(moveURL);
			 rd.forward(request, response);
			 
		}else {//redirect�� �̵�
			response.sendRedirect(moveURL);
			
		}//end else
		
		
	}//movePage

}
