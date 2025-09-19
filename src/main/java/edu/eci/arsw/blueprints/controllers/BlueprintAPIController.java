package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/blueprints")
public class BlueprintAPIController {

    @Autowired
    BlueprintsServices bps;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Blueprint> getAllBlueprints() {
        return bps.getAllBlueprints();
    }

    @RequestMapping(path="/{author}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<Blueprint> getBlueprintsByAuthor(@PathVariable String author) throws ResourceNotFoundException {
        try {
            return bps.getBlueprintsByAuthor(author);
        } catch (BlueprintNotFoundException ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(path="/{author}/{bpname}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Blueprint getBlueprint(@PathVariable String author, @PathVariable("bpname") String bpname) throws ResourceNotFoundException {
        try {
            return bps.getBlueprint(author, bpname);
        } catch (BlueprintNotFoundException ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewBlueprint(@RequestBody Blueprint bp) throws ResourceNotFoundException {
        try {
            bps.addNewBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            throw new ResourceNotFoundException();
        }
    }
}
