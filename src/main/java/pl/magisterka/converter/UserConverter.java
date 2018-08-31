package pl.magisterka.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.magisterka.entity.User;
import pl.magisterka.repository.UserRepository;

public class UserConverter implements Converter<String, User>{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User convert(String source) {
		return userRepository.findById(Long.parseLong(source));
	}

}
