package com.etiya.RentACarSpringProject.business.dtos.forUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUserDto {

	private int userId;

	private String email;

}
