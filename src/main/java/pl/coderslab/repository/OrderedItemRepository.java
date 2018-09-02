package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.OrderedItem;
import pl.coderslab.entity.Product;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Long> {
	Product findById(Long id);
}
