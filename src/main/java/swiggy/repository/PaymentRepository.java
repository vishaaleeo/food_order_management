package swiggy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swiggy.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {



}
