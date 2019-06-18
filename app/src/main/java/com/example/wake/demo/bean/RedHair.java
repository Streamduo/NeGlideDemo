package com.example.wake.demo.bean;

public class RedHair extends AvatarDecorator {

    public RedHair(Avatar avatar) {
        super(avatar);
    }

    @Override
    public String describe() {
        return super.describe() + "红色头发";
    }
}
