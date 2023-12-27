package com.programming3.devcompany.repository;

import com.programming3.devcompany.domain.Office;

import java.util.List;

public interface OfficeRepository {

    void addOffice(Office office);

    List<Office> getAllOffices();
}
