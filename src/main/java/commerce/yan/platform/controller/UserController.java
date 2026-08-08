package commerce.yan.platform.controller;

import commerce.yan.platform.dto.UserCreateRequest;
import commerce.yan.platform.dto.UserResponse;
import commerce.yan.platform.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// POST /api/users
	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest dto) {
		UserResponse created = userService.createUser(dto);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(created.getId())
				.toUri();

		return ResponseEntity.created(location).body(created);
	}

	// GET /api/users/{id}
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
		UserResponse user = userService.getUserById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

	// GET /api/users
	@GetMapping
	public List<UserResponse> getAllUsers() {
		return userService.getAllUsers();
	}

	// DELETE /api/users/{id}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		boolean deleted = userService.deleteUser(id);
		if (!deleted) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
