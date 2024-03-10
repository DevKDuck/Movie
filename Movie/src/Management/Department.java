package Management;

public class Department {
	private int deptno;
	private String dname;
	private String loc;
	
	public Department(int deptno, String dname, String loc) {
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	public void printDetail() {
		System.out.println("부서번호 : " + deptno);
		System.out.println("부서이름 : " + dname);
		System.out.println("지역  : " + loc);
	}

	
	
}
