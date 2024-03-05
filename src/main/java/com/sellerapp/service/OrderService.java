
package com.sellerapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sellerapp.entity.Order;
import com.sellerapp.model.OrderDTO;
import com.sellerapp.repository.OrderRepo;

@Service
public class OrderService {

	private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OrderService.class);

	@Autowired
	OrderRepo orderRepo;

	public OrderDTO saveOrder(List<MultipartFile> productImageData, OrderDTO orderDTO) {

		try {
			List<byte[]> byteList = new ArrayList<>();
			for (MultipartFile image : productImageData) {
				byteList.add(image.getBytes());

			}
			Order order = new Order(); // order.setId(orderDTO.getId().toString());
			order.setProductId(orderDTO.getProductId());
			order.setAddress(orderDTO.getAddress());
			order.setDistanceFromMandi(orderDTO.getDistanceFromMandi());
			order.setMandiName(orderDTO.getMandiName());
			order.setPrice(orderDTO.getPrice());
			order.setQuality(orderDTO.getQuality());
			order.setQuantity(orderDTO.getQuantity());
			order.setProductImages(byteList);
			orderRepo.save(order);
			return orderDTO;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("There is unable to take a order :{} ", e.getMessage());
			return orderDTO;
		}
	}

	public List<Order> getAllOrder() {
		return orderRepo.findAll();
	}

	public double getTotalSum() {
		List<Order> orders = orderRepo.findAll();
		double totalSum = 0.0;
		for (Order or : orders) {
			totalSum += or.getPrice();
		}
		return totalSum;
	}

	public Map<LocalDate, List<Order>> getAllOrderDayWise() {
		List<Order> allOrders = orderRepo.findAll();
		Map<LocalDate, List<Order>> dayWiseOrders = new HashMap<

		>();
		for (Order oe : allOrders) {
			LocalDate harvestDate = oe.getHarvestDate();
			if (!dayWiseOrders.containsKey(harvestDate)) {
				dayWiseOrders.put(harvestDate, new ArrayList<>());
			}
			dayWiseOrders.get(harvestDate).add(oe);
		}
		return dayWiseOrders;

	}

}
