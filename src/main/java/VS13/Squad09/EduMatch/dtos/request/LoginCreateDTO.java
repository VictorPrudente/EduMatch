package VS13.Squad09.EduMatch.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginCreateDTO {

    private String email;
    private String senha;
}
