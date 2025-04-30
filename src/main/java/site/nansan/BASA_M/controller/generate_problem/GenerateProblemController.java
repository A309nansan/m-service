package site.nansan.BASA_M.controller.generate_problem;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.dto.GeneratedTestProblemResponse;
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
    public ResponseEntity<GeneratedProblemResponse> generateProblemWithParent(int group, int child) {

        int categoryCode = group * 100 + child;

        GeneratedProblemResponse response = dispatcherService.generateProblem(Integer.toString(categoryCode));

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GeneratedProblemResponse> generateProblemSelf(int childId, int group, int child) {

        int categoryCode = group * 100 + child;

        GeneratedProblemResponse response = dispatcherService.generateProblem(Integer.toString(categoryCode));

        response.setProblemNumber(
                problemNumberService.getNextProblemNumber(Integer.toString(childId), LocalDate.now(), categoryCode)
        );

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<GeneratedTestProblemResponse> generateRandomProblem(int childId) {

        int group = ThreadLocalRandom.current().nextInt(1, 7);
        int child = ThreadLocalRandom.current().nextInt(1, 5);
        int categoryCode = group * 100 + child;

        GeneratedProblemResponse problem =
                dispatcherService.generateProblem(Integer.toString(categoryCode));

        problem.setProblemNumber(
                problemNumberService.getNextTestProblemNumber(Integer.toString(childId))
        );

        return ResponseEntity.ok(
                GeneratedTestProblemResponse.builder()
                        .problemNumber(problem.getProblemNumber())
                        .group(group)
                        .child(child)
                        .problem(problem.getProblem())
                        .answer(problem.getAnswer())
                        .build()
        );
    }

}
