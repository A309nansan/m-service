package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import site.nansan.BASA_M.domain.Operator;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemDTO {
    private Integer first;
    private Integer second;
    private Operator operator;
}
