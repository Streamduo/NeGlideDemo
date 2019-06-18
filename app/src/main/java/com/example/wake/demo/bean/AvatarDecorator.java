package com.example.wake.demo.bean;

public class AvatarDecorator implements Avatar {

    private Avatar avatar;

    public AvatarDecorator(Avatar avatar){
        this.avatar = avatar;
    }

    @Override
    public String describe() {
        return avatar.describe();
    }
}
