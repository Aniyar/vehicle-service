package nu.swe.vehicleservice.auction.mapper;

import nu.swe.vehicleservice.auction.dto.request.AuctionUpdateRequest;
import nu.swe.vehicleservice.auction.dto.response.AuctionResponse;
import nu.swe.vehicleservice.auction.entity.AuctionEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface AuctionMapper {

    AuctionMapper INSTANCE = Mappers.getMapper(AuctionMapper.class);

    AuctionResponse toResponse(AuctionEntity entity);

    void updateEntityFromRequest(AuctionUpdateRequest request, @MappingTarget AuctionEntity entity);

}
