package com.kyle.Request;

import com.kyle.domain.Book;
import com.kyle.domain.Money;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BookMoney implements Serializable {
   Book book;
   BigDecimal money;
}
