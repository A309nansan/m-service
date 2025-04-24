package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import site.nansan.BASA_M.global.exception.AnswerSubmissionErrorCode;
import site.nansan.BASA_M.global.exception.AnswerSubmissionException;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultDTO {
    private Integer one;
    private Integer two;
    private Integer three;
    private Integer four;
    private Integer five;

    private ResultDTO(int one){
        this.one=one;
    }

    public ResultDTO copy() {
        // 안전을 위해 기본값 0으로 생성한 뒤 수동 세터 사용 (primitive 생성자 NPE 방지)
        ResultDTO dto = new ResultDTO(0);
        dto.setOne(this.one);
        dto.setTwo(this.two);
        dto.setThree(this.three);
        dto.setFour(this.four);
        dto.setFive(this.five);
        return dto;
    }

    // index 기반 접근자 (0~4)
    public void setDigitAt(int index, int value) {
        switch (index) {
            case 0 -> this.one = value;
            case 1 -> this.two = value;
            case 2 -> this.three = value;
            case 3 -> this.four = value;
            case 4 -> this.five = value;
            default -> throw new IllegalArgumentException("Index out of bounds: " + index);
        }
    }

    public static ResultDTO from(int number){
        if(number < 0){
            throw new AnswerSubmissionException(AnswerSubmissionErrorCode.NUMBER_CANNOT_MINUS);
        }

        String numStr = String.valueOf(number);
        int len = numStr.length();

        ResultDTO dto = new ResultDTO(Integer.parseInt(String.valueOf(numStr.charAt(len - 1))));
        if(len >= 2){
            dto.setTwo(Integer.parseInt(String.valueOf(numStr.charAt(len-2))));
        }
        if(len >= 3){
            dto.setThree(Integer.parseInt(String.valueOf(numStr.charAt(len-3))));
        }
        if(len >= 4){
            dto.setFour(Integer.parseInt(String.valueOf(numStr.charAt(len-4))));
        }
        if(len >= 5){
            dto.setFive(Integer.parseInt(String.valueOf(numStr.charAt(len-5))));
        }
        return dto;
    }

    public int toNumber(){
        int result = 0;
        if( one != null ) result += one;
        if( two != null ) result += two*10;
        if( three != null ) result += three*100;
        if( four != null )  result += four*1000;
        if( five != null ) result += five*10000;

        return result;
    }

    @JsonIgnore
    public int getSize(){
        if(five != null) return 5;
        else if(four != null) return 4;
        else if(three != null) return 3;
        else if(two != null) return 2;
        else if(one != null) return 1;
        else return 0;
    }

    public int getDigitAt(int pos){
        return switch (pos) {
            case 0 -> one != null ? one : -1;
            case 1 -> two != null ? two : -1;
            case 2 -> three != null ? three : -1;
            case 3 -> four != null ? four : -1;
            case 4 -> five != null ? five : -1;
            default -> 0;
        };
    }
}
