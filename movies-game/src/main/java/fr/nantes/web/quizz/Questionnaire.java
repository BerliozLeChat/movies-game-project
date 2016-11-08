package fr.nantes.web.quizz;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francois on 08/11/16.
 */
public class Questionnaire {
    List<Film> liste;

    public Questionnaire(){
        liste = new ArrayList<Film>(10);
    }
}
