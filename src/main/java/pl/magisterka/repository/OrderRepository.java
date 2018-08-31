package pl.magisterka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.magisterka.entity.Category;
import pl.magisterka.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	Category findById(Long id);
}
