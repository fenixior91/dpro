package com.dpro.utils;

/**
 * Wszytkie tabele zanjdujące się w bazie danych. Przydatne do refaktoryzacji,
 * gdy schemat bazy danych ulegnie zmianie.
 *
 * @author TOmasz Truszkowski
 */
public class DatabaseTables {

    /**
     * Tabela użytkowniów
     */
    public static final String USERS_TABLE = "users";
    /**
     * Tabela studentów
     */
    public static final String STUDENTS_TABLE = "students";
    /**
     * Tabela wykładowców
     */
    public static final String INSTRUCTORS_TABLE = "instructors";
    /**
     * Tablea rów
     */
    public static final String ROLES_TABLE = "roles";
    /**
     * Tabela ocen
     */
    public static final String MARKS_TABLE = "marks";
    /**
     * Tabela przedmiotów
     */
    public static final String SUBJECTS_TABLE = "subjects";
    /**
     * Tabela typów przedmiotów
     */
    public static final String SUBJECT_TYPES_TABLE = "subject_types";
    /**
     * Tabela przedmiotów użytkownika
     */
    public static final String USERS_SUBJECTS_TABLE = "user_subjects";
    /**
     * Tabela ocen studenta
     */
    public static final String STUDENTS_MARKS_TABLE = "student_marks";
    
    private DatabaseTables() {
        
    }
}
