package com.app.happybox.entity.customer;

import com.app.happybox.audity.Period;
import com.app.happybox.entity.file.InquiryAnswerFile;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString
@Table(name = "TBL_INQUIRY_ANSWER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InquiryAnswer extends Period {
    @Id @GeneratedValue @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String inquiryAnswerContent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INQUIRY_ID")
    private Inquiry inquiry;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE}, mappedBy = "inquiryAnswer")
    private List<InquiryAnswerFile> inquiryAnswerFiles = new ArrayList<>();
}
