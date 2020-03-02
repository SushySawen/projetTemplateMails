package projet42;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Controller
@RequestMapping("mail")
public class RestController {
	
	@Bean
	//fais le lien avec BDD
	public DataSource dataSource() {
		//une lib fournissant une datasource
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(password);
		config.setDriverClassName("com.mysql.jdbc.Driver");
		return new HikariDataSource(config);
	}
	
	@Bean
	//utilise la datasource pour faire des requetes sur la bdd
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(method = RequestMethod.POST, value = "template/{id}")
	public void save(@RequestParam("id") int id, @RequestBody String template) {
		// TODO google preparedStatement
		jdbcTemplate.update("UPDATE maTable SET maColonne = '"+template+"' WHERE id = "+id);
	}
	
}
