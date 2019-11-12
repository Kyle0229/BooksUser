package com.kyle.mapper;

import com.kyle.pojo.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogResporitory extends JpaRepository<Catalog,Integer> {
}
