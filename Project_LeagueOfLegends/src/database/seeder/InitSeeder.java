package database.seeder;


import controller.GlobalController;
import database.model.Hero;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class InitSeeder {
    public void initializableSeeder(){
        ArrayList<Hero> list = new ArrayList<>();
        list.add(new Hero("Leesin", "Thầy tu mù", "http://lienminh360.vn/wp-content/uploads/2016/09/Lee_Sin_OriginalSkin.jpg", "https://lienminh.garena.vn/images/icons/LeeSin.png"));
        list.add(new Hero("Pyke", "Thủy quái", "https://lienminh.garena.vn/images/champions/skin/555_Pyke/Pyke_0.jpg", "https://lienminh.garena.vn/images/icons/Pyke.png"));
        GlobalController.addAllConfigsHero();
        Session session = GlobalController.factory.getCurrentSession();

        GlobalController.factory.openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Hero");
            query.setMaxResults(1);
            ArrayList<Hero> heroes = (ArrayList<Hero>) query.getResultList();
            if (heroes.isEmpty()){
                for (int i = 0; i < list.size(); i++) {
                    try{
                        session.save(new Hero(list.get(i).getName(), list.get(i).getDescription(), list.get(i).getImage(), list.get(i).getThumbnail()));
                        System.out.println("seeder hero success!");
                    }catch (HibernateException e){
                        e.printStackTrace();
                        if (transaction != null){
                            transaction.rollback();
                        }
                    }finally {
                        if (i == list.size() - 1){
                            transaction.commit();
                            session.close();
                        }
                    }
                }
            }
        }catch (HibernateException e){
            e.printStackTrace();
            if(transaction != null){
                transaction.rollback();
            }
        }finally {
            session.close();
        }
    }
}
