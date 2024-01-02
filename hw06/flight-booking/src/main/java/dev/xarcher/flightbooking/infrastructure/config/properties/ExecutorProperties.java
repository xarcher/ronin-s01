package dev.xarcher.flightbooking.infrastructure.config.properties;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class ExecutorProperties {
    @Min(1)
    @NotNull
    protected Integer corePoolSize;

    @Min(1)
    @NotNull
    protected Integer maxPoolSize;

    @Min(1)
    @NotNull
    protected Integer queueCapacity;

    protected String threadNamePrefix;

    @Min(1)
    protected Long waitTimeFull;

    protected boolean retryAdd;
}
