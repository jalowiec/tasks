package com.crud.tasks;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TasksApplicationTests {

	@Test
	public void contextLoads() {
		boolean test = true;
		Assert.assertTrue(test);
	}

}
