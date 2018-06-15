package kh.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kh.web.utils.DBUtils;
import kh.web.dto.BoardDTO;

public class BoardDAO {
	
	

	public List selectData(int startNum, int endNum) throws Exception {
		
		Connection con = DBUtils.getConnection();
		String sql = "select * from (select board.*, row_number() over(order by writerdate desc) as num from board) where num between ? and ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		
		pstat.setInt(1, startNum);
		pstat.setInt(2, endNum);

		List<BoardDTO> list = new ArrayList<>();
		ResultSet rs = pstat.executeQuery();
		
		while (rs.next()) {
			
			
			list.add(new BoardDTO(rs.getInt("seq"), rs.getString("title"), rs.getString("writer"),
					rs.getString("writerdate"), rs.getInt("viewcount"), rs.getString("ip")));
		}
		con.commit();
		pstat.close();
		con.close();

		return list;
	}

	public int insertData(String id, String title, String content, String ip) throws Exception {

		Connection con = DBUtils.getConnection();
		String sql1 = "select BOARD_SEQ.NEXTVAL from dual";
		PreparedStatement pstat1 = con.prepareStatement(sql1);
		ResultSet rs = pstat1.executeQuery();
		if(!(rs.next())) {
			pstat1.close();
		}
		int seq = rs.getInt("NEXTVAL")+1;
		String sql = "insert into board values(?,?,?,?,sysdate,default,?)";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setInt(1, seq);
		pstat.setString(2, title);
		pstat.setString(3, content);
		pstat.setString(4, id);
		pstat.setString(5, ip);

		pstat.executeUpdate();

		con.commit();
		pstat.close();
		con.close();
		pstat1.close();

		return seq;

	}

	public BoardDTO readContent(int seq,String writerId, String aceessId) throws Exception {

		Connection con = DBUtils.getConnection();
		
		
		String sql = "select writer, title, contents, writerdate, viewcount from board where seq=?";
		PreparedStatement pstat = con.prepareStatement(sql);

		pstat.setInt(1, seq);
		ResultSet rs = pstat.executeQuery();
		BoardDTO dto =null;
		if (rs.next()) {
			
			
			dto = new BoardDTO(rs.getString("writer"), rs.getString("title"), rs.getString("contents"),
			rs.getString("writerdate"));
		}
		
		if(!(aceessId.equals(writerId))) {
		int count = rs.getInt("viewcount");
		count++;
		String sql1 = "update board set viewcount=? where seq =?";
		PreparedStatement pstat1 = con.prepareStatement(sql1);
		pstat1.setInt(1, count);
		pstat1.setInt(2, seq);
		pstat1.executeUpdate();
		
		con.commit();
		con.close();
		pstat.close();
		pstat1.close();
		
		return dto;
		}else {
			con.commit();
			con.close();
			pstat.close();
			return dto;
			
		}

	}
	public BoardDTO modifySelect(int seq) throws Exception{
		

		Connection con = DBUtils.getConnection();

		String sql = "select writer, title, contents from board where seq=? order by comment_seq desc";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setInt(1, seq);
		ResultSet rs = pstat.executeQuery();
		BoardDTO dto =null;
		if (rs.next()) {
			dto = new BoardDTO(rs.getString("writer"), rs.getString("title"), rs.getString("contents"));
			con.commit();
			con.close();
			pstat.close();
			
		} 
		return dto;
		
	}
	public int modifyUpdate(String title, String content, int seq) throws Exception{

		Connection con =DBUtils.getConnection();

		String sql = "update board set  title=?, contents=?, writerdate=sysdate where seq=?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setString(1, title);
		pstat.setString(2, content);
		pstat.setInt(3, seq);
		
		int result = pstat.executeUpdate();
		con.commit();
		pstat.close();
		con.close();
		return result;

		

	}
	public int deleteData(int seq) throws Exception{
		
		Connection con = DBUtils.getConnection();

		String sql = "delete from board where seq=?";
		PreparedStatement pstat = con.prepareStatement(sql);
		pstat.setInt(1, seq);
		
		int result = pstat.executeUpdate();
		
		con.commit();
		con.close();
		pstat.close();
		
		return result;
		
	}
	public void makeDummyData() throws Exception{
		Connection con = DBUtils.getConnection();
		
		String sql = "insert into board values(board_seq.nextval,?,?,?,sysdate,default,?)";
		PreparedStatement pstat = con.prepareStatement(sql);
		
		for(int i=0; i< 100; i++) {
			pstat.setString(1, "제목"+i);
			pstat.setString(2, "내용"+i);
			pstat.setString(3, "작성자"+i);
			pstat.setString(4, "192.168.20."+i);
			//pstat.addBatch(); // update 는 하나처리하고 batch는 쌓아놓는것
			pstat.executeUpdate();
			Thread.sleep(1000);
			System.out.println("입력행:" + i);
			
			
		}
		//pstat.executeBatch();
		con.commit();
		pstat.close();
		con.close();
	}
	public static void main(String[] args) throws Exception{
		
		new BoardDAO().makeDummyData();
		
	}
	
	public String  getPageNavi(int currentPage1) throws Exception{
		
		Connection con = DBUtils.getConnection();
		String sql = "select count(*) totalCount from board";
		PreparedStatement pstat = con.prepareStatement(sql);
		ResultSet rs = pstat.executeQuery();
		rs.next(); //값이 없더라도 0이라는 값은 가져오기때문에 테이블이 없는경우는 없다.
		
		int recordTotalCount = rs.getInt("totalCount"); // 전체 글(레코드)의 개수를 저장하는 변수
		int recordCountPerPage = 10; // 한페이지에 보이는 게시글의 수 
		int naviCountPerPage = 10; // 한 페이지에서 네비게이터가 몇개씩 보일 것인가
		int pageTotalCount = 0; // 전체가 몇 페이지로 구성될 것인지
		
		if(recordTotalCount % recordCountPerPage > 0) { //10으로 나누어 떨어지지 않음
			//구분하는 이유 - 총몇페이지인지 구하고 그게 소수점일수도 딱 떨어질수도 있다
			//근데 딱떨어지는경우에는 +1을 할 필요가 없음, 잘리는 부분이 없으니까
			//하지만 딱안떨어질경우에는 +1을 해서 데이터가 잘리지 않게 만들어야 한다.
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}
		else { //10으로 나누어 떨어지는 것들 
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		//------------------------------------------------------------------
		
		int currentPage = currentPage1; // 현재페이지 (주소에다가 직접 입력해서 하는 사람도 있을수 있음, 이경우 버그발생할수 있는데 그걸 막는코드 작성해야함)
		
		if(currentPage < 1) {
			//음수로 버그내려고 하면 강제로 1페이지로 이동하도록 만들어줌
			currentPage = 1;
		}else if(currentPage > pageTotalCount) {
			//최대값 벗어나는 수로 버그내려고 하면 강제로 최대페이지로 이동하도록 만들어줌
			currentPage = pageTotalCount;
		}//현재페이지가 비정상인지 검증하는 코드
		//----------------------------------------------
		
		
		int startNavi = (currentPage -1)/naviCountPerPage * naviCountPerPage + 1;
		// 정확히 나누어 떨어지는 애들을 막기위해 이렇게 코드 작성//네비게이터가 시작 값?
		//currentPage / naviCountPerPage * naviCountPerPage + 1;
		//(currentPage/navicountPerpage )* 보여주곡자하는 페이지  + 1
		
		int endNavi = startNavi + (naviCountPerPage - 1); 
		//네비게이터가 끝 값 , 괄호공식을 상수값 9로 하면 안됨, naviCountPerPage 가 가변적이기때문에
		
		
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;	
		}
		
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		StringBuilder sb = new StringBuilder(); //문자열 만들어서 쌓아 올린다.
		
		if(needPrev) {
			sb.append("<a href='BoardController.bo?currentPage="+(startNavi-1)+"'>< </a>");
		}
		
		for(int i=startNavi; i<= endNavi; i++) {
			if(currentPage == i) {
				sb.append("<a id='naviBt'href='BoardController.bo?currentPage="+ i +"'> <b> " + i + "</b></a>"); 
				//스트링빌더에 차곡차곡 쌓고있는것, 현재페이지를 두각시키위해 b태그 추가
			}else {
				sb.append("<a id='naviBt'href='BoardController.bo?currentPage="+ i +"'> " + i + "</a>"); //스트링빌더에 차곡차곡 쌓고있는것
			}
			
		}
		if(needNext) {
			sb.append("<a href='BoardController.bo?currentPage="+(endNavi+1)+"'>></a>");
		}
		System.out.println(sb.toString()); // 쌓았던것을 한번에 출력
		
		con.close();
		pstat.close();
		return sb.toString();
	}
	
	public List searchData(String titleMsg,int startNum, int endNum) throws Exception{
		
		Connection con = DBUtils.getConnection();

		String sql = "select * from (select board.*, row_number() over(order by writerdate desc) as num from board where title like ?) where num between ? and ?";
		PreparedStatement pstat = con.prepareStatement(sql);
		
		pstat.setString(1, titleMsg+"%");
		pstat.setInt(2, startNum);
		pstat.setInt(3, endNum);
		

		List<BoardDTO> list = new ArrayList<>();
		ResultSet rs = pstat.executeQuery();
		
		while (rs.next()) {
			
			
			list.add(new BoardDTO(rs.getInt("seq"), rs.getString("title"), rs.getString("writer"),
					rs.getString("writerdate"), rs.getInt("viewcount"), rs.getString("ip")));
		}
		con.commit();
		pstat.close();
		con.close();

		return list;
	}

		
		
	}

