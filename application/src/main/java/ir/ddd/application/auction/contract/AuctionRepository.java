package ir.ddd.application.auction.contract;

import ir.ddd.domain.auction.Auction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AuctionRepository {

    List<Auction> getPage(int page, int pageSize);

    String insert(Auction auction);

    Optional<Auction> find(String auctionId);
}
