package ir.ddd.domain.auction;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

//FixMe: we have some Invariants on winningBid and end time, so their setters must not be public, but hibernate needs.
@Data
@Document("auction")
public class Auction {

    @Id
    private String id;
    private long productId;
    private long sellerId;
    private double initialPrice;
    private LocalDateTime end;
    private LocalDateTime start;
    private AuctionState state;
    private WinningBid winningBid;

    //FixMe: this is only for hibernate not DDD
    public Auction() {
    }


    public Auction(long productId, long sellerId, double initialPrice, LocalDateTime end) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.initialPrice = initialPrice;
        this.start = LocalDateTime.now();
        setEnd(end);
        this.state = AuctionState.OPEN;
    }

    /*
     * Invariants
     * 1) end must be greater than (now + 15 minutes)
     * */
    public void setEnd(LocalDateTime end) {
        if (!LocalDateTime.now().plusMinutes(15).isBefore(end)) throw new RuntimeException("end invariant is violated");
        this.end = end;
    }

    /*
     * Invariants:
     * 1) Seller <> Bidder
     * 2) amount must be:
     *   2.1) on first bid ==> amount > auction.initialPrice
     *   2.2) on subsequent bids ==> amount > winningBid.amount
     * 3) auction must be open
     * 4)
     * */
    public void placeBid(long bidderId, double amount) {
        if (sellerId == bidderId) throw new RuntimeException("Bidder can not be the same as Seller");
        if (!AuctionState.OPEN.equals(state)) throw new RuntimeException("Auction is not open");
        if (!isValidAmount(amount))
            throw new RuntimeException(String.format("amount must be greater than %s", getCurrentAmount()));

        this.winningBid = new WinningBid(bidderId, amount);
    }

    private boolean isValidAmount(double amount) {
        return amount > getCurrentAmount();
    }

    private double getCurrentAmount() {
        if (winningBid != null)
            return winningBid.amount();

        return initialPrice;
    }
}
