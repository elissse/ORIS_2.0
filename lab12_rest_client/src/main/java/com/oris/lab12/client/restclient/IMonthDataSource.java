package com.oris.lab12.client.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oris.lab12.model.Month;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface IMonthDataSource {

    List<Month> findAll() throws URISyntaxException, IOException, InterruptedException;

    Month findById(Long id) throws URISyntaxException, IOException, InterruptedException;

    Month save(Month month) throws URISyntaxException, IOException, InterruptedException ;

    void delete(Long id) throws URISyntaxException, IOException, InterruptedException ;
}