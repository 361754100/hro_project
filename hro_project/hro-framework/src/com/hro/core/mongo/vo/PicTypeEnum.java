package com.hro.core.mongo.vo;

public enum PicTypeEnum {
	
	HOTEL_TYPE(1),	//酒店信息
	MUSIC_TYPE(2);	//音乐信息
	
	int value;

	PicTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
    
    public static void main(String[] args){
    	int value = PicTypeEnum.valueOf("MUSIC_TYPE").getValue();
    	System.out.println("value --->"+ value);
    }
}
