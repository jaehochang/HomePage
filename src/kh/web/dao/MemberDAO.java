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
}
