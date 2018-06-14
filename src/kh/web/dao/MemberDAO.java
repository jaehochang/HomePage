package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kh.web.dto.RegisterDTO;
import kh.web.utils.DBUtils;

public class MemberDAO {
	public boolean isLoginAvailable(String id, String pw) throws Exception {
		Connection con = DBUtils.getConnection();

		String sql = "select id,pw from members where id=? and pw=? and isblocked='N'";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, id);
		pstat.setString(2, pw);

		ResultSet rs = pstat.executeQuery();

		boolean result = rs.next();

		pstat.close();
		con.close();

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
			dto.setIsBlocked(rs.getString("block"));
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

}
