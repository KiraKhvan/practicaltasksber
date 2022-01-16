package project.controller.reference;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.dto.response.CountryDto;
import project.dto.response.DocumentTypeDto;
import project.service.CountryService;
import project.service.DocumentTypeService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "ReferenceController", description = "Управление информацией о справочниках")
@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class ReferenceController {

    private final CountryService countryService;

    private final DocumentTypeService documentTypeService;

    @Autowired
    public ReferenceController(CountryService countryService, DocumentTypeService documentTypeService) {
        this.countryService = countryService;
        this.documentTypeService = documentTypeService;
    }

    @ApiOperation(value = "Получить список всех типов документов", httpMethod = "GET")
    @GetMapping("/docs")
    public List<DocumentTypeDto> docs() {
        return documentTypeService.getAllDocumentTypes();
    }

    @ApiOperation(value = "Получить список всех стран", httpMethod = "GET")
    @GetMapping("/countries")
    public List<CountryDto> countries() {
        return countryService.getAllCountries();
    }
}
