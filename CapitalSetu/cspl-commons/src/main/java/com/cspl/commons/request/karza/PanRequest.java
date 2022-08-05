package com.cspl.commons.request.karza;

import com.cspl.commons.request.UniqueId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PanRequest extends UniqueId {

    private String consent;
    private String pan;

}
