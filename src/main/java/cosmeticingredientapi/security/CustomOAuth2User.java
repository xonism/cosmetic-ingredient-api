package cosmeticingredientapi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final OAuth2User oauth2User;
    private final List<GrantedAuthority> customAuthorities;

    public CustomOAuth2User(OAuth2User oauth2User, List<GrantedAuthority> customAuthorities) {
        this.oauth2User = oauth2User;
        this.customAuthorities = customAuthorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>(oauth2User.getAuthorities());
        authorities.addAll(customAuthorities);
        return authorities;
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }

    public Integer getId() {
        return oauth2User.getAttribute("id");
    }

    public String getLogin() {
        return oauth2User.getAttribute("login");
    }

    public String getAvatarUrl() {
        return oauth2User.getAttribute("avatar_url");
    }

    public String getLocation() {
        return oauth2User.getAttribute("location");
    }

    public Boolean getTwoFactorAuthentication() {
        return oauth2User.getAttribute("two_factor_authentication");
    }
}
