package pl.magisterka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.magisterka.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findById(Long id);

}
