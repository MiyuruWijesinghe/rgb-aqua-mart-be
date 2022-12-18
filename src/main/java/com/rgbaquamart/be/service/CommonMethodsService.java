package com.rgbaquamart.be.service;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

@Service
public interface CommonMethodsService {

	Timestamp getCreateOrModifyDate();
}
