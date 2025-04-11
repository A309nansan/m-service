package site.nansan.BASA_M.service.generate_problem;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.UserSolvedProblem;
import site.nansan.BASA_M.repository.UserSolvedProblemRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemNumberService {

    private final UserSolvedProblemRepository repository;

    public int getNextProblemNumber(LocalDate solvedDate, int categoryCode) {
        List<UserSolvedProblem> problems = repository.findBySolvedDateAndCategoryCode(
                solvedDate,
                categoryCode,
                Sort.by("problemNumber").descending()
        );
        if (problems.isEmpty()) {
            return 1;
        }
        return problems.get(0).getProblemNumber() + 1;
    }

}
