package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kh.web.utils.DBUtils;

public class DAO {

	public int memberOutData(String id , String pw) throws Exception{
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
}
