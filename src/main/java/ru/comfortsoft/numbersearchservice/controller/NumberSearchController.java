package ru.comfortsoft.numbersearchservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.comfortsoft.numbersearchservice.dto.ResponseDto;
import ru.comfortsoft.numbersearchservice.exception.IncorrectFileFormatException;
import ru.comfortsoft.numbersearchservice.exception.NumberNotFoundException;
import ru.comfortsoft.numbersearchservice.service.NumberSearchService;

import java.io.IOException;

@Tag(name="Контроллер по работе с функционалом поиска числа", description="Спецификация API функционала поиска числа.")
@RestController
public class NumberSearchController {

    private final NumberSearchService numberSearchService;

    @Autowired
    public NumberSearchController(NumberSearchService numberSearchService) {
        this.numberSearchService = numberSearchService;
    }

    @Operation(summary = "Найти указанное число в выбранном файле.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class))
            })
    })
    @PostMapping(value = "/search", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto> findNumberInFile(@Parameter(description = "Файл со списком целых чисел")
                                                        @RequestParam MultipartFile file,
                                                        @Parameter(description = "Искомое число")
                                                        @RequestParam Long number)
            throws IOException, NumberNotFoundException, IncorrectFileFormatException {

        return ResponseEntity.ok(numberSearchService.findNumberInFile(file, number));
    }
}
