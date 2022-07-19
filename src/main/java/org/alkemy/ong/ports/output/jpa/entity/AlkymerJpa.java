package org.alkemy.ong.ports.output.jpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.alkemy.ong.ports.output.jpa.audit.Audit;
import org.alkemy.ong.ports.output.jpa.audit.AuditListener;
import org.alkemy.ong.ports.output.jpa.audit.Auditable;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "alkymer")
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE alkymer SET is_active=false WHERE alkymer_id=?")
@EntityListeners(AuditListener.class)
public class AlkymerJpa implements Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alkymer_id")
    private Long id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @ManyToMany
    @JoinTable(name = "alkymer_skill",
            joinColumns = @JoinColumn(name = "alkymer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    @ToString.Exclude
    private Set<SkillJpa> skills = new HashSet<>();

    @Embedded
    private Audit audit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlkymerJpa alkymer = (AlkymerJpa) o;
        return Objects.equals(id, alkymer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
