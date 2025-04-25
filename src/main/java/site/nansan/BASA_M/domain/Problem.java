package site.nansan.BASA_M.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
    private Integer first;
    private Integer second;
    private Operator operator;
}
