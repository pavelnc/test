package ro.pavel.tradeshift.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
		basePackages = "ro.pavel.tradeshift.repository",
		entityManagerFactoryRef = "entityManagerFactory",
		transactionManagerRef = "transactionManager"
)
@PropertySource({"classpath:config.properties"})
public class Config {


	@Value("${jdbc.password}")
	private String password;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.driver-class-name}")
	private String driver;

	@Value("${tradeshift.datasource.url}")
	private String url;


	@Primary
	@Bean(name = "dataSource")
	public DataSource dataSource() {

		return DataSourceBuilder.create()
				.driverClassName(driver)
				.url(url)
				.username(username)
				.password(password)
				.build();
	}

	@Primary
	@Bean("transactionManager")
	public PlatformTransactionManager transactionManager() {
		EntityManagerFactory factory = entityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}

	@Primary
	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPackagesToScan("ro.pavel.tradeshift.enity");
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		jpaProperties.put("hibernate.show-sql", "false");
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		factory.setJpaProperties(jpaProperties);

		return factory;
	}


}
