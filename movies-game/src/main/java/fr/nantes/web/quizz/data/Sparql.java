package fr.nantes.web.quizz.data;

import com.hp.hpl.jena.query.*;
import fr.nantes.web.quizz.data.Film;

import java.util.ArrayList;

/**
 * Created by francois on 08/11/16.
 */
public class Sparql {

    public static int countMovies() {
        ParameterizedSparqlString qs = new ParameterizedSparqlString("" +
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
                "Select (count(?name) as ?nb_movies) where\n" +
                "                {\n" +
                "                        ?x rdf:type dbo:Film.\n" +
                "                        ?x dbp:name ?name.\n" +
                "                        FILTER (LANG(?name)='en').\n" +
                "                        ?x dbp:released ?date.\n" +
                "                        FILTER(datatype(?date)=xsd:date)\n" +
                "                        ?x dbp:country ?country.\n" +
                "                        ?x dbo:director ?director.\n" +
                "                        ?director dbp:name ?name_director.\n" +
                "                        FILTER (?date > '1990-01-01'^^xsd:dateTime).\n" +
                "                        ?x dbo:wikiPageID ?id_wiki.\n" +
                "                        ?country dbp:commonName ?country_name.\n" +
                "                        ?director dbo:wikiPageID ?id_wiki_director.\n" +
                "                }");
        QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", qs.asQuery());

        ResultSet results = ResultSetFactory.copyResults(exec.execSelect());

        QuerySolution row;
        String nb_movies_string;
        row = results.next();
        nb_movies_string = row.get("?nb_movies").toString();

        nb_movies_string = nb_movies_string.substring(0, nb_movies_string.indexOf('^'));

        return Integer.parseInt(nb_movies_string);
    }

    public static Film getMovie(int offset) {
        ParameterizedSparqlString qs = new ParameterizedSparqlString("" +
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
                "Select ?name ?date ?name_director ?id_wiki ?id_wiki_director ?country_name where\n" +
                "                {\n" +
                "                        ?x rdf:type dbo:Film.\n" +
                "                        ?x dbp:name ?name.\n" +
                "                        FILTER (LANG(?name)='en').\n" +
                "                        ?x dbp:released ?date.\n" +
                "                        FILTER(datatype(?date)=xsd:date)\n" +
                "                        ?x dbp:country ?country.\n" +
                "                        ?x dbo:director ?director.\n" +
                "                        ?director dbp:name ?name_director.\n" +
                "                        FILTER (?date > '1990-01-01'^^xsd:dateTime).\n" +
                "                        ?x dbo:wikiPageID ?id_wiki.\n" +
                "                        ?country dbp:commonName ?country_name.\n" +
                "                        ?director dbo:wikiPageID ?id_wiki_director.\n" +
                "                }\n" +
                "limit 1\n" +
                "offset " + offset);

        QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", qs.asQuery());

        ResultSet results = ResultSetFactory.copyResults(exec.execSelect());

        QuerySolution row = results.next();

        String annee = row.get("?date").toString().substring(0, 4);

        String name_movie = row.get("?name").toString();
        name_movie = name_movie.replace("@en", "");

        String name_director = row.get("?name_director").toString();
        name_director = name_director.replace("@en", "");

        String country = row.get("?country_name").toString();
        country = country.replace("@en", "");

        String id_wiki = row.get("?id_wiki").toString();
        id_wiki = id_wiki.replace("^^http://www.w3.org/2001/XMLSchema#integer", "");

        String id_wiki_director = row.get("?id_wiki_director").toString();
        id_wiki_director = id_wiki_director.replace("^^http://www.w3.org/2001/XMLSchema#integer", "");

        return new Film(name_movie, country, name_director, annee, id_wiki, id_wiki_director);
    }

    public static ArrayList<Director> getDirectors(int limit) {
        ArrayList<Director> liste = new ArrayList<Director>();
        ParameterizedSparqlString qs = new ParameterizedSparqlString(""+
                "" +
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
                "Select ?name_director ?id_wiki where\n" +
                "{\n" +
                "?x rdf:type dbo:Film.\n" +
                "?x dbo:director ?director.\n" +
                "?director dbp:name ?name_director.\n" +
                "?x dbp:country ?country.\n" +
                "?director dbo:wikiPageID ?id_wiki.\n" +
                "}");

        int i = 1;

        QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );
        // Normally you'd just do results = exec.execSelect(), but I want to
        // use this ResultSet twice, so I'm making a copy of it.
        ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );
        ArrayList list_for_group_by = new ArrayList();
        String name_director;
        String wiki_director;
        int count =0;
        while ( results.hasNext() && count<limit) {
            QuerySolution row = (QuerySolution) results.next();
            wiki_director = row.get("?id_wiki").toString();
            if (!list_for_group_by.contains(wiki_director)) {
                list_for_group_by.add(wiki_director);
                name_director = row.get("?name_director").toString();
                name_director = name_director.replace("@en", "");

                wiki_director = row.get("?id_wiki").toString();
                wiki_director = wiki_director.replace("^^http://www.w3.org/2001/XMLSchema#integer", "");
                wiki_director = "d" + wiki_director;
                ++count;
                liste.add(new Director(name_director, wiki_director));
            }
        }
        return liste;
    }

    public static ArrayList<Film> getMovies(int limit, int offset) {
        ArrayList<Film> liste = new ArrayList<Film>();
        ParameterizedSparqlString qs = new ParameterizedSparqlString("" +
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
                "Select ?name ?date ?name_director ?id_wiki ?id_wiki_director ?country_name where\n" +
                "                {\n" +
                "                        ?x rdf:type dbo:Film.\n" +
                "                        ?x dbp:name ?name.\n" +
                "                        FILTER (LANG(?name)='en').\n" +
                "                        ?x dbp:released ?date.\n" +
                "                        FILTER(datatype(?date)=xsd:date)\n" +
                "                        ?x dbp:country ?country.\n" +
                "                        ?x dbo:director ?director.\n" +
                "                        ?director dbp:name ?name_director.\n" +
                "                        FILTER (?date > '1990-01-01'^^xsd:dateTime).\n" +
                "                        ?x dbo:wikiPageID ?id_wiki.\n" +
                "                        ?country dbp:commonName ?country_name.\n" +
                "                        ?director dbo:wikiPageID ?id_wiki_director.\n" +
                "                }\n" +
                "order by (?date)\n" +
                "offset "+ offset +"\n"+
                "limit "+limit);

        QueryExecution exec = QueryExecutionFactory.sparqlService( "http://dbpedia.org/sparql", qs.asQuery() );
        // Normally you'd just do results = exec.execSelect(), but I want to
        // use this ResultSet twice, so I'm making a copy of it.
        ResultSet results = ResultSetFactory.copyResults( exec.execSelect() );
        ArrayList list_for_group_by = new ArrayList();
        String annee;
        String name_movie, name_director, country, id_wiki, id_wiki_director;
        while ( results.hasNext() ) {
            QuerySolution row = (QuerySolution) results.next();

            annee = row.get("?date").toString().substring(0, 4);

            name_movie = row.get("?name").toString();
            name_movie = name_movie.replace("@en", "");

            name_director = row.get("?name_director").toString();
            name_director = name_director.replace("@en", "");


            country = row.get("?country_name").toString();
            country = country.replace("@en", "");


            id_wiki = row.get("?id_wiki").toString();
            id_wiki = id_wiki.replace("^^http://www.w3.org/2001/XMLSchema#integer", "");

            id_wiki_director = row.get("?id_wiki_director").toString();
            id_wiki_director = id_wiki_director.replace("^^http://www.w3.org/2001/XMLSchema#integer", "");
            liste.add( new Film(name_movie, country, name_director, annee, id_wiki, id_wiki_director));
        }
        return liste;
    }
}
