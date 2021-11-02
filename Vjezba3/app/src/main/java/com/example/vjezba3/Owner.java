package com.example.vjezba3;

import com.google.gson.annotations.SerializedName;

public class Owner {
    @SerializedName("avatar_url")
    String avatar_url;

    public Owner() {
        getAvatar_url();
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
