package ir.ddd.infrastructure.auction.persistence;

import ir.ddd.domain.auction.Auction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionMongoRepository extends MongoRepository<Auction, String> {
}

