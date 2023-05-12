package com.app.happybox.repository.subscript;

import com.app.happybox.entity.subscript.FoodCalendar;
import com.app.happybox.entity.subscript.Subscription;

import java.time.LocalDate;
import java.util.List;

public interface FoodCalendarQueryDsl {
//    해당 일이 포함된 달의 복지관 음식 일정 조회 ex) 5월 12일 -> 5월의 복지관 음식 일정 조회
    public List<FoodCalendar> findAllWithFoodListBySubscriptionAndDateBetween_QueryDSL(LocalDate date, Long subId);

//    subscription PK 의 List 를 받아와 in 절로 조회
    public List<FoodCalendar> findAllInSubscriptionIds(List<Long> ids);
}
