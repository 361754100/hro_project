package com.hro.core.model.mapper;

import com.hro.core.model.MusicInfo;

public interface MusicInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music_info
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music_info
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    int insert(MusicInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music_info
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    int insertSelective(MusicInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music_info
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    MusicInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music_info
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    int updateByPrimaryKeySelective(MusicInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table music_info
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    int updateByPrimaryKey(MusicInfo record);
}