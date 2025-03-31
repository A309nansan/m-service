package site.nansan.BASA_M.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import site.nansan.BASA_M.dto.answer.AnswerDTO;
import site.nansan.BASA_M.dto.problem.ProblemDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "user_solved_problem")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserSolvedProblem {
    @Id
    private String id; // MongoDB는 주로 문자열 ID를 사용합니다.

    private Long studentId;
    private LocalDate solvedDate;
    private LocalDateTime solvedTime;
    private int categoryCode;
    private int problemNumber;

    private ProblemDTO generatedProblem;
    private AnswerDTO generatedAnswer;
    private AnswerDTO userAnswer;
}
