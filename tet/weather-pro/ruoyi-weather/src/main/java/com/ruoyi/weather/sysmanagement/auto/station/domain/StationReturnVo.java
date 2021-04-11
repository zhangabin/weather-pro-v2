package com.ruoyi.weather.sysmanagement.auto.station.domain;

import java.util.List;

/**
 * @author yyeran yyearn_a@163.cim
 * @title stationReturnVo
 * @date 2021/4/7
 */
public class StationReturnVo {
    private String returnCode;
    private String returnMessage;
    private String rowCount;
    private String colCount;
    private String requestParams;
    private String requestTime;
    private String responseTime;
    private String takeTime;
    private String fieldNames;
    private String fieldUnits;
    private List<TAutoStation> DS;
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
    public String getReturnMessage() {
        return returnMessage;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }
    public String getRowCount() {
        return rowCount;
    }

    public void setColCount(String colCount) {
        this.colCount = colCount;
    }
    public String getColCount() {
        return colCount;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }
    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }
    public String getRequestTime() {
        return requestTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }
    public String getResponseTime() {
        return responseTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }
    public String getTakeTime() {
        return takeTime;
    }

    public void setFieldNames(String fieldNames) {
        this.fieldNames = fieldNames;
    }
    public String getFieldNames() {
        return fieldNames;
    }

    public void setFieldUnits(String fieldUnits) {
        this.fieldUnits = fieldUnits;
    }
    public String getFieldUnits() {
        return fieldUnits;
    }

    public List<TAutoStation> getDS() {
        return DS;
    }

    public void setDS(List<TAutoStation> DS) {
        this.DS = DS;
    }

    @Override
    public String toString() {
        return "StationReturnVo{" +
                "returnCode='" + returnCode + '\'' +
                ", returnMessage='" + returnMessage + '\'' +
                ", rowCount='" + rowCount + '\'' +
                ", colCount='" + colCount + '\'' +
                ", requestParams='" + requestParams + '\'' +
                ", requestTime=" + requestTime +
                ", responseTime=" + responseTime +
                ", takeTime='" + takeTime + '\'' +
                ", fieldNames='" + fieldNames + '\'' +
                ", fieldUnits='" + fieldUnits + '\'' +
                ", DS=" + DS +
                '}';
    }
}
