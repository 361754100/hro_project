package com.hro.core.model;

public class MusicServerInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column music_server_info.ID
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column music_server_info.SERVER_IP
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    private String serverIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column music_server_info.SERVER_PORT
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    private String serverPort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column music_server_info.SERVER_STATUS
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    private Integer serverStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column music_server_info.ID
     *
     * @return the value of music_server_info.ID
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column music_server_info.ID
     *
     * @param id the value for music_server_info.ID
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column music_server_info.SERVER_IP
     *
     * @return the value of music_server_info.SERVER_IP
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public String getServerIp() {
        return serverIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column music_server_info.SERVER_IP
     *
     * @param serverIp the value for music_server_info.SERVER_IP
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column music_server_info.SERVER_PORT
     *
     * @return the value of music_server_info.SERVER_PORT
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public String getServerPort() {
        return serverPort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column music_server_info.SERVER_PORT
     *
     * @param serverPort the value for music_server_info.SERVER_PORT
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column music_server_info.SERVER_STATUS
     *
     * @return the value of music_server_info.SERVER_STATUS
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public Integer getServerStatus() {
        return serverStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column music_server_info.SERVER_STATUS
     *
     * @param serverStatus the value for music_server_info.SERVER_STATUS
     *
     * @mbggenerated Thu Mar 24 17:15:49 CST 2016
     */
    public void setServerStatus(Integer serverStatus) {
        this.serverStatus = serverStatus;
    }
}