package ir.ddd.infrastructure.auction.persistence;

import ir.ddd.domain.auction.Auction;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuctionRepository implements ir.ddd.application.auction.contract.AuctionRepository {

    private final AuctionMongoRepository auctionMongoRepository;

    @Override
    public List<Auction> getPage(int page, int pageSize) {
        return auctionMongoRepository.findAll(Pageable.unpaged()).toList();
    }

    @Override
    public String insert(Auction auction) {
        return auctionMongoRepository.insert(auction).getId();
    }

    @Override
    public Optional<Auction> find(String auctionId) {
        return auctionMongoRepository.findById(auctionId);
    }
}
