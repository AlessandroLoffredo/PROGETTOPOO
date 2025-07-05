package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UsersInterface {
    void getOrganizers(ArrayList<String> organizers) throws SQLException;
}
