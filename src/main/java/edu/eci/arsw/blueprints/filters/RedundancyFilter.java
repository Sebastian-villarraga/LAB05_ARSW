package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Remove consecutive duplicate points and duplicates preserving order.
 */
@Component("redundancyFilter")
public class RedundancyFilter implements BlueprintsFilter {

    @Override
    public Blueprint filter(Blueprint bp) {
        List<Point> pts = bp.getPoints();
        if (pts == null || pts.isEmpty()) return bp;
        LinkedHashSet<Point> unique = new LinkedHashSet<>(pts);
        List<Point> filtered = new ArrayList<>(unique);
        Blueprint nb = new Blueprint(bp.getAuthor(), bp.getName(), filtered);
        return nb;
    }
}
