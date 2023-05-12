package com.app.happybox.controller.board;

import com.app.happybox.entity.board.*;
import com.app.happybox.repository.board.DonationBoardRepository;
import com.app.happybox.service.board.DonationBoardService;
import com.app.happybox.service.board.RecipeBoardService;
import com.app.happybox.service.board.ReviewBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user-board/*")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    @Qualifier
    private final ReviewBoardService reviewBoardService;
    @Qualifier
    private final RecipeBoardService recipeBoardService;
    @Qualifier
    private final DonationBoardService donationBoardService;

//    @GetMapping("review-board-list")
//    public String goReviewList(@PageableDefault(page = 1,size = 5) Pageable pageable, Model model) {
//        model.addAttribute("reviewList", reviewBoardService.getReviewBoards(PageRequest.of(pageable.getPageNumber() - 1,
//                pageable.getPageSize())));
//        return "user-board/review-board-list";
//    }

    @GetMapping("review-board-list")
    public String goReviewList(){
        return "user-board/review-board-list";
    }

    @GetMapping("review-board-insert")
    public String goReviewWrite() {
        return "user-board/review-board-insert";
    }

    @PostMapping("review-board-insert")
    public void write(ReviewBoard reviewBoard) {
        reviewBoardService.write(reviewBoard);
    }

    //    리뷰 게시판 리스트(인기순)
    @GetMapping("review-board-list/popular")
    @ResponseBody
    public Slice<ReviewBoardDTO> goPopularList(@PageableDefault(page=1, size=5) Pageable pageable) {
                return reviewBoardService
                        .getPopularReviewBoards(PageRequest.of(pageable.getPageNumber() - 1,
                                pageable.getPageSize()));
    }

    //    레시피 게시판 리스트 (최신순)
    @GetMapping("recipe-board-list")
    public Slice<RecipeBoardDTO> getRecipeBoardList(int page, int size) {
        return recipeBoardService.getRecipeBoards(PageRequest.of(page, size));
    }

    //    기부 게시판 리스트
    @GetMapping("donate-list")
    public Page<DonationBoardDTO> getDonateBoardList(int page, int size) {
        return donationBoardService.getList(PageRequest.of(page, size));
    }

}
