package com.dpro.repositories;

import com.dpro.domains.SubjectType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import static com.dpro.utils.DatabaseColumns.*;
import static com.dpro.utils.DatabaseTables.*;

@Repository
public class SubjectTypeJDBCRepository implements SubjectTypeRepository {

    private final JdbcTemplate template;

    private static final String SQL_FIND_ALL_QUERY
            = "SELECT * FROM " + SUBJECT_TYPES_TABLE;

    private static final String SQL_FIND_BY_ID_QUERY
            = "SELECT * FROM " + SUBJECT_TYPES_TABLE + " WHERE " + SUBJECT_TYPE_ID_COLUMN + " = ?";

    public SubjectTypeJDBCRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    /**
     * Odnajduje typ przedmiotu w bazie danych za pomocą id
     *
     * @param id identyfikator typu przedmiotu w bazie danych
     * @return obiekt typu przedmiotu pobranego z bazy danych
     */
    @Override
    public SubjectType findById(Long id) {
        return template.query(SQL_FIND_BY_ID_QUERY, new SubjectTypeResultSetExtractor(), id);
    }

    /**
     * Odnajdue wszystkie typy przedmiotów w bazie danych
     *
     * @return lista typów przedmiotów
     */
    @Override
    public List<SubjectType> findAll() {
        return template.query(SQL_FIND_ALL_QUERY, new SubjectTypeRowMapper());
    }

    private static SubjectType generate(ResultSet rs) throws SQLException {
        SubjectType subjectType = new SubjectType();
        subjectType.setId(rs.getLong(SUBJECT_TYPE_ID_COLUMN));
        subjectType.setName(rs.getString(SUBJECT_TYPE_NAME_COLUMN));

        return subjectType;
    }

    private static class SubjectTypeRowMapper implements RowMapper<SubjectType> {

        @Override
        public SubjectType mapRow(ResultSet resultSet, int i) throws SQLException {
            return generate(resultSet);
        }
    }

    private static class SubjectTypeResultSetExtractor implements ResultSetExtractor<SubjectType> {

        @Override
        public SubjectType extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            if (resultSet.next()) {
                return generate(resultSet);
            }

            return null;
        }
    }
}
