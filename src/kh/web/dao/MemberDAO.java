package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kh.web.dto.RegisterDTO;
import kh.web.utils.DBUtils;

public class MemberDAO {
	public boolean isLoginAvailable(String id, String pw) throws Exception {
		Connection con = DBUtils.getConnection();

		String sql = "select id,pw from members where id=? and pw=?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, id);
		pstat.setString(2, pw);

		ResultSet rs = pstat.executeQuery();

		boolean result = rs.next();

		pstat.close();
		con.close();

		return result;
	}

	public boolean signUp(RegisterDTO dto) throws Exception {

		Connection con = DBUtils.getConnection();
		String sql = "insert into members values(?,?,?,?,?,?,?,?,?,?,'n')";
		PreparedStatement pstat = con.prepareStatement(sql);

		pstat.setString(1, dto.getId());
		pstat.setString(2, dto.getPw());
		pstat.setString(3, dto.getName());
		pstat.setString(4, dto.getPhone1());
		pstat.setString(5, dto.getPhone2());
		pstat.setString(6, dto.getPhone3());
		pstat.setString(7, dto.getEmail());
		pstat.setString(8, dto.getZipcode());
		pstat.setString(9, dto.getAddress1());
		pstat.setString(10, dto.getAddress2());

		int resultInt = pstat.executeUpdate();
		boolean result = false;

		if (resultInt > 0) {
			result = true;
		} else {
			result = false;
		}

		con.commit();
		con.close();
		pstat.close();

		return result;

	}

	public RegisterDTO getAlldata(String id) throws Exception {
		Connection con = DBUtils.getConnection();

		String sql = "select * from members where id=?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, id);
		ResultSet rs = pstat.executeQuery();
		RegisterDTO dto = new RegisterDTO();

		while (rs.next()) {
			dto.setId(rs.getString("id"));
			dto.setPw(rs.getString("pw"));
			dto.setName(rs.getString("name"));
			dto.setPhone1(rs.getString("phone1"));
			dto.setPhone2(rs.getString("phone2"));
			dto.setPhone3(rs.getString("phone3"));
			dto.setEmail(rs.getString("email"));
			dto.setZipcode(rs.getString("zipcode"));
			dto.setAddress1(rs.getString("address1"));
			dto.setAddress2(rs.getString("address2"));
			dto.setIsBlocked(rs.getString("isblocked"));
		}
		rs.close();
		pstat.close();
		con.close();

		return dto;
	}

	public int memberModify(RegisterDTO dto) throws Exception {
		Connection con = DBUtils.getConnection();

		String sql = "update members set pw=?,name=?,phone1=?,phone2=?,phone3=?,email=?,zipcode=?,address1=?,address2=? where id=?";
		PreparedStatement pstat = con.prepareStatement(sql);

		pstat.setString(1, dto.getPw());
		pstat.setString(2, dto.getName());
		pstat.setString(3, dto.getPhone1());
		pstat.setString(4, dto.getPhone2());
		pstat.setString(5, dto.getPhone3());
		pstat.setString(6, dto.getEmail());
		pstat.setString(7, dto.getZipcode());
		pstat.setString(8, dto.getAddress1());
		pstat.setString(9, dto.getAddress2());
		pstat.setString(10, dto.getId());
		int result = pstat.executeUpdate();

		con.commit();
		pstat.close();
		con.close();

		return result;
	}

	public int memberOutData(String id, String pw) throws Exception {
		Connection con = DBUtils.getConnection();
		String sql = "delete from members where id=? and pw=?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, id);
		pstat.setString(2, pw);
		int result = pstat.executeUpdate();
		con.commit();
		pstat.close();
		con.close();
		return result;
	}

	public RegisterDTO myPage(String id) throws Exception {
		Connection con = DBUtils.getConnection();

		String sql = "select * from members where id=?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, id);
		ResultSet rs = pstat.executeQuery();
		RegisterDTO rdto = new RegisterDTO();

		while (rs.next()) {
			rdto.setId(rs.getString("id"));
			rdto.setPw(rs.getString("pw"));
			rdto.setName(rs.getString("name"));
			rdto.setPhone1(rs.getString("phone1"));
			rdto.setPhone2(rs.getString("phone2"));
			rdto.setPhone3(rs.getString("phone3"));
			rdto.setEmail(rs.getString("email"));
			rdto.setZipcode(rs.getString("zipcode"));
			rdto.setAddress1(rs.getString("address1"));
			rdto.setAddress2(rs.getString("address2"));
			rdto.setIsBlocked(rs.getString("isblocked"));
		}
		rs.close();
		pstat.close();
		con.close();

		return rdto;
	}

	public boolean idDupCheckDAO(String id) throws Exception {
		Connection con = DBUtils.getConnection();

		String sql = "select * from members where id=?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, id);
		ResultSet rs = pstat.executeQuery();

	
		
		boolean result = false;
		if (rs.next()) {
			System.out.println(rs.getString(1));
			result = true;
		} else {
			result = false;
		}
			
		con.close();
		pstat.close();
		
		return result;
	}

}
