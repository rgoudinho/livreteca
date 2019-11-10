package br.com.livreteca.service;

import br.com.livreteca.model.dao.BookDAO;
import br.com.livreteca.model.entity.Book;
import br.com.livreteca.util.JPAUtil;

public class BookService extends AbstractService<String, Book>{
	public BookService() {
        dao = new BookDAO();
    }
    public boolean saveBook(Book book){
        boolean isSuccess = true;
        try {
            JPAUtil.beginTransaction();
            dao.save(book);
            JPAUtil.commit();
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
            JPAUtil.rollBack();
        } finally {
            JPAUtil.closeEntityManager();
        }
        return isSuccess;
    }

}
