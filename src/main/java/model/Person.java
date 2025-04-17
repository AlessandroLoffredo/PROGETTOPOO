package model;

import java.time.LocalDate;

public class Person {
    private final String fName;
    private final String lName;
    private final LocalDate birthDate;

    //BULDER
    public Person(String fName, String lName, LocalDate birthDate){
        this.fName = fName;
        this.lName = lName;
        this.birthDate = birthDate;
    }


}
