package site.nansan.BASA_M.controller.answer_submission;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import site.nansan.BASA_M.dto.AnswerResponse;
import site.nansan.BASA_M.dto.AnswerTestSubmissionRequest;
import site.nansan.BASA_M.dto.EvaluationResultDTO;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;
import site.nansan.BASA_M.service.answer_submission.AnswerSubmissionFacade;
import site.nansan.BASA_M.service.user.UserSolvedProblemService;
import site.nansan.BASA_M.util.TestAnswerSubmissionMapper;

@RestController
@RequiredArgsConstructor
public class AnswerSubmissionController implements AnswerSubmissionSwaggerController{

    private final AnswerSubmissionFacade facade;
    private final UserSolvedProblemService userSolvedProblemService;

    /** (부모와 함께하기) 답안 저장 없이 채점만 수행 */
    @Override
    public ResponseEntity<AnswerResponse> checkAnswer(AnswerSubmissionRequest request, int group, int child) {

        EvaluationResultDTO result = facade.evaluate(request, group, child);

        AnswerResponse response = AnswerResponse.from(result);

        return ResponseEntity.ok(response);

    }

    /** (스스로 하기) 문제 답안 제출 */
    @Override
    public ResponseEntity<Void> submitAnswer(AnswerSubmissionRequest request, int childId, int group, int child) {

        EvaluationResultDTO response = facade.evaluate(request, group, child);

        userSolvedProblemService.saveUserSolvedProblemAsync(Integer.toString(childId), request, response, group, child);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> submitTestAnswer(AnswerTestSubmissionRequest request, int childId) {

        TestAnswerSubmissionMapper.SplitResult res = TestAnswerSubmissionMapper.split(request);

        AnswerSubmissionRequest answerSubmissionRequest = res.request();
        int group  = res.group();
        int child  = res.child();

        EvaluationResultDTO response = facade.evaluate(answerSubmissionRequest, group, child);

        userSolvedProblemService.saveUserSolvedTestProblemAsync(Integer.toString(childId), answerSubmissionRequest, response, group, child);

        return ResponseEntity.ok().build();
    }

}
