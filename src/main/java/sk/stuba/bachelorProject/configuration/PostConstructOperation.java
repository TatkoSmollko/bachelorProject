package sk.stuba.bachelorProject.configuration;

import org.jboss.jdeparser.FormatPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;
import sk.stuba.bachelorProject.model.Authority;
import sk.stuba.bachelorProject.model.Store;
import sk.stuba.bachelorProject.model.User;
import sk.stuba.bachelorProject.repositories.AuthorityRepository;
import sk.stuba.bachelorProject.repositories.ItemRepository;
import sk.stuba.bachelorProject.repositories.StoreRepository;
import sk.stuba.bachelorProject.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.swing.text.html.Option;
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
    StoreRepository storeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    // Generated UUID for the default authority each user has
    public static final String DEFAULT_AUTHORITY_ID = "347fc71f-cc9d-4008-a032-1238d6483efc";

    // Admin username
    public static final String ADMIN_USERNAME = "admin";

    // Admin password
    public static final String ADMIN_PASSWORD = "admin";

    //Default store id
    public static final String DEFAULT_STORE_ID = "237a6132-6cbb-11eb-9439-0242ac130002";

    // Default authority
    public static final String DEFAULT_AUTHORITY = "AppUser";

    //Default items

    public static final String DEFAULT_ID_FATRAFOL = "a287899a-6cc1-11eb-9439-0242ac130002";
    public static final String DEFAULT_ID_LISTEL = "a2878ec2-6cc1-11eb-9439-0242ac130002";
    public static final String DEFAULT_ID_SKREW = "a2878fd0-6cc1-11eb-9439-0242ac130002";
    public static final String DEFAULT_ID_GUTTER = "a2879098-6cc1-11eb-9439-0242ac130002\n";
    public static final String DEFAULT_ID_LUTE = "a287914c-6cc1-11eb-9439-0242ac130002";



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
                sql = "INSERT INTO users(username, enabled, password) VALUES('"
                        + ADMIN_USERNAME + "', 1, '" + cryptor.encode(ADMIN_PASSWORD)+ "')";
                stmt.executeUpdate(sql);
            }

            /**
             * Inserting of default authority for user authorizing
             */
            Optional<Authority> authority = authorityRepository.findById(DEFAULT_AUTHORITY_ID);
            if (!authority.isPresent()) {
                sql = "INSERT INTO authorities(id, authority) VALUES('" + DEFAULT_AUTHORITY_ID + "', '"
                        + DEFAULT_AUTHORITY + "')";
                stmt.executeUpdate(sql);
                sql = "INSERT INTO user_authority(username, authority_id) VALUES('" + ADMIN_USERNAME + "', '"
                        + DEFAULT_AUTHORITY_ID + "')";
                stmt.executeUpdate(sql);
            }

            /**
             * insert default store and default items into the store
             */
            if(!storeRepository.findById(DEFAULT_STORE_ID).isPresent()){
                sql = "INSERT INTO stores VALUES('" + DEFAULT_STORE_ID + "')";
                stmt.executeUpdate(sql);

                sql = "INSERT INTO items VALUES('" + DEFAULT_ID_FATRAFOL + "','0', '0','26','"+ DEFAULT_STORE_ID
                        +"','Fatrafol')";
                stmt.executeUpdate(sql);

                sql = "INSERT INTO items VALUES('" + DEFAULT_ID_LISTEL + "','0', '0','2.5','"+ DEFAULT_STORE_ID
                        +"','Rohová lišta')";
                stmt.executeUpdate(sql);

                sql = "INSERT INTO items VALUES('" + DEFAULT_ID_SKREW + "','0', '0','1','"+ DEFAULT_STORE_ID
                        +"','Šróby')";
                stmt.executeUpdate(sql);

                sql = "INSERT INTO items VALUES('" + DEFAULT_ID_GUTTER + "','0', '0','2.5','"+ DEFAULT_STORE_ID
                        +"','Okapový plech')";
                stmt.executeUpdate(sql);

                sql = "INSERT INTO items VALUES('" + DEFAULT_ID_LUTE + "','0', '0','1','"+ DEFAULT_STORE_ID
                        +"','Tmel')";
                stmt.executeUpdate(sql);
            }





        }catch (Exception e){
            System.out.println(e);

        }
        }
}
