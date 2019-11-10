package br.com.livreteca.model.mapper;

import br.com.livreteca.dto.BookDTO;
import br.com.livreteca.model.entity.Book;

public class BookMapper {
    public static Book toEntity(BookDTO dto){
        Book entity = new Book(dto.getName(), dto.getWriter(), dto.getAmount());
        return entity;
    }

    public static BookDTO toDTO(Book entity){
        BookDTO dto = new BookDTO(entity.getName(), entity.getWriter(), entity.getAmount());
        return dto;
    }
}
