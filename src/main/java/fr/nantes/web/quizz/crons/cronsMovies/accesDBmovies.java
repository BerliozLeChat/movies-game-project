package fr.nantes.web.quizz.crons.cronsMovies;

import fr.nantes.web.quizz.data.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;

import java.util.ArrayList;

public class accesDBmovies {
    public static boolean goCron() {
        boolean result = true;

        DatastoreService datastore;
        datastore = DatastoreServiceFactory.getDatastoreService();
        Key offsetCronsMoviescle = KeyFactory.createKey("offsetCronsMovies",1);
        try{
            int offsetCronsMovies;

            Entity offsetCronsMoviesentite = datastore.get(offsetCronsMoviescle);
            offsetCronsMovies = ((Long) offsetCronsMoviesentite.getProperty("valeur")).intValue();
            int moviesUpdate = 0;
            ArrayList<Film> liste = Sparql.getMovies(10, offsetCronsMovies);
            Entity e;
            for(int i=0; i<liste.size();++i){
                e = new Entity("movies", i + offsetCronsMovies +1);
                e.setProperty("nom", liste.get(i).getNom());
                e.setProperty("pays", liste.get(i).getPays());
                e.setProperty("realisateur", liste.get(i).getRealisateur());
                e.setProperty("annee", liste.get(i).getAnnee());
                e.setProperty("id_wiki", liste.get(i).getId_wiki());
                e.setProperty("id_wiki_realisateur", liste.get(i).getId_wiki_realisateur());
                datastore.put(e);
                moviesUpdate++;
            }
            int countmovies = Requetesdatastore.getcountmovies();
            if(countmovies<offsetCronsMovies+moviesUpdate){
                e = new Entity("count_movies",1 );
                e.setProperty("valeur", offsetCronsMovies+moviesUpdate);
                datastore.put(e);
            }
            if(countmovies==(offsetCronsMovies+moviesUpdate)){
                e = new Entity("offsetCronsMovies",1);
                e.setProperty("valeur", 0);
                datastore.put(e);
            }
        } catch (Exception e){
            Entity offsetCronsMovies = new Entity("offsetCronsMovies",1);
            offsetCronsMovies.setProperty("valeur", 0);
            datastore.put(offsetCronsMovies);
            result=false;
        }
        return result;
    }
}