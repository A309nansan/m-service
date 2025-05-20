package site.nansan.BASA_M.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import site.nansan.BASA_M.domain.answer.Answer;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_solved_problem")
@CompoundIndexes({
        @CompoundIndex(
                name = "student_cat_date_num_idx",
                def = "{'studentId':1,'categoryCode':1,'solvedDate':-1,'problemNumber':1}",
                background = true
        )
})
public class UserSolvedProblem {

    @Id
    private String id;
    private String studentId;
    private LocalDate solvedDate;
    private String solvedTime;

    private int categoryCode;
    private int problemNumber;

    private Problem generatedProblem;
    private Answer generatedAnswer;
    private Answer userAnswer;

    private Boolean isCorrect;
    private int basaTotalScore;
    private int basaMyScore;
    private Set<ProblemErrorCode> errorCodes;

}
