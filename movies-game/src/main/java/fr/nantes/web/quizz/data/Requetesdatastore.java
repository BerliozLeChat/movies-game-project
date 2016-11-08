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

    public static void adddirectors(int limit, HttpServletResponse response) {
        ArrayList<Director> liste = Sparql.getDirectors(limit);
        int i =0;
        DatastoreService datastore;
        Key cle_date;
        Entity e;
        try {
            response.getWriter().println("Ajout directors avec une limit de "+liste.size());
            for (i = 0; i < liste.size(); ++i) {
                datastore = DatastoreServiceFactory.getDatastoreService();
                e = new Entity("directors", i+1);
                e.setProperty("wiki_director", liste.get(i).getWiki_director());
                e.setProperty("name_director", liste.get(i).getName_director());
                datastore = DatastoreServiceFactory.getDatastoreService();
                datastore.put(e);
                response.getWriter().println("Add director wiki : "+ liste.get(i).getWiki_director() + " succesfull");
                response.getWriter().println("-----------------------------------------------------");
            }
            e = new Entity("count_directors", 1);
            e.setProperty("valeur",i);
            datastore = DatastoreServiceFactory.getDatastoreService();
            datastore.put(e);
            response.getWriter().println("Add "+ i + " directors");
            response.getWriter().println("-----------------------------------------------------");


        }catch (Exception ex){
            try{
                response.getWriter().println(ex.toString());
            }catch (Exception eee){}
        }
    }
    public static int getcountdirectors(HttpServletResponse response) {
        int nb = 0;
        try{
            DatastoreService datastore;
            datastore = DatastoreServiceFactory.getDatastoreService();
            Key cle_count_directors = KeyFactory.createKey("count_directors",1);
            Entity count_trouve = datastore.get(cle_count_directors);
            nb = ((Long) count_trouve.getProperty("valeur")).intValue();
        }catch (Exception e){
            try{
                response.getWriter().println(e.toString());
            }catch (Exception ee){}
        }
        return nb;
    }
}