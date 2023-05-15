package com.app.happybox.entity.board;

import com.app.happybox.domain.user.MemberDTO;
import com.app.happybox.entity.file.BoardFile;
import com.app.happybox.entity.file.BoardFileDTO;
import com.app.happybox.entity.reply.ReplyDTO;
import com.app.happybox.entity.subscript.Subscription;
import com.app.happybox.entity.subscript.SubscriptionDTO;
import com.app.happybox.entity.user.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @ToString
public class ReviewBoardDTO {
    private Long id;

    /*-- 회원 정보 --*/
    private MemberDTO memberDTO;

    /*-- 리뷰 복지관명 --*/
    private SubscriptionDTO subscriptionDTO;

    /*-- 게시글 정보 --*/
    private String reviewBoardTitle;
    private String reviewBoardContent;
    private LocalDate reviewBoardRegisterDate;

    /*-- 별점 --*/
    private Integer reviewRating;

    private Integer reviewLikeCount;

    private Integer reviewBoardReplyCount;

    /*-- 파일 리스트 --*/
    private List<BoardFileDTO> boardFiles;

    /*-- 댓글 리스트 --*/
    private List<ReplyDTO> replies;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public ReviewBoardDTO(){
        this.boardFiles = new ArrayList<>();
    }

    @Builder
    public ReviewBoardDTO(Long id, MemberDTO memberDTO, SubscriptionDTO subscriptionDTO, String reviewBoardTitle, String reviewBoardContent, LocalDate reviewBoardRegisterDate, Integer reviewRating, Integer reviewLikeCount, Integer reviewBoardReplyCount, List<BoardFileDTO> boardFiles, List<ReplyDTO> replies, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.memberDTO = memberDTO;
        this.subscriptionDTO = subscriptionDTO;
        this.reviewBoardTitle = reviewBoardTitle;
        this.reviewBoardContent = reviewBoardContent;
        this.reviewBoardRegisterDate = reviewBoardRegisterDate;
        this.reviewRating = reviewRating;
        this.reviewLikeCount = reviewLikeCount;
        this.reviewBoardReplyCount = reviewBoardReplyCount;
        this.boardFiles = boardFiles;
        this.replies = replies;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public void setMemberDTO(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
    }

    public void setSubscriptionDTO(SubscriptionDTO subscriptionDTO) {
        this.subscriptionDTO = subscriptionDTO;
    }
}
