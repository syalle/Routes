package repository;

import domain.Route;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Repository {
    protected HashMap<Integer, Route> routes = new HashMap<>();


    public void add(Integer key, Route rout) {
        // add objects with a certain key only if it doesnt exist already
        if(!routes.containsKey(key))
            routes.put(key,rout);

    }
    public void modify(Integer key, Route rout) {
        if(routes.containsKey(key))
            routes.put(key,rout);}

    public Route findById(Integer key) {
        if (routes.containsKey(key))
            return routes.get(key);
        return null;
    }

    public String toStringRepo() {
        String repositoryStringRepresentation ="";
        for(Route route: routes.values())
            repositoryStringRepresentation = repositoryStringRepresentation + route.toString() + "\n";
        return repositoryStringRepresentation;
    }

    public Iterable<Route> getAll() {
        return  routes.values();
    }
//    public List<Route
//   > getAllUnanswered() {
//        List<Route
//       > qUnanswered = new ArrayList<>();
//        for(Route
//       route
//     : route
//     s.values()){
//            if(route
//          .getUserAnswer().equals("\"\"")){
//                qUnanswered.add(route
//              );}}
//
//        List<Route
//       > qAnsweredSorted = qUnanswered.stream()
//                .sorted((q1,q2)->Integer.compare(q1.getScore(),q2.getScore()))
//                .toList();
//        return qAnsweredSorted;
//
//    }
}
