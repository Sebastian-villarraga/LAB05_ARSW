package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filters.BlueprintsFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BlueprintsServicesImpl implements BlueprintsServices {

    @Autowired
    BlueprintsPersistence bpp;

    @Autowired
    @Qualifier("redundancyFilter")
    BlueprintsFilter filter;

    @Override
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }

    @Override
    public Blueprint getBlueprint(String author, String name) throws BlueprintNotFoundException {
        Blueprint bp = bpp.getBlueprint(author, name);
        return filter.filter(bp);
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> s = bpp.getBlueprintsByAuthor(author);
        // apply filter to each blueprint
        s.forEach(b -> {
            Blueprint fb = filter.filter(b);
            b.setPoints(fb.getPoints());
        });
        return s;
    }

    @Override
    public Set<Blueprint> getAllBlueprints() {
        Set<Blueprint> s = bpp.getAllBlueprints();
        s.forEach(b -> {
            Blueprint fb = filter.filter(b);
            b.setPoints(fb.getPoints());
        });
        return s;
    }
}
