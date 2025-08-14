package com.winter.app.account;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountVO {
	private String accountNum;
	private LocalDate accountDate;
	private Long accountBalance;

	private String username;
	private Long productNum;
}