package facades;

import dto.MovieDTO;
import utils.EMF_Creator;
import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private static Movie friday;
    private static List<Movie> movies = new ArrayList<>();

    public MovieFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/imdb_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = MovieFacade.getMovieFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = MovieFacade.getMovieFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        facade = MovieFacade.getMovieFacade(emf);
        friday = new Movie(1995, "Friday", new String[]{"Chris Rock", "Ice Cube"});
        movies.add(friday);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNativeQuery("DELETE FROM MOVIE").executeUpdate();
            em.persist(friday);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testAFacadeMethod() {
        assertEquals(1, facade.getNumberOfMoviesInDB(), "Expects one rows in the database");
    }

    @Test
    public void testGetAllMovies() {
        //Arrange
        List<MovieDTO> expResult = new ArrayList<>();
        expResult.add(new MovieDTO(friday));
        //Act
        List<MovieDTO> result = facade.getAllMoviesDTO();
        //Assert
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMovieByID() throws Exception {
        //Arrange 
        MovieDTO expResult = new MovieDTO(friday);
        //Act
        MovieDTO result = facade.getMovieDTOById(1);
        //Assert
        assertEquals(expResult, result);
    }

     @Test
    public void testGetMovieByName() throws Exception {
        //Arrange 
        MovieDTO expResult = new MovieDTO(friday);
        //Act
        MovieDTO result = facade.getMovieDTOByName("Friday");
        //Assert
        assertEquals(expResult, result);
    }
    
     @Test
    public void testMakeMovie() {
        //Arrange
        Movie nextFriday = new Movie(1997, "Next Friday", new String[]{"Chris Rock","Ice Cube"});
        //Act
        Movie result = facade.makeMovie(nextFriday);
        //Assert
        assertEquals(nextFriday, result);
        // Removing the user again so it doesn't mess up the other tests.
        EntityManager em = emf.createEntityManager();
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(Movie.class, new Long(movies.size() + 1)));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
