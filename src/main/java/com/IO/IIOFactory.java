package com.IO;

public interface IIOFactory {
    IAppInput createCommandLineInput();

    IAppOutput createCommandLineOutput();
}
