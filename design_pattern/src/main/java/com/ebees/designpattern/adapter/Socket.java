package com.ebees.designpattern.adapter;

public class Socket {

	public Volt getVolt() {
		return new Volt(120);
	}
}
