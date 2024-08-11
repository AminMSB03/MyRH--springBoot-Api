package ma.myrh.web;


import lombok.extern.slf4j.Slf4j;
import ma.myrh.dtos.OfferDtoRequest;
import ma.myrh.dtos.OfferDtoResponseTwo;
import ma.myrh.dtos.UpdateOffer;
import ma.myrh.enums.OfferStatus;
import ma.myrh.services.offerService.OfferService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin("*")
public class OfferRestController {

    OfferService service;

    public OfferRestController(OfferService service) {
        this.service = service;
    }



    @PostMapping("/offers")
    public OfferDtoResponseTwo offers(@RequestBody OfferDtoRequest offerDtoRequest, Principal principal){
        return this.service.addOffer(offerDtoRequest,principal.getName());
    }

    @GetMapping("/accepted_offers")
    public List<OfferDtoResponseTwo> offers(){
        return this.service.getOfferByStatus(OfferStatus.ACCEPTED);
    }

    @GetMapping("/offers")
    public List<OfferDtoResponseTwo> offers(Principal principal){
        return this.service.getOwnOffers(principal.getName());
    }

    @GetMapping("/pending_offers")
    public List<OfferDtoResponseTwo> offersPending(){
        return this.service.getOfferByStatus(OfferStatus.PENDING);
    }

    @PutMapping("/offers")
    public Map<String, String> updateOffer(@RequestBody UpdateOffer offer){
        Boolean updateOffer = this.service.updateOffer(offer.getAction(), offer.getId());
        return Collections.singletonMap("message", "the offer has been updated successfully");
    }


}
