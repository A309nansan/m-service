package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import site.nansan.BASA_M.domain.UserSolvedProblem;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSolvedProblemResultResponse {
    private LocalDate solvedDate;
    private List<UserSolvedProblemResultDTO> problems;

    public static List<UserSolvedProblemResultResponse> fromGroupedByDate(List<UserSolvedProblem> entities) {
        List<UserSolvedProblem> sortedList = entities.stream()
                .sorted((a, b) -> {
                    int cmp = b.getSolvedDate().compareTo(a.getSolvedDate());
                    if (cmp == 0) {
                        return Integer.compare(a.getProblemNumber(), b.getProblemNumber());
                    }
                    return cmp;
                })
                .toList();

        Map<LocalDate, List<UserSolvedProblem>> grouped = sortedList.stream()
                .collect(Collectors.groupingBy(UserSolvedProblem::getSolvedDate));

        return grouped.entrySet().stream()
                .sorted(Map.Entry.<LocalDate, List<UserSolvedProblem>>comparingByKey().reversed())
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    List<UserSolvedProblemResultDTO> dtos = entry.getValue().stream()
                            .map(UserSolvedProblemResultDTO::from)
                            .collect(Collectors.toList());
                    return UserSolvedProblemResultResponse.builder()
                            .solvedDate(date)
                            .problems(dtos)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
