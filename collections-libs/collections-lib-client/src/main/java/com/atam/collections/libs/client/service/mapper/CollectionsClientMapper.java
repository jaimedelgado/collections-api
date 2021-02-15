package com.atam.collections.libs.client.service.mapper;

import com.atam.collections.libs.client.dto.response.CollectionsResponseDTO;

public interface CollectionsClientMapper {
    CollectionsResponseDTO toCollectionsResponseDTO(String response);
}
