package site.nansan.BASA_M.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import site.nansan.BASA_M.domain.UserSolvedProblem;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserSolvedProblemRepository extends MongoRepository<UserSolvedProblem, String> {

    List<UserSolvedProblem> findByStudentIdAndCategoryCode(String studentId, int categoryCode, Sort sort);

    Optional<ProblemNumberView> findTopBySolvedDateAndCategoryCodeOrderByProblemNumberDesc(LocalDate solvedDate, int categoryCode);

    Optional<ProblemNumberView> findTopByStudentIdAndSolvedDateAndCategoryCodeOrderByProblemNumberDesc(String studentId, LocalDate solvedDate, int categoryCode);
}
