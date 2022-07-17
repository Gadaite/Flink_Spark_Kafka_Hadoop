package Package3rd.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Account implements Serializable {
    private int id;
    private String name;
    private double money;
}
