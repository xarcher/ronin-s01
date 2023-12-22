package dev.xarcher.server.dns;

import java.util.Objects;
import java.util.regex.Pattern;

public record DNSRequest(String domain) {
    private static final String DOMAIN_PATTERN = "^((?!-)[A-Za-z0–9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";

    public boolean isValid() {
        if (Objects.isNull(domain) || domain.isBlank()) {
            return false;
        }

        var pattern = Pattern.compile(DOMAIN_PATTERN);
        var matcher = pattern.matcher(domain);

        return matcher.matches();
    }
}
