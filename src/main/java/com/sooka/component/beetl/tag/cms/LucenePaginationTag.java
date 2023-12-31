package com.sooka.component.beetl.tag.cms;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sooka.common.exception.CmsException;
import com.sooka.common.utils.CmsUtil;
import com.sooka.component.lucene.util.IndexObject;
import com.sooka.module.web.cms.vo.PageActionVo;
import com.sooka.module.web.cms.vo.PaginateVo;
import org.beetl.core.GeneralVarTagBinding;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:分页标记
 *
 *
 * @create 2017-06-05
 **/
@Service
@Scope("prototype")
public class LucenePaginationTag extends GeneralVarTagBinding {

    @Value("${system.http.protocol}")
    private String httpProtocol;

    @Value("${system.site.subfix}")
    private String siteSubfix;

    @Override
    public void render() {

        PageInfo<IndexObject> contentPage = (PageInfo<IndexObject>) this.getAttributeValue("page");
        if(CmsUtil.isNullOrEmpty(contentPage)) {
            throw new CmsException("[分页标签]此标签只能和内容分页标签!");
        }
        String action = (String) this.getAttributeValue("action");
        action += "&p={pageNumber}";
        PaginateVo page = paging(contentPage.getPageNum(), Integer.parseInt(String.valueOf(contentPage.getPages())),contentPage.getPageNum(), action);
        this.binds(page);
        this.doBodyRender();

    }


    public PaginateVo paging(int currentPage, int totalPage, int pageNumber, String url) {

        PaginateVo paging = new PaginateVo();

        String lastPage = "<a href=\"" + url + "\">上一页</a>";
        String changeStr = "<a href=\"" + url + "\">{pageNumber}</a>";
        String nextPage = "<a href=\"" + url + "\">下一页</a>";
        List<PageActionVo> changeLink = Lists.newArrayList();

        paging.setCurrent(currentPage);
        paging.setTotal(totalPage);

        if (totalPage <= 0 || pageNumber > totalPage) {
            paging.setFirst("<a>上一页</a>");
            PageActionVo p1 = new PageActionVo();
            p1.setLink(false);
            p1.setValue("<a>1</a>");
            changeLink.add(p1);
            paging.setCurrent(1);
            paging.setChangePage(changeLink);
            paging.setNext("<a>下一页</a>");
            return paging;
        }

        Integer startPage = currentPage - 4;
        Integer endPage = currentPage + 4;

        if (startPage < 1) {
            startPage = 1;
        }

        if (endPage > totalPage) {
            endPage = totalPage;
        }

        if (currentPage <= 8) {
            startPage = 1;
        }

        if (totalPage - currentPage < 8) {
            endPage = totalPage;
        }

        if (currentPage == 1) {
            lastPage = "<a>上一页</a>";
        } else {
            lastPage = lastPage.replace("{pageNumber}", (currentPage - 1) + "");
        }

        paging.setFirst(lastPage);

        if (currentPage > 8) {

            // 第一页
            PageActionVo p1 = new PageActionVo();
            p1.setPageNumber(1);
            p1.setUrl(changeStr.replace("{pageNumber}", "1"));
            p1.setLink(true);
            changeLink.add(p1);
            // 第二页
            PageActionVo p2 = new PageActionVo();
            p1.setPageNumber(2);
            p1.setUrl(changeStr.replace("{pageNumber}", "2"));
            p1.setLink(true);
            changeLink.add(p2);
            // 更多字符串.....
            PageActionVo more = new PageActionVo();
            more.setLink(false);
            more.setMore(true);
            more.setValue("<a>...</a>");
            changeLink.add(more);
        }

        Integer index = startPage;

        while (index <= endPage) {
            PageActionVo pageCurrent = new PageActionVo();
            if (currentPage == index) {
                pageCurrent.setLink(false);
                pageCurrent.setValue("<a>"+index+"</a>");
                changeLink.add(pageCurrent);
            } else {
                pageCurrent.setLink(true);
                pageCurrent.setPageNumber(index);
                pageCurrent.setUrl(changeStr.replace("{pageNumber}", index + ""));
                changeLink.add(pageCurrent);
            }
            index = index + 1;
        }

        if ((totalPage - currentPage) >= 8) {

            // 更多字符串.....
            PageActionVo more1 = new PageActionVo();
            more1.setLink(false);
            more1.setMore(true);
            more1.setValue("<a>...</a>");
            changeLink.add(more1);
            // 第一页
            PageActionVo p11 = new PageActionVo();
            p11.setPageNumber(totalPage - 1);
            p11.setUrl(changeStr.replace("{pageNumber}", (totalPage - 1) + ""));
            p11.setLink(true);
            changeLink.add(p11);
            // 第二页
            PageActionVo p22 = new PageActionVo();
            p22.setPageNumber(totalPage);
            p22.setUrl(changeStr.replace("{pageNumber}", totalPage + ""));
            p22.setLink(true);
            changeLink.add(p22);

        }

        paging.setChangePage(changeLink);

        if (currentPage == totalPage) {
            nextPage = "<a>下一页</a>";
        } else {
            nextPage = nextPage.replace("{pageNumber}", (currentPage + 1) + "");

        }
        paging.setNext(nextPage);
        return paging;

    }
}
