package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import project.dao.office.OfficeDao;
import project.dao.organization.OrganizationDao;
import project.dto.filter.office.OfficeFilter;
import project.dto.request.office.AddOfficeRequest;
import project.dto.request.office.EditOfficeRequest;
import project.dto.response.office.OfficeListResponse;
import project.dto.response.office.OfficeResponse;
import project.exception.BadRequestException;
import project.exception.NotFoundException;
import project.model.Office;
import project.model.Organization;
import project.model.mapper.MapperFacade;

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
    public boolean save(AddOfficeRequest request) {
        if (request.orgId == null) {
            throw new BadRequestException("Organization id cannot be null");
        }
        Office office = updateOrCreateOffice(
                null,
                request.orgId,
                request.name,
                request.address,
                request.phone,
                request.isActive
        );
        officeDao.save(office);
        return true;
    }

    @Override
    public boolean update(EditOfficeRequest request) {
        if (request.id == null) {
            throw new BadRequestException("Office id cannot be null");
        }
        if (StringUtils.isEmpty(request.name)) {
            throw new BadRequestException("Name cannot be empty");
        }
        if (StringUtils.isEmpty(request.address)) {
            throw new BadRequestException("Address cannot be empty");
        }
        Office office = updateOrCreateOffice(
                request.id,
                null,
                request.name,
                request.address,
                request.phone,
                request.isActive
        );
        officeDao.save(office);
        return true;
    }

    @Override
    public List<OfficeListResponse> getOffices(OfficeFilter filter) {
        List<Office> officeList = officeDao.getFilteredOfficeList(
                filter.orgId,
                filter.name,
                filter.phone,
                filter.isActive
        );
        return mapperFacade.mapAsList(officeList, OfficeListResponse.class);
    }

    @Override
    public OfficeResponse getOffice(Long id) {
        return mapperFacade.map(officeDao.loadById(id), OfficeResponse.class);
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
            if (office == null) {
                throw new NotFoundException("There is no such office");
            }
        }

        if (orgId != null) {
            Organization organization = organizationDao.loadById(orgId);
            if (organization == null) {
                throw new NotFoundException("There is no such organization");
            }
            office.setOrganization(organization);
        }

        office.setName(name);
        office.setAddress(address);
        office.setPhone(phone);
        office.setActive(isActive);
        return office;
    }
}
