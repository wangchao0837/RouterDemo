package com.example.router_compile;

import com.example.router_annotion.Path;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

//APT 发生在编译时
@AutoService(Processor.class)
@SupportedAnnotationTypes("com.example.router_annotion.Path")
public class RouterProcessor extends AbstractProcessor {


    private Filer filer;
    private String className;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        className = processingEnv.getOptions().get("moduleName");


    }

    //javac会把@SupportedAnnotationTypes指明的Path 执行process
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {


        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Path.class);


        generatedClass(elements);

        return false;
    }

    private void generatedClass(Set<? extends Element> elements) {

        StringBuffer sb = new StringBuffer();

        sb.append("package com.example.router;\n");
        sb.append("import android.app.Activity;\n");
        sb.append("import com.example.baselibrary.IRouter;\n");
        sb.append("import java.util.HashMap;\n");
        sb.append("import com.example.router_annotion.Path;\n");


        for (Element element : elements) {
            TypeElement typeElement = (TypeElement) element;
            sb.append("import ");
            sb.append(typeElement.getQualifiedName());
            sb.append(";\n");
        }

        sb.append("public class " + className + "_Router ");
        sb.append("implements IRouter {\n");
        sb.append("@Override\n");
        sb.append("public void load(HashMap<String, Class<? extends Activity>> activityMap) {\n");
        for (Element element : elements) {
            Path annotation = element.getAnnotation(Path.class);
            String tag = annotation.value();
            sb.append("activityMap.put(\"" + tag + "\"," + element.getSimpleName() + ".class);\n");
        }
        sb.append("}");
        sb.append("}");

        try {
            JavaFileObject sourceFile = filer.createSourceFile("com.example.router." + className + "_Router");
            OutputStream outputStream = sourceFile.openOutputStream();
            outputStream.write(sb.toString().getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
