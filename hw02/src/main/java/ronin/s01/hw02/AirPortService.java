package ronin.s01.hw02;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
public class AirPortService {
    private static final Map<String, AirPortModel> AIR_PORT_DATA = Map.of(
            "HN1", new AirPortModel("HN1", "Ha noi 1"),
            "HN2", new AirPortModel("HN2", "Ha noi 2"),
            "HN3", new AirPortModel("HN3", "Ha noi 3"),
            "HN4", new AirPortModel("HN4", "Ha noi 4"));

    private final Cache<String, AirPortModel> airPortCacheManager;

    public AirPortModel getByCode(final String code) {
        var airPortModel = airPortCacheManager.getIfPresent(code);
        if (Objects.nonNull(airPortModel)) {
            System.out.printf("-> Found air port %s in cache%n", code);
            return airPortModel;
        }

        airPortModel = AIR_PORT_DATA.get(code);
        System.out.printf("-> Get air port %s in database%n", code);
        this.airPortCacheManager.put(code, airPortModel);

        return airPortModel;
    }
}
