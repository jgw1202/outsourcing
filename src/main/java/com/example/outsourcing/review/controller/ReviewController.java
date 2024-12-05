package com.example.outsourcing.review.controller;

import com.example.outsourcing.review.dto.ReviewRequestDto;
import com.example.outsourcing.review.dto.ReviewResponseDto;
import com.example.outsourcing.review.service.ReviewService;
import com.example.outsourcing.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ReviewResponseDto> createReview(
            @RequestBody ReviewRequestDto requestDto,
            HttpServletRequest servletRequest
    ){
        HttpSession session = servletRequest.getSession();
        Long userId = (Long) session.getAttribute("id");
        ReviewResponseDto responseDto = reviewService.createReview(
                userId,
                requestDto.getStar(),
                requestDto.getContents(),
                requestDto.getUserId(),
                requestDto.getMenuId(),
                requestDto.getState(),
                requestDto.getStoreId(),
                requestDto.getMenuName()
        );
        return new ResponseEntity(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<List<ReviewResponseDto>> reviewsByStore(
            @PathVariable Long storeId,
            @RequestParam(required = false, value="star") List<Integer> star
    ){
        List<ReviewResponseDto> responseDtos = new ArrayList<>();
        if(star != null){
            log.info("** star : {}",star);
            log.info("requestParam : {}", star.get(0));
            log.info("requestParam : {}", star.get(1));

            responseDtos = reviewService.reviewsByStar(storeId,star);

        } else {
            responseDtos = reviewService.reviewsByStore(storeId);
        }

        return new ResponseEntity(responseDtos,HttpStatus.OK);
    }

//    @GetMapping("/{storeId}")
//    public ResponseEntity<List<ReviewResponseDto>> reviewsByStar(@PathVariable Long storeId,@RequestParam List<Integer>starValue){
//
//        log.info("starValue[0] : {}  ", starValue.get(0));
//
////        List<ReviewResponseDto> responseDtos = reviewService.reviewsByStar(storeId,);
//
//        return new ResponseEntity<>( HttpStatus.OK);
//    }


}
