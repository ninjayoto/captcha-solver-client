package com.example;

public class CaptchaResponse {

    private int requestId;
    private String hostName;
    private String siteKey;
    private String requestTimeStamp;
    private Object solutionTimeStamp;
    private String solution;
    private boolean assigned;

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getSiteKey() {
        return siteKey;
    }

    public void setSiteKey(String siteKey) {
        this.siteKey = siteKey;
    }

    public String getRequestTimeStamp() {
        return requestTimeStamp;
    }

    public void setRequestTimeStamp(String requestTimeStamp) {
        this.requestTimeStamp = requestTimeStamp;
    }

    public Object getSolutionTimeStamp() {
        return solutionTimeStamp;
    }

    public void setSolutionTimeStamp(Object solutionTimeStamp) {
        this.solutionTimeStamp = solutionTimeStamp;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}
