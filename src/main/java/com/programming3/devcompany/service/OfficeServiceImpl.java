package com.programming3.devcompany.service;

import com.programming3.devcompany.domain.Office;
import com.programming3.devcompany.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    @Autowired
    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public void createOffice(Office office) {
        officeRepository.addOffice(office);
    }

    @Override
    public void createOffice(String name, String location, Integer officeId) {
        officeRepository.addOffice(new Office(
                name,
                location,
                officeId
        ));
    }

    @Override
    public List<Office> getAllOffices() {
        return officeRepository.getAllOffices();
    }

    @Override
    public Office getOneByName(String name) {
        return (Office) officeRepository.getAllOffices()
                .stream()
                .filter(office -> office.getName().equalsIgnoreCase(name));
    }
}
