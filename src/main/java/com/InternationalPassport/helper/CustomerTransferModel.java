package com.InternationalPassport.helper;

public class CustomerTransferModel {
    private String login;
    private boolean statusAccount;

    public CustomerTransferModel() {
    }

    public CustomerTransferModel(String login, boolean statusAccount) {
        this.login = login;
        this.statusAccount = statusAccount;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isStatusAccount() {
        return statusAccount;
    }

    public void setStatusAccount(boolean statusAccount) {
        this.statusAccount = statusAccount;
    }

    @Override
    public String toString() {
        return "CustomerTransferModel{" +
                "login='" + login + '\'' +
                ", statusAccount=" + statusAccount +
                '}';
    }
}
