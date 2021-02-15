package com.atam.collections.libs.client.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CollectionsResponseDTO {
    private List<CollectionDTO> collections;
}
