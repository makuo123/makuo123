package com.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class R<T> {
    private Integer code;
    private T data;
    private String msg;
    private boolean success;
}
