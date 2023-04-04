package ir.ddd.infrastructure.auction.dto;

import ir.ddd.domain.auction.Auction;

import java.time.LocalDateTime;

public record AuctionDto(String id, long productId, long sellerId, double initialPrice, LocalDateTime end) {

    public AuctionDto(Auction auction) {
        this(auction.getId(), auction.getProductId(), auction.getSellerId(), auction.getInitialPrice(), auction.getEnd());
    }
}
