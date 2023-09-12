package com.kestrel.weddingbookkeeper.api.wedding.factory;

import com.kestrel.weddingbookkeeper.api.member.vo.Gender;
import com.kestrel.weddingbookkeeper.api.wedding.service.WeddingStrategy;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class WeddingStrategyFactory {

    private final Map<String, WeddingStrategy> weddingStrategyMap;

    public WeddingStrategyFactory(final Map<String, WeddingStrategy> weddingStrategyMap) {
        this.weddingStrategyMap = weddingStrategyMap;
    }

    public WeddingStrategy getWeddingStrategy(Gender gender) {
        String strategyName = gender == Gender.MALE ? "groomWeddingStrategy" : "brideWeddingStrategy";
        return weddingStrategyMap.get(strategyName);
    }
}
