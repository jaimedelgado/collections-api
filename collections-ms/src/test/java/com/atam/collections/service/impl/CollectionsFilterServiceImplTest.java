package com.atam.collections.service.impl;

import com.atam.collections.dto.Collection;
import com.atam.collections.libs.client.dto.response.CollectionDTO;
import com.atam.collections.libs.client.dto.response.CollectionsResponseDTO;
import com.atam.collections.libs.client.service.mapper.impl.CollectionsClientMapperImpl;
import com.atam.collections.service.mapper.CollectionsMapper;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Collections filter service tests")
@ExtendWith(MockitoExtension.class)
class CollectionsFilterServiceImplTest {
    @InjectMocks
    private static CollectionsFilterServiceImpl service;
    @Spy
    private CollectionsMapper mapper;

    private List<String> searchId1 = List.of("i1");
    private List<String> searchTitle1 = List.of("t1");
    private List<String> searchDescription1 = List.of("d1");
    private List<String> searchCover1 = List.of("c1");

    @BeforeAll
    public static void setUp() {
        service = new CollectionsFilterServiceImpl();
    }

    @Test
    void filterId() {
        //GIVEN
        CollectionDTO collection1 = collection("i1", "t1", "d1", "c1");
        CollectionDTO collection2 = collection("i2", "t2", "d2", "c2");
        CollectionsResponseDTO responseDTO = collections(collection1,collection2);
        ArgumentCaptor<CollectionDTO> captor = ArgumentCaptor.forClass(CollectionDTO.class);
        when(mapper.toCollection(any())).thenReturn(new Collection());
        service.getCollections(searchId1, null, null, null, responseDTO);
        Mockito.verify(mapper).toCollection(captor.capture());
        Assertions.assertEquals(collection1, captor.getValue());
    }

    @Test
    void filterTitle() {
        //GIVEN
        CollectionDTO collection1 = collection("i1", "t1", "d1", "c1");
        CollectionDTO collection2 = collection("i2", "t2", "d2", "c2");
        CollectionsResponseDTO responseDTO = collections(collection1,collection2);
        ArgumentCaptor<CollectionDTO> captor = ArgumentCaptor.forClass(CollectionDTO.class);
        when(mapper.toCollection(any())).thenReturn(new Collection());
        service.getCollections(null, searchTitle1, null, null, responseDTO);
        Mockito.verify(mapper).toCollection(captor.capture());
        Assertions.assertEquals(collection1, captor.getValue());
    }

    @Test
    void filterDescription() {
        //GIVEN
        CollectionDTO collection1 = collection("i1", "t1", "d1", "c1");
        CollectionDTO collection2 = collection("i2", "t2", "d2", "c2");
        CollectionsResponseDTO responseDTO = collections(collection1,collection2);
        ArgumentCaptor<CollectionDTO> captor = ArgumentCaptor.forClass(CollectionDTO.class);
        when(mapper.toCollection(any())).thenReturn(new Collection());
        service.getCollections(null, null, searchDescription1, null, responseDTO);
        Mockito.verify(mapper).toCollection(captor.capture());
        Assertions.assertEquals(collection1, captor.getValue());
    }

    @Test
    void filterCoverPhotoId() {
        //GIVEN
        CollectionDTO collection1 = collection("i1", "t1", "d1", "c1");
        CollectionDTO collection2 = collection("i2", "t2", "d2", "c2");
        CollectionsResponseDTO responseDTO = collections(collection1,collection2);
        ArgumentCaptor<CollectionDTO> captor = ArgumentCaptor.forClass(CollectionDTO.class);
        when(mapper.toCollection(any())).thenReturn(new Collection());
        service.getCollections(null, null, null, searchCover1, responseDTO);
        Mockito.verify(mapper).toCollection(captor.capture());
        Assertions.assertEquals(collection1, captor.getValue());
    }

    private CollectionsResponseDTO collections(CollectionDTO ... collections){
        return CollectionsResponseDTO.builder().collections(java.util.Arrays.asList(collections.clone())).build();
    }

    private CollectionDTO collection(String id, String title, String description, String coverPhotoId){
        return CollectionDTO.builder()
                .id(id)
                .title(title)
                .description(description)
                .coverPhotoId(coverPhotoId)
                .build();
    }
}
