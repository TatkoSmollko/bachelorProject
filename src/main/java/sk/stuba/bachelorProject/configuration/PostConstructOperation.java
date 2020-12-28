package sk.stuba.bachelorProject.configuration;

import org.jboss.jdeparser.FormatPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;
import sk.stuba.bachelorProject.model.Authority;
import sk.stuba.bachelorProject.model.User;
import sk.stuba.bachelorProject.repositories.AuthorityRepository;
import sk.stuba.bachelorProject.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Optional;

@Component
public class PostConstructOperation {
    @Autowired
    Environment env;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    // Generated UUID for the default authority each user has
    public static final String DEFAULT_AUTHORITY_ID = "347fc71f-cc9d-4008-a032-1238d6483efc";

    // Admin username
    public static final String ADMIN_USERNAME = "admin";

    // Admin password
    public static final String ADMIN_PASSWORD = "admin";

    // Default authority
    public static final String DEFAULT_AUTHORITY = "AppUser";


    @PostConstruct
    public void initializeDefaultObjects(){
        String sql;
        try (Connection conn = DriverManager.getConnection(env.getProperty("spring.datasource.url"),
                env.getProperty("spring.datasource.username"), env.getProperty("spring.datasource.password"));
             Statement stmt = conn.createStatement()) {

            // Try to create admin user
            Optional<User> admin = userRepository.findByUsername(ADMIN_USERNAME);
            if (!admin.isPresent()) {
                BCryptPasswordEncoder cryptor = new BCryptPasswordEncoder();
                sql = "INSERT INTO users(username, enabled, password, is_admin, email, full_name) VALUES('"
                        + ADMIN_USERNAME + "', 1, '" + cryptor.encode(ADMIN_PASSWORD)
                        + "', 1, 'support@miasolutions.sk', 'DMS Admin')";
                stmt.executeUpdate(sql);
            }


            Optional<Authority> authority = authorityRepository.findById(DEFAULT_AUTHORITY_ID);
            if (!authority.isPresent()) {
                sql = "INSERT INTO authorities(id, authority) VALUES('" + DEFAULT_AUTHORITY_ID + "', '"
                        + DEFAULT_AUTHORITY + "')";
                stmt.executeUpdate(sql);

                sql = "INSERT INTO user_authority(username, authority_id) VALUES('" + ADMIN_USERNAME + "', '"
                        + DEFAULT_AUTHORITY_ID + "')";
                stmt.executeUpdate(sql);
            }

        }catch (Exception e){

        }
        }
}
