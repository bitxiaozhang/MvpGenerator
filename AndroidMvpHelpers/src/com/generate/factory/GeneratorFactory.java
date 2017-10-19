package com.generate.factory;

import com.MvpGeneratorAction;
import com.generate.BaseGenerator;
import com.generate.impl.ActivityMvpCreateImpl;
import com.generate.impl.FragmentCreateImpl;

public class GeneratorFactory implements IFactory{

    private MvpGeneratorAction.CodeType mCodeType;

    public GeneratorFactory(MvpGeneratorAction.CodeType mCodeType) {
        this.mCodeType = mCodeType;
    }

    @Override
    public BaseGenerator create(String basePackage, String currentPackage, String author, String moduleName,String appPath) {
        switch (mCodeType){
            case Activity:
                return new ActivityMvpCreateImpl(basePackage, currentPackage, author, moduleName,appPath);
            case Fragment:
                return new FragmentCreateImpl(basePackage, currentPackage, author, moduleName,appPath);
                default:
                return null;
        }
    }
}
