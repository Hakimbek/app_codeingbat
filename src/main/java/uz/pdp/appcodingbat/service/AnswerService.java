package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Answer;
import uz.pdp.appcodingbat.entity.Task;
import uz.pdp.appcodingbat.entity.User;
import uz.pdp.appcodingbat.payload.AnswerDto;
import uz.pdp.appcodingbat.payload.Result;
import uz.pdp.appcodingbat.repository.AnswerRepository;
import uz.pdp.appcodingbat.repository.TaskRepository;
import uz.pdp.appcodingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public List<Answer> get() {
        return answerRepository.findAll();
    }

    public Answer getById(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.orElse(null);
    }

    public Result delete(Integer id) {
        try {
            answerRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        } catch (Exception e) {
            return new Result("Error", false);
        }
    }

    public Result add(AnswerDto answerDto) {
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent()) {
            return new Result("Task not found", false);
        }
        Task task = optionalTask.get();

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent()) {
            return new Result("User not found", false);
        }
        User user = optionalUser.get();

        Answer answer = new Answer(user, task, answerDto.getCorrect());
        answerRepository.save(answer);
        return new Result("Successfully added", true);
    }

    public Result edit(Integer id, AnswerDto answerDto) {
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent()) {
            return new Result("Task not found", false);
        }
        Task task = optionalTask.get();

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent()) {
            return new Result("User not found", false);
        }
        User user = optionalUser.get();

        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent()) {
            return new Result("Answer not found", false);
        }

        Answer answer = optionalAnswer.get();
        answer.setTask(task);
        answer.setUser(user);
        answer.setCorrect(answerDto.getCorrect());
        answerRepository.save(answer);
        return new Result("Successfully edited", true);
    }
}
