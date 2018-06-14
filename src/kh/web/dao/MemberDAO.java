package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kh.web.utils.DBUtils;

public class MemberDAO {
	public boolean isLoginAvailable(String id, String pw) throws Exception{
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
	
	
	
}
