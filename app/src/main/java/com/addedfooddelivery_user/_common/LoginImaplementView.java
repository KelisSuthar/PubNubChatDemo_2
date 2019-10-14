package com.addedfooddelivery_user._common;



public interface LoginImaplementView {

    void email();

    void emailValidation();

    void passwordValidation();

    void passwordMinValidation();

    void success();


    interface SignUpImaplementView {
        void firstName();

        void email();

        void emailValidation();

        void passwordValidation();

        void passwordMinValidation();

        void passwordCompare();

        void success();


    }

    interface UpdateImaplementView {
        void firstName();

        void lastName();

        void email();

        void emailValidation();

        void mobileValidation();

        void mobileMinValidation();

        void addressValidation();

        void success();
    }

}
