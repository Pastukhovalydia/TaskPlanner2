package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByTitle() {
        SimpleTask task1 = new SimpleTask(1, "Task 1");
        SimpleTask task2 = new SimpleTask(2, "Task 2");
        Todos todos = new Todos();
        todos.add(task1);
        todos.add(task2);

        Task[] expected = {task2};
        Task[] actual = todos.search("Task 2");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchBySubtaskInEpic() {
        String[] subtasks = {"Subtask 1", "Subtask 2", "Subtask 3"};
        Epic epic = new Epic(1, subtasks);
        Todos todos = new Todos();
        todos.add(epic);

        Task[] expected = {epic};
        Task[] actual = todos.search("Subtask 2");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByTopicInMeeting() {
        Meeting meeting = new Meeting(1, "Topic 1", "Project 1", "Start 1");
        Todos todos = new Todos();
        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("Topic 1");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindTask() {
        SimpleTask task = new SimpleTask(1, "Task");
        Todos todos = new Todos();
        todos.add(task);

        Task[] expected = {};
        Task[] actual = todos.search("Non-existing task");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotFindTaskInEmptyList() {
        Todos todos = new Todos();

        Task[] expected = {};
        Task[] actual = todos.search("Task");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchMultipleTasks() {
        Todos todos = new Todos();
        Task task1 = new SimpleTask(1, "Complete homework");
        Task task2 = new SimpleTask(2, "Finish project");
        Task task3 = new Meeting(3, "Team meeting", "Project A", "2022-01-01");
        todos.add(task1);
        todos.add(task2);
        todos.add(task3);

        Task[] expected = {task2};
        Task[] result = todos.search("project");

        assertArrayEquals(expected, result);
    }

    @Test
    public void testSearchOneTask() {
        Todos todos = new Todos();
        Task task1 = new SimpleTask(1, "Complete homework");
        Task task2 = new SimpleTask(2, "Finish project");
        todos.add(task1);
        todos.add(task2);

        Task[] expected = {task1};
        Task[] result = todos.search("homework");

        assertArrayEquals(expected, result);
    }

    @Test
    public void testSearchNoTasks() {
        Todos todos = new Todos();
        Task task1 = new SimpleTask(1, "Complete homework");
        Task task2 = new SimpleTask(2, "Finish project");
        todos.add(task1);
        todos.add(task2);

        Task[] expected = {};
        Task[] result = todos.search("meeting");

        assertArrayEquals(expected, result);
    }

}