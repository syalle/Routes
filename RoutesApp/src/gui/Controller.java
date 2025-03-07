package gui;

import domain.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
        this.ticketCountField = ticketCountField;
    }

    private int totalPrice = 0;

    private ObservableList<Route> routesObservableList;
    private ObservableList<Route> availableRoutesObservableList;

    @FXML
    private ListView<Route> routeListView;

    @FXML
    private ComboBox<String> sourceCityComboBox;

    @FXML
    private ComboBox<String> destinationComboBox;

    @FXML
    private ListView<Route> availableRoutesListView;

    @FXML
    private Button buttonBook;

    @FXML
    private Label priceLabel;

    @FXML
    private TextField ticketCountField;

    @FXML
    void buttonBookHandler(ActionEvent event) {
        Route selectedRoute = availableRoutesListView.getSelectionModel().getSelectedItem();
        if (selectedRoute == null) {
            showAlert("Error", "No route selected", "Please select a route to book.", AlertType.ERROR);
            return;
        }

        // Check if there are available seats
        if (selectedRoute.getNrSeats() <= 0) {
            showAlert("Error", "No seats available", "This route is fully booked.", AlertType.ERROR);
            return;
        }
        int ticketCount = Integer.parseInt(ticketCountField.getText());
        // Update total price
        int price = selectedRoute.getPrice()*ticketCount;
        totalPrice += price;
        priceLabel.setText("Total Price: $" + totalPrice);

        int id = selectedRoute.getRouteID();
        int updatedSeats = selectedRoute.getNrSeats() - ticketCount;

        // Calculate the duration (in minutes or hours)
        int duration = selectedRoute.getArrivalTime() - selectedRoute.getDepartureTime();

        // Update the route with the new details
        service.modify(
                id,
                selectedRoute.getSource(),
                selectedRoute.getDestination(),
                selectedRoute.getDepartureTime(),
                selectedRoute.getArrivalTime(),
                updatedSeats,
                selectedRoute.getPrice()
        );

        // Optionally refresh the data or the UI here if needed
        initializeOrResetQuestions();
    }




    @FXML
    void sourceCityComboBoxHandler(ActionEvent event) {
        destinationComboBox.getItems().clear();

        String selectedSource = sourceCityComboBox.getSelectionModel().getSelectedItem();
        if (selectedSource == null) return;

        List<String> destinations = routesObservableList.stream()
                .filter(route -> route.getSource().equals(selectedSource))
                .map(Route::getDestination)
                .distinct()
                .collect(Collectors.toList());

        destinationComboBox.setItems(FXCollections.observableArrayList(destinations));
    }

    @FXML
    void onSelectDestination(ActionEvent event) {
        availableRoutesObservableList = FXCollections.observableArrayList();
        int duration;
        String selectedSource = sourceCityComboBox.getSelectionModel().getSelectedItem();
        String selectedDestination = destinationComboBox.getSelectionModel().getSelectedItem();

        if (selectedSource == null || selectedDestination == null) return;

        List<Route> filteredRoutes = routesObservableList.stream()
                .filter(route -> route.getSource().equals(selectedSource) && route.getDestination().equals(selectedDestination))
                .map(route -> new Route(route.getRouteID(), route.getSource(), route.getDestination(),
                        route.getDepartureTime(), route.getArrivalTime(),
                        route.getArrivalTime() - route.getDepartureTime(), route.getPrice()))
                .collect(Collectors.toList());

        availableRoutesObservableList.addAll(filteredRoutes);
        availableRoutesListView.setItems(availableRoutesObservableList);
    }



    void initializeOrResetQuestions(){
        routesObservableList= FXCollections.observableArrayList();

        List<Route> routes= new ArrayList<>();
        for(Route r: this.service.getAll())
            routes.add(r);

        routes = routes.stream()
                .sorted((r1,r2)-> r1.getSource().compareTo(r2.getSource()))
                .sorted((r1,r2)-> Integer.compare(r1.getDepartureTime(), r2.getDepartureTime()))
                .toList();
        routesObservableList.addAll(routes);
        routeListView.setItems(routesObservableList);
    }


    public void initialize() {
        initializeOrResetQuestions();

        List<String> sourceCities = routesObservableList.stream()
                .map(Route::getSource)
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        sourceCityComboBox.setItems(FXCollections.observableArrayList(sourceCities));
        priceLabel.setText("Total Price: $0.00");

        if (buttonBook == null) {
            System.out.println("buttonBook is null. Check FXML file for fx:id binding.");
        } else {
            System.out.println("buttonBook initialized successfully.");
        }
    }

    private void showAlert(String title, String header, String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
