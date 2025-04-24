package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalculateDTO {
    private Integer one;
    private Integer two;
    private Integer three;
    private Integer four;

    public CalculateDTO copy() {
        CalculateDTO dto = new CalculateDTO();
        dto.setOne(this.one);
        dto.setTwo(this.two);
        dto.setThree(this.three);
        dto.setFour(this.four);
        return dto;
    }

    @JsonIgnore
    public int getSize(){
        if(four != null) return 4;
        else if(three != null) return 3;
        else if(two != null) return 2;
        else if(one != null) return 1;
        else return 0;
    }

    @JsonIgnore
    public boolean isNullDTO(){
        return one == null && two == null && three == null && this.four == null;
    }

    @JsonIgnore
    public int getDigitAt(int pos){
        return switch (pos) {
            case 0 -> one != null ? one : -1;
            case 1 -> two != null ? two : -1;
            case 2 -> three != null ? three : -1;
            case 3 -> four != null ? four : -1;
            default -> 0;
        };
    }
    public void setDigitAt(int index, int value) {
        switch (index) {
            case 0 -> this.one = value;
            case 1 -> this.two = value;
            case 2 -> this.three = value;
            case 3 -> this.four = value;
            default -> throw new IllegalArgumentException("Index out of bounds: " + index);
        }
    }
}
