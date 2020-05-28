package nl.teama.server.models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DataDTO {
    @NotEmpty(message = "Please provide: App Name")
    private String appName;

    @NotEmpty(message = "Please provide: Time Used")
    private Long timeUsed;
}
