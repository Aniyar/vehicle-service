package nu.swe.vehicleservice.vehicle.service;

import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.vehicle.dto.request.VehicleCreateRequest;
import nu.swe.vehicleservice.vehicle.dto.request.VehicleUpdateRequest;
import nu.swe.vehicleservice.vehicle.dto.response.VehicleResponse;
import org.springframework.data.domain.Pageable;

public interface VehicleService {
    void create(VehicleCreateRequest request);

    PageResponse<VehicleResponse> findAll(Pageable pageable);

    VehicleResponse findById(Integer id);

    void update(VehicleUpdateRequest request);
}
