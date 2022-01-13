package project.controller.office;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.dto.filter.office.OfficeFilter;
import project.dto.request.office.AddOfficeRequest;
import project.dto.request.office.EditOfficeRequest;
import project.dto.response.office.OfficeListResponse;
import project.dto.response.office.OfficeResponse;
import project.service.OfficeService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OfficeController", description = "Управление информацией об офисах")
@RestController
@RequestMapping(value = "/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @ApiOperation(value = "Добавить новый офис", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public void save(@RequestBody AddOfficeRequest request) {
        officeService.save(request);
    }

    @ApiOperation(value = "Изменить офис", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/update")
    public void update(@RequestBody EditOfficeRequest request) {
        officeService.update(request);
    }

    @ApiOperation(value = "Получить список всех офисов", httpMethod = "POST")
    @PostMapping("/list")
    public List<OfficeListResponse> offices(@RequestBody OfficeFilter filter) {
        return officeService.getOffices(filter);
    }

    @ApiOperation(value = "Получить офис по идентификатору", httpMethod = "GET")
    @GetMapping("/{id}")
    public OfficeResponse office(@PathVariable Long id) {
        return officeService.getOffice(id);
    }
}
