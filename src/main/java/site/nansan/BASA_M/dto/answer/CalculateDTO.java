package site.nansan.BASA_M.dto.answer;

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

    @JsonIgnore
    public boolean isNullDTO(){
        return this.one == null && this.two == null && this.three == null && this.four == null;
    }
}
