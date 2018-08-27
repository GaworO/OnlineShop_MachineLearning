package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Category;
import pl.coderslab.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	Category findById(Long id);
}
