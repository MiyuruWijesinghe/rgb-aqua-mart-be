package com.rgbaquamart.be.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.rgbaquamart.be.service.CommonMethodsService;

@Component
@Transactional(rollbackFor=Exception.class)
public class CommonMethodsServiceImpl implements CommonMethodsService {

	@Override
	public Timestamp getCreateOrModifyDate() {
		Calendar calendar = Calendar.getInstance();
    	java.util.Date now = calendar.getTime();
    	return new Timestamp(now.getTime());
	}
}
