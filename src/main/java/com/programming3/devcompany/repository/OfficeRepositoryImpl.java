package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Office;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OfficeRepositoryImpl implements OfficeRepository {

    List<Office> offices = new ArrayList<>();

    @Override
    public void addOffice(Office office) {
        if (!offices.contains(office)) {
            offices.add(office);
        }
    }

    @Override
    public List<Office> getAllOffices() {
        return offices;
    }
}
