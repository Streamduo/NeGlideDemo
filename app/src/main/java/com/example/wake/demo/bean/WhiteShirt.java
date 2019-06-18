package com.example.wake.demo.bean;

public class WhiteShirt extends AvatarDecorator {

    public WhiteShirt(Avatar avatar) {
        super(avatar);
    }

    @Override
    public String describe() {
        return super.describe() + "白色衬衫";
    }
}
