package com.kyle.Request;

import com.kyle.domain.Book;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookMoney {
   Book book;
   BigDecimal money;
}
