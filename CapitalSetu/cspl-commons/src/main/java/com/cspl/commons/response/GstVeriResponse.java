package com.cspl.commons.response;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GstVeriResponse {

	private String canFlag;
	//contacted make class
	private GstVeriContacted contacted;  
	private String ppr;
	private String cmpRt;
	private String rgdt;
	private String tradeNam;

	//NBA make arry
	private ArrayList<String> nba;

	// mbr make array
	private ArrayList<String> mbr;

	// array
	private ArrayList<GstVeriPradr> adadr;
	// pradr make class
	private GstVeriPradr pradr;

	private String stjCd;
	private String lstupdt;
	private String gstin;
	private String ctjcd;
	private String stj;
	private String dty;
	private String cxdt;
	private String ctb;
	private String sts;
	private String lgnm;
	private String ctj;

}
