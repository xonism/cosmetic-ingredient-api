package cosmeticingredientapi.controllers.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import cosmeticingredientapi.models.User;
import cosmeticingredientapi.security.CustomOAuth2User;
import cosmeticingredientapi.utils.JsonUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/v1/user")
    public String user(@AuthenticationPrincipal CustomOAuth2User oAuth2User) throws JsonProcessingException {
        User user = new User();

        user.setId(oAuth2User.getId().longValue());
        user.setLogin(oAuth2User.getLogin());
        user.setAvatarUrl(oAuth2User.getAvatarUrl());
        user.setTwoFactorAuthentication(oAuth2User.getTwoFactorAuthentication().toString());
        user.setLocation(oAuth2User.getLocation());

        return JsonUtils.getObjectAsJsonString(user);
    }
}
