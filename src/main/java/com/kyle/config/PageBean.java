package com.kyle.config;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageBean<T> implements Serializable {

    private List<T> list;

    private Long total;
}
