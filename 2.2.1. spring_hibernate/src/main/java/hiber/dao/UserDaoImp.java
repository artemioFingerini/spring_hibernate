package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      if(user.getCar() != null) {
         sessionFactory.getCurrentSession().save(user.getCar());
      }
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByModelAndSeries(String model, int series) {
      User result = new User();
      try {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("SELECT us FROM User us JOIN us.car aut WHERE aut.model = :carModel AND aut.series = :carSeries", User.class);
      query.setParameter("carModel",model);
      query.setParameter("carSeries",series);
      result = query.getSingleResult();
      return result;
   }catch (NoResultException e) {
      }
      return null;
}
}
