package com.sellerapp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sellerapp.entity.Order;
import com.sellerapp.model.OrderDTO;
import com.sellerapp.model.ResponseWithObject;
import com.sellerapp.service.OrderService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
@Tag(name = "Order-API")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping(value = "/orderDetails", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addOrder(@RequestParam("list") List<MultipartFile> productImageData,
			@ModelAttribute OrderDTO orderDTO) {

		OrderDTO savedorderDTO = orderService.saveOrder(productImageData, orderDTO);
		if (savedorderDTO != null) {
			return new ResponseWithObject().generateResponse("Order details are saved", HttpStatus.OK, "200", orderDTO);
		} else {
			return new ResponseWithObject().generateResponse("provide", HttpStatus.INTERNAL_SERVER_ERROR, "500",
					orderDTO);
		}

	}

	@GetMapping(value = "/getAllOrder")
	public List<Order> getAllOrder() {
		return orderService.getAllOrder();
	}

	@GetMapping(value = "/totalSumOfOrders")
	public ResponseEntity<?> getTotalSumofOrders() {
		double totalSum = orderService.getTotalSum();
		return new ResponseWithObject().generateResponse("Total sum of orders", HttpStatus.OK, "200", totalSum);

	}

	/*
	 * @GetMapping(value="/alldayWiseOrders") public
	 * ResponseEntity<Map<LocalDate,List<Order>>> getAllOrderDayWise() {
	 * Map<LocalDate,List<Order>> dayWiseOrders=orderService.getAllOrderDayWise();
	 * return new ResponseWithObject().
	 * generateResponse("Give the details all the order daywise", HttpStatus.OK,
	 * "200", dayWiseOrders); }
	 */
	@GetMapping(value = "/allDayWiseOrders")
	public ResponseEntity<Object> getAllOrderDayWise() {
		Map<LocalDate, List<Order>> dayWiseOrders = orderService.getAllOrderDayWise();
		return new ResponseWithObject().generateResponse("Give the details all the order daywise", HttpStatus.OK, "200",
				dayWiseOrders);
	}

}
