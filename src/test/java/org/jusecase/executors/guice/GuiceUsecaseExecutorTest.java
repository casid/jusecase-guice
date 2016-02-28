package org.jusecase.executors.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import org.jusecase.UsecaseExecutorException;
import org.jusecase.example.trivial.AppendCharacters;
import org.jusecase.example.trivial.CalculateSum;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class GuiceUsecaseExecutorTest {
    private GuiceUsecaseExecutor executor;
    private UsecaseExecutorException exception;

    @Before
    public void setUp() throws Exception {
        executor = new GuiceUsecaseExecutor(Guice.createInjector());
    }

    @Test
    public void nullRequest() {
        try {
            executor.execute(null);
        } catch (UsecaseExecutorException e) {
            exception = e;
        }

        thenExceptionMessageIs("Request must not be null.");
    }

    @Test
    public void addOneUsecase() {
        executor.addUsecase(AppendCharacters.class);
        thenAppendCharactersCanBeExecuted();
    }

    @Test
    public void addMultipleUsecases() {
        executor.addUsecase(CalculateSum.class);
        executor.addUsecase(AppendCharacters.class);

        thenAppendCharactersCanBeExecuted();
        thenCalculateSumCanBeExecuted();
    }

    @Test
    public void addUsecaseWithAlreadyExistingRequest() {
        executor.addUsecase(CalculateSum.class);

        try {
            executor.addUsecase(CalculateSum.class);
        } catch (UsecaseExecutorException e) {
            exception = e;
        }

        thenExceptionMessageIs("Request 'org.jusecase.example.trivial.CalculateSum$Request' is already handled by a usecase.");
    }

    @Test
    public void injectorIsExposed() {
        Injector injector = Guice.createInjector();
        executor = new GuiceUsecaseExecutor(injector);

        assertSame(injector, executor.getInjector());
    }

    private void thenAppendCharactersCanBeExecuted() {
        AppendCharacters.Request request = new AppendCharacters.Request('A', 5);
        assertEquals("AAAAA", executor.execute(request));
    }

    private void thenCalculateSumCanBeExecuted() {
        CalculateSum.Request request = new CalculateSum.Request(2, 3);
        assertEquals(5, executor.execute(request));
    }

    private void thenExceptionMessageIs(String expected) {
        assertNotNull("Expected exception with message '" + expected + "', but nothing was thrown.", exception);
        assertEquals(expected, exception.getMessage());
    }
}