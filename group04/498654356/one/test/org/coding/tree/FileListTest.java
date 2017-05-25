package org.coding.tree;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileListTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		FileList fl = new FileList();
		fl.list(new File("D:/Dev/git_repository/coding2017"));
	}

}
