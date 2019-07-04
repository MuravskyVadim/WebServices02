package base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserProfile {
    @Getter private final String login;
    @Getter private final String password;
}
