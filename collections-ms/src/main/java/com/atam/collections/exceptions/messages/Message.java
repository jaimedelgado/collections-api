package com.atam.collections.exceptions.messages;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String type;
    private String alias;
    private String description;

    Message(String type, String alias, String description) {
        this.type = type;
        this.alias = alias;
        this.description = description;
    }
}
