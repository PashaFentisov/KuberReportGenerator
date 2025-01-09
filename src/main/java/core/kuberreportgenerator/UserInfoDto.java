package core.kuberreportgenerator;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfoDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
}
