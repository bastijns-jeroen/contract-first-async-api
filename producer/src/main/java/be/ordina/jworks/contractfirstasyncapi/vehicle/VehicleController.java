package be.ordina.jworks.contractfirstasyncapi.vehicle;

import be.ordina.jworks.contractfirstasyncapi.Coordinates;
import be.ordina.jworks.contractfirstasyncapi.FuelType;
import be.ordina.jworks.contractfirstasyncapi.FuelUnit;
import be.ordina.jworks.contractfirstasyncapi.VehicleRefueled;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final StreamBridge streamBridge;

    @PostMapping(path = "refuel")
    public void refuel(@RequestBody RefuelVehicle refuelVehicle) {
        var vehicleRefueled = VehicleRefueled.newBuilder()
                .setLicensePlate(refuelVehicle.licensePlate())
                .setFuelType(FuelType.valueOf(refuelVehicle.fuelType()))
                .setFuelAmount(refuelVehicle.fuelAmount())
                .setFuelUnit(FuelUnit.valueOf(refuelVehicle.fuelUnit()))
//                .setCity(refuelVehicle.city())
                .setLocation(Coordinates.newBuilder()
                        .setLatitude(23.567)
                        .setLongitude(26.56)
                        .build())
                .build();
        streamBridge.send("vehicleRefueled-out-0", MessageBuilder.withPayload(vehicleRefueled)
                .setHeader(KafkaHeaders.MESSAGE_KEY, refuelVehicle.licensePlate())
                .build());
    }
}
