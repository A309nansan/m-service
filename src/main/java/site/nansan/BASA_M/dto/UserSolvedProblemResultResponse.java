package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import site.nansan.BASA_M.domain.UserSolvedProblem;
import site.nansan.BASA_M.domain.UserSolvedTestProblem;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSolvedProblemResultResponse {
    private LocalDate solvedDate;
    private List<UserSolvedProblemResultDTO> problems;

    private static <T> List<UserSolvedProblemResultResponse> groupAndMap(
            List<T> entities,
            Function<T, LocalDate> dateFn,
            Function<T, Integer> numberFn,
            Function<T, UserSolvedProblemResultDTO> dtoFn
    ) {

        var sorted = entities.stream()
                .sorted((a, b) -> {
                    int cmp = dateFn.apply(b).compareTo(dateFn.apply(a));
                    return (cmp == 0)
                            ? Integer.compare(numberFn.apply(a), numberFn.apply(b))
                            : cmp;
                })
                .toList();

        Map<LocalDate, List<T>> grouped =
                sorted.stream().collect(Collectors.groupingBy(dateFn));

        return grouped.entrySet().stream()
                .sorted(Map.Entry.<LocalDate, List<T>>comparingByKey().reversed())
                .map(e -> UserSolvedProblemResultResponse.builder()
                        .solvedDate(e.getKey())
                        .problems(
                                e.getValue().stream()
                                        .map(dtoFn)
                                        .collect(Collectors.toList())
                        )
                        .build())
                .toList();
    }

    public static List<UserSolvedProblemResultResponse> fromGroupedByDate(
            List<UserSolvedProblem> entities) {

        return groupAndMap(
                entities,
                UserSolvedProblem::getSolvedDate,
                UserSolvedProblem::getProblemNumber,
                UserSolvedProblemResultDTO::from
        );
    }

    public static List<UserSolvedProblemResultResponse> fromGroupedByDateTest(List<UserSolvedTestProblem> entities) {

        return groupAndMap(
                entities,
                UserSolvedTestProblem::getSolvedDate,
                UserSolvedTestProblem::getProblemNumber,
                UserSolvedProblemResultDTO::from
        );
    }
}
