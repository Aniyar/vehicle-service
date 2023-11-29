package nu.swe.vehicleservice.driver.service;

import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.driver.dto.request.DriverCreateRequest;
import nu.swe.vehicleservice.driver.dto.request.DriverUpdateRequest;
import nu.swe.vehicleservice.driver.dto.response.DriverResponse;

import org.springframework.data.domain.Pageable;

public interface DriverService
{
    DriverResponse findById(Long id);

    DriverResponse findCurrent();

    void create(DriverCreateRequest request);

    PageResponse<DriverResponse> findAll(Pageable pageable);

    void assignVehicle(Long id, Long vehicleId);

    void update(DriverUpdateRequest request);

    void delete(Long id);
}
