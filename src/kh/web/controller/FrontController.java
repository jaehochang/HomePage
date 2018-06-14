package kh.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.web.dao.BoardDAO;
import kh.web.dto.BoardDTO;

@WebServlet("*.bo")
public class FrontController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String requestURI = request.getRequestURI();
			String contextPath = request.getContextPath();
			String command = requestURI.substring(contextPath.length());

			BoardDAO dao = new BoardDAO();

			String dst = null;
			boolean isRedirect = true;

			if (command.equals("/BoardController.bo")) {
				
				request.setCharacterEncoding("UTF-8");
				int currentPage = 0;
				String titleMsg = request.getParameter("titleMsg");
				String currentPageString = request.getParameter("currentPage");
				
				if(currentPageString == null){
					currentPage = 1;						
				}else{
					currentPage = Integer.parseInt(currentPageString);
				}
				
				if(titleMsg == null){
					List<BoardDTO> result = dao.selectData(currentPage * 10-9,currentPage*10);// 10은 recordCountPerPage, 9는 
					request.setAttribute("result", result);
					request.setAttribute("titleMsg", titleMsg);
					String navi = dao.getPageNavi(currentPage);
					request.setAttribute("navi", navi);
				}else {
					List<BoardDTO> list = dao.searchData(titleMsg,currentPage * 10-9,currentPage*10);
					request.setAttribute("list", list);
					request.setAttribute("titleMsg", titleMsg);
					String navisearch = dao.getPageNavi(1);
					request.setAttribute("navisearch", navisearch);
				}
				isRedirect = false;
				dst = "boardView.jsp";

			} else if (command.equals("")) {
			
			}

			if (isRedirect) {
				response.sendRedirect(dst);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(dst);
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
