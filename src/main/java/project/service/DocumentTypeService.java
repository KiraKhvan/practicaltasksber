package project.service;

import org.springframework.validation.annotation.Validated;
import project.dto.response.DocumentTypeDto;

import java.util.List;

/**
 * Сервис
 */
@Validated
public interface DocumentTypeService {

    /**
     * Получить список всех типов документов
     *
     * @return {@DocumentType}
     */
    List<DocumentTypeDto> getAllDocumentTypes();
}
