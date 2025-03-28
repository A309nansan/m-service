package site.nansan.BASA_M.dto.answer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Calculate2DTO {
    private Integer one;
    private Integer two;
    private Integer three;

    private Calculate2DTO(int one){
        this.one=one;
    }

    public static Calculate2DTO from(int number){
        String numStr = String.valueOf(number);
        int len = numStr.length();

        Calculate2DTO dto = new Calculate2DTO(Integer.parseInt(String.valueOf(numStr.charAt(len - 1))));
        if(len >= 2){
            dto.setTwo(Integer.parseInt(String.valueOf(numStr.charAt(len-2))));
        }
        if(len >= 3){
            dto.setThree(Integer.parseInt(String.valueOf(numStr.charAt(len-3))));
        }

        return dto;
    }
    public int toNumber(){
        int result = 0;
        if( one != null ) result += one;
        if( two != null ) result += two*10;
        if( three != null ) result += three*100;

        return result;
    }
}
