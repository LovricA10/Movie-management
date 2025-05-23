/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author dnlbe
 */
public class RepositoryFactory {

    private static UserRepository userRepository;
    private static MovieRepository movieRepository;
    private static ActorRepository actorRepository;
    private static DirectorRepository directorRepository;
    private static GenreRepository genreRepository;
    private static MovieDirectorRepository movieDirectorRepository;
    private static MovieActorRepository movieActorRepository;
    private static MovieGenreRepository movieGenreRepository;
    private static MovieUserRepository movieUserRepository;
    private static AdminRepository adminRepository;

    private static final Properties PROPERTIES = new Properties();
    private static final String PATH = "/config/repository.properties";
    //private static final String CLASS_NAME = "CLASS_NAME";
    private static final Map<Class<?>, Object> REPOSITORIES = new HashMap<>();

    static {
        try (InputStream is = RepositoryFactory.class.getResourceAsStream(PATH)) {
            PROPERTIES.load(is);
            for (String interfaceName : PROPERTIES.stringPropertyNames()) {
                Class<?> interfaceClass = Class.forName(interfaceName);
                Class<?> implClass = Class.forName(PROPERTIES.getProperty(interfaceName));
                Object instance = implClass.getDeclaredConstructor().newInstance();
                REPOSITORIES.put(interfaceClass, instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RepositoryFactory() {
    }

    // LAZY SINGLETON
    public static <T> T getRepository(Class<T> repoType) {
        return (T) REPOSITORIES.get(repoType);
    }
}
