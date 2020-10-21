package com.IO;

public abstract class AbstractIOFactory {
    private static AbstractIOFactory abstractIOFactory;

    public static AbstractIOFactory getFactory() {
        return abstractIOFactory;
    }

    public static void setFactory(AbstractIOFactory ioFactory) {
        abstractIOFactory = ioFactory;
    }

    public abstract IAppInput getCommandLineInput();
    public abstract IAppOutput getCommandLineOutput();
}
