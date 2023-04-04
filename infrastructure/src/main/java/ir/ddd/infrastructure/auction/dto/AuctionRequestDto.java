package ir.ddd.infrastructure.auction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AuctionRequestDto(long productId, long sellerId, double initialPrice,
                                @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime end) {
}
