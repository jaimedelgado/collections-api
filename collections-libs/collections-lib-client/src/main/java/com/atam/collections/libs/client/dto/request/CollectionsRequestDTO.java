package com.atam.collections.libs.client.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionsRequestDTO {
    private Integer page;
    private Integer perPage;
    private String clientId;
}
