package com.atam.collections.libs.client.service.mapper.impl;

import com.atam.collections.libs.client.dto.response.CollectionDTO;
import com.atam.collections.libs.client.dto.response.CollectionsResponseDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Collections mapper tests")
public class CollectionsClientMapperImplTest {
    private static CollectionsClientMapperImpl collectionsMapper;

    @BeforeAll
    public static void setUp() {
        collectionsMapper = new CollectionsClientMapperImpl();
    }

    @DisplayName("Given empty json, when map to response, then return empty list")
    @ParameterizedTest
    @MethodSource("givenEmptyJson_whenMapToResponse_thenReturnEmptyList")
    public void givenEmptyJson_whenMapToResponse_thenReturnEmptyList(String input, CollectionsResponseDTO expected) {
        assertEquals(expected, collectionsMapper.toCollectionsResponseDTO(input));
    }

    @DisplayName("Given json with 1 collection, when map to response, then return given mapped collection")
    @ParameterizedTest
    @MethodSource("givenJsonWithOneCollection_whenMapToResponse_thenReturnGivenMappedCollection")
    public void givenJsonWithOneCollection_whenMapToResponse_thenReturnGivenMappedCollection(String input, CollectionsResponseDTO expected) {
        assertEquals(expected, collectionsMapper.toCollectionsResponseDTO(input));
    }

    @DisplayName("Given json with 2 collections, when map to response, then return given mapped collections")
    @ParameterizedTest
    @MethodSource("givenJsonWithTwoCollections_whenMapToResponse_thenReturnGivenMappedCollections")
    public void givenJsonWithTwoCollections_whenMapToResponse_thenReturnGivenMappedCollections(String input, CollectionsResponseDTO expected) {
        assertEquals(expected, collectionsMapper.toCollectionsResponseDTO(input));
    }

    private static Stream<Arguments> givenEmptyJson_whenMapToResponse_thenReturnEmptyList() throws IOException {
        CollectionsResponseDTO expected = collectionsResponse();
        return getArgumentsStream("collections-empty-response.json", expected);
    }

    private static Stream<Arguments> givenJsonWithOneCollection_whenMapToResponse_thenReturnGivenMappedCollection() throws IOException {
        CollectionsResponseDTO expected = collectionsResponse(
                collection("296", "I like a man with a beard.", "Yeah even Santa...", "C-mxLOk6ANs")
        );
        return getArgumentsStream("collections-one-response.json", expected);
    }

    private static Stream<Arguments> givenJsonWithTwoCollections_whenMapToResponse_thenReturnGivenMappedCollections() throws IOException {
        CollectionsResponseDTO expected = collectionsResponse(
                collection("296", "I like a man with a beard.", "Yeah even Santa...", "C-mxLOk6ANs"),
                collection("292", "I like a man with a beard 2.", "Yeah even Santa 2...", "C-mxLOk6AN2")
        );
        return getArgumentsStream("collections-two-response.json", expected);
    }

    /////////////////7AUXILIAR METHODS

    private static Stream<Arguments> getArgumentsStream(String inputFileName, CollectionsResponseDTO collectionsResponseDTO) throws IOException {
        String input = readFile(inputFileName);
        CollectionsResponseDTO expected = collectionsResponseDTO;
        return Stream.of(Arguments.of(input, expected));
    }

    private static String readFile(String fileName) throws IOException {
        ClassLoader classLoader = CollectionsClientMapperImplTest.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return Files.readString(file.toPath());
    }

    private static CollectionsResponseDTO collectionsResponse(CollectionDTO... collections) {
        return CollectionsResponseDTO.builder()
                .collections(Arrays.asList(collections))
                .build();
    }

    private static CollectionDTO collection(String id, String title, String description, String coverPhotoId) {
        return CollectionDTO.builder()
                .id(id)
                .title(title)
                .description(description)
                .coverPhotoId(coverPhotoId)
                .build();
    }
}
