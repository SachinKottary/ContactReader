package com.techtreeit.sample.techtree.interfaces;

public class TTEvents<T> {

    public static final String EVENT_FACEBOOK_LOGIN_SUCCESS = "event_facebook_login_success";
    public static final String EVENT_GUEST_LOGIN = "event_guest_login";
    public static final String EVENT_DASHBOARD_CONTACT_SELECTED = "event_contact_selected";
    public static final String EVENT_DASHBOARD_WEBPAGE_SELECTED = "event_webpage_selected";
    public static final String EVENT_CONTACT_LIST_SELECTED = "event_contact_list_selected";
    public static final String EVENT_CONTACT_LIST_LOADED = "event_contact_list_loaded";

    private String flag;
    private T data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
