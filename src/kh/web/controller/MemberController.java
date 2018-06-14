package kh.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.web.dao.DAO;
import kh.web.dao.MypageDAO;
import kh.web.dto.RegisterDTO;

@WebServlet("*.do")
public class MemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String requestURI = request.getRequestURI();
			String contextPath = request.getContextPath();
			String command = requestURI.substring(contextPath.length());

			DAO dao = new DAO();
            MypageDAO myPagedao= new MypageDAO();
			
			String dst = null;
			boolean isRedirect = true;

			if (command.equals("/login.do")) {

			} else if (command.equals("/mypage.do")) {
				String id = (String) request.getSession().getAttribute("id");
				
				request.setAttribute("id", id);
				
				List<RegisterDTO> result;
				result=myPagedao.myPage(id);
				
				request.setAttribute("result", result);
				isRedirect = false;
				dst = "mypage.jsp";
			} else if (command.equals("/modify.do")) {

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
