package site.nansan.BASA_M.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.ProblemErrorCode;
import site.nansan.BASA_M.domain.UserSolvedProblem;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;
import site.nansan.BASA_M.dto.UserSolvedProblemResultResponse;
import site.nansan.BASA_M.repository.UserSolvedProblemRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserSolvedProblemService {
    private final UserSolvedProblemRepository repository;

    @Async
    public void saveUserSolvedProblem(String id, AnswerSubmissionRequest request, Boolean isCorrect, Set<ProblemErrorCode> errorCodes, int group, int child) {
        int categoryCode = group * 100 + child;

        UserSolvedProblem problem = UserSolvedProblem.builder()
                .studentId(id)
                .solvedDate(request.getSolvedDate())
                .solvedTime(request.getSolvedTime())
                .categoryCode(categoryCode)
                .problemNumber(request.getProblemNumber())
                .generatedProblem(request.getGeneratedProblem())
                .generatedAnswer(request.getGeneratedAnswer())
                .userAnswer(request.getUserAnswer())
                .isCorrect(isCorrect)
                .errorCodes(errorCodes)
                .build();

        repository.save(problem);
    }

    public List<UserSolvedProblemResultResponse> getProblemsGroupedByDate(int group, int child) {
        int categoryCode = group * 100 + child;
        List<UserSolvedProblem> problems = repository.findByCategoryCode(categoryCode,
                Sort.by("solvedDate").descending().and(Sort.by("problemNumber").ascending()));

        return UserSolvedProblemResultResponse.fromGroupedByDate(problems);
    }

}
