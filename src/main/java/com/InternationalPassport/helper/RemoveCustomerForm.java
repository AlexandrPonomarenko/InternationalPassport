package com.InternationalPassport.helper;

public class RemoveCustomerForm {
    private String action = null;
    private String login = null;

    public RemoveCustomerForm(String action, String login) {
        this.action = action;
        this.login = login;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "RemoveCustomerForm{" +
                "action='" + action + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
