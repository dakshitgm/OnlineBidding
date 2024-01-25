package com.dakshit.OnlineBidding.Controller;

import com.dakshit.OnlineBidding.Entity.Bid;
import com.dakshit.OnlineBidding.Exception.BidNotFoundException;
import com.dakshit.OnlineBidding.Exception.UnauthorisedAccessException;
import com.dakshit.OnlineBidding.Payload.Request.BidRequest;
import com.dakshit.OnlineBidding.Payload.Response.BidResponse;
import com.dakshit.OnlineBidding.Services.BidServices;
import com.dakshit.OnlineBidding.Services.ProductService;
import com.dakshit.OnlineBidding.Utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bid")
public class BidController {
    @Autowired
    private BidServices bidServices;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserUtils userUtils;

    @PostMapping("")
    public ResponseEntity<?> addBid(@RequestBody BidRequest bidRequest) throws BidNotFoundException, UnauthorisedAccessException {
        Bid bid = new Bid();
        bid.setProductId(bidRequest.getProductId());
        bid.setProduct(productService.getProduct(bid.getProductId()));
        bid.setBidderId(userUtils.getLoggedInUserId());
        bid.setPrice(bidRequest.getPrice());

        bidServices.addBid(bid, userUtils.getLoggedInUserId());

        return ResponseEntity.ok("Bid added successfully");
    }

    @GetMapping("/")
    public List<BidResponse> getBidList(@RequestParam long productId){
        List<Bid> bidList = bidServices.getBidListByProduct(productId);

        List<BidResponse> bidResponseList = bidList
                .stream()
                .map(BidResponse::new)
                .collect(Collectors.toList());
        return bidResponseList;
    }

    @GetMapping("/mybids")
    public List<BidResponse> getBidList(){
        List<Bid> bidList = bidServices.getBidListByUser(userUtils.getLoggedInUserId());

        List<BidResponse> bidResponseList = bidList
                .stream()
                .map(BidResponse::new)
                .collect(Collectors.toList());

        return bidResponseList;
    }

    @GetMapping("/{bidId}")
    public BidResponse getBid(@PathVariable("bidId") long bidId) throws BidNotFoundException, UnauthorisedAccessException {
        Bid bid = bidServices.getBid(bidId, userUtils.getLoggedInUserId());
        return new BidResponse(bid);
    }


    @PutMapping("/{bidId}")
    public ResponseEntity<?> updateBid(@PathVariable Long bidId, @RequestBody BidRequest bidRequest) throws BidNotFoundException, UnauthorisedAccessException {

        Bid bid = new Bid();
        bid.setId(bidId);
        bid.setProductId(bidRequest.getProductId());
        bid.setBidderId(userUtils.getLoggedInUserId());
        bid.setPrice(bidRequest.getPrice());

        bidServices.updateBid(bidId, bid);

        return ResponseEntity.ok("bid updated");
    }

    @DeleteMapping("/{bidId}")
    public ResponseEntity<?> deleteBid(@PathVariable("bidId") long bidId) throws BidNotFoundException, UnauthorisedAccessException{
        bidServices.deleteBid(bidId, userUtils.getLoggedInUserId());
        return ResponseEntity.ok("Bid deleted successfully");
    }



}
