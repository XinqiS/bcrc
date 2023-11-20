package com.sooka.module.web.cms.service.impl;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.JsonUtil;
import com.sooka.module.web.cms.service.MedicCountService;
import com.sooka.mybatis.mapper.TCmsMedicCountMapper;
import com.sooka.mybatis.model.TCmsMediaCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MedicCountServiceImpl implements MedicCountService {
    
    @Autowired
    private TCmsMedicCountMapper medicCountMapper;

    @Override
    public List<TCmsMediaCount> selectByYear(String callYear, String callMonth) {

        return medicCountMapper.selectByYear(callYear,callMonth);
    }

    @Override
    public String save(TCmsMediaCount pojo) {
        pojo.setCallDate(new Date());
        pojo.setCallConunt(1);
        if(medicCountMapper.insert(pojo)>0) {
            return JsonUtil.toSUCCESS("操作成功！","topic-tab",true);
        }
        return JsonUtil.toERROR("操作失败！");
    }

    @Override
    public String update(TCmsMediaCount pojo) {
        return null;
    }

    @Override
    public String updateByYear(TCmsMediaCount pojo){
        if(medicCountMapper.updateByYear(pojo)>0){
            return JsonUtil.toSUCCESS("操作成功!","lbt-tab",true);
        }
        return JsonUtil.toERROR("操作失败!");
    }

    @Override
    public String delete(Integer[] ids) {
        return null;
    }

    @Override
    public TCmsMediaCount findById(Integer id) {
        return null;
    }

    @Override
    public List<TCmsMediaCount> findList(TCmsMediaCount pojo) {
        return null;
    }

    @Override
    public List<TCmsMediaCount> findAll() {
        return null;
    }

    @Override
    public PageInfo<TCmsMediaCount> page(Integer pageNumber, Integer pageSize, TCmsMediaCount pojo) {
        return null;
    }

    @Override
    public PageInfo<TCmsMediaCount> page(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public TCmsMediaCount findByMaxId(){
        TCmsMediaCount media= medicCountMapper.findByMaxId();
        return media;
    }
    @Override
    public String findAllCount() {
        TCmsMediaCount tCmsMediaCount=  medicCountMapper.selectByCount();
        String zz=tCmsMediaCount.getZl();
        String json = "{" +

                "  \"legend\": {\n" +
                "    \"data\": [\n" +
                "      \""+zz+"\"\n"+
                "    ]\n" +
                "  }}\n";
                return json;
    }

  @Override
    public String findAllMonthCount() {
        List<TCmsMediaCount> list = this.medicCountMapper.selectAllMonthCount();
         TCmsMediaCount tCmsMediaCount=  medicCountMapper.selectByCount();
        SimpleDateFormat formatMonth = new SimpleDateFormat("M");
        if(list==null){
            return null;
        }
        if(list.size()==0){
            return null;
        }
        String xx="";
        String yy="";
        String zz=tCmsMediaCount.getZl();
        for(int i=0;i<list.size();i++){
            if(i==(list.size()-1)){
                xx+="\""+formatMonth.format(list.get(i).getCallDate())+"月"+"\"\n";
                yy+="\""+list.get(i).getCallConunt()+"\"\n";
            }else{
                xx+="\""+formatMonth.format(list.get(i).getCallDate())+"月"+"\",\n";
                yy+="\""+list.get(i).getCallConunt()+"\",\n";
            }
        }
        String json = "{" +
                "  \"tooltip\": {\n" +
                "    \"trigger\": \"axis\"" +
                "  },\n" +
                "  \"legend\": {\n" +
                "    \"data\": [\n" +
                "      \"访问量统计("+zz+")\"\n"+
                "    ]\n" +
                "  },\n" +
                "  \"toolbox\": {\n" +
                "    \"show\": true,\n" +
                "    \"feature\": {\n" +
                "      \"magicType\": {\n" +
                "        \"show\": true,\n" +
                "        \"type\": [\n" +
                "          \"line\",\n" +
                "          \"bar\"\n" +
                "        ]\n" +
                "      },\n" +
                "      \"restore\": {\n" +
                "        \"show\": true\n" +
                "      },\n" +
                "      \"saveAsImage\": {\n" +
                "        \"show\": true\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"calculable\": true,\n" +
                "  \"xAxis\": [\n" +
                "    {\n" +
                "      \"type\": \"category\",\n" +
                "      \"axisLabel\" : {\n" +
                "           \"show\":true,\n" +
                "           \"interval\": 0,\n" +
                "           \"rotate\": 10"+
                "   },\n"+
                "      \"data\": [\n" +
                "        \"1月\",\n" +
                "        \"2月\",\n" +
                "        \"3月\",\n" +
                "        \"4月\",\n" +
                "        \"5月\",\n" +
                "        \"6月\",\n" +
                "        \"7月\",\n" +
                "        \"8月\",\n" +
                "        \"9月\",\n" +
                "        \"10月\",\n" +
                "        \"11月\",\n" +
                "        \"12月\"\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"yAxis\": [\n" +
                "    {\n" +
                "      \"type\": \"value\",\n" +
                "      \"splitArea\": {\n" +
                "        \"show\": true\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"series\": [\n" +
                "    {\n" +
                "      \"name\": \"访问量统计\",\n" +
                "      \"type\": \"bar\",\n" +
                "      \"data\": [\n" +yy+
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        return json;
    }

}
