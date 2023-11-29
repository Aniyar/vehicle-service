package nu.swe.vehicleservice.route.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteReportResponse {
    private String fileLink;
    private String fileName;
}
