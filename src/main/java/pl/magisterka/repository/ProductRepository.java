package pl.magisterka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.magisterka.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	Product findById(Long id);
//	List<Product> findAllBy

}
