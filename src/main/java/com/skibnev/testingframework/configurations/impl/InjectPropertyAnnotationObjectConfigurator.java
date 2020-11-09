package com.skibnev.testingframework.configurations.impl;

import com.skibnev.testingframework.core.ApplicationContext;
import com.skibnev.testingframework.annotations.InjectProperty;
import com.skibnev.testingframework.configurations.ObjectConfigurator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    private Map<String, String> propertiesMap;

    public InjectPropertyAnnotationObjectConfigurator() {
        propertiesMap = getPropertiesFromFile();
    }

    private Map<String, String> getPropertiesFromFile() {
        URL resource = ClassLoader.getSystemClassLoader().getResource("application.properties");
        Map<String, String> map = null;
            try {
                if (resource == null) {
                    throw new Exception();
                }
                String path = resource.getPath();
                Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
                map = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return map;
    }

    @Override
    public void configure(Object t, ApplicationContext context) {
        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);
            if (annotation != null) {
                String value = annotation.value().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.value());
                field.setAccessible(true);
                try {
                    field.set(t,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
