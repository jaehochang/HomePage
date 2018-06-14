package kh.web.dto;

public class BoardDTO {
	
	int seq;
	String title;
	String writer;
	String writeday;
	int viewcount;
	String ip;
	String contents;
	
	
	
	public BoardDTO(int seq, String title, String writer, String writeday, int viewcount, String ip) {
		super();
		this.seq = seq;
		this.title = title;
		this.writer = writer;
		this.writeday = writeday;
		this.viewcount = viewcount;
		this.ip = ip;
	}
	
	public BoardDTO(String writer, String title, String contents, String writeday) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.writeday = writeday;
		
	}
	public BoardDTO(String writer, String title, String contents) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		
		
	}
	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteday() {
		return writeday;
	}
	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}
	public int getViewcount() {
		return viewcount;
	}
	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	
	

}
