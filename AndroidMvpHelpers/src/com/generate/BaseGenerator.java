package com.generate;

import com.MvpGeneratorAction;
import com.util.FileUtil;
import com.util.PropertiesUtil;

import java.io.File;
import java.util.Date;

public abstract class BaseGenerator implements ICreate{

    protected String basePackage;
    protected String currentPackage;
    protected String author;
    protected String moduleName;
    protected String appPath;
    protected String baseMvpAFPackage;//mvp activity的包名

    public BaseGenerator(String basePackage, String currentPackage, String author, String moduleName, String appPath) {
        this.basePackage = basePackage;
        this.currentPackage = currentPackage;
        this.author = author;
        this.moduleName = moduleName;
        this.appPath = appPath;

        this.baseMvpAFPackage = PropertiesUtil.get(PropertiesUtil.KEYS.BASE_ACTIVITY_PACKAGE);
    }

    protected void createFile(MvpGeneratorAction.CodeType codeType){
        String fileName = "";
        String content = "";

        switch (codeType){
            case Activity:
                fileName = "TemplateActivity.txt";
                content = FileUtil.readTemplateFile(fileName);
                content = dealTemplateContent(content);
                FileUtil.writeToFile(content,appPath  + File.separator + "activity", moduleName+ "Activity.java");
                break;
            case Fragment:
                fileName = "TemplateFragment.txt";
                content = FileUtil.readTemplateFile(fileName);
                content = dealTemplateContent(content);
                FileUtil.writeToFile(content,appPath   + File.separator + "fragments", moduleName+ "Fragment.java");
                break;
            case Contract:
                fileName = "TemplateContract.txt";
                content = FileUtil.readTemplateFile(fileName);
                content = dealTemplateContent(content);
                FileUtil.writeToFile(content,appPath   + File.separator + "mvp", moduleName+ "Contract.java");
                break;
            case Model:
                fileName = "TemplateModel.txt";
                content = FileUtil.readTemplateFile(fileName);
                content = dealTemplateContent(content);
                FileUtil.writeToFile(content,appPath   + File.separator + "mvp", moduleName+ "Model.java");
                break;
            case Presenter:
                fileName = "TemplatePresenter.txt";
                content = FileUtil.readTemplateFile(fileName);
                content = dealTemplateContent(content);
                FileUtil.writeToFile(content,appPath   + File.separator + "mvp", moduleName+ "Presenter.java");
                break;
            default:
                break;

        }
    }

    private String dealTemplateContent(String content) {
        content = content.replace("$name", moduleName);
        if (content.contains("$packagename")){
            content = content.replace("$packagename", currentPackage);
        }
        if (content.contains("$basepackagename")){
            content = content.replace("$basepackagename", basePackage);
        }

        if (content.contains("$basemvppackage")){
            content = content.replace("$basemvppackage",baseMvpAFPackage);
        }
        content = content.replace("$author", author);
        content = content.replace("$date", new Date().toString());
        return content;
    }
}
