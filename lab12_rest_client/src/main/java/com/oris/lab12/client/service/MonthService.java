package com.oris.lab12.client.service;

import com.oris.lab12.client.restclient.IMonthDataSource;
import com.oris.lab12.client.restclient.MonthRestDataSource;
import com.oris.lab12.model.Month;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class MonthService {

    private final IMonthDataSource dataSource = new MonthRestDataSource();

    public List<Month> findAll() throws URISyntaxException, IOException, InterruptedException {
        return dataSource.findAll();
    }

    public Month findById(Long id) throws URISyntaxException, IOException, InterruptedException {
        return dataSource.findById(id);
    }

    public Month save(Month month) throws URISyntaxException, IOException, InterruptedException {
        return dataSource.save(month);
    }

    public void delete(Long id) throws URISyntaxException, IOException, InterruptedException {
        dataSource.delete(id);
    }
}