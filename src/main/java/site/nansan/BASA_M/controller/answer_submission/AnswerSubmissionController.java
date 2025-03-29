package site.nansan.BASA_M.controller.answer_submission;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;
import site.nansan.BASA_M.service.UserService;
import site.nansan.BASA_M.service.answer_submission.ProblemScoringService;

@RestController
@RequiredArgsConstructor
public class AnswerSubmissionController implements AnswerSubmissionSwaggerController{
    private final UserService userService;
    private final ProblemScoringService problemScoringService;

    @Override
    public ResponseEntity<?> submitAnswer(AnswerSubmissionRequest request) {
        String id = userService.getAuthenticatedUserId();

        int point = problemScoringService.computeTotalScore(request.getGeneratedAnswer(), request.getUserAnswer());
        return ResponseEntity.ok(point);
    }
}
