package site.nansan.BASA_M.dto;

import lombok.Data;

@Data
public class CalculationRequest {
    private int firstDigits;
    private int secondDigits;
    private String operator;
}
