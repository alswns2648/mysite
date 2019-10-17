package kr.co.itcen.config.app;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
	//applicationContext 의 SqlSessionFactoryBean 대체
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception{
		
		SqlSessionFactoryBean selSessionFactoryBean = new SqlSessionFactoryBean();
		selSessionFactoryBean.setDataSource(dataSource);
		selSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:kr/co/itcen/mysite/config/app/mybatis/configuration.xml"));
		
		return selSessionFactoryBean.getObject();
	}
	
	//applicationContext 의 'MyBatis SqlSessionTemplate 생성자를 통한 DI' 대체
	@Bean
	public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);		
	}
	
}
