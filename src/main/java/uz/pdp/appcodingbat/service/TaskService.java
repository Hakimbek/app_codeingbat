package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Task;
import uz.pdp.appcodingbat.entity.TaskCategory;
import uz.pdp.appcodingbat.payload.Result;
import uz.pdp.appcodingbat.payload.TaskDto;
import uz.pdp.appcodingbat.repository.TaskCategoryRepository;
import uz.pdp.appcodingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskCategoryRepository taskCategoryRepository;

    public List<Task> get() {
        return taskRepository.findAll();
    }

    public Task getById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public Result delete(Integer id) {
        try {
            taskRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        } catch (Exception e) {
            return new Result("Error", false);
        }
    }

    public Result add(TaskDto taskDto) {
        boolean existsByName = taskRepository.existsByName(taskDto.getName());
        if (existsByName) {
            return new Result("Task already exist", false);
        }

        Optional<TaskCategory> optionalTaskCategory = taskCategoryRepository.findById(taskDto.getTaskCategoryId());
        if (!optionalTaskCategory.isPresent()) {
            return new Result("TaskCategory not found", false);
        }
        TaskCategory taskCategory = optionalTaskCategory.get();

        Task task = new Task(taskDto.getName(), taskDto.getText(), taskDto.getSolution(),
                                taskDto.getExample(), taskCategory);
        taskRepository.save(task);
        return new Result("Successfully added", true);
    }

    public Result edit(Integer id, TaskDto taskDto) {
        boolean existsByNameAndIdNot = taskRepository.existsByNameAndIdNot(taskDto.getName(), id);
        if (existsByNameAndIdNot) {
            return new Result("Task already exist", false);
        }

        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return new Result("Task not found", false);
        }

        Task task = optionalTask.get();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setExample(taskDto.getExample());
        task.setSolution(taskDto.getSolution());
        taskRepository.save(task);
        return new Result("Successfully edited", true);
    }
}
