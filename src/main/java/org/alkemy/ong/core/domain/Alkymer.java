package org.alkemy.ong.core.domain;

import java.time.LocalDateTime;
import java.util.Set;

public record Alkymer(
        Long id,
        String name,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Set<Skill> skills
) {
}
