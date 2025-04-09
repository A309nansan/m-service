package site.nansan.BASA_M.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import site.nansan.BASA_M.dto.AnswerDTO;
import site.nansan.BASA_M.dto.ProblemDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_solved_problem")
public class UserSolvedProblem {
    @Id
    private String id;
    private String studentId;
    private LocalDate solvedDate;
    private String solvedTime;

    private int categoryCode;
    private int problemNumber;

    private ProblemDTO generatedProblem;
    private AnswerDTO generatedAnswer;
    private AnswerDTO userAnswer;

    private Boolean isCorrect;
    private int basaTotalScore;
    private int basaMyScore;
    private Set<ProblemErrorCode> errorCodes;
}
