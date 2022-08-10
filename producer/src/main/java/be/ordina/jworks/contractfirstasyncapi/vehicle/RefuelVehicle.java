package be.ordina.jworks.contractfirstasyncapi.vehicle;

public record RefuelVehicle(String licensePlate,
                            String fuelType,
                            Double fuelAmount,
                            String fuelUnit,
                            String city) {
}
