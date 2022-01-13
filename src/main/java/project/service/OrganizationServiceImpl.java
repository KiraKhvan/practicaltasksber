package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.controller.EntityIdParser;
import project.controller.FieldValidator;
import project.dao.organization.OrganizationDao;
import project.dto.filter.organization.OrganizationFilter;
import project.dto.request.organization.AddOrganizationRequest;
import project.dto.request.organization.EditOrganizationRequest;
import project.dto.response.organization.OrganizationListResponse;
import project.dto.response.organization.OrganizationResponse;
import project.model.Organization;
import project.model.mapper.MapperFacade;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao, MapperFacade mapperFacade) {
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public boolean save(AddOrganizationRequest request) {
        Organization organization = updateOrCreateOrganization(
                null,
                request.name,
                request.fullName,
                request.inn,
                request.kpp,
                request.address,
                request.phone,
                request.isActive
        );
        organizationDao.save(organization);
        return true;
    }

    @Override
    public boolean update(EditOrganizationRequest request) {
        Organization organization = updateOrCreateOrganization(
                EntityIdParser.parseId(request.id),
                request.name,
                request.fullName,
                request.inn,
                request.kpp,
                request.address,
                request.phone,
                request.isActive
        );
        organizationDao.update(organization);
        return true;
    }

    @Override
    public List<OrganizationListResponse> getOrganizations(OrganizationFilter filter) {
        FieldValidator.validateRequiredField("Name", filter.name);
        List<Organization> organizationList = organizationDao.getFilteredOrganizationList(
                filter.name,
                filter.inn,
                filter.isActive
        );
        return mapperFacade.mapAsList(organizationList, OrganizationListResponse.class);
    }

    @Override
    public OrganizationResponse getOrganization(String id) {
        return mapperFacade.map(organizationDao.loadById(EntityIdParser.parseId(id)), OrganizationResponse.class);
    }


    protected Organization updateOrCreateOrganization(
            Long id,
            String name,
            String fullName,
            String inn,
            String kpp,
            String address,
            String phone,
            Boolean isActive
    ) {
        FieldValidator.validateRequiredField("Name", name);
        FieldValidator.validateRequiredField("Full name", fullName);
        FieldValidator.validateRequiredField("INN", inn);
        FieldValidator.validateRequiredField("KPP", kpp);
        FieldValidator.validateRequiredField("Address", address);
        Organization organization;
        if (id == null) {
            organization = new Organization();
        } else {
            organization = organizationDao.loadById(id);
        }

        organization.setName(name);
        organization.setFullName(fullName);
        organization.setInn(inn);
        organization.setPhone(phone);
        organization.setKpp(kpp);
        organization.setAddress(address);
        organization.setActive(isActive);

        return organization;
    }
}
