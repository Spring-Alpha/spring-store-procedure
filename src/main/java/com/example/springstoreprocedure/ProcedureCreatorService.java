package com.example.springstoreprocedure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProcedureCreatorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createProcedure() {
        String sql = """
            CREATE PROCEDURE getUserById(IN userId INT)
            BEGIN
                SELECT * FROM users WHERE id = userId LIMIT 1;
            END;
        """;

        // Execute the SQL to create the procedure
        jdbcTemplate.execute(sql);
    }

    public void deleteProcedure() {
        String dropProcedureSql = "DROP PROCEDURE IF EXISTS getUserById";

        // Execute the SQL to drop the stored procedure
        jdbcTemplate.execute(dropProcedureSql);
        System.out.println("Stored procedure dropped successfully (if it existed).");
    }
}

