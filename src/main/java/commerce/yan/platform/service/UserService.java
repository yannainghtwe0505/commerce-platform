package commerce.yan.platform.service;

import commerce.yan.platform.dto.UserCreateRequest;
import commerce.yan.platform.dto.UserResponse;
import commerce.yan.platform.entity.User;
import commerce.yan.platform.exception.EmailAlreadyExistsException;
import commerce.yan.platform.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	// CREATE
	public UserResponse createUser(UserCreateRequest request) {
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new EmailAlreadyExistsException(request.getEmail());
		}

		User user = new User();
		user.setEmail(request.getEmail());
		user.setName(request.getName());
		user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

		return toResponse(userRepository.save(user));
	}

	// GET BY ID
	public UserResponse getUserById(Long id) {
		return userRepository.findById(id).map(this::toResponse).orElse(null);
	}

	// GET ALL
	public List<UserResponse> getAllUsers() {
		return userRepository.findAll().stream().map(this::toResponse).toList();
	}

	// DELETE
	public boolean deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			return false;
		}
		userRepository.deleteById(id);
		return true;
	}

	private UserResponse toResponse(User user) {
		return new UserResponse(user.getId(), user.getEmail(), user.getName(), user.getCreatedAt(),
				user.getUpdatedAt());
	}
}
