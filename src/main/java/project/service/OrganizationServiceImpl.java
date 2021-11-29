package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.organization.OrganizationDao;
import project.model.Organization;
import project.model.mapper.MapperFacade;
import project.view.OrganizationListView;
import project.view.OrganizationView;

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
            Integer inn,
            Integer kpp,
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
            Long id,
            String name,
            String fullName,
            Integer inn,
            Integer kpp,
            String address,
            String phone,
            Boolean isActive
    ) {
        Organization organization = updateOrCreateOrganization(
                id,
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
    public List<OrganizationListView> getOrganizations(String name, String inn, Boolean isActive) {
        List<Organization> organizationList = organizationDao.getFilteredOrganizationList(name, inn, isActive);
        return mapperFacade.mapAsList(organizationList, OrganizationListView.class);
    }

    @Override
    public OrganizationView getOrganization(Long id) {
        return mapperFacade.map(organizationDao.loadById(id), OrganizationView.class);
    }


    protected Organization updateOrCreateOrganization(
            Long id,
            String name,
            String fullName,
            Integer inn,
            Integer kpp,
            String address,
            String phone,
            Boolean isActive
    ) {
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
