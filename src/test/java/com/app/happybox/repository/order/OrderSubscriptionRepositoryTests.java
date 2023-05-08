package com.app.happybox.repository.order;

import com.app.happybox.entity.order.OrderSubscription;
import com.app.happybox.entity.subscript.Subscription;
import com.app.happybox.entity.user.Address;
import com.app.happybox.entity.user.Member;
import com.app.happybox.repository.subscript.SubscriptionRepository;
import com.app.happybox.repository.user.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional @Rollback(false)
@Slf4j
class OrderSubscriptionRepositoryTests {
    @Autowired
    private OrderSubscriptionRepository orderSubscriptionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Test
    public void saveTest(){
        // given
        Optional<Member> member = memberRepository.findById(1L);
        Optional<Subscription> subscription = subscriptionRepository.findById(3L);
        Address address = new Address("11111", "서울시 역삼동", "코리아IT");

        // when
        if (!member.isPresent() || !subscription.isPresent()) fail("member 혹은 subscription 없음.");

        OrderSubscription orderSubscription = new OrderSubscription(
                address,
                subscription.get(),
                member.get()
        );

        orderSubscriptionRepository.save(orderSubscription);

        // then
    }
}