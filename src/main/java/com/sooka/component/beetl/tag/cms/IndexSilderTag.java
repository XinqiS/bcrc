package com.sooka.component.beetl.tag.cms;

import com.github.pagehelper.PageInfo;
import com.sooka.module.web.cms.service.SilderService;
import com.sooka.mybatis.model.TCmsAdSilder;
import com.sooka.common.exception.CmsException;
import com.sooka.common.exception.SystemException;
import com.sooka.common.utils.CmsUtil;
import com.sooka.common.utils.Pojo2MapUtil;
import org.beetl.core.GeneralVarTagBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:首页幻灯标签
 *
 *
 * @create 2017-05-26
 **/
@Service
@Scope("prototype")
public class IndexSilderTag extends GeneralVarTagBinding {

    @Autowired
    private SilderService silderService;


    @Override
    public void render() {
        if (CmsUtil.isNullOrEmpty(this.args[1])) {
            throw  new SystemException("标签参数不能为空！");
        }
        Integer titleLen =  Integer.parseInt((String) this.getAttributeValue("titleLen"));
        Integer size =  Integer.parseInt((String) this.getAttributeValue("size"));
        PageInfo<TCmsAdSilder> pageInfo = silderService.page(1,size);
        try {
            wrapRender(pageInfo.getList(),titleLen);
        } catch (Exception e) {
            throw new CmsException(e.getMessage());
        }
    }

    private void wrapRender(List<TCmsAdSilder>  silderList, int titleLen) throws Exception {
        int i = 1;
        for (TCmsAdSilder silder : silderList) {
            String title = silder.getSildeName();
            int length = title.length();
            if (length > titleLen) {
                silder.setSildeName(title.substring(0, titleLen) + "...");
            }
            Map result = Pojo2MapUtil.toMap(silder);
            result.put("index",i);
            this.binds(result);
            this.doBodyRender();
            i++;
        }

    }

}
