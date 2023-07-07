package br.ind.cmil.gestao.configs;

import br.ind.cmil.gestao.model.services.interfaces.impl.DBService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 *
 * @author ti
 */
//@Component
@Configuration
@Profile("dev")
public class DevConfig {
    
      @Autowired
    private DBService dbService;

    @Bean
    public void instaciaBaseDeDados() {
        this.dbService.instanciaBaseDeDados();

    }
    
 @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("password1");
        //dataSource.setUrl("jdbc:mysql://us-cdbr-east-06.cleardb.net/heroku_c991871a871e2f2");
        //dataSource.setUsername("b337869a1735f0");
        //dataSource.setPassword("f326b0d7");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);        
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }
}
