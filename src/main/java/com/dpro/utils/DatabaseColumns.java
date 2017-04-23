package com.dpro.utils;

/**
 * Wszytkie kolumny zanjdujące się w bazie danych. Przydatne do refaktoryzacji,
 * gdy schemat bazy danych ulegnie zmianie.
 *
 * @author Tomasz Truszkowski
 */
public class DatabaseColumns {

    /**
     * Identyfikator użytkownika
     */
    public static final String USER_ID_COLUMN = "user_id";
    /**
     * Nazwa użytkownika - login
     */
    public static final String USER_NAME_COLUMN = "user_name";
    /**
     * Hasło
     */
    public static final String USER_PASSWORD_COLUMN = "user_password";
    /**
     * Imię studenta
     */
    public static final String USER_FIRST_NAME_COLUMN = "user_first_name";
    /**
     * Nazwisko
     */
    public static final String USER_LAST_NAME_COLUMN = "user_last_name";
    /**
     * Czy konto jest aktywne?
     */
    public static final String USER_ENABLED_COLUMN = "user_enabled";
    /**
     * Adres e-mail
     */
    public static final String USER_EMAIL_COLUMN = "user_email";
    /**
     * Data urodzenia
     */
    public static final String USER_DATE_OF_BIRTH_COLUMN = "user_date_of_birth";
    /**
     * Numer PESEL
     */
    public static final String USER_PESEL_COLUMN = "user_pesel";

    /**
     * Rola użytkownika
     */
    public static final String ROLES_ROLE_COLUMN = "role";

    /**
     * Identyfiaktor studenta
     */
    public static final String STUDENT_ID_COLUMN = "student_id";
    /**
     * Album studenta
     */
    public static final String ALBUM_COLUMN = "student_album";

    /**
     * Identyfikator wykładowcy
     */
    public static final String INSTRUCTOR_ID_COLUMN = "instructor_id";
    /**
     * Stopień naukowy
     */
    public static final String SCIENCE_DEGREE_COLUMN = "instructor_science_degree";

    /**
     * Identyfikator przedmiotu
     */
    public static final String SUBJECT_ID_COLUMN = "subject_id";
    /**
     * Nazwa przedmiotu
     */
    public static final String SUBJECT_NAME_COLUMN = "subject_name";
    /**
     * Punkty ECTS
     */
    public static final String SUBJECT_ECTS_COLUMN = "subject_ects";
    /**
     * Liczba godzin
     */
    public static final String SUBJECT_HOURS_COLUMN = "subject_hours";

    /**
     * Identyfikaotor typu przedmiotu
     */
    public static final String SUBJECT_TYPE_ID_COLUMN = "subject_type_id";
    /**
     * Nazwa typu przedmiotu
     */
    public static final String SUBJECT_TYPE_NAME_COLUMN = "subject_type_name";

    /**
     * Identyfikator oceny
     */
    public static final String MARK_ID_COLUMN = "mark_id";
    /**
     * Wartość oceny
     */
    public static final String MARK_VALUE_COLUMN = "mark_value";
    /**
     * Opis oceny
     */
    public static final String MARK_DESC_COLUMN = "mark_desc";
    /**
     * Czy ocena zalicza przedmiot?
     */
    public static final String MARK_IS_PASS_COLUMN = "mark_is_pass";

    private DatabaseColumns() {
    }
}
