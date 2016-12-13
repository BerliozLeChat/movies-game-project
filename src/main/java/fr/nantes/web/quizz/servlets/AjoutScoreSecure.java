package fr.nantes.web.quizz.servlets;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import fr.nantes.web.quizz.data.Requetesdatastore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Sébastien on 13/12/2016.
 */
public class AjoutScoreSecure {

    @Override
    public void doP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        User user = UserServiceFactory.getUserService().getCurrentUser();

        if(user != null){
            if(Requetesdatastore.existScoreUser(user.getUserId()))
                try {
                    if (Requetesdatastore.updateExistingScore(user.getUserId(), (int) request.getAttribute("score")))
                        response.setStatus(251, "Félicitation, vous avez battu votre meilleur score !");
                    else
                        response.setStatus(250, "Vous n'avez pas battu votre meilleur score, essayez encore ;)");
                }catch (EntityNotFoundException e){
                    response.sendError(500, "erreur de fonctionnement interne");
                }
            else{
                    Requetesdatastore.insertNewScore(user.getUserId(),user.getNickname(),(int) request.getAttribute("score"));
                    response.setStatus(201,"Merci d'avoir jouer, votre score est enregistré. Vous pouvez continuer à vous amuser :)");

            }



        }



    }
}
