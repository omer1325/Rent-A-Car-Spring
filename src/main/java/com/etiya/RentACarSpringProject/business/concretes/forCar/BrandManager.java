package com.etiya.RentACarSpringProject.business.concretes.forCar;

import java.util.List;
import lombok.var;
import java.util.stream.Collectors;

import com.etiya.RentACarSpringProject.business.abstracts.languages.LanguageWordService;
import com.etiya.RentACarSpringProject.business.constants.Messages;
import com.etiya.RentACarSpringProject.core.results.*;
import com.etiya.RentACarSpringProject.dataAccess.forCar.BrandDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.etiya.RentACarSpringProject.business.abstracts.forCar.BrandService;
import com.etiya.RentACarSpringProject.business.dtos.forCar.BrandDto;
import com.etiya.RentACarSpringProject.business.requests.brandRequest.CreateBrandRequest;
import com.etiya.RentACarSpringProject.business.requests.brandRequest.DeleteBrandRequest;
import com.etiya.RentACarSpringProject.business.requests.brandRequest.UpdateBrandRequest;
import com.etiya.RentACarSpringProject.core.business.BusinessRules;
import com.etiya.RentACarSpringProject.core.mapping.ModelMapperService;

import com.etiya.RentACarSpringProject.entities.Brand;

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	private Environment environment;
	private LanguageWordService languageWordService;

	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService, Environment environment, LanguageWordService languageWordService) {
		super();
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
		this.environment = environment;
		this.languageWordService = languageWordService;
	}

	@Override
	public DataResult<List<Brand>> findAll() {

        return new SuccessDataResult<List<Brand>>(this.brandDao.findAll(),languageWordService.getByLanguageAndKeyId(Messages.BrandsListed,Integer.parseInt(environment.getProperty("language"))));

	}

	@Override
	public DataResult<List<BrandDto>> getAll() {

		List<Brand> brands = this.brandDao.findAll();
		List<BrandDto> brandsDto = brands.stream()
				.map(brand -> modelMapperService.forDto().map(brand, BrandDto.class))
				.collect(Collectors.toList());

        return new SuccessDataResult<List<BrandDto>>(brandsDto,languageWordService.getByLanguageAndKeyId(Messages.BrandsListed,Integer.parseInt(environment.getProperty("language"))));

	}

	@Override
	public DataResult<Brand> findById(int brandId) {

		var result = BusinessRules.run(checkIfBrandIdExists(brandId));

		if (result != null) {
			return new ErrorDataResult(result);
		}

        return new SuccessDataResult<Brand>(this.brandDao.getById(brandId), languageWordService.getByLanguageAndKeyId(Messages.GetBrand,Integer.parseInt(environment.getProperty("language"))));

	}

	@Override
	public DataResult<BrandDto> getById(int brandId) {

		var result = BusinessRules.run(checkIfBrandIdExists(brandId));

		if (result != null) {
			return new ErrorDataResult(result);
		}

		Brand brand = this.brandDao.getById(brandId);

        return new SuccessDataResult<BrandDto>(modelMapperService.forDto().map(brand, BrandDto.class), languageWordService.getByLanguageAndKeyId(Messages.GetBrand,Integer.parseInt(environment.getProperty("language"))));

	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {

		var result = BusinessRules.run(this.checkIfBrandNameExists(createBrandRequest.getBrandName()));

		if (result != null) {
			return result;
		}

		Brand brand = modelMapperService.forDto().map(createBrandRequest, Brand.class);

		this.brandDao.save(brand);
        return new SuccessResult(languageWordService.getByLanguageAndKeyId(Messages.BrandAdded,Integer.parseInt(environment.getProperty("language"))));

	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {

		var result = BusinessRules.run(this.checkIfBrandNameExists(updateBrandRequest.getBrandName()),
				checkIfBrandIdExists(updateBrandRequest.getBrandId()));

		if (result != null) {
			return result;
		}

		Brand brand = modelMapperService.forDto().map(updateBrandRequest, Brand.class);

		this.brandDao.save(brand);
        return new SuccessResult(languageWordService.getByLanguageAndKeyId(Messages.BrandUpdated,Integer.parseInt(environment.getProperty("language"))));

	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {

		var result = BusinessRules.run(checkIfBrandIdExists(deleteBrandRequest.getBrandId()));

		if (result != null) {
			return result;
		}

		Brand brand = this.brandDao.getById(deleteBrandRequest.getBrandId());

		this.brandDao.delete(brand);
        return new SuccessResult(languageWordService.getByLanguageAndKeyId(Messages.BrandDeleted,Integer.parseInt(environment.getProperty("language"))));

	}

	private Result checkIfBrandNameExists(String brandName) {

		if (this.brandDao.existsByBrandName(brandName)) {
	        return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.ExistBrand,Integer.parseInt(environment.getProperty("language"))));

		}
		return new SuccessResult();
	}

	public Result checkIfBrandIdExists(int brandId) {

		var result = this.brandDao.existsById(brandId);
		if (!result) {
	        return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.NoBrand,Integer.parseInt(environment.getProperty("language"))));

		}
		return new SuccessResult();
	}

}
