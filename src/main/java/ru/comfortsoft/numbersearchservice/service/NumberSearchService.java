package ru.comfortsoft.numbersearchservice.service;

import org.springframework.web.multipart.MultipartFile;
import ru.comfortsoft.numbersearchservice.dto.ResponseDto;
import ru.comfortsoft.numbersearchservice.exception.IncorrectFileFormatException;
import ru.comfortsoft.numbersearchservice.exception.NumberNotFoundException;

import java.io.IOException;

public interface NumberSearchService {

    ResponseDto findNumberInFile(MultipartFile file, Long number) throws IOException, NumberNotFoundException,
            IncorrectFileFormatException;
}
