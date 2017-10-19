package com.generate.impl;

import com.MvpGeneratorAction;
import com.generate.BaseGenerator;

public class FragmentCreateImpl extends BaseGenerator{


    public FragmentCreateImpl(String basePackage, String currentPackage, String author, String moduleName, String appPath) {
        super(basePackage, currentPackage, author, moduleName, appPath);
    }

    @Override
    public void generate() {
        createFile(MvpGeneratorAction.CodeType.Fragment);
        createFile(MvpGeneratorAction.CodeType.Contract);
        createFile(MvpGeneratorAction.CodeType.Model);
        createFile(MvpGeneratorAction.CodeType.Presenter);
    }
}
