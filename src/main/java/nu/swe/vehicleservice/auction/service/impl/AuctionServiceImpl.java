package nu.swe.vehicleservice.auction.service.impl;

import lombok.RequiredArgsConstructor;
import nu.swe.vehicleservice.auction.dto.request.AuctionBidRequest;
import nu.swe.vehicleservice.auction.enums.AuctionStatus;
import nu.swe.vehicleservice.core.dto.PageResponse;
import nu.swe.vehicleservice.auction.dto.request.AuctionCreateRequest;
import nu.swe.vehicleservice.auction.dto.request.AuctionGetRequest;
import nu.swe.vehicleservice.auction.dto.request.AuctionUpdateRequest;
import nu.swe.vehicleservice.auction.dto.response.AuctionResponse;
import nu.swe.vehicleservice.auction.entity.AuctionEntity;
import nu.swe.vehicleservice.auction.exception.AuctionException;
import nu.swe.vehicleservice.auction.mapper.AuctionMapper;
import nu.swe.vehicleservice.auction.repository.AuctionRepository;
import nu.swe.vehicleservice.auction.service.AuctionService;
import nu.swe.vehicleservice.security.CurrentUser;
import nu.swe.vehicleservice.user.exception.UserException;
import nu.swe.vehicleservice.user.repository.UserRepository;
import nu.swe.vehicleservice.vehicle.exception.VehicleException;
import nu.swe.vehicleservice.vehicle.repository.VehicleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static nu.swe.vehicleservice.auction.enums.AuctionErrorCode.AUCTION_INVALID_BID;
import static nu.swe.vehicleservice.core.specification.CommonSpecification.*;
import static nu.swe.vehicleservice.auction.enums.AuctionErrorCode.AUCTION_NOT_FOUND;
import static nu.swe.vehicleservice.user.enums.UserErrorCode.USER_NOT_FOUND;
import static nu.swe.vehicleservice.vehicle.enums.VehicleErrorCode.VEHICLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final AuctionRepository auctionRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public AuctionResponse findById(Long id) {
        AuctionEntity auction = auctionRepository.findById(id).orElseThrow(() -> new AuctionException(AUCTION_NOT_FOUND));
        return AuctionMapper.INSTANCE.toResponse(auction);
    }

    @Override
    public void create(AuctionCreateRequest request) {
        var vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new VehicleException((VEHICLE_NOT_FOUND)));

        AuctionEntity auction = AuctionEntity.builder()
                .vehicle(vehicle)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status(AuctionStatus.BIDDING)
                .currentBid(request.getCurrentBid())
                .build();
        auctionRepository.save(auction);
    }

    @Override
    public PageResponse<AuctionResponse> findAll(AuctionGetRequest request, Pageable pageable) {
        Specification<AuctionEntity> where = alwaysTrue();
        if (request.getStatus() != null) {
            where = where.and(attributeEquals("status", request.getStatus()));
        }
        if (request.getVehicleId() != null) {
            where = where.and(attributeEquals("vehicle", "id", request.getVehicleId()));
        }
        Page<AuctionEntity> page = auctionRepository.findAll(where, pageable);
        return PageResponse.fromPage(page.map(AuctionMapper.INSTANCE::toResponse));
    }

    @Override
    public void update(AuctionUpdateRequest request) {
        AuctionEntity auction = auctionRepository.findById(request.getId())
                .orElseThrow(() -> new AuctionException(AUCTION_NOT_FOUND));

        AuctionMapper.INSTANCE.updateEntityFromRequest(request, auction);
        auctionRepository.save(auction);
    }

    @Override
    public void bid(AuctionBidRequest request) {
        AuctionEntity auction = auctionRepository.findById(request.getId())
                .orElseThrow(() -> new AuctionException(AUCTION_NOT_FOUND));
        if (request.getBid().compareTo(auction.getCurrentBid()) < 0 ) {
            throw new AuctionException(AUCTION_INVALID_BID);
        }

        auction.setCurrentBid(request.getBid());

        var currentBidHolder = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new UserException(USER_NOT_FOUND));
        auction.setCurrentBidHolder(currentBidHolder);

        auctionRepository.save(auction);
    }

    @Override
    public void changeStatus(Long id, AuctionStatus status) {
        AuctionEntity auction = auctionRepository.findById(id)
                .orElseThrow(() -> new AuctionException(AUCTION_NOT_FOUND));

        auction.setStatus(status);
        auctionRepository.save(auction);

    }
}
