package com.rca.myspringsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String username;
    private String password;
    public String getUsername() {
        return username;
    }
    public Object getPassword() {
        return password;
    }


}
