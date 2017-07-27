package com.qiaoyu.app.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="ORDER", schema="qiaoyulogistics", catalog="qiaoyulogistics")
public class Order implements Serializable {
	
	private Integer id;
	
	private Client client;
	
	private String status;
	
	private String packingFee;
	
	private String additionalFee;
	
	private String additionalFeeReason;
	
	private String totalFeePaied;
	
	private String shroffFee;
	
	private String deliveryFee;
	
	private Date deliveryDate;
	
	private Date createDate;
	
	private String serviceProvider;
	
	private String netWeight;
	
	private String capacity;
	
	private String grossWeight;
	
	private String trackingNo;
	
	private String trackingUrl;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CLIENT_ID", insertable=true)
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Column(name = "ORDER_STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PACKING_FEE")
	public String getPackingFee() {
		return packingFee;
	}

	public void setPackingFee(String packingFee) {
		this.packingFee = packingFee;
	}

	@Column(name = "ADDITIONAL_FEE")
	public String getAdditionalFee() {
		return additionalFee;
	}

	public void setAdditionalFee(String additionalFee) {
		this.additionalFee = additionalFee;
	}

	@Column(name = "ADDITIONAL_FEE_REASON")
	public String getAdditionalFeeReason() {
		return additionalFeeReason;
	}

	public void setAdditionalFeeReason(String additionalFeeReason) {
		this.additionalFeeReason = additionalFeeReason;
	}

	@Column(name = "TOTAL_FEE_PAIED")
	public String getTotalFeePaied() {
		return totalFeePaied;
	}

	public void setTotalFeePaied(String totalFeePaied) {
		this.totalFeePaied = totalFeePaied;
	}

	@Column(name = "SHROFF_FEE")
	public String getShroffFee() {
		return shroffFee;
	}

	public void setShroffFee(String shroffFee) {
		this.shroffFee = shroffFee;
	}

	@Column(name = "DELIVERY_FEE")
	public String getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(String deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	@Column(name = "DELIVERY_DATE")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "SERVICE_PROVIDER")
	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	
	@Column(name = "NETWEIGHT")
	public String getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}

	@Column(name = "CAPACITY")
	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	@Column(name = "GROSS_WEIGHT")
	public String getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}

	@Column(name = "TRACKING_NO")
	public String getTrackingNo() {
		return trackingNo;
	}

	public void setTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	@Column(name = "TRACKING_URL")
	public String getTrackingUrl() {
		return trackingUrl;
	}

	public void setTrackingUrl(String trackingUrl) {
		this.trackingUrl = trackingUrl;
	}
	
	
}
