package org.alkemy.ong.core.model;

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
