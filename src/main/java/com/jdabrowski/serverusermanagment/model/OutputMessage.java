package com.jdabrowski.serverusermanagment.model;

import lombok.Data;

@Data
public class OutputMessage {
    private String token;
    private User user;

}
