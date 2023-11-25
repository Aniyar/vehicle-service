package nu.swe.vehicleservice.fuel.service;


import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.fuel.dto.request.FuelCreateRequest;
import nu.swe.vehicleservice.fuel.dto.request.FuelGetRequest;
import nu.swe.vehicleservice.fuel.dto.request.FuelUpdateRequest;
import nu.swe.vehicleservice.fuel.dto.response.FuelResponse;
import org.springframework.data.domain.Pageable;

public interface FuelService {
    FuelResponse findById(Long id);

    void create(FuelCreateRequest request);

    PageResponse<FuelResponse> findAll(FuelGetRequest request, Pageable pageable);

    void update(FuelUpdateRequest request);
}
