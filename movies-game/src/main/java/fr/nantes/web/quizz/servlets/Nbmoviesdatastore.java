package fr.nantes.web.quizz.servlets;

import fr.nantes.web.quizz.data.Requetesdatastore;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Sullivan on 22/11/2016.
 */
public class Nbmoviesdatastore  extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int countmovies = Requetesdatastore.getcountmovies();
        JSONArray result = new JSONArray();
        JSONObject map = new JSONObject();
        map.put("count",countmovies);
        result.add(map);

        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println(result);
        }
    }
}
