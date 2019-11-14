package com.kyle.Request;

import com.kyle.domain.Author;
import com.kyle.domain.Book;
import com.kyle.domain.Catalog;
import lombok.Data;



@Data
public class BookDown {
    Book book;
    Author author;
    Catalog catalog;
}
