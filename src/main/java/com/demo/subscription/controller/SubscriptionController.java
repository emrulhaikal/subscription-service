package com.demo.subscription.controller;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.demo.subscription.dto.SubscriptionDTO;
import com.demo.subscription.util.DateUtil;
import org.springframework.http.ResponseEntity;
import com.demo.subscription.enumeration.SubscriptionTypeEnum;
import com.demo.subscription.response.SubscriptionResponseObject;



@RestController
public class SubscriptionController {
    @RequestMapping(value = {"/subscription/create"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public ResponseEntity<SubscriptionResponseObject> createSubscription(@Validated @RequestBody final SubscriptionDTO dto) throws IllegalArgumentException{
        final DateUtil dateUtil = new DateUtil();
        if (!dateUtil.isMonthValid(dto.getStartDate(), dto.getEndDate())){
            throw new IllegalArgumentException("Date is invalid");
        }
        List<String> dateRange;
        if(dto.getType() == SubscriptionTypeEnum.WEEKLY){
            //can use Apache library for simplicity
            if (!Arrays.stream(DayOfWeek.values()).anyMatch((t) -> t.name().equals(dto.getDayOfWeekMonth()))){
                throw new IllegalArgumentException("dayOfWeekMonth is invalid");
            }
            dateRange = new DateUtil().getWeeklySubscription(dto.getStartDate(), dto.getEndDate(), dto.getDayOfWeekMonth());

        }
        else if(dto.getType() == SubscriptionTypeEnum.MONTHLY){
            //we should do checking if dayOfWeekMonth is valid or not before passing but I leave it for now
            dateRange = new DateUtil().getMonthlySubscription(dto.getStartDate(), dto.getEndDate(),
                Integer.parseInt(dto.getDayOfWeekMonth()));
        }
        else{
            dateRange = new DateUtil().getDailySubscription(dto.getStartDate(), dto.getEndDate());
        }
        final SubscriptionResponseObject response = new SubscriptionResponseObject.Builder().setAmount(dto.getAmount()).setDateRange(dateRange)
            .setType(dto.getType()).build();

        return ResponseEntity.ok(response);
    }

}
