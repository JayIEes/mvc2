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
 * �Ķ���� ó��, �Ķ���� ��ȿ�� ����, �������� ��ü�� ���, service��ü ���
 * ó������� scope ��ü�� �Ҵ�, view�������� ����, �̵���� ���� 
 * ��� ��� ������ �μ� ������ ��ȸ�Ͽ� scope ��ü�� �Ҵ��ϴ� ��
 * @author user
 */
public class MainAction implements Action {

	private String url;
	private boolean forwardFlag;
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���� ������ ó��
		DeptService ds=new DeptService();
		EmpService es=new EmpService();
		
		List<DeptVO> listDept=ds.searchDept();
		List<EmpVO> listEmp=es.searchEmp(0);
		//System.out.println( listDept);
		//System.out.println( listEmp);
		
		//�̵��� ������ URL ���� 
		if(listDept==null||listEmp==null) {
			url="http://localhost/mvc_prj2/err/error_page.jsp";
			forwardFlag=false; //redirect�� ���Ŵ�
			return;
		}
		
		//view ���������� ���� ����� �� �ֵ��� scope ��ü�� ���� 
		request.setAttribute("deptData", listDept);
		request.setAttribute("empData", listEmp);
		
		//�������� �� �̵��� ������ ���� 
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
