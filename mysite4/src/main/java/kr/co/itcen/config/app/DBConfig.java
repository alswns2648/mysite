package kr.co.itcen.config.app;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:kr/co/itcen/config/app/properties/jdbc.properties")
public class DBConfig {
	
	@Autowired
	Environment env;
	
	@Bean
	public DataSource dataSource() {
		// applicationContext.xml 의 Connection Pool DataSource 부분 대체
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(env.getProperty("jdbc.driverClassNmae"));
		basicDataSource.setUrl(env.getProperty("jdbc.url"));
		basicDataSource.setUsername(env.getProperty("jdbc.username"));
		basicDataSource.setPassword(env.getProperty("jdbc.password"));
		basicDataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class)); // connection pool 갯수 설정
		basicDataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class)); // connection pool threa 최대 한계
		return basicDataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource) ;
	}
}
