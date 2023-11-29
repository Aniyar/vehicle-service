package nu.swe.vehicleservice.route.service;


import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.route.dto.request.RouteCreateRequest;
import nu.swe.vehicleservice.route.dto.request.RouteGetRequest;
import nu.swe.vehicleservice.route.dto.request.RouteUpdateRequest;
import nu.swe.vehicleservice.route.dto.response.RouteResponse;
import nu.swe.vehicleservice.route.enums.RouteStatus;
import org.springframework.data.domain.Pageable;

public interface RouteService {
    RouteResponse findById(Long id);

    void create(RouteCreateRequest request);

    PageResponse<RouteResponse> findAll(RouteGetRequest request, Pageable pageable);

    void update(RouteUpdateRequest request);

    void changeStatus(Long id, RouteStatus status);

    void assignDriver(Long id, Long driverId);

    void delete(Long id);
}
