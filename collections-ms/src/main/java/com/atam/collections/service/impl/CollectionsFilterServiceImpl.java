package com.atam.collections.service.impl;
import com.atam.collections.dto.Collection;
import com.atam.collections.libs.client.dto.response.CollectionDTO;
import com.atam.collections.libs.client.dto.response.CollectionsResponseDTO;
import com.atam.collections.service.CollectionsFilterService;
import com.atam.collections.service.mapper.CollectionsMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CollectionsFilterServiceImpl implements CollectionsFilterService {

    @Autowired
    private CollectionsMapper mapper;

    @Override
    public List<Collection> getCollections(List<String> id, List<String> title, List<String> description, List<String> coverPhotoId, CollectionsResponseDTO collectionsResponseDTO) {
        return collectionsResponseDTO
                .getCollections()
                .stream()
                .filter(collection -> filter(collection.getId(), id))
                .filter(collection -> filter(collection.getTitle(), title))
                .filter(collection -> filter(collection.getDescription(), description))
                .filter(collection -> filter(collection.getCoverPhotoId(), coverPhotoId))
                .map(dto -> mapper.toCollection(dto))
                .collect(Collectors.toList());
    }

    @Override
    public List<Collection> getCollections(String filter, CollectionsResponseDTO collectionsResponseDTO) {
        return collectionsResponseDTO
                .getCollections()
                .stream()
                .filter(collection -> filter(collection, filter))
                .map(dto -> mapper.toCollection(dto))
                .collect(Collectors.toList());
    }

    private boolean filter(CollectionDTO collection, String filter) {
        String[] split = filter.split(" ");
        String field = split[0].toLowerCase();
        String fieldToMatch = "";
        switch(field){
            case "id": fieldToMatch = collection.getId(); break;
            case "title": fieldToMatch = collection.getTitle(); break;
            case "description": fieldToMatch = collection.getDescription(); break;
            case "cover_photo_id": fieldToMatch = collection.getCoverPhotoId(); break;
        }
        return fieldToMatch.contains(split[2]);
    }

    public boolean filter(String field, List<String> ids) {
        return ids == null ||
                ids.parallelStream()
                        .map(id -> (id.startsWith("~") && field.contains(id.substring(1))) || (!id.startsWith("~") && field.equals(id)))
                        .reduce((a,b) -> a&&b)
                        .orElse(true);
    }
}
