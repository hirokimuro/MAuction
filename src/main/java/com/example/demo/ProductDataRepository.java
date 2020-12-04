package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface ProductDataRepository extends 
JpaRepository<ProductData,Long>{
	
}
