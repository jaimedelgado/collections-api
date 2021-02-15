package com.atam.collections.exceptions.messages;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Messages {
    private List<Message> messages;
}
