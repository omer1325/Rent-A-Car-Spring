package com.etiya.RentACarSpringProject.business.requests.cityRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCityRequest {
   private int cityId;

   String cityName;
}
