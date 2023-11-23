package nu.swe.vehicleservice.driver.service;

import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.driver.dto.request.DriverCreateRequest;
import nu.swe.vehicleservice.driver.dto.request.DriverUpdateRequest;
import nu.swe.vehicleservice.driver.dto.response.DriverResponse;

import org.springframework.data.domain.Pageable;

public interface DriverService
{
    DriverResponse findById(Integer id);

    DriverResponse findCurrent();

    void create(DriverCreateRequest request);

    PageResponse<DriverResponse> findAll(Pageable pageable);

    void assignVehicle(Integer id, Integer vehicleId);

    void update(DriverUpdateRequest request);
}
