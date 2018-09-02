package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findById(Long id);
	Category findByProducts(String name) ;
}
