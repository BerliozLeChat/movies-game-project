package fr.nantes.web.quizz.servlets;

import fr.nantes.web.quizz.data.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import fr.nantes.web.quizz.data.Requetesdatastore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetScore extends HttpServlet {


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = UserServiceFactory.getUserService().getCurrentUser();

        if(user != null) {
            int score = Requetesdatastore.getscore(user.getUserId());
            JSONArray result = new JSONArray();
            JSONObject map = new JSONObject();
            map.put("score",score);
            result.add(map);

            response.setContentType("application/json;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {

                out.println(result);
            }
        }

    }
}
