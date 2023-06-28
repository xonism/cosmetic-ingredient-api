package cosmeticingredientapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    private long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String login;

    @NotBlank
    @Column(nullable = false)
    private String location;

    @NotBlank
    @Column(name = "avatar_url", unique = true, nullable = false)
    private String avatarUrl;

    @Column(name = "two_factor_authentication", nullable = false)
    private String twoFactorAuthentication;
}
