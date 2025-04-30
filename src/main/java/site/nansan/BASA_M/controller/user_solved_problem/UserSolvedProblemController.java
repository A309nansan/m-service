package site.nansan.BASA_M.controller.user_solved_problem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import site.nansan.BASA_M.dto.UserSolvedProblemResultResponse;
import site.nansan.BASA_M.service.user.UserSolvedProblemService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserSolvedProblemController implements UserSolvedProblemSwaggerController {

    private final UserSolvedProblemService userSolvedProblemService;

    @Override
    public ResponseEntity<List<UserSolvedProblemResultResponse>> getProblems(int childId, int group, int child) {

        List<UserSolvedProblemResultResponse> responses = userSolvedProblemService.getProblemsGroupedByDate(childId, group, child);

        return ResponseEntity.ok(responses);
    }

    @Override
    public ResponseEntity<List<UserSolvedProblemResultResponse>> getTestProblems(int childId) {

        List<UserSolvedProblemResultResponse> responses = userSolvedProblemService.getTestProblemsGroupedByDate(childId);

        return ResponseEntity.ok(responses);
    }
}
