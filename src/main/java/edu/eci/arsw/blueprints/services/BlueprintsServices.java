package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import java.util.Set;

public interface BlueprintsServices {
    void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException;
    Blueprint getBlueprint(String author, String name) throws BlueprintNotFoundException;
    Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;
    Set<Blueprint> getAllBlueprints();
}
