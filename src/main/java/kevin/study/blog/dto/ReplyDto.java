package kevin.study.blog.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {

    private int userId;
    private int boardId;
    private String content;

}
