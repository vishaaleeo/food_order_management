package swiggy.domain;


import javax.persistence.*;

@Entity
@Table(name="payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="payment_identifier")
    private Integer payementIdentifier;

    @Column(name="payment_mode")
    private String paymentMode;

    @Column(name="payment_status")
    private String paymentStatus;

    @Column(name="payment_amount")
    private String paymentAmount;

    @Column(name="order_identifier")
    private Integer orderIdentifier;

    public Integer getPayementIdentifier() {
        return payementIdentifier;
    }

    public void setPayementIdentifier(Integer payementIdentifier) {
        this.payementIdentifier = payementIdentifier;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Integer getOrderIdentifier() {
        return orderIdentifier;
    }

    public void setOrderIdentifier(Integer orderIdentifier) {
        this.orderIdentifier = orderIdentifier;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payementIdentifier=" + payementIdentifier +
                ", paymentMode='" + paymentMode + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentAmount='" + paymentAmount + '\'' +
                ", orderIdentifier=" + orderIdentifier +
                '}';
    }
}
