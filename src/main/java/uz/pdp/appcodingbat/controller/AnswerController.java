package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Answer;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.AnswerDto;
import uz.pdp.appcodingbat.payload.Result;
import uz.pdp.appcodingbat.payload.UserDto;
import uz.pdp.appcodingbat.service.AnswerService;
import uz.pdp.appcodingbat.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;


    /**
     * GET ANSWER LIST
     *
     * @return ANSWER LIST
     */
    @GetMapping
    public ResponseEntity<List<Answer>> get() {
        List<Answer> answers = answerService.get();
        return ResponseEntity.ok(answers);
    }


    /**
     * GET ONE ANSWER BY ID
     *
     * @param id INTEGER
     * @return ONE ANSWER
     */
    @GetMapping("/{id}")
    public ResponseEntity<Answer> getById(@PathVariable Integer id) {
        Answer answer = answerService.getById(id);
        return ResponseEntity.ok(answer);
    }


    /**
     * DELETE ANSWER BY ID
     *
     * @param id INTEGER
     * @return RESULT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id) {
        Result result = answerService.delete(id);
        return ResponseEntity.status(result.isSuccess() ? 200 : 405).body(result);
    }


    /**
     * ADD ANSWER
     *
     * @param answerDto USER ID (Integer), TASK ID (Integer), CORRECT (Boolean)
     * @return RESULT
     */
    @PostMapping
    public ResponseEntity<Result> add(@RequestBody AnswerDto answerDto) {
        Result result = answerService.add(answerDto);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }


    /**
     * EDIT ANSWER BY ID
     *
     * @param id          INTEGER
     * @param answerDto USER ID (Integer), TASK ID (Integer), CORRECT (Boolean)
     * @return RESULT
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @RequestBody AnswerDto answerDto) {
        Result result = answerService.edit(id, answerDto);
        return ResponseEntity.status(result.isSuccess() ? 202 : 409).body(result);
    }
}
