package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.UserData;

@Repository
public interface UserDataRepository extends 
JpaRepository<UserData,Long> {

	UserData findByUsername(String username);

}
