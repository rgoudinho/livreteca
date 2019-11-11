package br.com.livreteca.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.livreteca.model.dao.BookDAO;
import br.com.livreteca.model.dao.BookItemDAO;
import br.com.livreteca.model.entity.BookItem;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = { "/u", "/u/emprestar" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		switch (action) {
		case "/u":
			callIndex(request, response);
			break;
		case "/u/emprestar":
			callLend(request, response);
			break;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		switch (action) {
		case "/u/emprestar":
			lend(request, response);
			break;
		}
	}

	private void callLend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		BookItemDAO bookItemDAO = new BookItemDAO();
		request.setAttribute("bookItems", bookItemDAO.findAll());
		String address = "/WEB-INF/view/user/book-items.jsp";
		request.getRequestDispatcher(address).forward(request, response);
	}
	
	private void callIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookDAO bookDAO = new BookDAO();
		request.setAttribute("books", bookDAO.findAll());
		String address = "/WEB-INF/view/user/index.jsp";
		request.getRequestDispatcher(address).forward(request, response);
	}

	private void lend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long bookItemId = Long.parseLong(request.getParameter("id"));
		BookItemDAO bookItemDAO = new BookItemDAO();
		BookItem bookItem = bookItemDAO.getById(bookItemId);
		bookItem.setBorrowed(!bookItem.getBorrowed());
		String address = "emprestar";
		response.sendRedirect(address);
	}

}
