package Board;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private String bdate;
	
	public Board(String title, String content, String writer) {
		this(0, title, content, writer);
	}
	
	public Board(int bno, String title, String content, String writer) {
		this(bno, title, content, writer, "");
	}

	
	public Board(int bno2, String title, String content, String writer, String string) {
		// TODO Auto-generated constructor stub
	}

	public void print() {
		System.out.printf("%-6s%-12s%-16s%-40s \n"
				, bno
				, bwriter
				, bdate
				, btitle);
	}
	
	public void printDetail() {
		System.out.println("번호 : " + bno);
		System.out.println("작성자 : " + bwriter);
		System.out.println("작성일자 : " + bdate);
		System.out.println("제목 : " + btitle);
		System.out.println("내용 : " + bcontent);
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}







	
}