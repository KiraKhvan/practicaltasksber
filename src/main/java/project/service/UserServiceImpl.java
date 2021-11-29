package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.country.CountryDao;
import project.dao.document.DocumentDao;
import project.dao.documenttype.DocumentTypeDao;
import project.dao.office.OfficeDao;
import project.dao.position.PositionDao;
import project.dao.user.UserDao;
import project.model.*;
import project.model.mapper.MapperFacade;
import project.view.UserListView;
import project.view.UserView;

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
    public boolean save(
            Long officeId,
            String firstName,
            String secondName,
            String middleName,
            String positionName,
            String phone,
            String docCode,
            String docName,
            Integer docNumber,
            Date docDate,
            String citizenshipCode,
            Boolean isIdentified
    ) {
        User user = updateOrCreateUser(
                null,
                officeId,
                firstName,
                secondName,
                middleName,
                positionName,
                phone,
                isIdentified
        );
        userDao.save(user);

        Document document = updateOrCreateDocument(
                null,
                docName,
                docNumber,
                docDate,
                citizenshipCode
        );
        document.setUser(user);

        documentDao.save(document);

        return true;
    }

    @Override
    public boolean update(
            Long userId,
            Long officeId,
            String firstName,
            String secondName,
            String middleName,
            String positionName,
            String phone,
            String docName,
            Integer docNumber,
            Date docDate,
            String citizenshipCode,
            Boolean isIdentified
    ) {
        User user = updateOrCreateUser(
                userId,
                officeId,
                firstName,
                secondName,
                middleName,
                positionName,
                phone,
                isIdentified
        );

        userDao.update(user);

        Document document = updateOrCreateDocument(
                userId,
                docName,
                docNumber,
                docDate,
                citizenshipCode
        );

        documentDao.update(document);

        return true;
    }

    @Override
    public List<UserListView> getUsers(
            Long officeId,
            String firstName,
            String lastName,
            String middleName,
            String position,
            String docCode,
            String citizenshipCode
    ) {
        List<User> userList = userDao.getFilteredUserList(
                officeId,
                firstName,
                lastName,
                middleName,
                position,
                docCode,
                citizenshipCode
        );

        return mapperFacade.mapAsList(userList, UserListView.class);
    }

    @Override
    public UserView getUser(Long id) {
        return mapperFacade.map(userDao.loadById(id), UserView.class);
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
        user.setPosition(position);
        user.setIdentified(isIdentified);

        return user;
    }

    protected Document updateOrCreateDocument(
            Long userId,
            String docName,
            Integer docNumber,
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