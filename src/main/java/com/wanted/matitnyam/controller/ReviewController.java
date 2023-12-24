package com.wanted.matitnyam.controller;

import com.wanted.matitnyam.dto.Principal;
import com.wanted.matitnyam.dto.PrincipalDto;
import com.wanted.matitnyam.dto.ReviewRequest;
import com.wanted.matitnyam.dto.ReviewResponse;
import com.wanted.matitnyam.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("")
    ResponseEntity<ReviewResponse> create(@ModelAttribute ReviewRequest reviewRequest,
                                          @Principal PrincipalDto principal) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewService.create(reviewRequest, principal.name()));
    }

    @PutMapping("/{id}")
    ResponseEntity<ReviewResponse> update(@ModelAttribute ReviewRequest reviewRequest,
                                          @PathVariable(value = "id") Long reviewId,
                                          @Principal PrincipalDto principal) {
        ReviewRequest reviewRequestWithReviewId = ReviewRequest.createReviewRequestByReviewIdAndRequest(reviewId,
                reviewRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reviewService.update(reviewRequestWithReviewId, principal.name()));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable(value = "id") Long id, @Principal PrincipalDto principal) {
        reviewService.delete(id, principal.name());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
