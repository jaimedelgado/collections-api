package com.atam.collections.libs.client.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionDTO {
    private String id;
    private String title;
    private String description;
    private String coverPhotoId;
}
