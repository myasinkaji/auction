package ir.ddd.infrastructure.auction.rest;

import ir.ddd.application.auction.AuctionService;
import ir.ddd.infrastructure.auction.dto.AuctionDto;
import ir.ddd.infrastructure.auction.dto.AuctionRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auction")
@RequiredArgsConstructor
public class AuctionController {
    private final AuctionService auctionService;

    @GetMapping
    public List<AuctionDto> getPage(int page, int pageSize) {
        return auctionService.getPage(page, pageSize).stream().map(AuctionDto::new).toList();
    }


    @PostMapping(consumes = "application/json")
    public String openAuction(@RequestBody AuctionRequestDto dto) {
        return auctionService.openAuction(dto.productId(), dto.sellerId(), dto.initialPrice(), dto.end());
    }

    @PutMapping
    public void placeBid(long bidderId, String auctionId, double amount) {
        auctionService.placeBid(bidderId, auctionId, amount);
    }
}

