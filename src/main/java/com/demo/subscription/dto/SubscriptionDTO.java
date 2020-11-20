package com.demo.subscription.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.math.BigDecimal;
import com.demo.subscription.enumeration.SubscriptionTypeEnum;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;

@JsonDeserialize(builder = SubscriptionDTO.Builder.class)
public class SubscriptionDTO {
    @JsonProperty("amount")
    @NotNull(message = "amount may not be null")
    private BigDecimal amount;
    @JsonProperty("type")
    @NotNull(message = "type may not be null")
    private SubscriptionTypeEnum type;
    @JsonProperty("dayOfWeekMonth")
    private String dayOfWeekMonth;
    @JsonProperty("startDate")
    @NotNull(message = "startDate may not be null")
    private LocalDate startDate;
    @JsonProperty("endDate")
    @NotNull(message = "endDate may not be null")
    private LocalDate endDate;

    private SubscriptionDTO(final BigDecimal amount, final SubscriptionTypeEnum type, final String dayOfWeekMonth,
        final LocalDate startDate, final LocalDate endDate) {
        this.amount = amount;
        this.type = type;
        this.dayOfWeekMonth = dayOfWeekMonth;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public SubscriptionTypeEnum getType() {
        return type;
    }

    public void setType(final SubscriptionTypeEnum type) {
        this.type = type;
    }

    public String getDayOfWeekMonth() {
        return dayOfWeekMonth;
    }

    public void setDayOfWeekMonth(final String dayOfWeekMonth) {
        this.dayOfWeekMonth = dayOfWeekMonth;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    public static SubscriptionDTO.Builder builder() {
        return new SubscriptionDTO.Builder();
    }

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "set")
    public static class Builder {
        private BigDecimal amount;
        private SubscriptionTypeEnum type;
        private String dayOfWeekMonth;
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder setAmount(final BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder setType(final SubscriptionTypeEnum type) {
            this.type = type;
            return this;
        }

        public Builder setDayOfWeekMonth(final String dayOfWeekMonth) {
            this.dayOfWeekMonth = dayOfWeekMonth;
            return this;
        }

        public Builder setStartDate(final LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(final LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public SubscriptionDTO build(){
            return new SubscriptionDTO(this.amount, this.type, this.dayOfWeekMonth, this.startDate, this.endDate);
        }
    }
}
