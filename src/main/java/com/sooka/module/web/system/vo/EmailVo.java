package com.sooka.module.web.system.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailVo {
	@Value("${com.sooka.module.web.system.vo.EmailVo}")
private String MethodName;
}
