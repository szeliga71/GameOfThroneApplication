package pl.wp.gameofthroneapplication.databaseutils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.wp.gameofthroneapplication.model.CustomUser;

import java.util.List;

public class CustomUserDao {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final SessionFactory sessionFactory = CustomUserSessionFactory.getSessionFactory();

    public void saveUser(CustomUser customUser) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        customUser.setPassword(passwordEncoder.encode(customUser.getPassword()));
        session.merge(customUser);
        transaction.commit();
        session.close();
    }

    private boolean userExists(CustomUser user) {
        return findUserByEmail(user.getEmail()) != null;
    }

    public CustomUser findUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CustomUser> userQuery = criteriaBuilder.createQuery(CustomUser.class);
        Root<CustomUser> root = userQuery.from(CustomUser.class);
        userQuery.select(root).where(criteriaBuilder.equal(root.get("email"), email));
        CustomUser user = session.createQuery(userQuery).getSingleResultOrNull();
        session.close();
        return user;
    }
    public CustomUser findUserById(Long id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<CustomUser> userQuery = criteriaBuilder.createQuery(CustomUser.class);
        Root<CustomUser> root = userQuery.from(CustomUser.class);
        userQuery.select(root).where(criteriaBuilder.equal(root.get("id"),id));
        CustomUser user = session.createQuery(userQuery).getSingleResultOrNull();
        session.close();
        return user;
    }

    public void deleteUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CustomUser user = findUserByEmail(email);
        System.out.println(user);
        System.out.println(user.getEmail());
        session.remove(user);
        transaction.commit();
        session.close();
    }

    public void deleteUserById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CustomUser user = findUserById(id);
        if (userExists(user)) {
            session.delete(user);
            transaction.commit();
            session.close();
        }
    }



    public List<CustomUser> getAllUsers() {
        Session session = sessionFactory.openSession();
        //Transaction transaction = session.beginTransaction();
        CriteriaQuery<CustomUser> userQuery = session.getCriteriaBuilder().createQuery(CustomUser.class);
        Root<CustomUser> root = userQuery.from(CustomUser.class);
        userQuery.select(root);
        List<CustomUser> users = session.createQuery(userQuery).getResultList();
        //transaction.commit();
        session.close();
        return users;
    }
}