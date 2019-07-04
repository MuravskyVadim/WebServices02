package dbService.dataSets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UsersDataSet {

    @Getter
    private final long id;

    @Getter
    private final String login;

    @Getter
    private final String password;
}
