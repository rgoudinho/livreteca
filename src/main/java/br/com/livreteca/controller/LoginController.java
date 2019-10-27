package br.com.livreteca.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.livreteca.util.Constants;
import br.com.livreteca.util.Routes;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    	if(req.getServletPath().contains(Routes.LOGOUT)){
			HttpSession session = req.getSession(false);
			if(session != null) {
				session.invalidate();
			}
			
			String address = req.getContextPath() + "/";
			resp.sendRedirect(address);
			return;
		}

		String address = "/WEB-INF/view/login.jsp";
		req.getRequestDispatcher(address).forward(req, resp);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			req.login(username, password);
			HttpSession session = req.getSession();
			session.setAttribute("username", req.getUserPrincipal().getName());
			
			if(req.isUserInRole(Constants.ADMIN)) {
				String address = "a";
				resp.sendRedirect(address);
			} else {
				String address = "u";
				resp.sendRedirect(address);
			}
		}
		
		catch (Exception e) {
			resp.sendRedirect("login?error");
		}
	}

}
