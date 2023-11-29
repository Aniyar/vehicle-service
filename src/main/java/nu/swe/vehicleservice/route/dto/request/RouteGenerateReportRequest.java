package nu.swe.vehicleservice.route.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RouteGenerateReportRequest {
    private LocalDate fromDate;
    private LocalDate toDate;
    private Long driverId;
    private Long vehicleId;
}
