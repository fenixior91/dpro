package com.dpro.utils;

import com.dpro.domains.SubjectType;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class SubjectTypeDBUtil {

    public static final String ID_COLUMN = "subject_type_id";
    public static final String NAME_COLUMN = "subject_type_name";

    private SubjectTypeDBUtil() {

    }

    public static SubjectType generate(ResultSet rs) throws SQLException {
        SubjectType subjectType = new SubjectType();
        subjectType.setId(rs.getLong(ID_COLUMN));
        subjectType.setName(rs.getString(NAME_COLUMN));

        return subjectType;
    }

    public static class SubjectTypeRowMapper implements RowMapper<SubjectType> {

        @Override
        public SubjectType mapRow(ResultSet resultSet, int i) throws SQLException {
            return generate(resultSet);
        }
    }

    public static class SubjectTypeResultSetExtractor implements ResultSetExtractor<SubjectType> {

        @Override
        public SubjectType extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            if (resultSet.next()) {
                return generate(resultSet);
            }

            return null;
        }
    }
}
