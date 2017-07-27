package com.qiaoyu.app.dao.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
	
	public static final String sql = "select o.* from qiaoyulogistics.order o, qiaoyulogistics.client c where o.CLIENT_ID=c.id and o.ORDER_STATUS != 'C' and c.SHIPPING_COUNTRY_CODE=?";
	
	public static final String sql2 = "select o.*, od.*,c.*,p.* from qiaoyulogistics.order o, qiaoyulogistics.order_detail od, qiaoyulogistics.client c, qiaoyulogistics.product p"
			+ "where o.client_id=c.id and od.order_id=o.id and od.product_id=p.id and o.id=?";
	
	public static final String sql3 = "select o.* from qiaoyulogistics.order o, qiaoyulogistics.client c where o.CLIENT_ID=c.id and c.FIRST_NAME=? and c.LAST_NAME=?";
	
	public static final String sql4 = "select o.* from qiaoyulogistics.order o, qiaoyulogistics.client c where o.CLIENT_ID=c.id and o.SERVICE_PROVIDER=?";
	
	public static final String sql5 = "select o.* from qiaoyulogistics.order o, qiaoyulogistics.client c where o.CLIENT_ID=c.id and o.SERVICE_PROVIDER=? and c.FIRST_NAME=? and c.LAST_NAME=?";
	
	@Query(value=sql, nativeQuery=true)
	public List<Order> getOrderListByShippingCountryCode(String scc);
	
	@Query(value=sql2, nativeQuery=true)
	public List<Object> getOrderDetailById(int id);
	
	@Query(value=sql3, nativeQuery=true)
	public List<Order> getOrdersByName(String firstName, String lastName);
	
	@Query(value=sql4, nativeQuery=true)
	public List<Order> getOrdersByDeliveryProvider(String deliveryProvider);
	
	@Query(value=sql5, nativeQuery=true)
	public List<Order> getOrdersByDeliveryProviderAndName(String deliveryProvider, String firstName, String lastName);
	
}
