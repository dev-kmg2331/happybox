package com.app.happybox.domain;

import com.app.happybox.entity.customer.Inquiry;
import com.app.happybox.entity.file.InquiryAnswerFile;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class InquiryAnswerDTO {
    private final Long id;
    private final String inquiryAnswerContent;
    private final LocalDateTime createdDate;
    private final LocalDateTime updatedDate;
    private final List<InquiryAnswerFileDTO> inquiryAnswerFileDTOS;
}
