package kr.co.sist.vo;

import java.util.Date;

public class EmpVO {

	private int empno,mgr,sal,comm,deptno;
	private String ename,job;
	private Date hiredate;
	
	
	public EmpVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmpVO(int empno, int mgr, int sal, int comm, int deptno, String ename, String job, Date hiredate) {
		super();
		this.empno = empno;
		this.mgr = mgr;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
		this.ename = ename;
		this.job = job;
		this.hiredate = hiredate;
	}


	/**
	 * @return the empno
	 */
	public int getEmpno() {
		return empno;
	}


	/**
	 * @param empno the empno to set
	 */
	public void setEmpno(int empno) {
		this.empno = empno;
	}


	/**
	 * @return the mgr
	 */
	public int getMgr() {
		return mgr;
	}


	/**
	 * @param mgr the mgr to set
	 */
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}


	/**
	 * @return the sal
	 */
	public int getSal() {
		return sal;
	}


	/**
	 * @param sal the sal to set
	 */
	public void setSal(int sal) {
		this.sal = sal;
	}


	/**
	 * @return the comm
	 */
	public int getComm() {
		return comm;
	}


	/**
	 * @param comm the comm to set
	 */
	public void setComm(int comm) {
		this.comm = comm;
	}


	/**
	 * @return the deptno
	 */
	public int getDeptno() {
		return deptno;
	}


	/**
	 * @param deptno the deptno to set
	 */
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}


	/**
	 * @return the ename
	 */
	public String getEname() {
		return ename;
	}


	/**
	 * @param ename the ename to set
	 */
	public void setEname(String ename) {
		this.ename = ename;
	}


	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}


	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}


	/**
	 * @return the hiredate
	 */
	public Date getHiredate() {
		return hiredate;
	}


	/**
	 * @param hiredate the hiredate to set
	 */
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}


	@Override
	public String toString() {
		return "EmpVO [empno=" + empno + ", mgr=" + mgr + ", sal=" + sal + ", comm=" + comm + ", deptno=" + deptno
				+ ", ename=" + ename + ", job=" + job + ", hiredate=" + hiredate + "]";
	}

	
}
