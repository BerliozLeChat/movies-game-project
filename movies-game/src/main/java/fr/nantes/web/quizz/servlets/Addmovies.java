
/**
 * Created by Sullivan on 22/11/2016.
 */

package fr.nantes.web.quizz.servlets;

import fr.nantes.web.quizz.data.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Addmovies extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONArray result = new JSONArray();
        JSONObject map = new JSONObject();
        int nbmoviesadd = Integer.parseInt(request.getParameter("nbmoviesadd"));

        if(nbmoviesadd>0&&nbmoviesadd<501) {
            int nbmoviedeb = Requetesdatastore.getcountmovies();
            if(nbmoviedeb>-1) {
                int returnadd = Requetesdatastore.addmovies(nbmoviesadd, nbmoviedeb,response);
                if (1 == returnadd) {
                    map.put("count", 1);
                    result.add(map);
                } else if (2 == returnadd) {
                    map.put("count", 2);
                    result.add(map);
                } else {
                    map.put("count", 3);
                    result.add(map);
                }
            }else{
                map.put("count", 0);
                result.add(map);
            }
        }else{
            map.put("count", 0);
            result.add(map);
        }
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println(result);
        }
    }
}
