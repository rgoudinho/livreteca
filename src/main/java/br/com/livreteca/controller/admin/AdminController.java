package br.com.livreteca.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.livreteca.model.dao.BookDAO;

/**
 * Servlet implementation class AdminController
 */
@WebServlet(urlPatterns = {"/a", "/a/index"})
public class AdminController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = new String();
		
		if(request.getParameter("message") != null) {
			message = (String) request.getParameter("message");
			request.setAttribute("message", message);
		}
		
		BookDAO bookDAO = new BookDAO();;
		request.setAttribute("books", bookDAO.findAll());
		String address = "/WEB-INF/view/admin/index.jsp";
		request.getRequestDispatcher(address).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
