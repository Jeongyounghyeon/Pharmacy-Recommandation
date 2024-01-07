package com.example.PharmacyRecommendation.pharmacy.service;

import com.example.PharmacyRecommendation.api.dto.DocumentDto;
import com.example.PharmacyRecommendation.api.dto.KakaoApiResponseDto;
import com.example.PharmacyRecommendation.api.service.KakaoAddressSearchService;
import com.example.PharmacyRecommendation.direction.entity.Direction;
import com.example.PharmacyRecommendation.direction.service.DirectionService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    private void recommendPharmacyList(String address) {
        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(kakaoApiResponseDto)) {
            log.error("[PharmacyRecommendationService recommendPharmacyList fail] Input address:{}", address);
            return;
        }

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);

        List<Direction> directionList = directionService.buildDirectionList(documentDto);

        directionService.saveAll(directionList);
    }
}
