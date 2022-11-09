package com.example.zaznoo.listiners;

import com.example.zaznoo.models.User;
import com.example.zaznoo.models.ZaznooUserResponse;

public interface ZaznooUserResponseListiner {

    void onZaznnoUserArrived(User user);
}
