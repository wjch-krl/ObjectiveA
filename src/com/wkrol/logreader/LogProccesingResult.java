package com.wkrol.logreader;

public class LogProccesingResult {
    private String sizeCategoryClass;
    private double requestTotalTime;
    private long count;
    private double precentOfTotalRequests;

    public LogProccesingResult(String sizeCategoryClass, double requestTotalTime, long count, double precentOfTotalRequests) {
        this.sizeCategoryClass = sizeCategoryClass;
        this.requestTotalTime = requestTotalTime;
        this.count = count;
        this.precentOfTotalRequests = precentOfTotalRequests;
    }

    public LogProccesingResult() {
    }

    @Override
    public String toString() {
        return "LogProccesingResult{" +
                "sizeCategoryClass='" + sizeCategoryClass + '\'' +
                ", requestTotalTime=" + requestTotalTime +
                ", precentOfTotalRequests=" + precentOfTotalRequests +
                '}';
    }

    public String getSizeCategoryClass() {
        return sizeCategoryClass;
    }

    public void setSizeCategoryClass(String sizeCategoryClass) {
        this.sizeCategoryClass = sizeCategoryClass;
    }

    public double getRequestTotalTime() {
        return requestTotalTime;
    }

    public void setRequestTotalTime(double requestTotalTime) {
        this.requestTotalTime = requestTotalTime;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getPrecentOfTotalRequests() {
        return precentOfTotalRequests;
    }

    public void setPrecentOfTotalRequests(double precentOfTotalRequests) {
        this.precentOfTotalRequests = precentOfTotalRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogProccesingResult result = (LogProccesingResult) o;

        if (Double.compare(result.requestTotalTime, requestTotalTime) != 0) return false;
        if (count != result.count) return false;
        if (Double.compare(result.precentOfTotalRequests, precentOfTotalRequests) != 0) return false;
        return sizeCategoryClass != null ? sizeCategoryClass.equals(result.sizeCategoryClass) : result.sizeCategoryClass == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = sizeCategoryClass != null ? sizeCategoryClass.hashCode() : 0;
        temp = Double.doubleToLongBits(requestTotalTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (count ^ (count >>> 32));
        temp = Double.doubleToLongBits(precentOfTotalRequests);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
