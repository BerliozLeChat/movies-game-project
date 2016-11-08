package fr.nantes.web.quizz.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

/**
 * Created by francois on 08/11/16.
 */
public class Questionnaire {

    HashMap<String,String>[] questions;

    public Questionnaire(){
        Films films = new Films();
        questions = new HashMap[10];
        for(int i = 0;i<10;++i){
            questions[i] = Generateurquestionsfilm.getquestionsfilm(films.get(i));
        }
    }

    public JSONArray toJson(){
        JSONArray result = new JSONArray();
        JSONObject map = new JSONObject();
        for(int i = 0;i<10;++i) {
            map.putAll(questions[i]);
            result.add(i,map);
        }
        return result;
    }
}
