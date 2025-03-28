package site.nansan.BASA_M.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.service.*;

@RestController
@RequiredArgsConstructor
public class GenerateProblemController implements GenerateProblemSwaggerController {

    private final M1ProblemGenerationService m1ProblemGenerationService;
    private final M2ProblemGenerationService m2ProblemGenerationService;
    private final M3ProblemGenerationService m3ProblemGenerationService;
    private final M4ProblemGenerationService m4ProblemGenerationService;
    private final M5ProblemGenerationService m5ProblemGenerationService;
    private final M6ProblemGenerationService m6ProblemGenerationService;

    @Override
    public ResponseEntity<GeneratedProblemResponse> generateProblem(int group, int child) {
        GeneratedProblemResponse response = switch (group) {
            case 1 -> switch (child) {
                case 1 -> m1ProblemGenerationService.generateM1_1Problem();
                case 2 -> m1ProblemGenerationService.generateM1_2Problem();
                case 3 -> m1ProblemGenerationService.generateM1_3Problem();
                case 4 -> m1ProblemGenerationService.generateM1_4Problem();
                default -> throw new IllegalArgumentException("Invalid child parameter for group 1");
            };
            case 2 -> switch (child) {
                case 1 -> m2ProblemGenerationService.generateM2_1Problem();
                case 2 -> m2ProblemGenerationService.generateM2_2Problem();
                case 3 -> m2ProblemGenerationService.generateM2_3Problem();
                case 4 -> m2ProblemGenerationService.generateM2_4Problem();
                default -> throw new IllegalArgumentException("Invalid child parameter for group 2");
            };
            case 3 -> switch (child) {
                case 1 -> m3ProblemGenerationService.generateM3_1Problem();
                case 2 -> m3ProblemGenerationService.generateM3_2Problem();
                case 3 -> m3ProblemGenerationService.generateM3_3Problem();
                case 4 -> m3ProblemGenerationService.generateM3_4Problem();
                default -> throw new IllegalArgumentException("Invalid child parameter for group 3");
            };
            case 4 -> switch (child) {
                case 1 -> m4ProblemGenerationService.generateM4_1Problem();
                case 2 -> m4ProblemGenerationService.generateM4_2Problem();
                case 3 -> m4ProblemGenerationService.generateM4_3Problem();
                case 4 -> m4ProblemGenerationService.generateM4_4Problem();
                default -> throw new IllegalArgumentException("Invalid child parameter for group 4");
            };
            case 5 -> switch (child) {
                case 1 -> m5ProblemGenerationService.generateM5_1Problem();
                case 2 -> m5ProblemGenerationService.generateM5_2Problem();
                case 3 -> m5ProblemGenerationService.generateM5_3Problem();
                case 4 -> m5ProblemGenerationService.generateM5_4Problem();
                default -> throw new IllegalArgumentException("Invalid child parameter for group 5");
            };
            case 6 -> switch (child) {
                case 1 -> m6ProblemGenerationService.generateM6_1Problem();
                case 2 -> m6ProblemGenerationService.generateM6_2Problem();
                case 3 -> m6ProblemGenerationService.generateM6_3Problem();
                case 4 -> m6ProblemGenerationService.generateM6_4Problem();
                case 5 -> m6ProblemGenerationService.generateM6_5Problem();
                case 6 -> m6ProblemGenerationService.generateM6_6Problem();
                default -> throw new IllegalArgumentException("Invalid child parameter for group 6");
            };
            default -> throw new IllegalArgumentException("Invalid group parameter");
        };
        return ResponseEntity.ok(response);
    }
}
