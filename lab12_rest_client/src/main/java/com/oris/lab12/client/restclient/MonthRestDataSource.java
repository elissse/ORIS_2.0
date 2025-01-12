package com.oris.lab12.client.restclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.oris.lab12.model.Month;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class MonthRestDataSource implements IMonthDataSource {
    public List<Month> findAll() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/lab12rest/month/list"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String respBody = response.body();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(respBody, new TypeReference<List<Month>>() {
        });
    }

    public Month findById(Long id) throws URISyntaxException, IOException, InterruptedException {
        // TODO send get to http://localhost:8080/lab12rest/month/{id}
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/lab12rest/month/%s".formatted(id)))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String respBody = response.body();
        System.out.println(respBody);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(respBody, new TypeReference<Month>() {
        });
    }

    public Month save(Month month)  throws URISyntaxException, IOException, InterruptedException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(month);
        HttpClient client = HttpClient.newHttpClient();
        System.out.println(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/lab12rest/month/save"))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String respBody = response.body();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(respBody);
        return mapper.readValue(respBody, new TypeReference<Month>() {
        });
    }

    public void delete(Long id) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/lab12rest/month/delete/%s".formatted(id)))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String respBody = response.body();
    }
}