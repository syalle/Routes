package domain;

import java.util.List;
import java.util.Objects;

public class Route {
    private int routeID;
    private String source;
    private String destination;
    private int departureTime;
    private int arrivalTime;
    public int nrSeats;
    public int price;


    public Route(int routeID, String source, String destination, int departureTime, int arrivalTime, int nrSeats, int price) {
        this.routeID = routeID;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.nrSeats = nrSeats;
        this.price = price;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getNrSeats() {
        return nrSeats;
    }

    public void setNrSeats(int nrSeats) {
        this.nrSeats = nrSeats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Route route = (Route) object;
        return getRouteID() == route.getRouteID() && getDepartureTime() == route.getDepartureTime() && getArrivalTime() == route.getArrivalTime() && getNrSeats() == route.getNrSeats() && getPrice() == route.getPrice() && Objects.equals(getSource(), route.getSource()) && Objects.equals(getDestination(), route.getDestination());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRouteID(), getSource(), getDestination(), getDepartureTime(), getArrivalTime(), getNrSeats(), getPrice());
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeID=" + routeID +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                " , " + nrSeats +
                ", price=" + price +
                '}';
    }
}
