package kh.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.web.dao.DAO;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String requestURI = request.getRequestURI();
			String contextPath = request.getContextPath();
			String command = requestURI.substring(contextPath.length());

			DAO dao = new DAO();

			String dst = null;
			boolean isRedirect = true;

			if (command.equals("/toMemberOut.do")) {
				
				isRedirect=false;
				dst="memberOut.jsp";

			} else if (command.equals("/memberOut.do")) {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				int result = dao.memberOutData(id, pw);
				request.setAttribute("result", result);
				isRedirect=false;
				dst="memberOutView.jsp";
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
