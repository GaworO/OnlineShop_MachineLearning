package pl.magisterka.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.magisterka.entity.Product;
import pl.magisterka.repository.ProductRepository;

public class ProductConverter implements Converter<String, Product>{

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product convert(String source) {
		return productRepository.findById(Long.parseLong(source));
	}

}
