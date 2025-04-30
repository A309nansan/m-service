package site.nansan.BASA_M.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;
import site.nansan.BASA_M.dto.AnswerTestSubmissionRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestAnswerSubmissionMapper {

    public record SplitResult(AnswerSubmissionRequest request,
                              int group,
                              int child) {}

    public static SplitResult split(AnswerTestSubmissionRequest src) {

        AnswerSubmissionRequest req = AnswerSubmissionRequest.builder()
                .problemNumber(src.getProblemNumber())
                .solvedDate(src.getSolvedDate())
                .solvedTime(src.getSolvedTime())
                .generatedProblem(src.getGeneratedProblem())
                .generatedAnswer(src.getGeneratedAnswer())
                .userAnswer(src.getUserAnswer())
                .build();

        return new SplitResult(req, src.getGroup(), src.getChild());
    }
}
