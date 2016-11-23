package fr.nantes.web.quizz.APIBackend;

/**
 * Created by Sébastien on 23/11/2016.
 */
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@Api(name = "scoresEndpoint", namespace = @ApiNamespace(ownerDomain = "mycompany.com", ownerName = "mycompany.com", packagePath="services"))
public class scoresEndpoint {
    /**
     * This inserts a new entity into App Engine datastore. If the entity already
     * exists in the datastore, an exception is thrown.
     * It uses HTTP POST method.
     *
     * @param scores the entity to be inserted.
     * @return The inserted entity.
     */
    @ApiMethod(name = "insertScores")
    public Scores insertScores(Scores scores) {
        PersistenceManager mgr = getPersistenceManager();
        try {
            if(containsScores(scores)) {
                throw new EntityExistsException("Object already exists");
            }
            mgr.makePersistent(scores);
        } finally {
            mgr.close();
        }
        return scores;
    }

    private boolean containsScores(Scores scores) {
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
