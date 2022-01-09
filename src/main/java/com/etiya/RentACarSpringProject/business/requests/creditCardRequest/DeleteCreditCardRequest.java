package com.etiya.RentACarSpringProject.business.requests.creditCardRequest;

import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCreditCardRequest {

	@NotNull
	private int creditCardId;

}