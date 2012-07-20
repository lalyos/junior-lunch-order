package com.epam.junior.service.simple;

public interface SimpleOrderServiceMBean {
    public String readOrderState(String ordrId);
    public void changeOrderState(String ordrId, String state);
}
