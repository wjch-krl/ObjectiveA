package com.wkrol.logreader;

public class RequestTimeAndSize {

    private long dataSize;
    private double requestTime;

    public RequestTimeAndSize(long dataSize, double requestTime) {
        this.dataSize = dataSize;
        this.requestTime = requestTime;
    }

    public long getDataSize() {
        return dataSize;
    }

    public void setDataSize(long dataSize) {
        this.dataSize = dataSize;
    }

    public double getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(double requestTime) {
        this.requestTime = requestTime;
    }

    public String getSizeClass() {
        if(dataSize < 0 || dataSize >= 1_073_741_824){
            throw new IllegalArgumentException("Data size out of class");
        }
        if (dataSize < 100_2400) {
            return "<100kB";
        } else if (dataSize < 1_048_576) {
            return ">100kB<1MB";
        } else if (dataSize < 10_485_760) {
            return ">1M<10MB";
        } else if (dataSize < 104_857_600) {
            return ">10MB<100MB";
        } else{
            return ">100MB<1GB";
        }
    }

    public boolean hasValidSizeClass() {
        return dataSize < 1_073_741_824;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestTimeAndSize that = (RequestTimeAndSize) o;

        if (dataSize != that.dataSize) return false;
        return Double.compare(that.requestTime, requestTime) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (dataSize ^ (dataSize >>> 32));
        temp = Double.doubleToLongBits(requestTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
