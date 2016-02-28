package org.jusecase.executors.guice;

import com.google.inject.Injector;
import org.jusecase.executors.AbstractUsecaseExecutor;
import org.jusecase.Usecase;

public class GuiceUsecaseExecutor extends AbstractUsecaseExecutor {
    private final Injector injector;

    public GuiceUsecaseExecutor(Injector injector) {
        this.injector = injector;
    }

    protected Injector getInjector() {
        return injector;
    }

    public void addUsecase(Class<? extends Usecase> usecaseClass) {
        addUsecase(getRequestClass(usecaseClass), usecaseClass);
    }

    @Override
    protected Object resolveUsecase(Object usecase) {
        return injector.getInstance((Class<?>)usecase);
    }
}
