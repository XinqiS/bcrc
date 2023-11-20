package com.sooka.component.beetl.tag.cms;

import com.github.pagehelper.PageInfo;
import com.sooka.common.exception.CmsException;
import com.sooka.common.exception.SystemException;
import com.sooka.common.utils.CmsUtil;
import com.sooka.common.utils.StrUtil;
import com.sooka.module.web.cms.service.ContentService;
import com.sooka.module.web.cms.service.SiteService;
import com.sooka.module.web.system.service.GwInfoService;
import com.sooka.module.web.system.vo.gjss;
import com.sooka.module.web.system.vo.tb_gw_info;
import com.sooka.mybatis.model.TCmsSite;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.GeneralVarTagBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class GwinfoListTag extends GeneralVarTagBinding {

    @Autowired
    private GwInfoService gwInfoServices;


    /**
     * 职位展示
     *
     */
    @Override
    public void render() {
        String gzxz =  (String) this.getAttributeValue("gzxz");
        Integer pageNumber =  Integer.parseInt((CmsUtil.isNullOrEmpty(this.getAttributeValue("pageNumber"))?"1":(String) this.getAttributeValue("pageNumber")));
        Integer pageSize =  Integer.parseInt((String) this.getAttributeValue("size"));
        gjss g=new gjss();
        if(StringUtils.isNotBlank(gzxz)){
            if(gzxz.equals("3")){
                g.setZw_gzxz(Integer.parseInt(gzxz));
            }
            if(gzxz.equals("0")){
                g.setZw_gzxz(Integer.parseInt(gzxz));
            }
        }
        PageInfo<Map<String, Object>> pageInfo = gwInfoServices.page(pageNumber,
                pageSize, g);
        if(CmsUtil.isNullOrEmpty(pageInfo.getList())) {
            return;
        }
        this.wrapRender(pageInfo.getList());
    }
    private void wrapRender(List<Map<String, Object>>  contentList) {
        int i = 1;
        for (Map content : contentList) {
            this.binds(content);
            this.doBodyRender();
            i++;
        }

    }


}
