package org.jamesdev.apitest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This will be the UserResponse
 */

public class UserInfo {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("age")
    @Expose
    int age;

    @SerializedName("gender")
    @Expose
    String gender;

    public UserInfo(String name, int age, String gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }
    
    public String getGender(){
        return this.gender;
    }

    public void setName(String value){
        this.name = value;
    }

    public void setAge(int value){
        this.age = value;
    }

    public void setGender(String value){
        this.gender = value;
    }

    @Override
    public String toString(){
        return String.format
                ("Name: %s%nAge: %d%nGender: %s%n",
                        this.name,this.age,this.gender);
    }


}
