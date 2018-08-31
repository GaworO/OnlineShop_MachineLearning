package pl.magisterka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.magisterka.entity.OrderedItem;
import pl.magisterka.entity.Product;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {
	Product findById(Long id);
}
