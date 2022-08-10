package be.ordina.jworks.contractfirstasyncapi.vehicle;

import be.ordina.jworks.contractfirstasyncapi.VehicleRefueled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class VehicleConsumerConfig {

    @Bean
    public Consumer<VehicleRefueled> vehicleConsumer() {
        return vehicle -> log.info(vehicle.toString());
    }
}
