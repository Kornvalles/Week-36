package facades;

import dto.MovieDTO;
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
    public static MovieFacade getMovieFacade(EntityManagerFactory _emf) {
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

    public Movie makeMovie(Movie movie) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(movie);
            em.getTransaction().commit();
            return movie;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<MovieDTO> getAllMoviesDTO() {
        EntityManager em = getEntityManager();
        try {
            List<Movie> movies = em.createNamedQuery("Movie.findAll").getResultList();
            List<MovieDTO> result = new ArrayList<>();
            for(Movie movie: movies){
                result.add(new MovieDTO(movie));
            }
            return result;
        } finally {
            em.close();
        }
    }

    @Override
    public MovieDTO getMovieDTOById(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Movie movie = em.find(Movie.class, id);
            return new MovieDTO(movie); 
        } finally {
            em.close();
        }
    }

    @Override
    public long getMovieCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long countMovies = (long) em.createQuery("SELECT COUNT(r) FROM Movie r").getSingleResult();
            return countMovies;
        } finally {
            em.close();
        }

    }

    @Override
    public MovieDTO getMovieDTOByName(String name){
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT new dto.MovieDTO(m) FROM Movie m WHERE m.name = :name", MovieDTO.class)
                    .setParameter("name", name).getSingleResult();
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
