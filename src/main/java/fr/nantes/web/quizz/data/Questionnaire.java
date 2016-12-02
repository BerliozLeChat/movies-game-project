package fr.nantes.web.quizz.data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

/**
 * Created by francois on 08/11/16.
 */
public class Questionnaire {

    private HashMap<String,String>[] questions;

    public Questionnaire(){
        Films films = new Films();
        this.questions = new HashMap[10];
        for(int i = 0;i<10;++i){
            this.questions[i] = Generateurquestionsfilm.getquestionsfilm(films.get(i));
        }
    }

    public JSONArray toJson(){
        JSONArray result = new JSONArray();
        JSONObject map = new JSONObject();
        for(int i = 0;i<10;++i) {
            map.put(i,questions[i]);
        }
        result.add(map);
        return result;
    }
    public String toJsonstring(){
        String result = "[";
        JSONObject map = new JSONObject();
        for(int i = 0;i<10;++i) {
            map.put(i,questions[i]);
        }
        result = result + map.toString() + "]";
        return result ;
    }
}
