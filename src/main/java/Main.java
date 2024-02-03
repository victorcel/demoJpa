import entity.Employee;
import jakarta.persistence.*;

public class Main {

    public static void main(String[] args) {
//        Employee employee = new Employee();
//        employee.setfName("John");
//        employee.setlName("Doe");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(employee);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        entityManagerFactory.close();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findAll", Employee.class);
            System.out.println(query.getResultList().size());
            query.getResultList().forEach(System.out::println);
            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }

    }
}
