package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.global.exception.ProblemGenerationErrorCode;
import site.nansan.BASA_M.global.exception.ProblemGenerationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DispatcherService {

    Map<String, ProblemGenerationService> dispatcherMap = new HashMap<>();

    public DispatcherService(List<ProblemGenerationService> services){

        for(ProblemGenerationService service : services){
            dispatcherMap.put(service.getProblemCode(), service);
        }
    }

    public GeneratedProblemResponse generateProblem(String categoryCode){

            ProblemGenerationService service = Optional.ofNullable(dispatcherMap.get("Problem" + categoryCode))
                    .orElseThrow(() -> new ProblemGenerationException(ProblemGenerationErrorCode.INCORRECT_PROBLEM_CODE));

            return service.makeProblem();
    }
}
