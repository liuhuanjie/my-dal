package com.ctrip.demo.util;

final public class FunctionWrapUtil {

    public static void wrapWithRuntimeException(Task runnable) {
        try {
            runnable.run();
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static <T> T wrapWithRuntimeException(TaskWithResult<T> runnable) {
        try {
            return runnable.run();
        } catch (Exception t) {
            throw new RuntimeException(t);
        }
    }

    public static interface Task {

        public void run() throws Exception;
    }

    public static interface TaskWithResult<T> {

        public T run() throws Exception;
    }
}
