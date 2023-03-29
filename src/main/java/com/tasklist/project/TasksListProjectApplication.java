package com.tasklist.project;

import com.tasklist.project.util.UtilityClass;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class TasksListProjectApplication implements CommandLineRunner {

    /*	FOR TESTING PURPOSES ONLY
	    Uncomment the code below if the tables don't exist or if test data is needed.
    */
//    @Resource
//    UtilityClass util;

    public static void main(String[] args) {
        SpringApplication.run(TasksListProjectApplication.class, args);
    }

    @Override
    public void run(String... args) {
        /* FOR TESTING PURPOSES ONLY
			Uncomment the code below if the tables don't exist
		*/
//        util.createTaskStatusesTable();
//        util.createUsersTable();
//        util.createTasksTable();

        /* FOR TESTING PURPOSES ONLY
			Uncomment the code below if test data is needed
		*/
//        util.insertIntoStatusTable();
//        util.insertIntoUsersTable();
//        util.insertIntoTasksTable();
    }
}
