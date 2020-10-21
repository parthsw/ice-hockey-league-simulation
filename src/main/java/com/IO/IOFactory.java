package com.IO;

public class IOFactory extends AbstractIOFactory {

    @Override
    public IAppInput getCommandLineInput() {
        return new CommandLineInput();
    }

    @Override
    public IAppOutput getCommandLineOutput() {
        return new CommandLineOutput();
    }
}
