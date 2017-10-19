package com;

import com.generate.BaseGenerator;
import com.generate.factory.GeneratorFactory;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.ui.InputModuleNameDialog;
import com.util.PropertiesUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MvpGeneratorAction extends AnAction {


    private Project mProject;
    private String mPackageName;
    private String mCurrentPackage;
    private String mMenuRelativePath;

    public enum CodeType{
        Activity,Fragment,Contract,Presenter,Model
    }

    private CodeType mCodeType = CodeType.Activity;

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here｀｀｀
        mProject = e.getData(PlatformDataKeys.PROJECT);
        mPackageName = getPackageName();

        DataContext dataContext = e.getDataContext();
        VirtualFile file = DataKeys.VIRTUAL_FILE.getData(dataContext);
        if (file != null && file.exists()){
            mMenuRelativePath = file.getPath();
            handleMenuPackagePath();
        }else{
            throw new RuntimeException("未获取到文件夹路径！");
        }

        Presentation presentation = e.getPresentation();
        String menuTest = presentation.getText();
        if (menuTest != null && menuTest.length() > 0){
            switch (menuTest){
                case "ActivityMvp":
                    mCodeType = CodeType.Activity;
                    break;
                case "FragmentMvp":
                    mCodeType = CodeType.Fragment;
                    break;
                default:
                    break;
            }
        }

        if (PropertiesUtil.get(PropertiesUtil.KEYS.BASE_ACTIVITY_PACKAGE) != null){
            showDialog();
        }else{
        String txt= Messages.showInputDialog(mProject, "Input BaseActivity package Name", "BaseActivity Package Name", Messages.getQuestionIcon());
        if (txt != null){
            PropertiesUtil.put(PropertiesUtil.KEYS.BASE_ACTIVITY_PACKAGE,txt);
            showDialog();
        }else{
            Messages.showMessageDialog(mProject, "You must set BaseActivity Package First", "Information", Messages.getInformationIcon());
        }
      }
    }


    private void handleMenuPackagePath(){
        if (mMenuRelativePath != null && mMenuRelativePath.length() > 0){
            mCurrentPackage = mMenuRelativePath.replaceAll("/",".");
            int index = mCurrentPackage.indexOf(mPackageName);
            if (index < 0){
                throw new RuntimeException("该目录不在根目录中！");
            }
            mCurrentPackage = mCurrentPackage.substring(index,mCurrentPackage.length());
        }
    }

    private void showDialog(){

        InputModuleNameDialog dialog = new InputModuleNameDialog(new InputModuleNameDialog.OnEnsureClickDelegate() {
            @Override
            public void onEnsure(String author, String moduleName) {
                GeneratorFactory factory = new GeneratorFactory(mCodeType);
                BaseGenerator generator = factory.create(mPackageName,mCurrentPackage,author,moduleName,mMenuRelativePath);
                generator.generate();
                if (mProject != null){
                    mProject.getBaseDir().refresh(true,true);
                }
            }
        },mCodeType);

        dialog.setVisible(true);
    }


    private String getPackageName(){
        String packageName = "";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(mProject.getBasePath() + "/app/AndroidManifest.xml");
            NodeList nodelist = document.getElementsByTagName("manifest");

            for (int i = 0;i < nodelist.getLength();i++){
                Node node = nodelist.item(i);
                Element element = (Element) node;
                packageName = element.getAttribute("package");
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return packageName;
    }

    @Override
    public void update(AnActionEvent e) {
        super.update(e);
    }
}
