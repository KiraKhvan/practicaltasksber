package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import project.dao.organization.OrganizationDao;
import project.dto.filter.organization.OrganizationFilter;
import project.dto.request.organization.AddOrganizationRequest;
import project.dto.request.organization.EditOrganizationRequest;
import project.dto.response.organization.OrganizationListResponse;
import project.dto.response.organization.OrganizationResponse;
import project.exception.BadRequestException;
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
                request.id,
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
        if (StringUtils.isEmpty(filter.name)) {
            throw new BadRequestException("Name cannot be empty");
        }
        List<Organization> organizationList = organizationDao.getFilteredOrganizationList(
                filter.name,
                filter.inn,
                filter.isActive
        );
        return mapperFacade.mapAsList(organizationList, OrganizationListResponse.class);
    }

    @Override
    public OrganizationResponse getOrganization(Long id) {
        return mapperFacade.map(organizationDao.loadById(id), OrganizationResponse.class);
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
        if (StringUtils.isEmpty(name)) {
            throw new BadRequestException("Name cannot be empty");
        }
        if (StringUtils.isEmpty(fullName)) {
            throw new BadRequestException("Full name cannot be empty");
        }
        if (StringUtils.isEmpty(inn)) {
            throw new BadRequestException("INN cannot be empty");
        }
        if (StringUtils.isEmpty(kpp)) {
            throw new BadRequestException("KPP cannot be empty");
        }
        if (StringUtils.isEmpty(address)) {
            throw new BadRequestException("Address cannot be empty");
        }
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
