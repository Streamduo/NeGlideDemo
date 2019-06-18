package com.example.wake.demo.bean;

public class BluePants extends AvatarDecorator {


    public BluePants(Avatar avatar) {
        super(avatar);
    }

    @Override
    public String describe() {
        return super.describe() +"蓝色裤子";
    }
}
