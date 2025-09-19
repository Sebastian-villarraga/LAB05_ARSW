package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Keep every second point (subsampling).
 */
@Component("subsamplingFilter")
public class SubsamplingFilter implements BlueprintsFilter {

    @Override
    public Blueprint filter(Blueprint bp) {
        List<Point> pts = bp.getPoints();
        if (pts == null || pts.isEmpty()) return bp;
        List<Point> filtered = new ArrayList<>();
        for (int i = 0; i < pts.size(); i += 2) {
            filtered.add(pts.get(i));
        }
        return new Blueprint(bp.getAuthor(), bp.getName(), filtered);
    }
}
