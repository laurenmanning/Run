package com.example.generateinitialitinerary

class DraftModelClass(var name: String, var address:String,var rating:String, var type:String, var visited:Boolean, var isSelected:Boolean) {

    override fun toString(): String {
        return "ModelClass{" + "name = '" + name + '\'' +
                ", address=" + address + '\''
                ", rating=" + rating + '\''
                ", type=" + type + '\''
                ", boolean=" + visited + '\''
                ", isSelected=" + isSelected + '\'' +
                '}'
    }
}