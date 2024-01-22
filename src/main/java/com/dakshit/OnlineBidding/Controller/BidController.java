package com.dakshit.OnlineBidding.Controller;

import com.dakshit.OnlineBidding.Entity.Bid;
import com.dakshit.OnlineBidding.Entity.Product;
import com.dakshit.OnlineBidding.Payload.Request.BidRequest;
import com.dakshit.OnlineBidding.Payload.Response.BidResponse;
import com.dakshit.OnlineBidding.Services.BidServices;
import com.dakshit.OnlineBidding.Services.ProductService;
import com.dakshit.OnlineBidding.Utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public ResponseEntity<?> addBid(@RequestBody BidRequest bidRequest) throws Exception {
        Bid bid = new Bid();
        bid.setProductId(bidRequest.getProductId());
        bid.setBidderId(userUtils.getLoggedInUser().getId());
        bid.setPrice(bidRequest.getPrice());

        bidServices.addBid(bid);

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
        List<Bid> bidList = bidServices.getBidListByUser(userUtils.getLoggedInUser().getId());

        List<BidResponse> bidResponseList = bidList
                .stream()
                .map(BidResponse::new)
                .collect(Collectors.toList());

        return bidResponseList;
    }

    @GetMapping("/{bidId}")
    public BidResponse getBid(@PathVariable("bidId") long bidId) throws Exception {
        Bid bid = bidServices.getBid(bidId);
        return new BidResponse(bid);
    }


    @PutMapping("/")
    public ResponseEntity<?> updateBid(@PathVariable Long bidId, @RequestBody BidRequest bidRequest) throws Exception {

        Bid bid = new Bid();
        bid.setId(bidId);
        bid.setProductId(bidRequest.getProductId());
        bid.setBidderId(userUtils.getLoggedInUser().getId());
        bid.setPrice(bidRequest.getPrice());

        bidServices.updateBid(bidId, bid);

        return ResponseEntity.ok("bid updated");
    }

    @DeleteMapping("/{bidId}")
    public ResponseEntity<?> deleteBid(@PathVariable("bidId") long bidId) throws Exception{
        bidServices.deleteBid(bidId);
        return ResponseEntity.ok("Bid deleted successfully");
    }



}
