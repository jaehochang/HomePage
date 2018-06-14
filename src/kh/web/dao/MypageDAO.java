package kh.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kh.web.dto.RegisterDTO;
import kh.web.utils.DBUtils;

public class MypageDAO {
	public List<RegisterDTO> myPage(String id) throws Exception{
		Connection con = DBUtils.getConnection();
		String sql = "select * from member where id=?";
		PreparedStatement pstat = con.prepareStatement(sql);
		
		pstat.setString(1, id);
		
		ResultSet rs= pstat.executeQuery();
		
		List<RegisterDTO> result= new ArrayList<RegisterDTO>();
		
		while(rs.next()) {
			result.add(new RegisterDTO(rs.getString("id"),rs.getString("pw"),rs.getString("name"),rs.getString("phone1"),rs.getString("phone2"),rs.getString("phone3"),rs.getString("email"),rs.getString("zipcode"),rs.getString("address1"),rs.getString("address2"),rs.getString("isBlocked")));
			
		}
		con.close();
		pstat.close();
		rs.close();

		return result;
		
}
}
