package com.kyle.mapper;

import com.kyle.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog,Integer> {
}
