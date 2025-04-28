package site.nansan.BASA_M.service.answer_submission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import site.nansan.BASA_M.domain.ProblemErrorCode;
import site.nansan.BASA_M.dto.AnswerEvaluationDTO;
import site.nansan.BASA_M.dto.EvaluationResultDTO;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;

import java.util.Collections;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AnswerSubmissionFacade {

    private final ProblemScoringService scoring;
    private final ErrorAnalysisService error;

    public EvaluationResultDTO evaluate(AnswerSubmissionRequest req, int group, int child) {

        // 점수 계산
        AnswerEvaluationDTO eval = scoring.computeTotalScore(
                req.getGeneratedAnswer(),
                req.getUserAnswer(),
                req.getGeneratedProblem().getOperator()
        );

        // 오답일때 오답 원인 분석
        Set<ProblemErrorCode> errs = eval.getIsCorrect()
                ? Collections.emptySet()
                : error.findCause(req, group, child);

        return EvaluationResultDTO.builder()
                .isCorrect(eval.getIsCorrect())
                .basaTotalScore(eval.getBasaTotalScore())
                .basaMyScore(eval.getBasaMyScore())
                .errorCodes(errs)
                .build();
    }
}
