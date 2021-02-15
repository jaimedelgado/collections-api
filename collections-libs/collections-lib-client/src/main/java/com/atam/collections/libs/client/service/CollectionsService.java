package com.atam.collections.libs.client.service;

import com.atam.collections.libs.client.dto.request.CollectionsRequestDTO;
import com.atam.collections.libs.client.dto.response.CollectionsResponseDTO;
import com.atam.collections.libs.client.exceptions.ClientException;

public interface CollectionsService {
    public CollectionsResponseDTO getCollections(CollectionsRequestDTO collectionsRequestDTO) throws ClientException;
}
