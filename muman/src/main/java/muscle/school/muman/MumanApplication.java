package muscle.school.muman;

import javax.servlet.http.HttpSessionListener;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@SpringBootApplication

@MapperScan(value={"main.service.mapper"})
public class MumanApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
	    SpringApplication.run(MumanApplication.class, args);
	}

    @Bean
    public HttpSessionListener httpSessionListener(){
	    return new SessionListener();
    }

	@Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        
        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*Mapper.xml");
        sessionFactory.setMapperLocations(res);
        
        return sessionFactory.getObject();
    }

}
