package org.alkemy.ong.ports.input.rs.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import org.alkemy.ong.configuration.exception.error.ApplicationErrorCode;
import org.alkemy.ong.configuration.exception.error.ErrorLocation;

import javax.validation.constraints.NotNull;

@Data
@Builder
@JsonPropertyOrder({"code", "detail", "field", "value", "location"})
public class ErrorDetails {

    /**
     * The unique and fine-grained application-level error code.
     */
    @NotNull
    private ApplicationErrorCode code;

    /**
     * The human-readable description for an issue. Provide non-standard more granular error message.
     */
    @NotNull
    private String detail;

    /**
     * The field that caused the error. If the field is in the body, set this value to the JSON pointer to that field.
     * Example: "field": {"$ref": "#/body-field"}
     */
    private String field;

    /**
     * The value of the field that caused the error.
     */
    private Object value;

    /**
     * The location of the field that caused the error. Value is `BODY`, `PATH`, `QUERY` or `HEADER`.
     */
    private ErrorLocation location;

}
