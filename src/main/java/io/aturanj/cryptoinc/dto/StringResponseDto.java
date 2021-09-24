package io.aturanj.cryptoinc.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

@ApiModel
public class StringResponseDto implements Serializable {

    private static final long serialVersionUID = 2130914510863364478L;

    private String response;

    public StringResponseDto() {
    }

    public StringResponseDto(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
