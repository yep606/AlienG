package spittr.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TaskSearchValues {

    private String text;
    private String completed;
    private Long categoryId;
    private Long priorityId;


}
