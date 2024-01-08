package com.example.PharmacyRecommendation.pharmacy.service;

import com.example.PharmacyRecommendation.api.dto.DocumentDto;
import com.example.PharmacyRecommendation.api.dto.KakaoApiResponseDto;
import com.example.PharmacyRecommendation.api.service.KakaoAddressSearchService;
import com.example.PharmacyRecommendation.direction.dto.OutputDto;
import com.example.PharmacyRecommendation.direction.entity.Direction;
import com.example.PharmacyRecommendation.direction.service.DirectionService;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public List<OutputDto> recommendPharmacyList(String address) {
        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(kakaoApiResponseDto)) {
            log.error("[PharmacyRecommendationService recommendPharmacyList fail] Input address:{}", address);
            return Collections.emptyList();
        }

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);

//        List<Direction> directionList = directionService.buildDirectionList(documentDto);
        List<Direction> directionList = directionService.buildDirectionListByCategoryApi(documentDto);


        return directionService.saveAll(directionList)
                .stream()
                .map(this::converToOutputDto)
                .collect(Collectors.toList());
    }

    private OutputDto converToOutputDto(Direction direction) {
        return OutputDto.builder()
                .pharmacyName(direction.getTargetPharmacyName())
                .pharmacyAddress(direction.getTargetAddress())
                .directionUrl("todo")   // todo
                .roadViewUrl("todo")    // todo
                .distance(String.format("%.2f km", direction.getDistance()))
                .build();
    }
}
