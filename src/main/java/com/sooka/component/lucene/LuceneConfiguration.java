package com.sooka.component.lucene;

import com.sooka.common.utils.PathUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Description:s
 *
 *
 * @create 2017-05-18
 **/
@Configuration
public class LuceneConfiguration {


    @Bean
    public LuceneManager luceneUtil() {
        LuceneManager luceneDao = new LuceneManager();
        luceneDao.setIndexDer(PathUtil.getRootClassPath()+ File.separator+"lucene");
        return luceneDao;
    }

}
