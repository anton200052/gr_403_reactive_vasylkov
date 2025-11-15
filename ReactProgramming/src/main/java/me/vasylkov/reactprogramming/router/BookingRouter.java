package me.vasylkov.reactprogramming.router;

import me.vasylkov.reactprogramming.handler.BookingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class BookingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(BookingHandler bookingHandler) {

        return RouterFunctions
                // http://localhost:8080/room -> поверне 1 кімнату (JSON)
                .route(RequestPredicates.GET("/room").and(accept(MediaType.APPLICATION_JSON)), bookingHandler::getRoomInfo)

                // http://localhost:8080/ -> поверне текст
                .andRoute(RequestPredicates.GET("/"), bookingHandler::home)

                // http://localhost:8080/requests -> поверне список заявок (JSON)
                .andRoute(RequestPredicates.GET("/requests"), bookingHandler::getPendingRequests);
    }
}
