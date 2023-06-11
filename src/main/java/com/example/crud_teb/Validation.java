package com.example.crud_teb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public boolean isValidNameAndSurname(String name) {

        String regex = "^(?!.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?!.*[!@#&()–[{}]:;',?/*~$^+=<>]).{1,20}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(name);

        return matcher.matches();

    }

    public boolean isValidPesel(String name) {

        String regex = "^(?=.*[0-9])(?!.*[a-z])(?!.*[A-Z])(?!.*[!@#&()–[{}]:;',?/*~$^+=<>])(?=\\S+$).{11}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(name);

        return matcher.matches();

    }

    public boolean isValidSalary(String name) {

        String regex = "^(?=.*[0-9])(?!.*[a-z])(?!.*[A-Z])(?!.*[!@#&()–[{}]:;',?/*~$^+=<>])(?=\\S+$).{1,20}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(name);

        return matcher.matches();

    }
}
