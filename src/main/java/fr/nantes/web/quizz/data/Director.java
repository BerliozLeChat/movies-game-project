package fr.nantes.web.quizz.data;

/**
 * Created by Sullivan on 08/11/2016.
 */
public class Director {
    String name_director;
    String wiki_director;

    public Director(String name_director, String wiki_director) {
        this.name_director = name_director;
        this.wiki_director = wiki_director;
    }

    public String getName_director() {
        return name_director;
    }

    public void setName_director(String name_director) {
        this.name_director = name_director;
    }

    public String getWiki_director() {
        return wiki_director;
    }

    public void setWiki_director(String wiki_director) {
        this.wiki_director = wiki_director;
    }

}
