package fr.nantes.web.quizz.data;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;

import javax.persistence.*;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public static Film getfilm(int id) {
        String nom = "";
        String pays = "";
        String realisateur = "";
        String annee = "1900";
        String id_wiki="";
        String id_wiki_realisateur="";

        DatastoreService datastore;
        datastore = DatastoreServiceFactory.getDatastoreService();
        Key cle_movie = KeyFactory.createKey("movies",id);
        try{
            Entity movie_trouve = datastore.get(cle_movie);
            nom = (String) movie_trouve.getProperty("nom");
            pays = (String) movie_trouve.getProperty("pays");
            realisateur = (String) movie_trouve.getProperty("realisateur");
            id_wiki= (String) movie_trouve.getProperty("id_wiki");
            id_wiki_realisateur= (String) movie_trouve.getProperty("id_wiki_realisateur");
            annee = (String) movie_trouve.getProperty("annee");
        } catch (Exception e){}

        return new Film(nom,pays,realisateur,annee, id_wiki,id_wiki_realisateur);
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

    public static int getscore(String userId) {
        int nb = 0;
        try{
            DatastoreService datastore;
            datastore = DatastoreServiceFactory.getDatastoreService();
            Key cle_score = KeyFactory.createKey("Scores",userId);
            Entity val_score = datastore.get(cle_score);
            nb = ((Long) val_score.getProperty("score")).intValue();
        }catch (Exception e){
        }
        return nb;
    }


    public static int getcountmovies(){
        int nb = 0;
        try{
            DatastoreService datastore;
            datastore = DatastoreServiceFactory.getDatastoreService();
            Key cle_count_directors = KeyFactory.createKey("count_movies",1);
            Entity count_trouve = datastore.get(cle_count_directors);
            if(count_trouve!=null) {
                nb = ((Long) count_trouve.getProperty("valeur")).intValue();
            }else{
                nb=0;
            }

        }catch (Exception e){
        }
        return nb;
    }
    //return 1 si tout c'est bien passé
    //return 2 si tout c'est bien passé et que le datastore possède tout les films que dbpedia possède
    //return 100 si une erreur

    public static int addmovies(int limit, HttpServletResponse resp) {
     try {
         int count_movies = getcountmovies();
         ArrayList<Film> liste = Sparql.getMovies(limit,count_movies);
         DatastoreService datastore;
         Entity e;
         int i = 0;
         datastore = DatastoreServiceFactory.getDatastoreService();

         while (i<liste.size()) {
            e = new Entity("movies", i + count_movies +1);
            e.setProperty("nom", liste.get(i).getNom());
            e.setProperty("pays", liste.get(i).getPays());
            e.setProperty("realisateur", liste.get(i).getRealisateur());
            e.setProperty("annee", liste.get(i).getAnnee());
            e.setProperty("id_wiki", liste.get(i).getId_wiki());
            e.setProperty("id_wiki_realisateur", liste.get(i).getId_wiki_realisateur());
            datastore.put(e);
             ++i;
         }

         e = new Entity("count_movies",1 );
         e.setProperty("valeur", i + count_movies);
         datastore = DatastoreServiceFactory.getDatastoreService();
         datastore.put(e);


         if (limit == i)
             return (1);
         else if (limit > i)
             return (2);
         else
             return (3);
     }catch (Exception e){
         try {
             resp.getWriter().println("UNE ERREUR S'EST PRODUIT !!!!! "+e.toString());
         }catch (Exception ex){}
     }
        return (3);
    }

    public static boolean existScoreUser(String id_user){
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key clef_user = KeyFactory.createKey("Scores",id_user);
        boolean res = false;
        try{
            datastore.get(clef_user);
            res = true;
        }
        catch (EntityNotFoundException e) {
        //do nothing
        }
        finally {
            return res;
        }


    }

    public static void insertNewScore(String id_user, String name, int score){
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity newScore = new Entity("Scores",id_user);
        newScore.setProperty("name", name);
        newScore.setProperty("score", score);

        datastore.put(newScore);


    }

    /**
     * Utilisateur doit exister dans la base sinon retourne une exeption EntityNotFoundException !!!
     */

    public static boolean updateExistingScore(String id_user, int newScore )throws EntityNotFoundException{
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key clef_user = KeyFactory.createKey("Scores",id_user);
        Entity score_user = datastore.get(clef_user);

        int scoreActuel = getscore(id_user);

        if(scoreActuel<newScore){
            score_user.setProperty("score",newScore);
            datastore.put(score_user);
            return true;
            }
            else
                return false;

    }


}