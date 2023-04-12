package br.ind.cmil.gestao.configs;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;

/**
 *
 * @author ti
 */
//@Component
//@Profile("dev")
public class DataConfigurationMysqlDev {
/**
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdev");
        dataSource.setUsername("ac");
        dataSource.setPassword("password");
        
        //dataSource.setUrl("jdbc:mysql://us-cdbr-east-05.cleardb.net:3306/heroku_324781cc1e68a72");
       // dataSource.setUsername("bc450ee94d7db0");
        //dataSource.setPassword("0477f9dc");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }
*/
}
