package facades;

import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade implements IMovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private MovieFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getNumberOfMoviesInDB() {
        EntityManager em = emf.createEntityManager();
        try {
            long countMovies = (long) em.createQuery("SELECT COUNT(r) FROM Movie r").getSingleResult();
            return countMovies;
        } finally {
            em.close();
        }

    }

    public Movie createMovie(Movie movie) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query
                    = em.createQuery("Select movie from Moive movie", Movie.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Movie getMovieById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Movie movie = em.find(Movie.class, id);
            return movie;
        } finally {
            em.close();
        }
    }

    @Override
    public long getMovieCountInDatabase() {
        EntityManager em = emf.createEntityManager();
        try {
            long countMovies = (long) em.createQuery("SELECT COUNT(r) FROM Movie r").getSingleResult();
            return countMovies;
        } finally {
            em.close();
        }

    }

    @Override
    public List<Movie> getMoviesByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m WHERE m.name = :name", Movie.class)
                    .setParameter("name", name);
            List<Movie> movie = new ArrayList(query.getResultList());
            return movie;
        } finally {
            em.close();
        }
    }

    @Override
    public void populateMovies() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            em.persist(new Movie(2014, "Ride Along", new String[]{"Kevin Hart", "Ice Cube"}));
            em.persist(new Movie(2017, "Jumanji: Welcome to the jungle", new String[]{"Kevin Hart", "Dwayne Johnson", "Jack Black"}));
            em.persist(new Movie(2017, "Ride Along 2", new String[]{"Kevin Hart", "Ice Cube"}));
            em.persist(new Movie(2019, "Night School", new String[]{"Kevin Hart", "Tiffany Haddish", "Rob Riggle"}));
            em.persist(new Movie(2016, "Kevin Hart: Laugh out loud!", new String[]{"Kevin Hart"}));
            em.persist(new Movie(2019, "Lil Dicky: Earth", new String[]{"Lil Dicky", "Kevin Hart"}));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
