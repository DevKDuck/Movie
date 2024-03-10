package Management;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {

	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate;
	private int sal;
	private int comm;
	private int deptno;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	
	
	
//	constructor
	public Employee(int empno, String ename, String job, int mgr, Date hiredate, int sal, int comm, int deptno) {
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}

	public void print() {
		System.out.println( empno + "   " +ename + "   " +  job +  "   " +  mgr + "   " + sdf.format(hiredate) + "   " + sal  + "   " +  comm +  "   " +deptno);
	}
	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public Date getHiredate() {
		
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	public void printDetail() {
		System.out.println("사원번호 : " + empno);
		System.out.println("사원이름 : " + ename);
		System.out.println("직업  : " + job);
		System.out.println("상관번호 : " + mgr);
		System.out.println("입사일자 : " + hiredate);
		System.out.println("급여  : " + sal);
		System.out.println("보너스 : " + comm);
		System.out.println("부서번호 :" + deptno);
	}

	
}
