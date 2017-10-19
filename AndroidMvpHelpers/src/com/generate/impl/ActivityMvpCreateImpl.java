package com.generate.impl;

import com.MvpGeneratorAction;
import com.generate.BaseGenerator;

public class ActivityMvpCreateImpl extends BaseGenerator{

    public ActivityMvpCreateImpl(String basePackage, String currentPackage, String author, String moduleName, String appPath) {
        super(basePackage, currentPackage, author, moduleName, appPath);
    }

    @Override
    public void generate() {
        createFile(MvpGeneratorAction.CodeType.Activity);
        createFile(MvpGeneratorAction.CodeType.Contract);
        createFile(MvpGeneratorAction.CodeType.Model);
        createFile(MvpGeneratorAction.CodeType.Presenter);
    }



}
