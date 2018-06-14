package kh.web.dto;

public class MemberDTO {
	private String id;
	private String pw;
	private String name;
	private String phone1;
	private String phone2;
	private String phone3;
	private String email;
	private String zipcode;
	private String addr1;
	private String addr2;
	private String isblocked;
	
	public MemberDTO() {
		super();
	}
	
	public MemberDTO(String id, String pw, String name, String phone1, String phone2, String phone3, String email,
			String zipcode, String addr1, String addr2, String isblocked) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.email = email;
		this.zipcode = zipcode;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.isblocked = isblocked;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getIsblocked() {
		return isblocked;
	}
	public void setIsblocked(String isblocked) {
		this.isblocked = isblocked;
	}
	
	
	
}
