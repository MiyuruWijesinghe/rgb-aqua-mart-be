package com.rgbaquamart.be.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rgbaquamart.be.service.SpecialInstructionsService;

@Component
@Transactional(rollbackFor=Exception.class)
public class SpecialInstructionsServiceImpl implements SpecialInstructionsService {

}
