package site.nansan.BASA_M.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.UserSolvedProblem;
import site.nansan.BASA_M.dto.EvaluationResultDTO;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;
import site.nansan.BASA_M.dto.UserSolvedProblemResultResponse;
import site.nansan.BASA_M.repository.UserSolvedProblemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSolvedProblemService {
    private final UserSolvedProblemRepository repository;

    @Async
    public void saveUserSolvedProblemAsync(String id, AnswerSubmissionRequest request, EvaluationResultDTO evaluatedAnswer, int group, int child) {

        UserSolvedProblem problem = UserSolvedProblem.builder()
                .studentId(id)
                .solvedDate(request.getSolvedDate())
                .solvedTime(request.getSolvedTime())
                .categoryCode(group * 100 + child)
                .problemNumber(request.getProblemNumber())
                .generatedProblem(request.getGeneratedProblem())
                .generatedAnswer(request.getGeneratedAnswer())
                .userAnswer(request.getUserAnswer())
                .isCorrect(evaluatedAnswer.getIsCorrect())
                .basaTotalScore(evaluatedAnswer.getBasaTotalScore())
                .basaMyScore(evaluatedAnswer.getBasaMyScore())
                .errorCodes(evaluatedAnswer.getErrorCodes())
                .build();

        repository.save(problem);
    }

    public List<UserSolvedProblemResultResponse> getProblemsGroupedByDate(int studentId, int group, int child) {

        int categoryCode = group * 100 + child;

        Sort sort = Sort.by(Sort.Order.desc("solvedDate"),
                Sort.Order.asc("problemNumber"));

        List<UserSolvedProblem> problems =
                repository.findByStudentIdAndCategoryCode(Integer.toString(studentId), categoryCode, sort);

        return UserSolvedProblemResultResponse.fromGroupedByDate(problems);


    }

}
