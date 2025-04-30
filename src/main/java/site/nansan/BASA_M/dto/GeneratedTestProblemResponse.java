package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneratedTestProblemResponse {

    private int problemNumber;
    private int group;
    private int child;

    private Problem problem;
    private Answer answer;
}
