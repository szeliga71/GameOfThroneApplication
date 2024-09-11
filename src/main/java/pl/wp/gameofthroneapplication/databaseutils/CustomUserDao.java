package pl.wp.gameofthroneapplication.databaseutils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.wp.gameofthroneapplication.model.CustomUser;

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
        return user;
    }
}