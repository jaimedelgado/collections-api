package com.atam.collections.service;

import com.atam.collections.dto.Collection;
import com.atam.collections.libs.client.dto.response.CollectionsResponseDTO;

import java.util.List;

public interface CollectionsFilterService {
    public List<Collection> getCollections(List<String> ids, List<String> title, List<String> description, List<String> coverPhotoId, CollectionsResponseDTO collectionsResponseDTO);
    public List<Collection> getCollections(String filter, CollectionsResponseDTO collectionsResponseDTO);
}
