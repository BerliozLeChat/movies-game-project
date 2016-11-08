package fr.nantes.web.quizz.data;

import com.hp.hpl.jena.query.*;
import fr.nantes.web.quizz.data.Film;

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

        ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );

        QuerySolution row;
        String nb_movies_string;
        row = results.next();
        nb_movies_string = row.get("?nb_movies").toString();

        nb_movies_string = nb_movies_string.substring(0,nb_movies_string.indexOf('^'));

        return Integer.parseInt(nb_movies_string);
    }

    public static Film getMovie(int offset){
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
                "Select ?name ?date ?name_director ?id_wiki ?id_wiki_director ?lat ?longi where\n" +
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
                "}"+
                "limit 1\n"+
                "offset "+offset);

        QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );

        ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );

        QuerySolution row = results.next();

        int annee = Integer.parseInt(row.get("?date").toString().substring(0,4));

        String name_movie = row.get("?name").toString();
        name_movie =  name_movie.replace("@en", "");

        String name_director = row.get("?name_director").toString();
        name_director =  name_director.replace("@en", "");


        String lat = row.get("?lat").toString();
        lat =  lat.replace("^^http://www.w3.org/2001/XMLSchema#float", "");

        String longi = row.get("?longi").toString();
        longi =  longi.replace("^^http://www.w3.org/2001/XMLSchema#float", "");

        String id_wiki = row.get("?id_wiki").toString();
        id_wiki =  id_wiki.replace("^^http://www.w3.org/2001/XMLSchema#integer", "");

        String id_wiki_director = row.get("?id_wiki_director").toString();
        id_wiki_director =  id_wiki_director.replace("^^http://www.w3.org/2001/XMLSchema#integer", "");

        return new Film(name_movie, longi, lat, name_director, annee, id_wiki, id_wiki_director);
    }
}
