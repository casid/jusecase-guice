package org.jusecase.executors.guice;

import com.google.inject.Injector;
import org.jusecase.Usecase;
import org.jusecase.executors.AbstractUsecaseExecutor;

public class GuiceUsecaseExecutor extends AbstractUsecaseExecutor {
    private Injector injector;

    public GuiceUsecaseExecutor() {
    }

    public GuiceUsecaseExecutor(Injector injector) {
        setInjector(injector);
    }

    protected Injector getInjector() {
        return injector;
    }

    protected void setInjector(Injector injector) {
        this.injector = injector;
    }

    public void addUsecase(Class<? extends Usecase> usecaseClass) {
        addUsecase(getRequestClass(usecaseClass), usecaseClass);
    }

    @Override
    protected Object resolveUsecase(Object usecase) {
        return injector.getInstance((Class<?>)usecase);
    }
}
