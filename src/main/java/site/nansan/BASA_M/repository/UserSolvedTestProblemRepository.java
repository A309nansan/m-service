package site.nansan.BASA_M.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import site.nansan.BASA_M.domain.UserSolvedTestProblem;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserSolvedTestProblemRepository extends MongoRepository<UserSolvedTestProblem, String> {

    Optional<ProblemNumberView> findTopByStudentIdAndSolvedDateOrderByProblemNumberDesc(String studentId, LocalDate solvedDate);

    List<UserSolvedTestProblem> findByStudentId(String studentId, Sort sort);
}
