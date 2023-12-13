package com.example.actividadesProgreso.Security.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class ResultObjectResponse {

    private Integer result;
    private Boolean error;
    private String message;
    private Object data;

}
