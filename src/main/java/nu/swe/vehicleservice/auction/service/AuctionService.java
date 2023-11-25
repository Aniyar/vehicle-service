package nu.swe.vehicleservice.auction.service;


import nu.swe.vehicleservice.auction.dto.request.AuctionBidRequest;
import nu.swe.vehicleservice.auction.enums.AuctionStatus;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.auction.dto.request.AuctionCreateRequest;
import nu.swe.vehicleservice.auction.dto.request.AuctionGetRequest;
import nu.swe.vehicleservice.auction.dto.request.AuctionUpdateRequest;
import nu.swe.vehicleservice.auction.dto.response.AuctionResponse;
import org.springframework.data.domain.Pageable;

public interface AuctionService {
    AuctionResponse findById(Long id);

    void create(AuctionCreateRequest request);

    PageResponse<AuctionResponse> findAll(AuctionGetRequest request, Pageable pageable);

    void update(AuctionUpdateRequest request);

    void bid(AuctionBidRequest request);

    void changeStatus(Long id, AuctionStatus status);
}
