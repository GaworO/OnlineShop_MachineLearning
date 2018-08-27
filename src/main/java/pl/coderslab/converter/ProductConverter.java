package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.entity.Product;
import pl.coderslab.repository.ProductRepository;

public class ProductConverter implements Converter<String, Product>{

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product convert(String source) {
		return productRepository.findById(Long.parseLong(source));
	}

}
