package com.app.happybox.repository.reply;

import com.app.happybox.entity.reply.RecipeBoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeBoardReplyRepository extends JpaRepository<RecipeBoardReply, Long>, RecipeBoardReplyQueryDsl {
}