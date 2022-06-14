package kr.co.sist.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.sist.service.EmpService;
import kr.co.sist.vo.EmpVO;

/**
 * ��� ��� ������ �μ� ������ ��ȸ�Ͽ� scope ��ü�� �Ҵ��ϴ� ��
 * @author user
 */
public class InfoAction implements Action {

	
	private String url;
	private boolean forwardFlag;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�Էµ� �μ���ȣ �ޱ�
		String paramDeptno=request.getParameter("deptno");
		
		//�� �Ķ���� ó��
		try {
			int deptno=Integer.parseInt(paramDeptno);
			//System.out.println(deptno);
			//�Էµ� �μ���ȣ�� ����Ͽ� �μ��� �ش��ϴ� ������� ��ȸ
			EmpService es=new EmpService();
			List<EmpVO> listEmp=es.searchEmp(deptno);
			request.setAttribute("empData", listEmp);
			
			//��ȸ�� ����� ���� �� ������ ������ ����
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
