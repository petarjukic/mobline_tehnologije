package com.example.vjezba3;

import com.google.gson.annotations.SerializedName;

public class Repozitorij {
    @SerializedName("name")
    String name;
    @SerializedName("stargazers_count")
    String stargazers_count;
    @SerializedName("owner")
    Owner owner;


    public Repozitorij() {
        getName();
        getStargazers_count();
        getOwner();
    }

    public String getName() {
        return name;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getStargazers_count() {
        return stargazers_count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStargazers_count(String stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
