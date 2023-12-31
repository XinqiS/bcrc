package com.sooka.common.template;

import java.io.File;
import java.util.List;

/**
 * Description:
 *
 *
 * @create 2017-08-11
 **/
public interface TemplateFileService {

    List<TemplateFile> findAll();

    TemplateFile findByPath(String path);

    void writeTemplateFileContent(TemplateFile templateFile);

    String readTemplateFileContent(File file);

    String delete();


}
