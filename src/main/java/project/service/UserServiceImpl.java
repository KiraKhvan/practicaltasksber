package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import project.dao.country.CountryDao;
import project.dao.document.DocumentDao;
import project.dao.documenttype.DocumentTypeDao;
import project.dao.office.OfficeDao;
import project.dao.position.PositionDao;
import project.dao.user.UserDao;
import project.dto.filter.user.UserFilter;
import project.dto.request.user.AddUserRequest;
import project.dto.request.user.EditUserRequest;
import project.dto.response.user.UserListResponse;
import project.dto.response.user.UserResponse;
import project.exception.BadRequestException;
import project.model.*;
import project.model.mapper.MapperFacade;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final PositionDao positionDao;
    private final DocumentDao documentDao;
    private final CountryDao countryDao;
    private final DocumentTypeDao documentTypeDao;

    private final MapperFacade mapperFacade;

    @Autowired
    public UserServiceImpl(
            UserDao userDao,
            OfficeDao officeDao,
            PositionDao positionDao,
            DocumentDao documentDao,
            CountryDao countryDao,
            DocumentTypeDao documentTypeDao,
            MapperFacade mapperFacade
    ) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.positionDao = positionDao;
        this.documentDao = documentDao;
        this.countryDao = countryDao;
        this.documentTypeDao = documentTypeDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public boolean save(AddUserRequest request) {
        if (request.officeId == null) {
            throw new BadRequestException("Office cannot be null");
        }
        if (StringUtils.isEmpty(request.firstName)) {
            throw new BadRequestException("FirstName cannot be empty");
        }
        if (StringUtils.isEmpty(request.position)) {
            throw new BadRequestException("Position cannot be empty");
        }
        User user = updateOrCreateUser(
                null,
                request.officeId,
                request.firstName,
                request.lastName,
                request.middleName,
                request.position,
                request.phone,
                request.isIdentified
        );
        userDao.save(user);

        Document document = updateOrCreateDocument(
                null,
                request.docName,
                request.docNumber,
                request.docDate,
                request.citizenshipCode
        );
        document.setUser(user);

        documentDao.save(document);

        return true;
    }

    @Override
    public boolean update(EditUserRequest request) {
        if (request.id == null) {
            throw new BadRequestException("Id cannot be null");
        }
        if (StringUtils.isEmpty(request.firstName)) {
            throw new BadRequestException("FirstName cannot be empty");
        }
        if (StringUtils.isEmpty(request.position)) {
            throw new BadRequestException("Position cannot be empty");
        }
        User user = updateOrCreateUser(
                request.id,
                request.officeId,
                request.firstName,
                request.lastName,
                request.middleName,
                request.position,
                request.phone,
                request.isIdentified
        );

        userDao.update(user);

        Document document = updateOrCreateDocument(
                request.id,
                request.docName,
                request.docNumber,
                request.docDate,
                request.citizenshipCode
        );

        documentDao.update(document);

        return true;
    }

    @Override
    public List<UserListResponse> getUsers(UserFilter filter) {
        if (filter.officeId == null) {
            throw new BadRequestException("Office cannot be null");
        }
        List<User> userList = userDao.getFilteredUserList(
                filter.officeId,
                filter.firstName,
                filter.lastName,
                filter.middleName,
                filter.position,
                filter.docCode,
                filter.citizenshipCode
        );

        return mapperFacade.mapAsList(userList, UserListResponse.class);
    }

    @Override
    public UserResponse getUser(Long id) {
        return mapperFacade.map(userDao.loadById(id), UserResponse.class);
    }

    protected User updateOrCreateUser(
            Long userId,
            Long officeId,
            String firstName,
            String secondName,
            String middleName,
            String positionName,
            String phone,
            Boolean isIdentified
    ) {
        User user;
        if (userId == null) {
            user = new User();
        } else {
            user = userDao.loadById(userId);
        }

        Office office = officeDao.loadById(officeId);
        Position position = positionDao.loadByName(positionName);

        user.setOffice(office);
        user.setFirstName(firstName);
        user.setLastName(secondName);
        user.setPhone(phone);
        user.setMiddleName(middleName);
        user.setIdentified(isIdentified);

        return user;
    }

    protected Document updateOrCreateDocument(
            Long userId,
            String docName,
            String docNumber,
            Date docDate,
            String citizenshipCode
    ) {
        Document document;

        if (userId == null) {
            document = new Document();
        } else {
            document = documentDao.loadByUserId(userId);
        }

        Country country = countryDao.loadByCode(citizenshipCode);
        DocumentType documentType = documentTypeDao.loadByName(docName);

        document.setCountry(country);
        document.setDate(docDate);
        document.setNumber(docNumber);
        document.setDocumentType(documentType);

        return document;
    }
}
