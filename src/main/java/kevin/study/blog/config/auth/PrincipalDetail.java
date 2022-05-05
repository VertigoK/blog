package kevin.study.blog.config.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import kevin.study.blog.domain.User;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class PrincipalDetail implements UserDetails {

    private User user;

    public PrincipalDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
//        collectors.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        collectors.add(() -> "ROLE_" + user.getRole());
        return collectors;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
