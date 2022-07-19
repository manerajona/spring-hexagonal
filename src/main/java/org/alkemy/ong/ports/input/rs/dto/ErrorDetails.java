package org.alkemy.ong.ports.input.rs.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Value;
import org.alkemy.ong.config.exception.error.ErrorCode;
import org.alkemy.ong.config.exception.error.ErrorLocation;

import javax.validation.constraints.NotNull;

@Value
@Builder
@JsonPropertyOrder({"code", "detail", "field", "value", "location"})
public class ErrorDetails {
    @NotNull ErrorCode code;
    @NotNull String detail;
    String field;
    Object value;
    ErrorLocation location;
}
