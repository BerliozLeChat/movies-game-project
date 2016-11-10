/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

public class Adddirectors extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int nbdirectors = Integer.parseInt(request.getParameter("nbdirectors"));

        JSONArray result = new JSONArray();
        JSONObject map = new JSONObject();
        if(nbdirectors>Requetesdatastore.getcountdirectors()){
            if(Requetesdatastore.adddirectors(nbdirectors)) {
                map.put("count", nbdirectors);
                result.add(map);
            }else{
                map.put("count", 0);
                result.add(map);
            }
        }else{
            map.put("count",-100);
            result.add(map);
        }
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println(result);
        }
    }
}
