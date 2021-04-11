package com.ruoyi.weather.sysmanagement.auto.station.domain;

/**
    * 区域自动站基础数据
    */
public class TAutoStation {
    private Long id;

    /**
    * 站名
    */
    private String stationName;

    /**
    * 站号
    */
    private String stationCode;

    /**
    * 站址-省
    */
    private String province;

    /**
    * 站址-市
    */
    private String city;

    /**
    * 站址-县
    */
    private String cnty;

    /**
    * 站址-乡镇
    */
    private String town;

    /**
    * 行政区域编码
    */
    private String adminCodeChn;

    /**
    * 乡镇-编码
    */
    private String townCode;

    /**
    * 经度
    */
    private String lon;

    /**
    * 纬度
    */
    private String lat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStation_id_C(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAdminCodeChn() {
        return adminCodeChn;
    }

    public void setAdmin_Code_Chn(String adminCodeChn) {
        this.adminCodeChn = adminCodeChn;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stationName=").append(stationName);
        sb.append(", stationCode=").append(stationCode);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", cnty=").append(cnty);
        sb.append(", town=").append(town);
        sb.append(", adminCodeChn=").append(adminCodeChn);
        sb.append(", townCode=").append(townCode);
        sb.append(", lon=").append(lon);
        sb.append(", lat=").append(lat);
        sb.append("]");
        return sb.toString();
    }
}