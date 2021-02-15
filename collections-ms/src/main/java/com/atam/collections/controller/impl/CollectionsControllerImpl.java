package com.atam.collections.controller.impl;

import com.atam.collections.controller.CollectionsApi;
import com.atam.collections.dto.Collection;
import com.atam.collections.exceptions.CollectionsException;
import com.atam.collections.libs.client.dto.request.CollectionsRequestDTO;
import com.atam.collections.libs.client.dto.response.CollectionsResponseDTO;
import com.atam.collections.libs.client.exceptions.ClientException;
import com.atam.collections.libs.client.service.CollectionsService;
import com.atam.collections.libs.client.service.mapper.CollectionsClientMapper;
import com.atam.collections.service.CollectionsFilterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Slf4j
public class CollectionsControllerImpl implements CollectionsApi {

    @Autowired
    private CollectionsService service;

    @Autowired
    private CollectionsFilterService filterService;

    @Value("${unsplash.client-id}")
    private String clientId;

    @Override
    public ResponseEntity<List<Collection>> listCollections(@Valid List<String> id, @Valid List<String> title, @Valid List<String> description, @Valid List<String> coverPhotoId) {
        try {
            log.info("Calling listCollections with ids({}) title({}) description({}) and coverPhotoId({})", id, title, description, coverPhotoId);
            CollectionsRequestDTO collectionsRequestDTO = CollectionsRequestDTO.builder().clientId(clientId).build();
            CollectionsResponseDTO collectionsResponseDTO = service.getCollections(collectionsRequestDTO);
            List<Collection> collections = filterService.getCollections(id, title, description, coverPhotoId, collectionsResponseDTO);
            return new ResponseEntity<>(collections, HttpStatus.OK);
        } catch (ClientException e) {
            throw new CollectionsException(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<List<Collection>> listCollectionsByFilter(@Valid String filter) {
        try {
            log.info("Calling listCollections with filter ({})", filter);
            CollectionsRequestDTO collectionsRequestDTO = CollectionsRequestDTO.builder().clientId(clientId).build();
            CollectionsResponseDTO collectionsResponseDTO = service.getCollections(collectionsRequestDTO);
            List<Collection> collections = filterService.getCollections(filter, collectionsResponseDTO);
            return new ResponseEntity<>(collections, HttpStatus.OK);
        } catch (ClientException e) {
            throw new CollectionsException(e.getMessage());
        }
    }
}
