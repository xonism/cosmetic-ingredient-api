package cosmeticingredientapi.security;

import cosmeticingredientapi.constants.Constants;
import cosmeticingredientapi.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Integer oAuth2UserId = Objects.requireNonNull(oAuth2User.getAttribute(Constants.ID));

        List<GrantedAuthority> customAuthorities = new ArrayList<>();
        if (userRepository.findById(oAuth2UserId.longValue()).isPresent()) {
            customAuthorities.add(new SimpleGrantedAuthority(Constants.ADMIN));
        }
        return new CustomOAuth2User(oAuth2User, customAuthorities);
    }
}
