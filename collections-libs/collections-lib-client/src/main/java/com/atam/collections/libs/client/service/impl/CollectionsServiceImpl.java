package com.atam.collections.libs.client.service.impl;

import com.atam.collections.libs.client.dto.request.CollectionsRequestDTO;
import com.atam.collections.libs.client.service.CollectionsService;
import com.atam.collections.libs.client.service.mapper.CollectionsClientMapper;
import com.atam.collections.libs.client.exceptions.ClientException;
import com.atam.collections.libs.client.dto.response.CollectionsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@ConditionalOnProperty(
        value="collections.libs.client.mock",
        havingValue = "false",
        matchIfMissing = true
)
@Primary
public class CollectionsServiceImpl implements CollectionsService {

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private CollectionsClientMapper mapper;

    @Value("${unsplash.host}")
    private String host;
    @Value("${unsplash.collections.path}")
    private String path;

    @Override
    public CollectionsResponseDTO getCollections(CollectionsRequestDTO collectionsRequestDTO) throws ClientException {
        return getCollectionsResponseDTO(collectionsRequestDTO);
    }

    private CollectionsResponseDTO getCollectionsResponseDTO(CollectionsRequestDTO collectionsRequestDTO) throws ClientException {
        HttpRequest httpRequest = getHttpRequest(collectionsRequestDTO);
        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return handleResponse(response);
        } catch (IOException e) {
            throw new ClientException("Error sending request: " + httpRequest.uri());
        } catch (InterruptedException e) {
            throw new ClientException("Interruption sending request: " + httpRequest.uri());
        }
    }

    private CollectionsResponseDTO handleResponse(HttpResponse<String> response) throws ClientException {
        final int statusCode = response.statusCode();
        String responseBody = "";
        switch (statusCode) {
            case 200:
                responseBody = response.body();
                break;
            case 401:
                throw new ClientException("Unauthorized request: " + response.request().uri());
        }
        return mapper.toCollectionsResponseDTO(responseBody);
    }

    public HttpRequest getHttpRequest(CollectionsRequestDTO collectionsRequestDTO) throws ClientException {
        try {
            return createUri(collectionsRequestDTO);
        } catch (URISyntaxException e) {
            throw new ClientException("Malformed uri: " + host + "/" + path);
        }
    }

    private HttpRequest createUri(CollectionsRequestDTO collectionsRequestDTO) throws URISyntaxException {
        UriBuilder uriBuilder = UriBuilder
                .fromUri(new URI("https", host, "/" + path, null));
        addQueryParam(uriBuilder, "page", collectionsRequestDTO.getPage());
        addQueryParam(uriBuilder, "per_page", collectionsRequestDTO.getPerPage());
        addQueryParam(uriBuilder, "client_id", collectionsRequestDTO.getClientId());
        return HttpRequest.newBuilder()
                .GET()
                .uri(uriBuilder.build())
                //.setHeader(HttpHeaders.AUTHORIZATION, /*"Bearer " + collectionsRequestDTO.getToken()*/"Bearer nBxq3ZTiMC0zoDVmdLF-GC8xS-UBVEmo467ltBX2NRI")
                .build();
    }

    private void addQueryParam(UriBuilder uriBuilder, String param, Object value) {
        if (value != null) {
            uriBuilder.queryParam(param, value);
        }

    }
}
