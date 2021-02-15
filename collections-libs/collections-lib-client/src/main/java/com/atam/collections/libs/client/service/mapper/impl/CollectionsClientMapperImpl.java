package com.atam.collections.libs.client.service.mapper.impl;

import com.atam.collections.libs.client.dto.response.CollectionDTO;
import com.atam.collections.libs.client.dto.response.CollectionsResponseDTO;
import com.atam.collections.libs.client.service.mapper.CollectionsClientMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@Component
public class CollectionsClientMapperImpl implements CollectionsClientMapper {

    @Override
    public CollectionsResponseDTO toCollectionsResponseDTO(String response) {
        return response.isEmpty() ?
                getCollectionsResponseDTO(Collections.emptyList()) :
                parseJson(response);
    }

    private CollectionsResponseDTO getCollectionsResponseDTO(List<CollectionDTO> objects) {
        return CollectionsResponseDTO.builder()
                .collections(objects)
                .build();
    }

    private CollectionsResponseDTO parseJson(String response) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<CollectionDTO>>(){}.getType();
        List<CollectionDTO> collections = gson.fromJson(response, type);
        return getCollectionsResponseDTO(collections);
    }
}
