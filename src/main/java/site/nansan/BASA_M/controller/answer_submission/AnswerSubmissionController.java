package site.nansan.BASA_M.controller.answer_submission;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import site.nansan.BASA_M.domain.ProblemErrorCode;
import site.nansan.BASA_M.dto.AnswerEvaluationDTO;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;
import site.nansan.BASA_M.service.user.UserService;
import site.nansan.BASA_M.service.answer_submission.ErrorAnalysisService;
import site.nansan.BASA_M.service.answer_submission.ProblemScoringService;
import site.nansan.BASA_M.service.user.UserSolvedProblemService;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class AnswerSubmissionController implements AnswerSubmissionSwaggerController{
    private final UserService userService;
    private final ErrorAnalysisService errorAnalysisService;
    private final ProblemScoringService problemScoringService;
    private final UserSolvedProblemService userSolvedProblemService;

    @Override
    public ResponseEntity<?> submitAnswer(AnswerSubmissionRequest request, int group, int child) {
        String id = userService.getAuthenticatedUserId();
        Set<ProblemErrorCode> errorCodes = null;
        AnswerEvaluationDTO evaluatedAnswer = problemScoringService.computeTotalScore(request.getGeneratedAnswer(), request.getUserAnswer(), request.getGeneratedProblem().getOperator());
        if(!evaluatedAnswer.isCorrect()){
            errorCodes = errorAnalysisService.findCause(request);
        }
        userSolvedProblemService.saveUserSolvedProblem(id, request, errorCodes, group, child);

        return ResponseEntity.ok(evaluatedAnswer.getScore());
    }
}
