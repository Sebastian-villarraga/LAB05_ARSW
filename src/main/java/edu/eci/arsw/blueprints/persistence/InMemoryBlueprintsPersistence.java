package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

/**
 * Simple thread-safe in-memory persistence.
 */
@Repository
public class InMemoryBlueprintsPersistence implements BlueprintsPersistence {

    private final Map<String, Map<String, Blueprint>> data = new ConcurrentHashMap<>();

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        data.putIfAbsent(bp.getAuthor(), new ConcurrentHashMap<>());
        Map<String, Blueprint> authorMap = data.get(bp.getAuthor());
        // atomic check-put
        synchronized (authorMap) {
            if (authorMap.containsKey(bp.getName())) {
                throw new BlueprintPersistenceException("The given blueprint already exists: " + bp.getAuthor() + "/" + bp.getName());
            }
            authorMap.put(bp.getName(), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String name) throws BlueprintNotFoundException {
        Map<String, Blueprint> authorMap = data.get(author);
        if (authorMap == null || !authorMap.containsKey(name)) {
            throw new BlueprintNotFoundException("Blueprint not found: " + author + "/" + name);
        }
        return authorMap.get(name);
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Map<String, Blueprint> authorMap = data.get(author);
        if (authorMap == null) {
            throw new BlueprintNotFoundException("No blueprints found for author: " + author);
        }
        return new HashSet<>(authorMap.values());
    }

    @Override
    public Set<Blueprint> getAllBlueprints() {
        Set<Blueprint> s = new HashSet<>();
        for (Map<String, Blueprint> m : data.values()) {
            s.addAll(m.values());
        }
        return s;
    }
}
