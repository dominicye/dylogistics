package com.qiaoyu.app.dao.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
public static final String sql = "select p.*,od.quantity from qiaoyulogistics.order o, qiaoyulogistics.order_detail od, qiaoyulogistics.product p where o.id=od.order_id and od.product_id=p.id and o.id=?";
	
	@Query(value=sql, nativeQuery=true)
	public List<Object> getProductListByOrderId(int orderId);

}
