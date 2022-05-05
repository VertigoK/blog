package kevin.study.blog.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    private LocalDateTime updatedDateTime;

    @ManyToOne  //Many = reply, One = user (FK 관계 설정)
    @JoinColumn(name = "userId")    //FK
    private User user;

    @ManyToOne  //Many = reply, One = board (FK 관계 설정)
    @JoinColumn(name = "boardId")   //FK
    private Board board;

    @Builder
    public Reply(String content) {
        this.content = content;
    }

}
