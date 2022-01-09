package com.etiya.RentACarSpringProject.business.dtos.forCar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairDto {

	private int repairId;
	
	private int carId;	
	
	private String repairStartDate;
	
	private String repairFinishDate;

}
