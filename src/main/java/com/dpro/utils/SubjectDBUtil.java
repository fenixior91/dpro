package com.dpro.utils;

import com.dpro.domains.Subject;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class SubjectDBUtil {
    
    public static final String ID_COLUMN = "subject_id";
    public static final String NAME_COLUMN = "subject_name";
    public static final String ECTS_COLUMN = "ects";
    public static final String HOURS_COLUMN = "hours";
    
    private SubjectDBUtil() {
        
    }
    
    public static Subject generate(ResultSet rs) throws SQLException {
        Subject subject = new Subject();
        subject.setId(rs.getLong(ID_COLUMN));
        subject.setName(rs.getString(NAME_COLUMN));
        subject.setEcts(rs.getInt(ECTS_COLUMN));
        subject.setHours(rs.getInt(HOURS_COLUMN));
        
        return subject;
    }
    
    public static class SubjectRowMapper implements RowMapper<Subject> {

        @Override
        public Subject mapRow(ResultSet resultSet, int i) throws SQLException {
            return generate(resultSet);
        }
    }

    public static class SubjectResultSetExtractor implements ResultSetExtractor<Subject> {

        @Override
        public Subject extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            if (resultSet.next()) {
                return generate(resultSet);
            }

            return null;
        }
    }
}
