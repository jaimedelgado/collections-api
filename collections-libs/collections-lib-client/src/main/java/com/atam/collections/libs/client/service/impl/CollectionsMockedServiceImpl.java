package com.atam.collections.libs.client.service.impl;

import com.atam.collections.libs.client.dto.request.CollectionsRequestDTO;
import com.atam.collections.libs.client.dto.response.CollectionsResponseDTO;
import com.atam.collections.libs.client.exceptions.ClientException;
import com.atam.collections.libs.client.service.CollectionsService;
import org.jeasy.random.EasyRandom;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnMissingBean(CollectionsServiceImpl.class )
public class CollectionsMockedServiceImpl implements CollectionsService {
    @Override
    public CollectionsResponseDTO getCollections(CollectionsRequestDTO collectionsRequestDTO) throws ClientException {
        return new EasyRandom().nextObject(CollectionsResponseDTO.class);
    }
}
