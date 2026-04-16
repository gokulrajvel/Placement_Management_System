package com.gokulrajvel.placementmonitoring.data.dto;

public class PlacementResult {
    private int resultId;
    private String status;
    private String packageOffer;
    public void  setResultId(int resultId) {
        this.resultId = resultId;
    }
    public int getResultId() {
        return resultId;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setPackageOffer(String packageOffer) {
        this.packageOffer = packageOffer;
    }
    public String getPackageOffer() {
        return packageOffer;
    }
}
