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
@Component
@Profile("dev")
public class DevConfig {

   /** @Autowired
    private DBService dbService;

    @Bean
    public void instaciaBaseDePerfis() {
        this.dbService.instanciaBaseDePerfis();

    }

    @Bean
    public void instaciaBaseDeDados() throws MessagingException {
        this.dbService.instanciaBaseDeUsuarios();

    }
*/
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/cmil");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        
        //dataSource.setUrl("jdbc:mysql://us-cluster-east-01.k8s.cleardb.net/heroku_303db21716a42ec");
        //dataSource.setUsername("bfd2f188c5cfeb");
        //dataSource.setPassword("1a43d318");
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
