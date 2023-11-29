package nu.swe.vehicleservice.corefeatures.docgenerator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * Data structure representing transaction-related information for document generation.
 */
@Data
public class RouteDocData {

    private String generatedAt;

    @JsonProperty("route")
    private List<Route> routes;

    /**
     * Represents a single transaction with its details.
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Route {

        private int index;
        private Long id;
        private Long driverId;
        private String driverName;

        private Long staffId;
        private String staffName;

        private Long vehicleId;
        private String vehiclePlate;

        private String startPoint;
        private String startLat;
        private String startLon;

        private String endPoint;
        private String endLat;
        private String endLon;

        private String startTime;
        private String endTime;

        private String status;
    }
}