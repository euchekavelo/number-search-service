package ru.comfortsoft.numbersearchservice.service.impl;

import com.github.pjfanning.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.comfortsoft.numbersearchservice.dto.ResponseDto;
import ru.comfortsoft.numbersearchservice.exception.IncorrectFileFormatException;
import ru.comfortsoft.numbersearchservice.exception.NumberNotFoundException;
import ru.comfortsoft.numbersearchservice.service.NumberSearchService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
public class NumberSearchServiceImpl implements NumberSearchService {

    @Value("${app.row-cache-size}")
    private Integer rowCacheSize;

    @Value("${app.buffer-size}")
    private Integer bufferSize;

    private static final String CORRECT_FILE_FORMAT = "xlsx";

    @Override
    public ResponseDto findNumberInFile(MultipartFile file, Long number) throws IOException, NumberNotFoundException,
            IncorrectFileFormatException {

        if (!isValidFormatFile(file)) {
            throw new IncorrectFileFormatException("Некорректный формат файла. Необходим формат xlsx.");
        }

        String totalMessage = "";
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = StreamingReader.builder().rowCacheSize(rowCacheSize).bufferSize(bufferSize)
                     .open(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                long numberFromFile = Double.valueOf(cell.getNumericCellValue()).longValue();
                if (number.equals(numberFromFile)) {
                    totalMessage = String.format("Наше число %d было найдено в строке с номером %d.",
                            number, cell.getRowIndex() + 1);
                    break;
                }
            }
        }

        if (totalMessage.isEmpty()) {
            throw new NumberNotFoundException("Указанное число не было найдено в приведенном файле 1-ого " +
                    "листа 1-ой колонки.");
        }

        return ResponseDto.builder().result(true).message(totalMessage).build();
    }

    private boolean isValidFormatFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileExtension = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf(".") + 1);

        return fileExtension.equalsIgnoreCase(CORRECT_FILE_FORMAT);
    }
}
