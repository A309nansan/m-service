package site.nansan.BASA_M.controller.user_solved_problem;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.nansan.BASA_M.dto.UserSolvedProblemResultResponse;

import java.util.List;

@Tag(name = "User Solved Problem API", description = "시험 회차별 문제 풀이 기록 관련 API")
public interface UserSolvedProblemSwaggerController {

    @Operation(
            summary = "카테고리별 문제풀이 기록 조회 (날짜별 그룹)",
            description = "group과 child를 통해 생성된 categoryCode로 데이터를 조회한 후, solvedDate별로 그룹화하여 최신 날짜 순으로 정렬된 문제풀이 기록을 반환합니다."
    )
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/{childId}/problems/{group}/{child}")
    ResponseEntity<List<UserSolvedProblemResultResponse>> getProblems(
            @PathVariable("childId") int childId,
            @PathVariable("group") int group,
            @PathVariable("child") int child
    );

    @Operation(
            summary = "테스트 문제풀이 기록 조회 (날짜별 그룹)",
            description = "테스트 데이터를 조회한 후, solvedDate별로 그룹화하여 최신 날짜 순으로 정렬된 문제풀이 기록을 반환합니다."
    )
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/test/problems/{childId}")
    ResponseEntity<List<UserSolvedProblemResultResponse>> getTestProblems(
            @PathVariable("childId") int childId
    );
}