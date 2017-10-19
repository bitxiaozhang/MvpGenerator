package com.generate.factory;

import com.generate.BaseGenerator;

public interface IFactory {
    BaseGenerator create(String basePackage, String currentPackage, String author, String moduleName,String appPath);
}
