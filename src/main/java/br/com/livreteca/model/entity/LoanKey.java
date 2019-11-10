//package br.com.livreteca.model.entity;
//
//import java.io.Serializable;
//
//import javax.persistence.*;
//
//@Embeddable
//public class LoanKey implements Serializable {
//
//    @Column(name = "id_book")
//    Long bookId;
// 
//    @Column(name = "id_user")
//    Long userId;
//
//    public LoanKey() {
//	}
//    
//	public LoanKey(Long bookId, Long userId) {
//		super();
//		this.bookId = bookId;
//		this.userId = userId;
//	}
//
//	public Long getBookId() {
//		return bookId;
//	}
//
//	public void setBookId(Long bookId) {
//		this.bookId = bookId;
//	}
//
//	public Long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
//		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		LoanKey other = (LoanKey) obj;
//		if (bookId == null) {
//			if (other.bookId != null)
//				return false;
//		} else if (!bookId.equals(other.bookId))
//			return false;
//		if (userId == null) {
//			if (other.userId != null)
//				return false;
//		} else if (!userId.equals(other.userId))
//			return false;
//		return true;
//	}
//	
//	
//
//}
