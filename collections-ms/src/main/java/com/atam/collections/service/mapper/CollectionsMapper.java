package com.atam.collections.service.mapper;

import com.atam.collections.dto.Collection;
import com.atam.collections.libs.client.dto.response.CollectionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CollectionsMapper {

    public Collection toCollection(CollectionDTO dto);
}
