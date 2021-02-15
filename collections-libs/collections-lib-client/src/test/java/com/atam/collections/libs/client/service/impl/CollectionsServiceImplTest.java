package com.atam.collections.libs.client.service.impl;

import com.atam.collections.libs.client.dto.request.CollectionsRequestDTO;
import com.atam.collections.libs.client.dto.response.CollectionDTO;
import com.atam.collections.libs.client.dto.response.CollectionsResponseDTO;
import com.atam.collections.libs.client.service.mapper.CollectionsClientMapper;
import com.atam.collections.libs.client.exceptions.ClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Collections service tests")
@ExtendWith(MockitoExtension.class)
class CollectionsServiceImplTest {
    @InjectMocks
    private CollectionsServiceImpl collectionsService;
    @Mock
    private HttpClient httpClient;
    @Mock
    private CollectionsClientMapper mapper;
    @Mock
    private HttpResponse httpResponse;

    private static CollectionsResponseDTO expected = getExpectedResponse();

    private static CollectionsResponseDTO getExpectedResponse() {
        return CollectionsResponseDTO.builder()
                .collections(
                        Arrays.asList(
                                CollectionDTO.builder()
                                        .id("1")
                                        .title("title")
                                        .description("description")
                                        .coverPhotoId("coverPhotoId")
                                        .build()))
                .build();
    }

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(collectionsService, "host", "host");
        ReflectionTestUtils.setField(collectionsService, "path", "path");
    }

    @Test
    public void whenCallService_thenReturnMappedResult() throws IOException, InterruptedException, ClientException {
        //GIVEN
        CollectionsRequestDTO collectionsRequestDTO = CollectionsRequestDTO.builder().build();
        //WHEN
        when(httpClient.send(any(), any())).thenReturn(httpResponse);
        when(mapper.toCollectionsResponseDTO(any())).thenReturn(expected);
        CollectionsResponseDTO result = collectionsService.getCollections(collectionsRequestDTO);
        //THEN
        assertEquals(expected, result);

    }
}
