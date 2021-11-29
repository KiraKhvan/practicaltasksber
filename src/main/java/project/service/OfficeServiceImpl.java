package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.office.OfficeDao;
import project.dao.organization.OrganizationDao;
import project.model.Office;
import project.model.Organization;
import project.model.mapper.MapperFacade;
import project.view.OfficeListView;
import project.view.OfficeView;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(
            OfficeDao officeDao,
            OrganizationDao organizationDao,
            MapperFacade mapperFacade
    ) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public boolean save(
            Long orgId,
            String name,
            String address,
            String phone,
            Boolean isActive
    ) {
        Office office = updateOrCreateOffice(
                null,
                orgId,
                name,
                address,
                phone,
                isActive
        );
        officeDao.save(office);
        return true;
    }

    @Override
    public boolean update(
            Long officeId,
            String name,
            String address,
            String phone,
            Boolean isActive
    ) {
        Office office = updateOrCreateOffice(
                officeId,
                null,
                name,
                address,
                phone,
                isActive
        );
        officeDao.save(office);
        return true;
    }

    @Override
    public List<OfficeListView> getOffices(
            Long orgId,
            String name,
            String phone,
            Boolean isActive
    ) {
        List<Office> officeList = officeDao.getFilteredOfficeList(orgId, name, phone, isActive);
        return mapperFacade.mapAsList(officeList, OfficeListView.class);
    }

    @Override
    public OfficeView getOffice(Long id) {
        return mapperFacade.map(officeDao.loadById(id), OfficeView.class);
    }

    protected Office updateOrCreateOffice(
            Long officeId,
            Long orgId,
            String name,
            String address,
            String phone,
            Boolean isActive
    ) {
        Office office;
        if (officeId == null) {
            office = new Office();
        } else {
            office = officeDao.loadById(officeId);
        }

        if (orgId != null) {
            Organization organization = organizationDao.loadById(orgId);
            office.setOrganization(organization);
        }

        office.setName(name);
        office.setAddress(address);
        office.setPhone(phone);
        office.setActive(isActive);
        return office;
    }
}
