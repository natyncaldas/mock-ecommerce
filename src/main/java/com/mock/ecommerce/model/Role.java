package com.mock.ecommerce.model;

public class Role {

    private ERole name;

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Role){
            Role role = (Role) other;
            return role.getName().equals(this.getName());
        }
        return false;
    }
}
