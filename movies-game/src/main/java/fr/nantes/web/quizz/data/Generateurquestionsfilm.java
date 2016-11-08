package fr.nantes.web.quizz.data;

import com.google.appengine.api.datastore.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Sullivan on 08/11/2016.
 */
public class Generateurquestionsfilm {
    public static HashMap<String, String> getquestionsfilm(Film film){
        HashMap<String, String> hmap = new HashMap<String, String>();

        String name=film.getNom();

        String qui_r1 = "" ;
        String qui_r2 = "" ;
        String qui_r3 = "" ;
        String qui_vrai = film.getRealisateur();
        String quand_r1 = "" ;
        String quand_r2 = "" ;
        String quand_r3 = "" ;
        String quand_vrai = String.valueOf(film.getAnnée());
        try {
            int nb_director = 0;
            String productor_movie_false_1 = "";
            String productor_movie_false_2 = "";
            int premier_random = -1;
            Random r = new Random();
            int valeur_random;
            int count_directors = Requetesdatastore.getcountdirectors(null);
            while (nb_director < 2) {
                valeur_random = 1 + r.nextInt(count_directors);
                Director director = Requetesdatastore.getdirector(valeur_random);

                if (director.getWiki_director() != ("d"+film.getRealisateur()) ) {
                    if (premier_random == (-1) || premier_random != valeur_random) {
                        premier_random = valeur_random;
                        if (nb_director == 0) {
                            productor_movie_false_1 = director.getName_director();
                        } else {
                            productor_movie_false_2 = director.getName_director();
                        }
                        ++nb_director;
                    }
                }
            }
            valeur_random = 1 + r.nextInt(3);
            if (valeur_random == 1) {
                qui_r1 = qui_vrai;
                qui_r2 = productor_movie_false_1;
                qui_r3 = productor_movie_false_2;
            } else if (valeur_random == 2) {
                qui_r1 = productor_movie_false_1;
                qui_r2 = qui_vrai;
                qui_r3 = productor_movie_false_2;
            } else {
                qui_r1 = productor_movie_false_1;
                qui_r2 = productor_movie_false_2;
                qui_r3 = qui_vrai;
            }
            int date_film = film.getAnnée();
            int date_false_1 =0;
            int date_false_2 =0;
            valeur_random = 1 + r.nextInt(3);
            if(valeur_random==1){
                valeur_random = 1 + r.nextInt(10);
                date_false_1 = date_film+valeur_random;
                date_false_2 = date_film+10+valeur_random;
                quand_r1 = quand_vrai;
                quand_r2 = String.valueOf(date_false_1);
                quand_r3 = String.valueOf(date_false_2);

            }else if(valeur_random==2){
                valeur_random = 1 + r.nextInt(10);
                date_false_1 = date_film-valeur_random;
                date_false_2 = date_film+valeur_random;
                quand_r1 = String.valueOf(date_false_1);
                quand_r2 = quand_vrai;
                quand_r3 = String.valueOf(date_false_2);
            }if(valeur_random==3){
                valeur_random = 1 + r.nextInt(10);
                date_false_1 = date_film-10-valeur_random;
                date_false_2 = date_film-valeur_random;
                quand_r1 = String.valueOf(date_false_1);
                quand_r2 = String.valueOf(date_false_2);
                quand_r3 = quand_vrai;
            }
        } catch (Exception e){}

        hmap.put("nom",name);
        hmap.put("qui_r1",qui_r1);
        hmap.put("qui_r2",qui_r2);
        hmap.put("qui_r3",qui_r3);
        hmap.put("qui_vrai",qui_vrai);
        hmap.put("quand_r1",quand_r1);
        hmap.put("quand_r2",quand_r2);
        hmap.put("quand_r3",quand_r3);
        hmap.put("quand_vrai",quand_vrai);
        hmap.put("longitude",film.getLongitude());
        hmap.put("latitude",film.getLatitude());

        return hmap;
    }

    public static void getquestionsfilm( HashMap<String, String>  hmap, HttpServletResponse response){
        try{
            response.getWriter().println("nom : "+hmap.get("nom"));
            response.getWriter().println("qui_r1 : "+hmap.get("qui_r1"));
            response.getWriter().println("qui_r2 : "+hmap.get("qui_r2"));
            response.getWriter().println("qui_r3 : "+hmap.get("qui_r3"));
            response.getWriter().println("qui_vrai : "+hmap.get("qui_vrai"));
            response.getWriter().println("quand_r1 : "+hmap.get("quand_r1"));
            response.getWriter().println("quand_r2 : "+hmap.get("quand_r2"));
            response.getWriter().println("quand_r3 : "+hmap.get("quand_r3"));
            response.getWriter().println("quand_vrai : "+hmap.get("quand_vrai"));
            response.getWriter().println("longitude : "+hmap.get("longitude"));
            response.getWriter().println("latitude : "+hmap.get("latitude"));
        } catch(Exception e){}
    }
}
