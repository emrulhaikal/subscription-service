package com.demo.subscription;

import static org.junit.Assert.assertEquals;
import com.demo.subscription.response.SubscriptionResponseObject;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.subscription.controller.SubscriptionController;
import com.demo.subscription.dto.SubscriptionDTO;
import com.demo.subscription.enumeration.SubscriptionTypeEnum;
import org.springframework.http.ResponseEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubscriptionServiceApplicationTests {

    @InjectMocks
    private SubscriptionController controller;


	@Test
    public void createSubscriptionSuccessWeekly() {
        final SubscriptionDTO dto = new SubscriptionDTO.Builder().setAmount(BigDecimal.valueOf(100)).setStartDate(LocalDate.parse("2018-06-02"))
            .setEndDate(LocalDate.parse("2018-06-27")).setType(SubscriptionTypeEnum.WEEKLY).setDayOfWeekMonth("TUESDAY").build();
        ResponseEntity<SubscriptionResponseObject> response =
            this.controller.createSubscription(dto);
        assertEquals(SubscriptionResponseObject.class, response.getBody().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(BigDecimal.valueOf(100), response.getBody().getAmount());
        assertEquals(SubscriptionTypeEnum.WEEKLY, response.getBody().getType());
        assertEquals(4, response.getBody().getDateRange().size());
    }

    @Test
    public void createSubscriptionSuccessDaily() {
        final SubscriptionDTO dto = new SubscriptionDTO.Builder().setAmount(BigDecimal.valueOf(200)).setStartDate(LocalDate.parse("2018-06-02"))
            .setEndDate(LocalDate.parse("2018-06-27")).setType(SubscriptionTypeEnum.DAILY).build();
        ResponseEntity<SubscriptionResponseObject> response =
            this.controller.createSubscription(dto);
        assertEquals(SubscriptionResponseObject.class, response.getBody().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(BigDecimal.valueOf(200), response.getBody().getAmount());
        assertEquals(SubscriptionTypeEnum.DAILY, response.getBody().getType());
        assertEquals(26, response.getBody().getDateRange().size());
    }

    @Test
    public void createSubscriptionSuccessMonthly() {
        final SubscriptionDTO dto = new SubscriptionDTO.Builder().setAmount(BigDecimal.valueOf(300)).setStartDate(LocalDate.parse("2018-06-02"))
            .setEndDate(LocalDate.parse("2018-09-27")).setType(SubscriptionTypeEnum.MONTHLY).setDayOfWeekMonth("25").build();
        ResponseEntity<SubscriptionResponseObject> response =
            this.controller.createSubscription(dto);
        assertEquals(SubscriptionResponseObject.class, response.getBody().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(BigDecimal.valueOf(300), response.getBody().getAmount());
        assertEquals(SubscriptionTypeEnum.MONTHLY, response.getBody().getType());
        assertEquals(3, response.getBody().getDateRange().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void startDateGreaterThanEndDate() {
        final SubscriptionDTO dto = new SubscriptionDTO.Builder().setAmount(BigDecimal.valueOf(300)).setStartDate(LocalDate.parse("2018-10-02"))
            .setEndDate(LocalDate.parse("2018-09-27")).setType(SubscriptionTypeEnum.MONTHLY).setDayOfWeekMonth("25").build();
        ResponseEntity<SubscriptionResponseObject> response =
            this.controller.createSubscription(dto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void greateThanThreeMonths() {
        final SubscriptionDTO dto = new SubscriptionDTO.Builder().setAmount(BigDecimal.valueOf(300)).setStartDate(LocalDate.parse("2018-07-02"))
            .setEndDate(LocalDate.parse("2018-11-27")).setType(SubscriptionTypeEnum.MONTHLY).setDayOfWeekMonth("25").build();
        ResponseEntity<SubscriptionResponseObject> response =
            this.controller.createSubscription(dto);
    }


}
