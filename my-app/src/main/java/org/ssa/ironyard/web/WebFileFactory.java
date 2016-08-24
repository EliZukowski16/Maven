package org.ssa.ironyard.web;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebFileFactory implements FileFactory
{
    final File file;

    public WebFileFactory(@Value("corncob_lowercase.txt") String fileName) throws URISyntaxException
    {
        URL resource = getClass().getClassLoader().getResource(fileName);
        this.file = new File(resource.toURI());
    }

    @Override
    public File getInstance()
    {
        return file;
    }

}
