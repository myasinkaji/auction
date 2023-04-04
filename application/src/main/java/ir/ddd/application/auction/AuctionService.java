package ir.ddd.application.auction;


import ir.ddd.application.auction.contract.AuctionRepository;
import ir.ddd.domain.auction.Auction;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;


    public List<Auction> getPage(int page, int pageSize) {
        return auctionRepository.getPage(page, pageSize);
    }
    public String openAuction(long productId, long sellerId, double initialPrice, LocalDateTime end) {
        final var auction = new Auction(productId, sellerId, initialPrice, end);
        return auctionRepository.insert(auction);
    }

    public void placeBid(long bidderId, String auctionId, double amount) {
        final var auction = auctionRepository.find(auctionId);
        auction.orElseThrow(() -> new IllegalStateException("Invalid auction")).placeBid(bidderId, amount);
    }
}


