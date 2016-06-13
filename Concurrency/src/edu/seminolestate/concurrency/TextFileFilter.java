package edu.seminolestate.concurrency;

import java.io.File;
import java.io.FileFilter;

public class TextFileFilter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		String name = pathname.getName();
        return name.endsWith(".txt");
	}
}
