package pl.magisterka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.magisterka.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findById(Long id);
	User findByEmail(String email);

}
