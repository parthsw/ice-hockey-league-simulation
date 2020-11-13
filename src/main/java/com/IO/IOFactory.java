package com.IO;

public class IOFactory implements IIOFactory {

    @Override
    public IAppInput createCommandLineInput() {
        return new CommandLineInput();
    }

    @Override
    public IAppOutput createCommandLineOutput() {
        return new CommandLineOutput();
    }

}
