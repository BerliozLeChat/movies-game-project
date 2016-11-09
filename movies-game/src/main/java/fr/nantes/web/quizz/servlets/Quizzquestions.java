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

public class Quizzquestions extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JSONArray quizzquestions = new JSONArray();
        Questionnaire questionnaire = new Questionnaire();
        quizzquestions = questionnaire.toJson();

        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println(quizzquestions);
            //out.println("[{\"0\":{\"qui_vrai\":\"Anjan Dutt\",\"quand_r1\":\"1989\",\"quand_r3\":\"2008\",\"quand_r2\":\"1999\",\"qui_r3\":\"Anjan Dutt\",\"qui_r2\":\"Teddy Soeriaatmadja\",\"longitude\":\"77.2083\",\"qui_r1\":\"Robert F. Hill\",\"latitude\":\"18.975\",\"nom\":\"Chalo Let's Go\",\"quand_vrai\":\"2008\"},\"1\":{\"qui_vrai\":\"दिव्या खोसला कुमार\",\"quand_r1\":\"2001\",\"quand_r3\":\"2013\",\"quand_r2\":\"2011\",\"qui_r3\":\"दिव्या खोसला कुमार\",\"qui_r2\":\"Ching Siu-tung\",\"longitude\":\"77.2083\",\"qui_r1\":\"Nakamura, Tetsuji\",\"latitude\":\"28.6133\",\"nom\":\"Yaariyan\",\"quand_vrai\":\"2013\"},\"2\":{\"qui_vrai\":\"Tuviera, Michael\",\"quand_r1\":\"1990\",\"quand_r3\":\"2007\",\"quand_r2\":\"2000\",\"qui_r3\":\"George T. Miller\",\"qui_r2\":\"Tuviera, Michael\",\"longitude\":\"120.967\",\"qui_r1\":\"Harry A. Pollard\",\"latitude\":\"13.0\",\"nom\":\"Shake, Rattle and Roll 9\",\"quand_vrai\":\"2007\"},\"3\":{\"qui_vrai\":\"Satyanarayana, E. V. V.\",\"quand_r1\":\"1990\",\"quand_r3\":\"2006\",\"quand_r2\":\"2000\",\"qui_r3\":\"Satyanarayana, E. V. V.\",\"qui_r2\":\"Jung Ji-woo\",\"longitude\":\"72.8258\",\"qui_r1\":\"Biju Viswanath\",\"latitude\":\"18.975\",\"nom\":\"Kithakithalu\",\"quand_vrai\":\"2006\"},\"4\":{\"qui_vrai\":\"David Dhawan\",\"quand_r1\":\"1984\",\"quand_r3\":\"2004\",\"quand_r2\":\"1994\",\"qui_r3\":\"Wych Kaosayananda\",\"qui_r2\":\"Jun Ichikawa\",\"longitude\":\"77.2083\",\"qui_r1\":\"David Dhawan\",\"latitude\":\"18.975\",\"nom\":\"Andaz\",\"quand_vrai\":\"1994\"},\"5\":{\"qui_vrai\":\"Fernando Trueba\",\"quand_r1\":\"2000\",\"quand_r3\":\"2012\",\"quand_r2\":\"2002\",\"qui_r3\":\"Nigel Cole\",\"qui_r2\":\"Earl Bellamy\",\"longitude\":\"2.0\",\"qui_r1\":\"Fernando Trueba\",\"latitude\":\"48.8567\",\"nom\":\"Calle 54\",\"quand_vrai\":\"2000\"},\"6\":{\"qui_vrai\":\"Nagamine, Tatsuya\",\"quand_r1\":\"1989\",\"quand_r3\":\"2000\",\"quand_r2\":\"1999\",\"qui_r3\":\"Nagamine, Tatsuya\",\"qui_r2\":\"Michele Massimo Tarantini\",\"longitude\":\"139.767\",\"qui_r1\":\"Juraj Nvota\",\"latitude\":\"35.0\",\"nom\":\"Digimon Adventure 02: Digimon Hurricane Touchdown!! \\/ Supreme Evolution!! The Golden Digimentals\",\"quand_vrai\":\"2000\"},\"7\":{\"qui_vrai\":\"Bahry, Deepak\",\"quand_r1\":\"1989\",\"quand_r3\":\"1997\",\"quand_r2\":\"1993\",\"qui_r3\":\"Sankar\",\"qui_r2\":\"Stefan Valdobrev\",\"longitude\":\"77.2083\",\"qui_r1\":\"Bahry, Deepak\",\"latitude\":\"28.6133\",\"nom\":\"Ek Hi Raasta\",\"quand_vrai\":\"1993\"},\"8\":{\"qui_vrai\":\"Thangar Bachan\",\"quand_r1\":\"1996\",\"quand_r3\":\"2007\",\"quand_r2\":\"2006\",\"qui_r3\":\"Thangar Bachan\",\"qui_r2\":\"Grayson, Godfrey\",\"longitude\":\"77.2083\",\"qui_r1\":\"Ralph Thomas\",\"latitude\":\"28.6133\",\"nom\":\"Pallikoodam\",\"quand_vrai\":\"2007\"},\"9\":{\"qui_vrai\":\"Popovski, Aleksandar\",\"quand_r1\":\"1988\",\"quand_r3\":\"1999\",\"quand_r2\":\"1998\",\"qui_r3\":\"Popovski, Aleksandar\",\"qui_r2\":\"Charles E. Sellier Jr.\",\"longitude\":\"21.7\",\"qui_r1\":\"Talmadge, Richard\",\"latitude\":\"42.0\",\"nom\":\"Goodbye, 20th Century!\",\"quand_vrai\":\"1999\"}}]");
        }
    }
}
