package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.DatailProduct;

@Repository
public interface DatailProductRepository extends 
JpaRepository<DatailProduct,Long> {

}
