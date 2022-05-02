package uniflow.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 150)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ColumnDefault("0")
    private int count;

    @ManyToOne  //Many = board, One = user (FK 관계 설정)
    @JoinColumn(name = "userId")    //FK
    private User user;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    private LocalDateTime updatedDateTime;

    //상대(Many=reply) 테이블에서 @ManyToOne으로 먼저 FK 관계를 설정한 후
    //FK 설정의 대상(One=board)이 되는 테이블에서 양방향 연관 관계를 사용하고 싶을 때(List<>)
    //@OneToMany(@mappedBy="board")를 사용한다. mappedBy는 DB에 칼럼을 만들지 않게 한다.
    //One = board, Many = reply (FK 관계 설정)
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"board"})
    @OrderBy("id asc")
    private List<Reply> replyList = new ArrayList<>();

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
