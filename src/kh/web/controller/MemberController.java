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
import kh.web.dao.MemberDAO;
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

			MemberDAO mdao = new MemberDAO();
            RegisterDTO rDTO = new RegisterDTO();
            
			String dst = null;
			boolean isRedirect = true;

			if (command.equals("/login.do")) {

				String id = request.getParameter("id");
				String pw = request.getParameter("pw");

				System.out.println("id"+id);
				System.out.println("pw"+pw);
				boolean result = mdao.isLoginAvailable(id, pw);

				if (result) {
					request.getSession().setAttribute("loginId", id);
					dst = "main.jsp";
				}else {
					dst = "login.jsp";
				}

				request.setAttribute("result", result);
				isRedirect = false;

			} else if (command.equals("/mypage.do")) {
				String id = (String) request.getSession().getAttribute("loginId");
				request.setAttribute("id", id);

				RegisterDTO result = new RegisterDTO();
				result = mdao.myPage(id);

				request.setAttribute("result", result);
				isRedirect = false;
				dst = "mypage.jsp";
			} else if (command.equals("/modifyForm.do")) {
				String id = (String) request.getSession().getAttribute("id");

				RegisterDTO rdto = new RegisterDTO();
				rdto = mdao.getAlldata(id);

				request.setAttribute("rdto", rdto);
				isRedirect = false;
				dst = "memberModifyForm.jsp";
			} else if (command.equals("/modify.do")) {
				String id = (String) request.getSession().getAttribute("id");

				RegisterDTO rdto = new RegisterDTO();
				rdto.setId(request.getParameter("id"));
				rdto.setPw(request.getParameter("pw"));
				rdto.setName(request.getParameter("name"));
				rdto.setPhone1(request.getParameter("phone1"));
				rdto.setPhone2(request.getParameter("phone2"));
				rdto.setPhone3(request.getParameter("phone3"));
				rdto.setEmail(request.getParameter("email"));
				rdto.setZipcode(request.getParameter("zipcode"));
				rdto.setAddress1(request.getParameter("address1"));
				rdto.setAddress2(request.getParameter("address2"));
				int result = mdao.memberModify(rdto);

				if (result > 0) {
					isRedirect = false;
					dst = "main.jsp";
				} else {
					throw new Exception();
				}

			} else if (command.equals("/signup.do")) {

				isRedirect = false;
					
				boolean result = mdao.signUp(rDTO);
				
				request.setAttribute("signupResult", result);

				dst="signup.jsp";
				
			}

			else if (command.equals("/toMemberOut.do")) {

				isRedirect = false;
				dst = "memberOut.jsp";

			} else if (command.equals("/memberOut.do")) {
				String id = (String)request.getSession().getAttribute("loginId");
				String pw = request.getParameter("pw");
				int result = mdao.memberOutData(id, pw);
				request.setAttribute("result", result);
				isRedirect = false;
				dst = "memberOutView.jsp";
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
