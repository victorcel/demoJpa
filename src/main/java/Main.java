import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.Movie;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Main {

    public static void main(String[] args) {
//        Employee employee = new Employee();
//        employee.setfName("John");
//        employee.setlName("Doe");

//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(employee);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        entityManagerFactory.close();
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//            TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findAll", Employee.class);
//            System.out.println(query.getResultList().size());
//            query.getResultList().forEach(System.out::println);
//            transaction.commit();
//        } finally {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            entityManager.close();
//            entityManagerFactory.close();
//        }

        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        // Replace the uri string with your MongoDB deployment's connection string
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("demo-mongo-jpa").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Movie> collection = database.getCollection("movies", Movie.class);
//            Movie movie = new Movie();
//            movie.setTitle("Back to the Future");
//            movie.setPlot("Marty McFly travels back in time to save Doc Brown.");
//            movie.setGenres(asList("Adventure", "Science Fiction"));
//            collection.insertOne(movie);
            Movie movie = collection.find(eq("title", "Back to the Future")).first();
            System.out.println(movie);
        } catch (Exception e) {
            System.out.println("Error de conexion: " + e.getMessage());
        }

    }
}
