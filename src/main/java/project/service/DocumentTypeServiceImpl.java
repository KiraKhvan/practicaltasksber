package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.documenttype.DocumentTypeDao;
import project.dto.DocumentTypeDto;
import project.model.DocumentType;
import project.model.mapper.MapperFacade;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeDao documentTypeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public DocumentTypeServiceImpl(DocumentTypeDao documentTypeDao, MapperFacade mapperFacade) {
        this.documentTypeDao = documentTypeDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<DocumentTypeDto> getAllDocumentTypes() {
        List<DocumentType> documentTypeList = documentTypeDao.getAllDocumentTypeList();
        return mapperFacade.mapAsList(documentTypeList, DocumentTypeDto.class);
    }
}
