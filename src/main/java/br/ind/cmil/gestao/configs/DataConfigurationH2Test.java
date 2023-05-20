package br.ind.cmil.gestao.configs;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 *
 * @author ti
 */
//@Configuration
//@Profile("test")

public class DataConfigurationH2Test {

 
   // @Bean
   // @Profile("test")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
         dataSource.setUrl("jdbc:h2:mem:testdev;DB_CLOSE_DELAY=-1");
         dataSource.setUsername("ca");
        dataSource.setPassword("password");

        return dataSource;
    }

    //@Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);        
        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }
}
