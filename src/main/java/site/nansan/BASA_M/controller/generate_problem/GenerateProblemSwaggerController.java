package site.nansan.BASA_M.controller.generate_problem;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.dto.GeneratedTestProblemResponse;

public interface GenerateProblemSwaggerController {

    @Operation(
            summary = "카테고리별 문제 생성",
            description = "URL 경로의 {group}과 {child} 파라미터에 따라 각 문제 유형(m1_1, m1_2, …, m6_4)을 생성합니다.\n예를 들어, /api/v1/basam/1/1 은 m1_1 문제를 요청하는 것입니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "문제 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @GetMapping("/{group}/{child}")
    ResponseEntity<GeneratedProblemResponse> generateProblem(
            @PathVariable("group") int group,
            @PathVariable("child") int child
    );

    @Operation(
            summary = "부모와 함꼐하기 카테고리별 문제 생성",
            description = "URL 경로의 {group}과 {child} 파라미터에 따라 각 문제 유형(m1_1, m1_2, …, m6_4)을 생성합니다.\n예를 들어, /api/v1/basam/1/1 은 m1_1 문제를 요청하는 것입니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "문제 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @GetMapping("/parent/{group}/{child}")
    ResponseEntity<GeneratedProblemResponse> generateProblemWithParent(
            @PathVariable("group") int group,
            @PathVariable("child") int child
    );

    @Operation(
            summary = "스스로하기 문제 생성",
            description = "URL 경로의 {group}과 {child} 파라미터에 따라 각 문제 유형(m1_1, m1_2, …, m6_4)을 생성합니다.\n예를 들어, /api/v1/basam/1/1 은 m1_1 문제를 요청하는 것입니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "문제 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @GetMapping("/self/{childId}/{group}/{child}")
    ResponseEntity<GeneratedProblemResponse> generateProblemSelf(
            @PathVariable("childId") int childId,
            @PathVariable("group") int group,
            @PathVariable("child") int child
    );

    @Operation(summary = "( 테스트 ) 무작위 문제 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "문제 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @GetMapping("/test/{childId}")
    ResponseEntity<GeneratedTestProblemResponse> generateRandomProblem(@PathVariable("childId") int childId);

}

