package site.nansan.BASA_M.controller.answer_submission;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import site.nansan.BASA_M.dto.AnswerResponse;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;
import site.nansan.BASA_M.service.answer_submission.AnswerSubmissionFacade;
import site.nansan.BASA_M.service.user.UserService;
import site.nansan.BASA_M.service.user.UserSolvedProblemService;

@RestController
@RequiredArgsConstructor
public class AnswerSubmissionController implements AnswerSubmissionSwaggerController{
    private final UserService userService;
    private final AnswerSubmissionFacade facade;
    private final UserSolvedProblemService userSolvedProblemService;

    @Override
    public ResponseEntity<AnswerResponse> checkAnswer(
            AnswerSubmissionRequest request, int group, int child) {

        AnswerResponse res = facade.evaluate(request, group, child);
        return ResponseEntity.ok(res);
    }

    @Override
    public ResponseEntity<AnswerResponse> submitAnswer(
            AnswerSubmissionRequest request, int group, int child) {

        String userId = userService.getAuthenticatedUserId();
        AnswerResponse res = facade.evaluate(request, group, child);

        userSolvedProblemService.saveUserSolvedProblemAsync(userId, request, res);
        return ResponseEntity.ok(res);
    }

}
