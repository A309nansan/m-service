package site.nansan.BASA_M.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.UserSolvedProblem;
import site.nansan.BASA_M.domain.UserSolvedTestProblem;
import site.nansan.BASA_M.dto.EvaluationResultDTO;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;
import site.nansan.BASA_M.dto.UserSolvedProblemResultResponse;
import site.nansan.BASA_M.repository.UserSolvedProblemRepository;
import site.nansan.BASA_M.repository.UserSolvedTestProblemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSolvedProblemService {

    private final UserSolvedProblemRepository repository;
    private final UserSolvedTestProblemRepository testRepository;

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

    @Async
    public void saveUserSolvedTestProblemAsync(String id, AnswerSubmissionRequest request, EvaluationResultDTO evaluatedAnswer, int group, int child) {

        UserSolvedTestProblem problem = UserSolvedTestProblem.builder()
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

        testRepository.save(problem);
    }

    public List<UserSolvedProblemResultResponse> getProblemsGroupedByDate(int studentId, int group, int child) {

        int categoryCode = group * 100 + child;

        Sort sort = Sort.by(Sort.Order.desc("solvedDate"),
                Sort.Order.asc("problemNumber"));

        List<UserSolvedProblem> problems =
                repository.findByStudentIdAndCategoryCode(Integer.toString(studentId), categoryCode, sort);

        return UserSolvedProblemResultResponse.fromGroupedByDate(problems);

    }
    public List<UserSolvedProblemResultResponse> getTestProblemsGroupedByDate(int studentId) {

        Sort sort = Sort.by(
                Sort.Order.desc("solvedDate"),
                Sort.Order.asc("problemNumber")
        );

        List<UserSolvedTestProblem> tests =
                testRepository.findByStudentId(Integer.toString(studentId), sort);

        return UserSolvedProblemResultResponse.fromGroupedByDateTest(tests);
    }

}
