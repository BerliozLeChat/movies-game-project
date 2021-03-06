package fr.nantes.web.quizz.APIBackend;

/**
 * Created by Sébastien on 23/11/2016.
 */
//import com.google.api.server.spi.auth.common.User;

import com.google.appengine.api.users.User;
import fr.nantes.web.quizz.APIBackend.PMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;
import com.google.appengine.api.oauth.OAuthRequestException;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@Api(name = "scoresendpoint",
        namespace = @ApiNamespace(
                ownerDomain = "mycompany.com",
                ownerName = "mycompany.com",
                packagePath="services"
        )
)
public class scoresEndpoint {
    /**
     * This method lists all the entities inserted in datastore.
     * It uses HTTP GET method and paging support.
     *
     * @return A CollectionResponse class containing the list of all entities
     * persisted and a cursor to the next page.
     */
    @SuppressWarnings({"unchecked", "unused"})
    @ApiMethod(name = "listscores")
    public CollectionResponse<Scores> listscores(
            @Nullable @Named("cursor") String cursorString,
            @Nullable @Named("limit") Integer limit) {

        PersistenceManager mgr = null;
        Cursor cursor = null;
        List<Scores> execute = null;

        try{
            mgr = getPersistenceManager();
            Query query = mgr.newQuery(Scores.class);
            if (cursorString != null && cursorString != "") {
                cursor = Cursor.fromWebSafeString(cursorString);
                HashMap<String, Object> extensionMap = new HashMap<String, Object>();
                extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
                query.setExtensions(extensionMap);
            }

            if (limit != null) {
                query.setRange(0, limit);
            }

            execute = (List<Scores>) query.execute();
            cursor = JDOCursorHelper.getCursor(execute);
            if (cursor != null) cursorString = cursor.toWebSafeString();

            // Tight loop for fetching all entities from datastore and accomodate
            // for lazy fetch.
            for (Scores obj : execute);
        } finally {
            mgr.close();
        }

        return CollectionResponse.<Scores>builder()
                .setItems(execute)
                .setNextPageToken(cursorString)
                .build();
    }

    /**
     * This method lists all the entities inserted in datastore.
     * It uses HTTP GET method and paging support.
     * @return A CollectionResponse class containing the list of top10 entities
     */
    @SuppressWarnings({"unchecked", "unused"})
    @ApiMethod(name = "top10listscores",  httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Scores> top10listscores() {

        PersistenceManager mgr = null;
        Cursor cursor = null;
        List<Scores> execute = null;

        try{
            mgr = getPersistenceManager();

            Query query = mgr.newQuery(Scores.class);
            query.setOrdering("score desc");
            query.setRange(0, 10);

            execute = (List<Scores>) query.execute();
            cursor = JDOCursorHelper.getCursor(execute);
            // Tight loop for fetching all entities from datastore and accomodate
            // for lazy fetch.
            for (Scores obj : execute);
        } finally {
            mgr.close();
        }

        return CollectionResponse.<Scores>builder()
                .setItems(execute)
                .build();
    }

/*    *//**
     * This method gets the entity having primary key id. It uses HTTP GET method.
     *
     * @param id the primary key of the java bean.
     * @return The entity with primary key id.
     *//*
    @ApiMethod(name = "getscores")
    public Scores getscores(@Named("id") String id) {
        PersistenceManager mgr = getPersistenceManager();
        Scores scores  = null;
        try {
            scores = mgr.getObjectById(Scores.class, id);
        } finally {
            mgr.close();
        }
        return scores;
    }

    *//**
     * This inserts a new entity into App Engine datastore. If the entity already
     * exists in the datastore, an exception is thrown.
     * It uses HTTP POST method.
     *
     * @param score the entity to be inserted.
     * @return The inserted entity.
     *//*
    @ApiMethod(name = "insertscores",  clientIds = {Constants.WEB_CLIENT_ID}, httpMethod = ApiMethod.HttpMethod.PUT)
    public Scores insertscores(@Named("score")int score, User user) throws OAuthRequestException {
        if (user == null) {
            throw new OAuthRequestException("Vous n'êtes pas connecté, veuillez dégager d'ici ! ");
        }else{
            Scores scores = new Scores(user.getUserId(), user.getNickname(), score);
            PersistenceManager mgr = getPersistenceManager();
            try {
                if(containsscores(scores)) {
                    Scores scoresPersistence = mgr.getObjectById(Scores.class, scores.id);
                    if(scoresPersistence.getScores() == scores.getScores())
                        throw new EntityExistsException("Vous venez d'égaliser votre meilleur score !!");
                    else if(scoresPersistence.getScores() > scores.getScores())
                        throw new EntityExistsException("Vous n'avez pas battu votre meilleur score");
                    else
                        mgr.makePersistent(scores);
                }
                else
                    mgr.makePersistent(scores);
                mgr.makePersistent(scores);
            } finally {
                mgr.close();
            }
            return scores;
        }
    }

    *//**
     * This method is used for updating an existing entity. If the entity does not
     * exist in the datastore, an exception is thrown.
     * It uses HTTP PUT method.
     *
     * @param scores the entity to be updated.
     * @return The updated entity.
     *//*
    @ApiMethod(name = "updatescores")
    public Scores updatescores(Scores scores) {
        PersistenceManager mgr = getPersistenceManager();
        try {
            mgr.makePersistent(scores);
        } finally {
            mgr.close();
        }
        return scores;
    }

    *//**
     * This method removes the entity with primary key id.
     * It uses HTTP DELETE method.
     *
     * @param id the primary key of the entity to be deleted.
     *//*
    @ApiMethod(name = "removescores")
    public void removescores(@Named("id") String id) {
        PersistenceManager mgr = getPersistenceManager();
        try {
            Scores scores = mgr.getObjectById(Scores.class, id);
            mgr.deletePersistent(scores);
        } finally {
            mgr.close();
        }
    }*/

    private boolean containsscores(Scores scores) {
        PersistenceManager mgr = getPersistenceManager();
        boolean contains = true;
        try {
            mgr.getObjectById(Scores.class, scores.getId());
        } catch (javax.jdo.JDOObjectNotFoundException ex) {
            contains = false;
        } finally {
            mgr.close();
        }
        return contains;
    }

    private static PersistenceManager getPersistenceManager() {
        return PMF.get().getPersistenceManager();
    }
}
