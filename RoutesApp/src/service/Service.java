package service;

import domain.Route;
import repository.Repository;

public class Service {
    private Repository repository;

    public Service(Repository repo) {
        this.repository = repo;
    }

    public void modify(int routeID, String source, String destination, int departureTime, int arrivalTime, int nrSeats, int price) {
        Route route = new Route(routeID, source, destination, departureTime, arrivalTime, nrSeats, price);
        repository.modify(routeID,route);
    }
    public Route findById(Integer key){
        return repository.findById(key);
    }

    public String toStringRepo(){
        return repository.toString();
    }
    public Iterable<Route> getAll(){
        return repository.getAll();
    }

}
