package br.com.livreteca.model.dao;

import javax.persistence.EntityManager;

import br.com.livreteca.model.entity.Book;
import br.com.livreteca.util.JPAUtil;

public class BookDAO extends AbstractDAO<String, Book> {
	protected EntityManager entityManager;

	public BookDAO() {
		entityManager = JPAUtil.getEntityManager();
	}
	
    public boolean saveBook(Book book){
        boolean isSuccess = true;
        try {
            JPAUtil.beginTransaction();
            save(book);
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

	public void remove(Book book) {
		try {
			entityManager.getTransaction().begin();
			book = entityManager.find(Book.class, book.getId());
			entityManager.remove(book);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public Book getById(Long id) {
		return entityManager.find(Book.class, id);
	}

	public void update(Book book, Long id) {
		Book bookE = entityManager.find(Book.class, id);
		try {
			entityManager.getTransaction().begin();
			bookE.setName(book.getName());
			bookE.setWriter(book.getWriter());
			bookE.setAmount(book.getAmount());
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
}
