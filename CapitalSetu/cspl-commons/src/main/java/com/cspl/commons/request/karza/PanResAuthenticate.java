package com.cspl.commons.request.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PanResAuthenticate {
    private String status;
    private boolean duplicate;
    private boolean nameMatch;
    private boolean dobMatch;
    
}
