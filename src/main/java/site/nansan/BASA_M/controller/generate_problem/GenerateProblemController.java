package site.nansan.BASA_M.controller.generate_problem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.global.exception.ProblemGenerationErrorCode;
import site.nansan.BASA_M.global.exception.ProblemGenerationException;
import site.nansan.BASA_M.service.generate_problem.*;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequiredArgsConstructor
public class GenerateProblemController implements GenerateProblemSwaggerController {

    private final ProblemNumberService problemNumberService;
    private final DispatcherService dispatcherService;

    @Override
    public ResponseEntity<GeneratedProblemResponse> generateProblem(int group, int child) {
        int categoryCode = group * 100 + child;

        GeneratedProblemResponse response = dispatcherService.generateProblem(Integer.toString(categoryCode));

        response.setProblemNumber(
                problemNumberService.getNextProblemNumber(LocalDate.now(), categoryCode)
        );

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GeneratedProblemResponse> generateRandomProblem() {
        int group = ThreadLocalRandom.current().nextInt(1, 7);
        int child = ThreadLocalRandom.current().nextInt(1, 5);
        int categoryCode = group * 100 + child;

        GeneratedProblemResponse response =
                dispatcherService.generateProblem(Integer.toString(categoryCode));

        response.setProblemNumber(
                problemNumberService.getNextProblemNumber(LocalDate.now(), categoryCode)
        );

        return ResponseEntity.ok(response);
    }

}
