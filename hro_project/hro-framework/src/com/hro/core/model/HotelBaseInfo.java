package com.hro.core.model;

import java.math.BigDecimal;
import java.util.List;

public class HotelBaseInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_base_info.ID
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_base_info.HOTEL_NAME
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    private String hotelName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_base_info.ADDRESS
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_base_info.INTRODUCTION
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    private String introduction;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_base_info.BD_X
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    private BigDecimal bdX;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_base_info.BD_Y
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    private BigDecimal bdY;
    
    /**
     * 跟图片是1对多的关系
     */
    private List<PicInfo> picInfos;
    
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_base_info.ID
     *
     * @return the value of hotel_base_info.ID
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_base_info.ID
     *
     * @param id the value for hotel_base_info.ID
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_base_info.HOTEL_NAME
     *
     * @return the value of hotel_base_info.HOTEL_NAME
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_base_info.HOTEL_NAME
     *
     * @param hotelName the value for hotel_base_info.HOTEL_NAME
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_base_info.ADDRESS
     *
     * @return the value of hotel_base_info.ADDRESS
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_base_info.ADDRESS
     *
     * @param address the value for hotel_base_info.ADDRESS
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_base_info.INTRODUCTION
     *
     * @return the value of hotel_base_info.INTRODUCTION
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_base_info.INTRODUCTION
     *
     * @param introduction the value for hotel_base_info.INTRODUCTION
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_base_info.BD_X
     *
     * @return the value of hotel_base_info.BD_X
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public BigDecimal getBdX() {
        return bdX;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_base_info.BD_X
     *
     * @param bdX the value for hotel_base_info.BD_X
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public void setBdX(BigDecimal bdX) {
        this.bdX = bdX;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_base_info.BD_Y
     *
     * @return the value of hotel_base_info.BD_Y
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public BigDecimal getBdY() {
        return bdY;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_base_info.BD_Y
     *
     * @param bdY the value for hotel_base_info.BD_Y
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public void setBdY(BigDecimal bdY) {
        this.bdY = bdY;
    }

	public List<PicInfo> getPicInfos() {
		return picInfos;
	}

	public void setPicInfos(List<PicInfo> picInfos) {
		this.picInfos = picInfos;
	}
}