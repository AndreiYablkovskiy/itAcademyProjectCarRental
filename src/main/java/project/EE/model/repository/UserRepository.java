package project.EE.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.EE.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  @Query(value = "SELECT * FROM car_rental.user where username = :username", nativeQuery = true)
  User findByUsername(@Param("username") String username);


}
