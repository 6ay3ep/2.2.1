package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional
   @Override
   public void add(User user, Car car) {
      sessionFactory.getCurrentSession().save(car);
      user.setCar(car);
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }


   @Transactional(readOnly = true)
   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      return userDao.getUserByCarModelAndSeries(model, series);
   }

}
