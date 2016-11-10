package fr.nantes.web.quizz.data;

import com.google.appengine.api.datastore.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by Sullivan on 08/11/2016.
 */
public class Requetesdatastore {
    public static Director getdirector(int id) {

        String name_director = "";
        String wiki_director = "";
        DatastoreService datastore;
        datastore = DatastoreServiceFactory.getDatastoreService();
        Key cle_director = KeyFactory.createKey("directors",id);
        try{
            Entity director_trouve = datastore.get(cle_director);
            name_director = (String) director_trouve.getProperty("name_director");
        } catch (Exception e){}

        return new Director(name_director,wiki_director);
    }

    public static boolean adddirectors(int limit) {
        ArrayList<Director> liste = Sparql.getDirectors(limit);
        int i =0;
        DatastoreService datastore;
        Key cle_date;
        Entity e;
        int count_directors=Requetesdatastore.getcountdirectors();
        for (i = count_directors; i < liste.size(); ++i) {
            datastore = DatastoreServiceFactory.getDatastoreService();
            e = new Entity("directors", i+1);
            e.setProperty("wiki_director", liste.get(i).getWiki_director());
            e.setProperty("name_director", liste.get(i).getName_director());
            datastore = DatastoreServiceFactory.getDatastoreService();
            datastore.put(e);
        }
        e = new Entity("count_directors", 1);
        e.setProperty("valeur",i);
        datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(e);

        return (Requetesdatastore.getcountdirectors()==limit);
    }

    public static String addadmin(String id) {
        DatastoreService datastore;
        Key cle_date;
        Entity e;
        datastore = DatastoreServiceFactory.getDatastoreService();
        e = new Entity("admin", id);
        e.setProperty("admin", true);
        datastore.put(e);
        return (id);
    }

    public static boolean isadmin(String id) {
        boolean isadmin = false;
        DatastoreService datastore;
        datastore = DatastoreServiceFactory.getDatastoreService();
        Key cle_admin = KeyFactory.createKey("admin",id);
        try{
            Entity admin_trouve = datastore.get(cle_admin);
            isadmin = (boolean) admin_trouve.getProperty("admin");
        } catch (Exception e){}

        return isadmin;
    }

    public static int getcountdirectors() {
        int nb = 0;
        try{
            DatastoreService datastore;
            datastore = DatastoreServiceFactory.getDatastoreService();
            Key cle_count_directors = KeyFactory.createKey("count_directors",1);
            Entity count_trouve = datastore.get(cle_count_directors);
            nb = ((Long) count_trouve.getProperty("valeur")).intValue();
        }catch (Exception e){
        }
        return nb;
    }
}