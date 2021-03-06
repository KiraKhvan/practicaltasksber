package project.controller.organization;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.dto.filter.organization.OrganizationFilter;
import project.dto.request.organization.AddOrganizationRequest;
import project.dto.request.organization.EditOrganizationRequest;
import project.dto.response.organization.OrganizationListResponse;
import project.dto.response.organization.OrganizationResponse;
import project.service.OrganizationService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "OrganizationController", description = "Управление информацией об организациях")
@RestController
@RequestMapping(value = "/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "Добавить новую организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/save")
    public void save(@RequestBody AddOrganizationRequest request) {
        organizationService.save(request);
    }

    @ApiOperation(value = "Изменить организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/update")
    public void update(@RequestBody EditOrganizationRequest request) {
        organizationService.update(request);
    }

    @ApiOperation(value = "Получить фильтрованный список организаций", httpMethod = "POST")
    @PostMapping("/list")
    public List<OrganizationListResponse> organizations(@RequestBody OrganizationFilter filter) {
        return organizationService.getOrganizations(filter);
    }

    @ApiOperation(value = "Получить организацию по идентификатору", httpMethod = "GET")
    @GetMapping("/{id}")
    public OrganizationResponse organization(@PathVariable Long id) {
        return organizationService.getOrganization(id);
    }
}
