package swiggy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import swiggy.domain.Payment;
import swiggy.repository.PaymentRepository;

@Service
public class PaymentService {


    @Autowired
    PaymentRepository paymentRepository;


    public String createPayment(Payment payment) {

        return paymentRepository.save(payment).toString();
    }

    public String updatePayment(Payment payment) {

        Payment toUpdatePayment;

        toUpdatePayment=paymentRepository.findOne(payment.getPayementIdentifier());

        if(toUpdatePayment==null) {

            return "invalid";

        }
        else {


            if (payment.getPaymentMode() != null) {

                toUpdatePayment.setPaymentMode(payment.getPaymentMode());

            }
            if(payment.getPaymentStatus()!=null) {

                toUpdatePayment.setPaymentStatus(payment.getPaymentStatus());
            }

            return paymentRepository.save(toUpdatePayment).toString();
        }
    }



}

