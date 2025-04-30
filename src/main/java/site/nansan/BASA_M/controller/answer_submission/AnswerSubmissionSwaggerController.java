package site.nansan.BASA_M.controller.answer_submission;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.nansan.BASA_M.dto.AnswerResponse;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;
import site.nansan.BASA_M.dto.AnswerTestSubmissionRequest;

public interface AnswerSubmissionSwaggerController {

    @Operation(
            summary = "(부모와 함께하기) 답안 저장 없이 채점만 수행"
    )
    @ApiResponse(responseCode = "200", description = "채점 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    @PostMapping("/check/{group}/{child}")
    ResponseEntity<AnswerResponse> checkAnswer(
            @RequestBody AnswerSubmissionRequest request,
            @PathVariable("group") int group,
            @PathVariable("child") int child
    );

    @Operation(
            summary = "(스스로 하기) 문제 답안 제출"
    )
    @ApiResponse(responseCode = "200", description = "답안 제출 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    @PostMapping("/{childId}/submit/{group}/{child}")
    ResponseEntity<Void> submitAnswer(
            @RequestBody AnswerSubmissionRequest request,
            @PathVariable("childId") int childId,
            @PathVariable("group") int group,
            @PathVariable("child") int child
    );

    @Operation(
            summary = "테스트 문제 답안 제출"
    )
    @ApiResponse(responseCode = "200", description = "답안 제출 성공")
    @ApiResponse(responseCode = "400", description = "잘못된 요청")
    @PostMapping("/test/{childId}")
    ResponseEntity<?> submitTestAnswer(
            @RequestBody AnswerTestSubmissionRequest request,
            @PathVariable("childId") int childId
    );

}
