package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface UsersInterface {
    void getOrganizers(ArrayList<String> organizers, LocalDate start, LocalDate end) throws SQLException;
}
