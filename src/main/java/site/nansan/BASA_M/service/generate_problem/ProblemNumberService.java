package site.nansan.BASA_M.service.generate_problem;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.UserSolvedProblem;
import site.nansan.BASA_M.domain.UserSolvedTestProblem;
import site.nansan.BASA_M.repository.UserSolvedProblemRepository;
import site.nansan.BASA_M.repository.UserSolvedTestProblemRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProblemNumberService {

    private final UserSolvedProblemRepository repository;
    private final UserSolvedTestProblemRepository testProblemRepository;

    public int getNextProblemNumber(LocalDate solvedDate, int categoryCode) {

        return repository
                .findTopBySolvedDateAndCategoryCodeOrderByProblemNumberDesc(
                        solvedDate, categoryCode)
                .map(v -> v.getProblemNumber() + 1)
                .orElse(1);
    }

    public int getNextProblemNumber(String studentId, LocalDate solvedDate, int categoryCode) {

        return repository
                .findTopByStudentIdAndSolvedDateAndCategoryCodeOrderByProblemNumberDesc(
                        studentId, solvedDate, categoryCode)
                .map(v -> v.getProblemNumber() + 1)
                .orElse(1);
    }

    public int getNextTestProblemNumber(String studentId) {

        LocalDate today = LocalDate.now();

        return testProblemRepository
                .findTopByStudentIdAndSolvedDateOrderByProblemNumberDesc(studentId, today)
                .map(v -> v.getProblemNumber() + 1)
                .orElse(1);
    }

}
