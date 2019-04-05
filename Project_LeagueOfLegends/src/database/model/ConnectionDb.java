package model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ConnectionDb {

   public void ConnectionDbMysql(){
       String configFileName = "hibernate.cfg.xml";

//       ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure(configFileName).build();
//
//       Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
       Configuration configuration = new Configuration().configure(configFileName);
       try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
           System.out.println(sessionFactory);
           System.out.println("Connection Complete!");
       }
   }
}
