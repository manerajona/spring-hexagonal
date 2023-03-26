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

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "skill")
@Where(clause = "is_active=true")
@SQLDelete(sql = "UPDATE skill SET is_active=false WHERE skill_id=?")
@EntityListeners(AuditListener.class)
public class SkillJpa implements Auditable {
    @Id
    @Column(name = "skill_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "skills")
    @ToString.Exclude
    private Set<AlkymerJpa> alkymers = new HashSet<>();

    @Embedded
    private Audit audit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillJpa skill = (SkillJpa) o;
        return Objects.equals(id, skill.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
