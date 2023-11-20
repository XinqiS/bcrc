package com.sooka.component.beetl.tag.cms;

import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.CmsUtil;
import com.sooka.module.web.cms.service.ZwtdService;
import com.sooka.module.web.system.service.ZwtdjlService;
import com.sooka.module.web.system.vo.gjss;
import com.sooka.mybatis.model.JlAll;
import org.beetl.core.GeneralVarTagBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:内容列表标签
 *
 *
 * @create 2017-05-26
 **/
@Service
@Scope("prototype")
public class ZwtdListTag extends GeneralVarTagBinding {

    @Autowired
    private ZwtdjlService zwtdlService;


    /**
     * 职位展示
     *
     */
    @Override
    public void render() {
        Integer titleLen =  Integer.parseInt((String) this.getAttributeValue("titleLen"));
        Integer pageNumber =  Integer.parseInt((CmsUtil.isNullOrEmpty(this.getAttributeValue("pageNumber"))?"1":(String) this.getAttributeValue("pageNumber")));
        Integer pageSize =  Integer.parseInt((String) this.getAttributeValue("size"));
        //PageInfo<Map<String, Object>> pageInfo =null;
        JlAll jl=new JlAll();
        PageInfo<Map<String,Object>> pageInfo;
        pageInfo = zwtdlService.pageAll(pageNumber,pageSize,jl);
       // pageInfo = zwtdlService.pageAll(pageNumber,pageSize,jl);
        if(CmsUtil.isNullOrEmpty(pageInfo.getList())) {
            return;
        }
        this.wrapRender(pageInfo.getList(),titleLen);
    }
    private void wrapRender(List<Map<String, Object>>  contentList, int titleLen) {
        int i = 1;
        for (Map content : contentList) {
            this.binds(content);
            this.doBodyRender();
            i++;
        }

    }


}
