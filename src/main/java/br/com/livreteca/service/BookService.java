package br.com.livreteca.service;

import br.com.livreteca.model.dao.BookDAO;
import br.com.livreteca.model.entity.Book;
import br.com.livreteca.util.JPAUtil;

public class BookService extends AbstractService<String, Book>{
	public BookService() {
        dao = new BookDAO();
    }


}
