package com.cognizant.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
	@NotBlank(message = "username cannot be blank")
	private String username;
	@NotBlank(message = "password cannot be blank")
	private String password;
	@Email
	private String email;
	@Size(min = 10, max = 10)
	@Pattern(regexp = "[6-9]{1}[0-9]{9}")
	private long mobileno;
	@NotBlank(message = "Address cannot be blank")
	private String address;

}
