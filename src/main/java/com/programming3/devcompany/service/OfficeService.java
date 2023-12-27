package com.programming3.devcompany.service;

import com.programming3.devcompany.domain.Office;

import java.util.List;

public interface OfficeService {

    void createOffice(Office office);

    void createOffice(String name, String location, Integer officeId);

    List<Office> getAllOffices();

    Office getOneByName(String name);

}
