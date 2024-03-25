package com.utc.app.safedriver.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    Integer id;
    Integer idDriver;
    String category;
}
