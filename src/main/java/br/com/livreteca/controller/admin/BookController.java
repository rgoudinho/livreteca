package br.com.livreteca.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.livreteca.dto.BookDTO;
import br.com.livreteca.error.ValidationError;
import br.com.livreteca.model.dao.BookDAO;
import br.com.livreteca.model.dao.BookItemDAO;
import br.com.livreteca.model.entity.Book;
import br.com.livreteca.model.entity.BookItem;
import br.com.livreteca.model.mapper.BookMapper;
import br.com.livreteca.service.BookService;
import br.com.livreteca.util.Routes;

/**
 * Servlet implementation class AddBookController
 */
@WebServlet(urlPatterns = {"/a/adicionar", "/a/excruir", "/a/editar"})
public class BookController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		
		switch (action) {
		case "/a/adicionar":
			callAdd(request, response);
			break;
		case "/a/excruir":
			delete(request, response);
			break;
		case "/a/editar":
			callEdit(request, response);
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		switch(action){
		case "/a/adicionar":
			add(request, response);
			break;
		case "/a/editar":
			edit(request, response);
			break;
			
		}
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		String nameBook = request.getParameter("name");
		String writerBook = request.getParameter("writer");
		Integer amountBook = Integer.parseInt(request.getParameter("amount"));
		Book book = new Book(nameBook, writerBook, amountBook); 
		BookDAO bookDAO = new BookDAO();
		
		String message = "Usuario atualizado com sucesso";
		try {
			bookDAO.update(book, id);
		}catch (Exception e) {
			message = "Erro " + e.getMessage();
		}

		String address = request.getContextPath()+ "/a?message="+message;
		System.out.println(address);;
		response.sendRedirect(address);
	}

	private void callEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long bookId = Long.parseLong(request.getParameter("id"));
		BookDAO bookDao = new BookDAO();
		Book book = bookDao.getById(bookId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/form-book-edit.jsp");
		request.setAttribute("book", book);
		dispatcher.forward(request, response);
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		BookDAO bookDao = new BookDAO();
		Book book = new Book(id);
		bookDao.remove(book);
		response.sendRedirect("index");
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nameBook = request.getParameter("name");
		String writerBook = request.getParameter("writer");
		String amountBook = request.getParameter("amount");
		Integer amountBookInt = Integer.parseInt(amountBook);
		
		BookDTO bookDTO = new BookDTO(nameBook, writerBook, amountBookInt);
		List<ValidationError> errors = formValidation(bookDTO);
		
		boolean hasError = errors != null;

		if (hasError) {
			sendError(request, response, errors);
			return;
		}
		
		if (request.getServletPath().contains(Routes.ADD)) {
			boolean isSuccess = persist(bookDTO);
		
			if (!isSuccess) {
				String address = "/WEB-INF/view/admin/book-form.jsp";
				
				errors = new ArrayList<>();
				errors.add(new ValidationError("", "Erro ao persistir os dados."));

				
				request.setAttribute("Erros", errors);
				request.getRequestDispatcher(address).forward(request, response);
				return;
			}
			String address = request.getContextPath()+"/a";
			response.sendRedirect(address);
		}
		
	}

	private void callAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = "/WEB-INF/view/admin/form-book.jsp";
		request.getRequestDispatcher(address).forward(request, response);
	}

	private boolean persist(BookDTO bookDTO) {
		Book book = BookMapper.toEntity(bookDTO);
		BookDAO bookDAO = new BookDAO();
		persistItems(book);
		return bookDAO.saveBook(book);
	}

	private void persistItems(Book book) {
		BookItem bookItem = null;
		BookItemDAO bookItemDAO = new BookItemDAO();
		for (int i = 0; i < book.getAmount(); i++) {
			bookItem = new BookItem(book, false);
			bookItemDAO.save(bookItem);
		}
	}

	private void sendError(HttpServletRequest request, HttpServletResponse response, List<ValidationError> errors) throws ServletException, IOException {
		String address = "/WEB-INF/view/admin/form-book.jsp";
		request.setAttribute("errors", errors);
		request.getRequestDispatcher(address).forward(request, response);
		
	}

	private List<ValidationError> formValidation(BookDTO bookDTO) {
		List<ValidationError> errors = new ArrayList<>();

		if (bookDTO.getName() == null || bookDTO.getName().isEmpty())
			errors.add(new ValidationError("nome", "O campo nome é obrigatório."));
		if (bookDTO.getWriter() == null || bookDTO.getWriter().isEmpty())
			errors.add(new ValidationError("escritor", "O campo nome é obrigatório."));
		if (bookDTO.getAmount() == 0)
			errors.add(new ValidationError("quantidade", "O campo nome é obrigatório."));

		return (errors.isEmpty() ? null : errors);
	}

}
