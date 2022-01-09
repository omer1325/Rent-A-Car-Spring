package com.etiya.RentACarSpringProject.business.abstracts.languages;


import com.etiya.RentACarSpringProject.business.dtos.languages.WordDto;
import com.etiya.RentACarSpringProject.business.requests.languages.Word.CreateWordRequest;
import com.etiya.RentACarSpringProject.business.requests.languages.Word.DeleteWordRequest;
import com.etiya.RentACarSpringProject.business.requests.languages.Word.UpdateWordRequest;
import com.etiya.RentACarSpringProject.core.results.DataResult;
import com.etiya.RentACarSpringProject.core.results.Result;

import java.util.List;

public interface WordService {

    DataResult<List<WordDto>> getAll();
    Result save( CreateWordRequest createWordRequest);
    Result update( UpdateWordRequest updateWordRequest);
    Result delete( DeleteWordRequest deleteWordRequest);
    Result checkWordIdExists(int wordId);
    Result checkKeyExists(String key);
    Result  checkIfLanguageWordIdExists(int wordId);
}
