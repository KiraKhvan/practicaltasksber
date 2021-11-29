package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.country.CountryDao;
import project.model.Country;
import project.model.mapper.MapperFacade;
import project.view.CountryView;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao, MapperFacade mapperFacade) {
        this.countryDao = countryDao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<CountryView> getAllCountries() {
        List<Country> countryList = countryDao.getAllCountryList();
        return mapperFacade.mapAsList(countryList, CountryView.class);
    }
}
