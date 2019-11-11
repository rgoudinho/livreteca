package br.com.livreteca.model.dao;

import javax.persistence.EntityManager;

import br.com.livreteca.model.entity.Book;
import br.com.livreteca.model.entity.BookItem;
import br.com.livreteca.util.JPAUtil;

public class BookItemDAO extends AbstractDAO<String, BookItem>{
	protected EntityManager entityManager;
	
	public BookItemDAO() {
		entityManager = JPAUtil.getEntityManager();
	}
	
    public boolean saveBookItem(BookItem bookItem){
        boolean isSuccess = true;
        try {
            JPAUtil.beginTransaction();
            save(bookItem);
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

	public BookItem getById(Long id) {
		return entityManager.find(BookItem.class, id);
	}
}
