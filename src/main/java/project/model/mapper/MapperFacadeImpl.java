package project.model.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dto.response.user.UserListResponse;
import project.dto.response.user.UserResponse;
import project.model.User;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class MapperFacadeImpl implements MapperFacade {
    private final MapperFactory mapperFactory;

    @Autowired
    public MapperFacadeImpl(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
        mapperFactory.classMap(UserListResponse.class, User.class)
                .byDefault()
                .register();

        mapperFactory.classMap(UserResponse.class, User.class)
                /// .field("position", "position.name")
                .field("docName", "document.documentType.name")
                .field("docNumber", "document.number")
                .field("docDate", "document.date")
                .field("citizenshipName", "document.country.name")
                .field("citizenshipCode", "document.country.code")
                .byDefault()
                .register();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> D map(S sourceObject, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().map(sourceObject, destinationClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> void map(S sourceObject, D destinationObject) {
        mapperFactory.getMapperFacade().map(sourceObject, destinationObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().mapAsList(source, destinationClass);
    }
}
