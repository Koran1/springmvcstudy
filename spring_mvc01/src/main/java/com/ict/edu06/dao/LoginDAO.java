package com.ict.edu06.dao;

import com.ict.edu06.vo.LoginVO;

public interface LoginDAO {
	public int LoginJoin(LoginVO lvo) throws Exception;
	public LoginVO LoginChk(LoginVO lvo) throws Exception;
}
