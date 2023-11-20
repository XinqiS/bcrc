package com.sooka.module.web.cms.service;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.sooka.common.base.BaseService;
import com.sooka.module.web.system.vo.Lists;

public interface ListsService extends BaseService<Lists, Integer> {

	PageInfo<Lists> page(Integer pageNumber, Integer pageSize,
			HttpServletRequest request, Integer type);

}
