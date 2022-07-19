package org.alkemy.ong.ports.output.jpa.audit;

public interface Auditable {
    Audit getAudit();

    void setAudit(Audit audit);
}
