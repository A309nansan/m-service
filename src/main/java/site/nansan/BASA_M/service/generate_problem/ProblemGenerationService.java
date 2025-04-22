package site.nansan.BASA_M.service.generate_problem;

import site.nansan.BASA_M.dto.GeneratedProblemResponse;

public abstract class ProblemGenerationService {

    public String getProblemCode(){

        return this.getClass().getSimpleName();
    }

    public abstract GeneratedProblemResponse makeProblem();
}
