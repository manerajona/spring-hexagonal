package org.alkemy.ong.domain.model;

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
