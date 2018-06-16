package kh.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.web.dao.MemberDAO;
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

				System.out.println("id" + id);
				System.out.println("pw" + pw);
				boolean result = mdao.isLoginAvailable(id, pw);

				if (result) {
					request.getSession().setAttribute("loginId", id);
					dst = "main.jsp";
				} else {
					dst = "login.jsp";
					System.out.println("로그인실패");
				}

				request.setAttribute("result", result);
				isRedirect = false;

			} else if (command.equals("/mypage.do")) {
				String id = (String) request.getSession().getAttribute("loginId");
				request.setAttribute("id", id);

				RegisterDTO result = new RegisterDTO();
				rDTO.setId(id);
				result = mdao.myPage(id);

				request.setAttribute("result", result);
				isRedirect = false;
				dst = "mypage.jsp";

			} else if (command.equals("/modifyForm.do")) {
				String id = (String) request.getSession().getAttribute("loginId");
				System.out.println("modifyForm.do: " + id);

				RegisterDTO rdto = new RegisterDTO();
				rdto = mdao.getAlldata(id);

				request.setAttribute("rdto", rdto);
				isRedirect = false;
				dst = "memberModifyForm.jsp";

			} else if (command.equals("/modify.do")) {
				String id = (String) request.getSession().getAttribute("loginId");

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

				request.setCharacterEncoding("utf8");
				response.setCharacterEncoding("utf8");
				
				isRedirect = false;
				RegisterDTO regDTO = new RegisterDTO();
				
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
				String phone1 = request.getParameter("phone1");
				String phone2 = request.getParameter("phone2");
				String phone3 = request.getParameter("phone3");
				String email = request.getParameter("email");
				String zipcode = request.getParameter("zipcode");
				String address1 = request.getParameter("address1");
				String address2 = request.getParameter("address2");
				
				
				regDTO.setId(id);
				regDTO.setPw(pw);
				regDTO.setName(name);
				regDTO.setPhone1(phone1);
				regDTO.setPhone2(phone2);
				regDTO.setPhone3(phone3);
				regDTO.setEmail(email);
				regDTO.setZipcode(zipcode);
				regDTO.setAddress1(address1);
				regDTO.setAddress2(address2);
				
				
				boolean result = mdao.signUp(regDTO);

				System.out.println("MemberController regDTO sysout :"+ regDTO.getId() + regDTO.getPw() + regDTO.getName() + regDTO.getPhone1() + regDTO.getPhone2() + regDTO.getPhone3() + regDTO.getZipcode() + regDTO.getAddress1() + regDTO.getAddress2() + regDTO.getIsBlocked());
				
				request.setAttribute("signupResult", result);

				if (result) {
					dst = "signup.jsp";
				} else {
					dst = "error.html";
				}
			}

			else if (command.equals("/toMemberOut.do")) {

				isRedirect = false;
				dst = "memberOut.jsp";

			} else if (command.equals("/memberOut.do")) {
				String id = (String) request.getSession().getAttribute("loginId");
				String pw = request.getParameter("pw");
				int result = mdao.memberOutData(id, pw);
				request.setAttribute("result", result);
				isRedirect = false;
				dst = "memberOutView.jsp";
			} else if (command.equals("/logout.do")) {
				request.getSession().invalidate();
				dst = "login.jsp";
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
