package fr.nantes.web.quizz;

import com.hp.hpl.jena.query.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francois on 08/11/16.
 */
public class Sparql {

    public static int countMovies(){
        ParameterizedSparqlString qs = new ParameterizedSparqlString( "" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n" +
                "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" +
                "PREFIX : <http://dbpedia.org/resource/>\n" +
                "PREFIX dbpedia2: <http://dbpedia.org/property/>\n" +
                "PREFIX dbpedia: <http://dbpedia.org/>\n" +
                "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" +
                "PREFIX dbo: <http://dbpedia.org/ontology/>\n" +
                "PREFIX dbp: <http://dbpedia.org/property/>\n" +
                "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>\n" +
                "\n" +
                "Select (count (*) as ?nb_movies) where\n" +
                "{\n" +
                "        ?x rdf:type dbo:Film.\n" +
                "        ?x dbp:name ?name.\n" +
                "        FILTER (LANG(?name)='en').\n" +
                "        ?x dbp:released ?date.\n" +
                "        FILTER(datatype(?date)=xsd:date)\n" +
                "        ?x dbp:country ?country.\n" +
                "        ?x dbo:director ?director.\n" +
                "        ?director dbp:name ?name_director.\n" +
                "        FILTER (?date > '1990-01-01'^^xsd:dateTime).\n" +
                "        ?x dbo:wikiPageID ?id_wiki.\n" +
                "        ?country geo:lat ?lat.\n" +
                "        ?country geo:long ?longi.\n" +
                "        ?director dbo:wikiPageID ?id_wiki_director.\n" +
                "}");
        QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );
        // Normally you'd just do results = exec.execSelect(), but I want to
        // use this ResultSet twice, so I'm making a copy of it.
        ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );
        List list_for_group_by = new ArrayList();
        int count = 0;
        QuerySolution row;
        String nb_movies_string="";
        row = (QuerySolution)results.next();
        nb_movies_string = row.get("?nb_movies").toString();

        nb_movies_string = nb_movies_string.substring(0,nb_movies_string.indexOf('^'));
        int nb_movies = Integer.parseInt(nb_movies_string);


        return nb_movies;
    }

    public static Film getMovie(int offset){
        return null;
    }
}
