package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.TaskCategory;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.Result;
import uz.pdp.appcodingbat.payload.TaskCategoryDto;
import uz.pdp.appcodingbat.payload.UserDto;
import uz.pdp.appcodingbat.service.TaskCategoryService;
import uz.pdp.appcodingbat.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/taskCategory")
public class TaskCategoryController {
    @Autowired
    TaskCategoryService taskCategoryService;


    /**
     * GET TASK CATEGORY LIST
     *
     * @return TASK CATEGORY LIST
     */
    @GetMapping
    public ResponseEntity<List<TaskCategory>> get() {
        List<TaskCategory> taskCategories = taskCategoryService.get();
        return ResponseEntity.ok(taskCategories);
    }


    /**
     * GET ONE TASK CATEGORY BY ID
     *
     * @param id INTEGER
     * @return ONE TASK CATEGORY
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskCategory> getById(@PathVariable Integer id) {
        TaskCategory taskCategory = taskCategoryService.getById(id);
        return ResponseEntity.ok(taskCategory);
    }


    /**
     * DELETE TASK CATEGORY BY ID
     *
     * @param id INTEGER
     * @return RESULT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id) {
        Result result = taskCategoryService.delete(id);
        return ResponseEntity.status(result.isSuccess() ? 200 : 405).body(result);
    }


    /**
     * ADD TASK CATEGORY
     *
     * @param taskCategoryDto NAME (String), INFO (String), LIST<Integer>
     * @return RESULT
     */
    @PostMapping
    public ResponseEntity<Result> add(@RequestBody TaskCategoryDto taskCategoryDto) {
        Result result = taskCategoryService.add(taskCategoryDto);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }


    /**
     * EDIT TASK CATEGORY BY ID
     *
     * @param id          INTEGER
     * @param taskCategoryDto NAME (String), INFO (String), LIST<Integer>
     * @return RESULT
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @RequestBody TaskCategoryDto taskCategoryDto) {
        Result result = taskCategoryService.edit(id, taskCategoryDto);
        return ResponseEntity.status(result.isSuccess() ? 202 : 409).body(result);
    }
}
