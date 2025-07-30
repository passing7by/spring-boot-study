package com.winter.app.factory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// Controller, Service, Repository가 아닌 모든 것은 Component
//@Component("ga")

//@Component
//@Qualifier("gunArm")

//@Component
//@Primary
public class GunArm implements Arm {
	@Override
	public void attack() {
		System.out.println("Gun");
	}
}
