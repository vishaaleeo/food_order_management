package swiggy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import swiggy.domain.Order;
import swiggy.repository.OrderRepository;
import swiggy.services.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {


    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody Order order) {

        Order createadOrder=orderService.createOrder(order);
        if(createadOrder==null) {
            return new ResponseEntity("different on cart order exists", HttpStatus.OK);
        }
        else {
            return new ResponseEntity(createadOrder,HttpStatus.OK);
        }

    }

    @PostMapping("/update")
    public ResponseEntity updateOrder(@RequestBody Order order) {

        return new ResponseEntity(orderService.updateOrder(order),HttpStatus.OK);
    }

    @GetMapping(value="/display", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity displayOrder() {


        return new ResponseEntity(orderService.onCartDisplay(),HttpStatus.OK);
    }
}
