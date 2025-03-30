package site.nansan.BASA_M.controller.answer_submission;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import site.nansan.BASA_M.dto.AnswerEvaluationDTO;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;
import site.nansan.BASA_M.service.UserService;
import site.nansan.BASA_M.service.answer_submission.ErrorAnalysisService;
import site.nansan.BASA_M.service.answer_submission.ProblemScoringService;

@RestController
@RequiredArgsConstructor
public class AnswerSubmissionController implements AnswerSubmissionSwaggerController{
    private final UserService userService;
    private final ProblemScoringService problemScoringService;
    private final ErrorAnalysisService errorAnalysisService;
    @Override
    public ResponseEntity<?> submitAnswer(AnswerSubmissionRequest request) {
        String id = userService.getAuthenticatedUserId();

        AnswerEvaluationDTO evaluatedAnswer = problemScoringService.computeTotalScore(request.getGeneratedAnswer(), request.getUserAnswer(), request.getGeneratedProblem().getOperator());
        if(!evaluatedAnswer.isCorrect()){
            errorAnalysisService.findCause(request);
        }

        return ResponseEntity.ok(evaluatedAnswer.getScore());
    }
}
