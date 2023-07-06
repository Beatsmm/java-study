package com.example.javastudy.skill.enums;

public enum Animal implements Eat{


    Dog(){
        @Override
        public String eat() {
            return "狗吃屎💩";
        }
    },

    Cat(){
        @Override
        public String eat() {
            return "猫吃🥩";
        }
    },

    Cattle(){
        @Override
        public String eat() {
            return "牛吃🌿";
        }
    }


}
