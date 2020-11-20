package com.demo.subscription.response;
import java.math.BigDecimal;
import com.demo.subscription.enumeration.SubscriptionTypeEnum;
import java.util.List;


public class SubscriptionResponseObject {
    private BigDecimal amount;
    private SubscriptionTypeEnum type;
    private List<String> dateRange;


    private SubscriptionResponseObject(BigDecimal amount, SubscriptionTypeEnum type, List<String> dateRange) {
        this.amount = amount;
        this.type = type;
        this.dateRange = dateRange;
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

    public List<String> getDateRange() {
        return dateRange;
    }

    public void setDateRange(final List<String> dateRange) {
        this.dateRange = dateRange;
    }


    public static class Builder {
        private BigDecimal amount;
        private SubscriptionTypeEnum type;
        private List<String> dateRange;


        public Builder setAmount(final BigDecimal amount) {
            this.amount = amount;
            return this;
        }


        public Builder setType(final SubscriptionTypeEnum type) {
            this.type = type;
            return this;
        }


        public Builder setDateRange(final List<String> dateRange) {
            this.dateRange = dateRange;
            return this;
        }

        public SubscriptionResponseObject build(){
            return new SubscriptionResponseObject(this.amount, this.type, this.dateRange);
        }
    }
}
