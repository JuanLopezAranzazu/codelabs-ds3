package com.juanlopezaranzazu.api_rest.service;

import com.juanlopezaranzazu.api_rest.model.Country;
import com.juanlopezaranzazu.api_rest.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }

    public Optional<Country> getCountryByName(String name) {
        return countryRepository.findByName(name);
    }

    public Country saveCountry(Country pais) {
        return countryRepository.save(pais);
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
