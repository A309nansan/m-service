package site.nansan.BASA_M.service.answer_submission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import site.nansan.BASA_M.domain.ProblemErrorCode;
import site.nansan.BASA_M.dto.AnswerEvaluationDTO;
import site.nansan.BASA_M.dto.AnswerResponse;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;

import java.util.Collections;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AnswerSubmissionFacade {

    private final ProblemScoringService scoring;
    private final ErrorAnalysisService error;

    public AnswerResponse evaluate(AnswerSubmissionRequest req, int group, int child) {

        AnswerEvaluationDTO eval = scoring.computeTotalScore(
                req.getGeneratedAnswer(),
                req.getUserAnswer(),
                req.getGeneratedProblem().getOperator()
        );

        Set<ProblemErrorCode> errs = eval.getIsCorrect()
                ? Collections.emptySet()
                : error.findCause(req, group, child);

        return AnswerResponse.builder()
                .solvedTime(req.getSolvedTime())
                .categoryCode(group * 100 + child)
                .problemNumber(req.getProblemNumber())
                .generatedProblem(req.getGeneratedProblem())
                .generatedAnswer(req.getGeneratedAnswer())
                .userAnswer(req.getUserAnswer())
                .isCorrect(eval.getIsCorrect())
                .basaTotalScore(eval.getBasaTotalScore())
                .basaMyScore(eval.getBasaMyScore())
                .errorCodes(errs)
                .build();
    }
}
