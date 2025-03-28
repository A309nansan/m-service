package site.nansan.BASA_M.dto.answer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarryDTO {
    private Integer one;
    private Integer two;
    private Integer three;
    private Integer four;
    private Integer five;

    /** 더하기할 때 carry 계산 */
    public static CarryDTO calculateAdditionCarry(int a, int b) {
        CarryDTO dto = new CarryDTO();
        int carry = 0;
        int position = 1;
        while ((a > 0 || b > 0 || carry > 0) && position < 5) {
            int da = a % 10;
            int db = b % 10;
            int sum = da + db + carry;
            int newCarry = sum / 10;
            if (position == 1 && newCarry != 0) {
                dto.setTwo(newCarry);
            } else if (position == 2 && newCarry != 0) {
                dto.setThree(newCarry);
            } else if (position == 3 && newCarry != 0) {
                dto.setFour(newCarry);
            } else if(position ==4 && newCarry != 0) {
                dto.setFive(newCarry);
            }
            carry = newCarry;
            a /= 10;
            b /= 10;
            position++;
        }
        return dto;
    }

    /** 빼기할 때 carry 계산 */
    public static CarryDTO calculateSubtractionBorrow(int a, int b) {
        CarryDTO dto = new CarryDTO();
        int borrow = 0;
        int position = 1;
        int origA = a;
        while ((a > 0 || b > 0) && position < 6) {
            int da = a % 10;
            int db = b % 10;
            int temp = da - borrow;
            if (temp < db) {
                borrow = 1;
                if (position == 1) {
                    int tens = (origA / 10) % 10;
                    if(tens==0){
                        tens+=10;
                        borrow++;
                    }
                    dto.setTwo(tens - 1);
                } else if (position == 2) {
                    int hundreds = (origA / 100) % 10;
                    if(hundreds==0){
                        hundreds+=10;
                        borrow++;
                    }
                    dto.setThree(hundreds - 1);
                } else if (position == 3) {
                    int thousands = (origA / 1000) % 10;
                    if(thousands==0){
                        thousands+=10;
                        borrow++;
                    }
                    dto.setFour(thousands - 1);
                } else if (position == 4) {
                    int tenThousands = (origA / 10000) % 10;
                    if(tenThousands==0){
                        tenThousands+=10;
                        borrow++;
                    }
                    dto.setFive(tenThousands - 1);
                }
            } else {
                borrow = 0;
            }
            a /= 10;
            b /= 10;
            position++;
        }
        return dto;
    }
}
