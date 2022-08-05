package com.cspl.commons.response;

import java.util.List;

import com.cspl.commons.response.GstInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GstListWithGivenPan {
	private  List<GstInfo> result;

}
