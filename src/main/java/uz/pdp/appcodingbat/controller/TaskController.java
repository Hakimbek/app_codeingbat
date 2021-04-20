package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Task;
import uz.pdp.appcodingbat.payload.Result;
import uz.pdp.appcodingbat.payload.TaskDto;
import uz.pdp.appcodingbat.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;


    /**
     * GET TASK LIST
     *
     * @return TASK LIST
     */
    @GetMapping
    public ResponseEntity<List<Task>> get() {
        List<Task> task = taskService.get();
        return ResponseEntity.ok(task);
    }


    /**
     * GET ONE TASK BY ID
     *
     * @param id INTEGER
     * @return ONE TASK
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Integer id) {
        Task task = taskService.getById(id);
        return ResponseEntity.status(task != null ? 200 : 409).body(task);
    }


    /**
     * DELETE TASK BY ID
     *
     * @param id INTEGER
     * @return RESULT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id) {
        Result result = taskService.delete(id);
        return ResponseEntity.status(result.isSuccess() ? 200 : 405).body(result);
    }


    /**
     * ADD TASK
     *
     * @param taskDto NAME (String),
     *                TEXT (String),
     *                SOLUTION (String),
     *                EXAMPLE (String),
     *                TASK CATEGORY ID (Integer)
     * @return RESULT
     */
    @PostMapping
    public ResponseEntity<Result> add(@RequestBody TaskDto taskDto) {
        Result result = taskService.add(taskDto);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }


    /**
     * EDIT TASK BY ID
     *
     * @param id      INTEGER
     * @param taskDto NAME (String),
     *                TEXT (String),
     *                SOLUTION (String),
     *                EXAMPLE (String),
     *                TASK CATEGORY ID (Integer)
     * @return RESULT
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @RequestBody TaskDto taskDto) {
        Result result = taskService.edit(id, taskDto);
        return ResponseEntity.status(result.isSuccess() ? 202 : 409).body(result);
    }
}
