/*
 * package com.sellerapp.entity;
 * 
 * import java.util.List;
 * 
 * import javax.persistence.Column; import javax.persistence.ElementCollection;
 * import javax.persistence.Entity; import javax.persistence.FetchType; import
 * javax.persistence.Table;
 * 
 * @Entity
 * 
 * @Table(name = "product_order_details") public class ProductOrderDetails {
 * 
 * @Column(name = "product_order_id") private Long id;
 * 
 * @ElementCollection(fetch = FetchType.EAGER) private List<Order> Orders;
 * 
 * @Column(name = "user_code") private String userCode;
 * 
 * public Long getId() { return id; }
 * 
 * public void setId(Long id) { this.id = id; }
 * 
 * public List<Order> getOrders() { return Orders; }
 * 
 * public void setOrders(List<Order> orders) { Orders = orders; }
 * 
 * public String getUserCode() { return userCode; }
 * 
 * public void setUserCode(String userCode) { this.userCode = userCode; }
 * 
 * }
 */
