package com.qiaoyu.app.dao.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Integer> {
	
	public static final String sql = "select c.* from qiaoyulogistics.order o, qiaoyulogistics.client c where o.CLIENT_ID=c.id and o.id=?";
	
	public static final String sql1 = "SELECT SHIPPING_COUNTRY, count(0)  FROM qiaoyulogistics.client where id in "
			+ "(SELECT o.client_id FROM qiaoyulogistics.`order` o, qiaoyulogistics.client c where o.client_id=c.id and o.DELIVERY_DATE >= ? and o.DELIVERY_DATE <= ? and o.service_provider=? and o.order_status='C') "
			+ " group by SHIPPING_COUNTRY";
	
	public static final String sql2 = "select o.id,o.capacity,o.netweight,od.quantity,p.item_no from qiaoyulogistics.order o, qiaoyulogistics.order_detail od, qiaoyulogistics.product p "
	+ "where od.order_id=o.id and od.product_id=p.id and o.id "
	+ "in(select o1.id from qiaoyulogistics.order o1, qiaoyulogistics.client c1 where o1.client_id=c1.id and o1.DELIVERY_DATE >= ? and o1.DELIVERY_DATE <= ? and o.service_provider=? and c1.shipping_country=? and o.order_status='C')";
	
	@Query(value=sql, nativeQuery=true)
	public Client getClientByOrderId(int orderId);
	
	@Query(value=sql1, nativeQuery=true)
	public List<Object[]> getShippingCountryPackage(Date dateFrom, Date dateTo, String sp);
	
	@Query(value=sql2 , nativeQuery=true)
	public List<Object[]> getStatisticsInfoByShippingCountry(Date dateFrom, Date dateTo, String sp, String shippingCountryCode);
	
	
	
}
