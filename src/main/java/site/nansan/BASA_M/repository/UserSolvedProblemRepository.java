package site.nansan.BASA_M.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import site.nansan.BASA_M.domain.UserSolvedProblem;

import java.time.LocalDate;
import java.util.List;

public interface UserSolvedProblemRepository extends MongoRepository<UserSolvedProblem, String> {
    List<UserSolvedProblem> findBySolvedDateAndCategoryCode(LocalDate solvedDate, int categoryCode, Sort sort);

    List<UserSolvedProblem> findByCategoryCode(int categoryCode, Sort sort);
}
