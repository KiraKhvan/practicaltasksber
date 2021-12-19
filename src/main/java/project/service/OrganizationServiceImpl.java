package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.controller.EntityIdParser;
import project.controller.FieldValidator;
import project.dao.organization.OrganizationDao;
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
    public boolean save(
            String name,
            String fullName,
            String inn,
            String kpp,
            String address,
            String phone,
            Boolean isActive
    ) {
        Organization organization = updateOrCreateOrganization(
                null,
                name,
                fullName,
                inn,
                kpp,
                address,
                phone,
                isActive
        );
        organizationDao.save(organization);
        return true;
    }

    @Override
    public boolean update(
            String id,
            String name,
            String fullName,
            String inn,
            String kpp,
            String address,
            String phone,
            Boolean isActive
    ) {
        Organization organization = updateOrCreateOrganization(
                EntityIdParser.parseId(id),
                name,
                fullName,
                inn,
                kpp,
                address,
                phone,
                isActive
        );
        organizationDao.update(organization);
        return true;
    }

    @Override
    public List<OrganizationListResponse> getOrganizations(String name, String inn, Boolean isActive) {
        FieldValidator.validateRequiredField("Name", name);
        List<Organization> organizationList = organizationDao.getFilteredOrganizationList(name, inn, isActive);
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
