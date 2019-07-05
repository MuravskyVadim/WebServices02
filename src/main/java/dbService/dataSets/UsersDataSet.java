package dbService.dataSets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsersDataSet {

    private final long id;

    private final String login;

    private final String password;
}
