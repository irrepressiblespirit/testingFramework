package com.skibnev.testingframework.configurations;

import com.skibnev.testingframework.core.ApplicationContext;

public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context);
}
