package cosmeticingredientapi.controllers.admin;

import cosmeticingredientapi.utils.JsonUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/api/v1/user")
    public String user(@AuthenticationPrincipal OAuth2User principal) {
        Map<String, Object> userInfo = new HashMap<>();

        userInfo.put("id", principal.getAttribute("id"));
        userInfo.put("login", principal.getAttribute("login"));
        userInfo.put("avatarUrl", principal.getAttribute("avatar_url"));
        userInfo.put("twoFactorAuthentication", principal.getAttribute("two_factor_authentication"));

        return JsonUtils.getObjectAsJsonString(userInfo);
    }
}
