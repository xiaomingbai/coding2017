package org.coding.tree;

import java.io.File;

public class FileList {

	public void list(File f) {
		doList(f, 0);
	}

	private void doList(File f, int count) {
		for(int i = 0; i < count; i++) {
			System.out.print("\t");
		}
		System.out.println(f.getName());
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			count++;
			for (File file : files) {
				doList(file, count);
			}
		}
	}
}
