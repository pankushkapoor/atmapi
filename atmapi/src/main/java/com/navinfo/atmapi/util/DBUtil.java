package com.navinfo.atmapi.util;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class DBUtil {
	
	private static EmbeddedDatabase getDataSource() {
        EmbeddedDatabaseBuilder embeddedBuilder = new EmbeddedDatabaseBuilder();

        EmbeddedDatabase embeddedDB = embeddedBuilder.setType(EmbeddedDatabaseType.H2)
        						.addScript("DB/schema.sql")
        						.addScript("DB/data.sql").build();
        return embeddedDB;
    }

    @Bean
    public static JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }
	
}
