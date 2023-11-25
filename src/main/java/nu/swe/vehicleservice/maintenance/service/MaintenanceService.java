package nu.swe.vehicleservice.maintenance.service;


import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.maintenance.dto.request.MainenanceCreateRequest;
import nu.swe.vehicleservice.maintenance.dto.request.MaintenanceGetRequest;
import nu.swe.vehicleservice.maintenance.dto.request.MaintenanceUpdateRequest;
import nu.swe.vehicleservice.maintenance.dto.response.MaintenanceResponse;
import org.springframework.data.domain.Pageable;

public interface MaintenanceService {
    MaintenanceResponse findById(Long id);

    void create(MainenanceCreateRequest request);

    PageResponse<MaintenanceResponse> findAll(MaintenanceGetRequest request, Pageable pageable);

    void update(MaintenanceUpdateRequest request);
}
