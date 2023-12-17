package ru.netology;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TasksTest {
    @Test
    public void testTaskConstructor() {
        Task task = new Task(1);
        assertEquals(1, task.getId());
    }

    @Test
    public void testSimpleTaskMatches() {
        SimpleTask task = new SimpleTask(1, "Complete homework");
        assertTrue(task.matches("homework"));
        assertFalse(task.matches("meeting"));
    }

    @Test
    public void testEpicMatches() {
        String[] subtasks = {"Fix bug", "Write tests"};
        Epic epic = new Epic(1, subtasks);
        assertTrue(epic.matches("bug"));
        assertTrue(epic.matches("tests"));
        assertFalse(epic.matches("meeting"));
    }

    @Test
    public void testMeetingMatches() {
        Meeting meeting = new Meeting(1, "Team meeting", "Project A", "2022-01-01");
        assertTrue(meeting.matches("meeting"));
        assertTrue(meeting.matches("Project"));
        assertFalse(meeting.matches("homework"));
    }

    @Test
    public void testAddTask() {
        Todos todos = new Todos();
        Task task = new SimpleTask(1, "Complete homework");
        todos.add(task);
        Task[] result = todos.findAll();
        assertEquals(1, result.length);
        assertEquals(task, result[0]);
    }

    @Test
    public void testSearchTasks() {
        Todos todos = new Todos();
        Task task1 = new SimpleTask(1, "Complete homework");
        Task task2 = new Meeting(2, "Team meeting", "Project A", "2022-01-01");
        Task task3 = new Epic(3, new String[]{"Fix bug", "Write tests"});
        todos.add(task1);
        todos.add(task2);
        todos.add(task3);
        Task[] result = todos.search("bug");
        assertEquals(1, result.length);
        assertEquals(task3, result[0]);
    }

    @Test
    public void testMatches() {
        String[] subtasks = {"Fix bug", "Write tests"};
        Epic epic = new Epic(1, subtasks);
        assertTrue(epic.matches("bug"));
        assertTrue(epic.matches("tests"));
        assertFalse(epic.matches("meeting"));
    }

    @Test
    public void testHashCode() {
        String[] subtasks = {"Fix bug", "Write tests"};
        Epic epic1 = new Epic(1, subtasks);
        Epic epic2 = new Epic(1, subtasks);
        assertEquals(epic1.hashCode(), epic2.hashCode());
    }

}