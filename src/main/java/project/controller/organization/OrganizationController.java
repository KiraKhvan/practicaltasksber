package project.controller.organization;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.service.OrganizationService;
import project.view.OrganizationListView;
import project.view.OrganizationView;

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
    public void save(

            @RequestParam String name,
            @RequestParam String fullName,
            @RequestParam Integer inn,
            @RequestParam Integer kpp,
            @RequestParam String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Boolean isActive
    ) {
        organizationService.save(
                name,
                fullName,
                inn,
                kpp,
                address,
                phone,
                isActive
        );
    }

    @ApiOperation(value = "Изменить организацию", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @PostMapping("/update")
    public void update(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String fullName,
            @RequestParam Integer inn,
            @RequestParam Integer kpp,
            @RequestParam String address,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) Boolean isActive
    ) {
        organizationService.update(
                id,
                name,
                fullName,
                inn,
                kpp,
                address,
                phone,
                isActive
        );
    }

    @ApiOperation(value = "Получить фильтрованный список организаций", httpMethod = "GET")
    @GetMapping("/list")
    public List<OrganizationListView> organizations(
            @RequestParam String name,
            @RequestParam(required = false) String inn,
            @RequestParam(required = false) Boolean isActive
    ) {
        return organizationService.getOrganizations(name, inn, isActive);
    }

    @ApiOperation(value = "Получить организацию по идентификатору", httpMethod = "GET")
    @GetMapping("/{id}")
    public OrganizationView organization(@PathVariable Long id) {
        return organizationService.getOrganization(id);
    }
}
