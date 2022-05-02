package uniflow.blog.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ROLE_ADMIN", "Admin"),
    USERR("ROLE_USERR", "UserToReadOnly"),
    USERRW("ROLE_USERRW", "UserToReadAndWrite");

    private final String key;
    private final String value;

}
