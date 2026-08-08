package commerce.yan.platform.dto;

import java.time.LocalDateTime;

public class UserResponse {

	private Long id;
	private String email;
	private String name;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public UserResponse(Long id, String email, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	// getters
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	// setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
