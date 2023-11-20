package com.sooka.module.web.cms.service;

import javax.servlet.http.HttpServletRequest;

import com.sooka.common.base.BaseService;
import com.sooka.module.web.system.vo.tb_zwscjl;

public interface TbZwscjlService extends BaseService<tb_zwscjl, Integer> {
	void save(tb_zwscjl scjl, HttpServletRequest request);
}
