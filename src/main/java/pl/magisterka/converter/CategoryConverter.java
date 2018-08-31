package pl.magisterka.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.magisterka.entity.Category;
import pl.magisterka.repository.CategoryRepository;

public class CategoryConverter implements Converter<String, Category>{

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category convert(String source) {
		return categoryRepository.findById(Long.parseLong(source));
	}

}
