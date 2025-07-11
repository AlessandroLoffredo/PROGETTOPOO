package dao;

import java.time.LocalDate;

public interface AdminInterface {
    int newHack(String title, String venue, LocalDate startDate, LocalDate endDate, int maxReg, int maxPerTeam, String username, byte[] photoData);
}
