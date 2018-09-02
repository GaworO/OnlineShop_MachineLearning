package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Product findById(Long id);
//	List<Product> findAllBy

}
