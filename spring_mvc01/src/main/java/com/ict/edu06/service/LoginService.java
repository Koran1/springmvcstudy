package com.ict.edu06.service;

import com.ict.edu06.vo.LoginVO;

public interface LoginService {
	public int LoginJoin(LoginVO lvo) throws Exception;
	public LoginVO LoginChk(LoginVO lvo) throws Exception;
}
